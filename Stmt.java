class Stmt extends ExampleToken implements Token {
  
  Args a;
  Expr e;
  Stmt s;
  Stmts stmts;
  Fielddecls f;
  Optionalsemi o;
  IfEnd ifEnd;
  Name n;
  Readlist r;
  Printlist printList;
  Printlinelist printLineList;
  boolean isIncrement;
  String id;
  int thisId;
  public Stmt(Expr e, Stmt s, IfEnd ifEnd) {
    this.e = e;
    this.s = s;
    this.ifEnd = ifEnd;
    thisId = 1;
  }
  
  public Stmt(Expr e, Stmt s) {
    this.e = e;
    this.s = s;
    thisId = 2;
  }
  
  public Stmt(Name n, Expr e) {
    this.n = n;
    this.e = e;
    thisId = 3;
  }
  
  public Stmt(Readlist r) {
    this.r = r;
    thisId = 4;
  }
  
  public Stmt(Printlist printList) {
    this.printList = printList;
    thisId = 5;
  }
  
  public Stmt(Printlinelist printLineList) {
    this.printLineList = printLineList;
    thisId = 6;
  }
  
  public Stmt(String id) {
    this.id = id;
    thisId = 7;
  }
  
  public Stmt(String id, Args a) {
    this.id = id;
    this.a = a;
    thisId = 8;
  }
  
  public Stmt() {
    thisId = 9;
  }
  
  public Stmt(Expr e) {
    this.e = e;
    thisId = 10;
  }
  
  public Stmt(boolean isIncrement, Name n) {
    this.isIncrement = isIncrement;
    this.n = n;
    thisId =11;
  }
  
  public Stmt(Fielddecls f, Stmts stmts, Optionalsemi o) {
    this.f = f;
    this.stmts = stmts;
    this.o = o;
    thisId = 12;
  }
  public String toString(int t) {
    String ret = "";
    String tabs = "";
    for(int i = 0; i < t; i++){
      tabs += "\t";
    }
    ret += tabs;
    if(thisId == 1) {
      ret += "if (" + e.toString(t) + ")\n" + s.toString(t+1) + " " + ifEnd.toString(t);
      return ret;
    }
    else if(thisId == 2) {
      ret += "while (" + e.toString(t) + ")\n" + s.toString(t+1);
    }
    else if(thisId == 3) {
      ret += n.toString(t) + " = " + e.toString(t) + ";";
    }
    else if(thisId == 4) {
      ret += "read(" + r.toString(t) + ");";
    }
    else if(thisId == 5) {
      ret += "print(" + printList.toString(t) + ");";
    }
    else if(thisId == 6) {
      ret += "printline(" + printLineList.toString(t) + ");";
    }  
    else if(thisId == 7) {
      ret += id + "( );";
    }
    else if(thisId == 8) {
      ret += id + "(" + a.toString(t) + ");";
    }
    else if(thisId == 9) {
      ret += "return;";
    }
    else if(thisId == 10) {
      ret += "return " + e.toString(t) + ";";
    }
    else if(thisId == 12) {
      ret += "{\n" + f.toString(t+1) + stmts.toString(t+1) + tabs + "}" + o.toString(t);
    }
    else {
      if(isIncrement) {
        ret += n.toString(t) + "++;";
      }
      else {
        ret += n.toString(t) + "--;";
      }
    }
    ret += "\n";
    return ret;
  }
  
  public FullType typeCheck() throws ExampleException {
    //Assignment
    if(thisId == 3) {
      FullType thisType = n.typeCheck();
      if(thisType.isFinal())
        throw new ExampleException("Cannot reassign final!");
      if(e.typeCheck().isCoercible(thisType)) {
        return thisType;
      }
      else
        throw new ExampleException("Type mismatch!");
    }
    //If statement
    else if(thisId == 1) {
      FullType thisType = e.typeCheck();
      if(isCoercible(thisType.getType(), TypeEnum.BOOL)) {
        varTable.raiseScope();
        s.typeCheck();
        varTable.exitScope();
        varTable.raiseScope();
        ifEnd.typeCheck();
        varTable.exitScope();
        return thisType;
      }
      else
        throw new ExampleException("Variable in if must be coercible to bool!");
    }
    else if(thisId == 2) {
      FullType thisType = e.typeCheck();
      if(isCoercible(thisType.getType(), TypeEnum.BOOL)) {
        varTable.raiseScope();
        s.typeCheck();
        varTable.exitScope();
        return thisType;
      }
      else
        throw new ExampleException("Expression in while must be coerible to bool!");
    }
    //Increment or decrement
    else if(thisId == 11) {
      FullType thisType = varTable.getType(n.id);
      if(thisType.isArray()) {
        throw new ExampleException("Cannot increment an array!");
      }
      if(thisType.isCoercible(TypeEnum.FLOAT)) {
        if(!thisType.isFinal()) {
          throw new ExampleException("Cannot increment a final variable!");
        }
      }
      return thisType;
    }
    else if(thisId == 9) {
      if(!(methodTable.currMethod.getReturnType().getType() == TypeEnum.VOID))
        throw new ExampleException("Method must return type void!");
      methodTable.currMethod.setReturn();
      return new FullType(TypeEnum.VOID, false, false);
    }
    else if(thisId == 10) {
      if(!e.typeCheck().isCoercible(methodTable.currMethod.getReturnType()))
        throw new ExampleException("Must return type coercible to return type!");
        methodTable.currMethod.setReturn();
      return e.typeCheck();
    }
    else if(thisId == 4) {
      return r.typeCheck();
    }
    else if(thisId == 5) {
      return printList.typeCheck();
    }
    else if(thisId == 6) {
      return printLineList.typeCheck();
    }
    
    else if(thisId == 7) {
      if(!methodTable.getMethodType(id).noParameters()) {
        throw new ExampleException("This method requires parameters!");
      }
      return methodTable.getMethodType(id).getReturnType();
    }
    else if(thisId == 8) {
      return a.typeCheck(methodTable.getMethodType(id), 0);
    }
    else if(thisId == 12) {
      varTable.raiseScope();
      f.typeCheck();
      stmts.typeCheck();
      varTable.exitScope();
      return new FullType(TypeEnum.VOID, false, false);
    }
    return new FullType(TypeEnum.VOID, false, false);
  }
}