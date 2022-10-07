class Expr extends ExampleToken implements Token {
  Name n = null;
  String id; 
  Expr e, f, g;
  Args a;
  int intlit;
  char charlit;
  String strlit;
  float floatlit;
  boolean bool;
  Type t;
  int opType;
  int thisId;
  Binaryop b;
  
  public Expr(Name n) {
    this.n = n;
    thisId = 1;
  }
  
  public Expr(String id) {
    this.id = id;
    thisId = 2;
  }  
  
  public Expr(String id, Args a) {
    this.id = id;
    this.a = a;
    thisId = 3;
  }
  
  public Expr(int intlit) {
    this.intlit = intlit;
    thisId = 4;
  }
  
  public Expr(char charlit) {
    this.charlit = charlit;
    thisId = 5;
  }
  
  public Expr(String strlit, boolean bool) {
    this.strlit = strlit;
    thisId = 6;
  }
  
  public Expr(float floatlit) {
    this.floatlit = floatlit;
    thisId = 7;
  }
  
  public Expr(boolean bool) {
    this.bool = bool;
    thisId = 8;
  }
  
  public Expr(Expr e) {
    this.e = e;
    thisId = 9;
  }
  
  public Expr(Expr e, int opType) {
    this.e = e;
    this.opType = opType;
    thisId = 10;
  }  
  
  public Expr(Type t, Expr e) {
    this.t = t;
    this.e = e;
    thisId = 11;
  }
  
  public Expr(Expr e, Expr f, Expr g) {
    this.e = e; 
    this.f = f;
    this.g = g;
    thisId = 12;
  }  
  
  public Expr(Binaryop b) {
    this.b = b;
    thisId = 13;
  }
  
  public String toString(int t) {
    String ret = "";
    if(thisId == 1) {
      ret += n.toString(t);
    }
    else if(thisId == 2) {
      ret += id + "()";
    }
    else if(thisId == 3) {
      ret += id + "(" + a.toString(t) + ")";
    }
    else if(thisId == 4) {
      ret = Integer.toString(intlit);
    }
    else if(thisId == 5) {
      ret = Character.toString(charlit);
    }
    else if(thisId == 6) {
      ret = strlit;
    }
    else if(thisId == 7) {
      ret = Float.toString(floatlit);
    }
    else if(thisId == 8) {
      if(bool) {
        ret = "true";
      }
      else {
        ret = "false";
      }
    }
    else if(thisId == 9) {
      ret += "(" + e.toString(t) + ")";
    }
    else if(thisId == 10) {
      if(opType == 1) {
        ret += "~" + e.toString(t);
      }
      else if(opType == 2) {
        ret += "-" + e.toString(t);
      }
      else {
        ret += "+" + e.toString(t);
      }
    }
    else if(thisId == 11) {
      ret += "(" + this.t.toString(t) + ")" + e.toString(t);
    }
    else if(thisId == 12) {
      ret += "(" + e.toString(t) + " ? " + f.toString(t) + " : " + g.toString(t) + ")";
    }
    else if(thisId == 13) {
      ret = "(" + b.toString(t) + ")";
    }
    return ret;
  }
  
  public FullType typeCheck() throws ExampleException {
    //BinaryOp
    if(thisId == 13) {
      return b.typeCheck();
    }
    else if(thisId == 1) {
      return n.typeCheck();
    }
    //Tertiary op
    else if(thisId == 12) {
      if(isCoercible(e.typeCheck().getType(), TypeEnum.BOOL)) {
        if(f.typeCheck().getType() == g.typeCheck().getType()) {
          return f.typeCheck();
        }
        else 
          throw new ExampleException("Y and Z must be equal types!");
      }
      else
        throw new ExampleException("X must be bool or coercible to bool!");
    }
    else if(thisId == 10) {
      if(opType == 1) {
        if(isCoercible(e.typeCheck().getType(), TypeEnum.BOOL)) {
          return new FullType(TypeEnum.BOOL, false, false);
        }
        throw new ExampleException("Cannot use ~ on non-bool.");
      }
      else if(opType == 2) {
        if(isCoercible(e.typeCheck().getType(), TypeEnum.FLOAT)) {
          return e.typeCheck();
        }
        throw new ExampleException("Cannot use - on nonnumeric type.");
      }
      else {
        if(isCoercible(e.typeCheck().getType(), TypeEnum.FLOAT)) {
          return e.typeCheck();
        }
        throw new ExampleException("Cannot use + on nonnumeric type.");
      }
    }
    else if(thisId == 4)  {
      return new FullType(TypeEnum.INT, false, false);
    }
    else if(thisId == 5) {
      return new FullType(TypeEnum.CHAR, false, false);
    }
    else if(thisId == 6) {
      return new FullType(TypeEnum.STRING, false, false);
    }
    else if(thisId == 7) {
      return new FullType(TypeEnum.FLOAT, false, false);
    }
    else if(thisId == 8) {
      return new FullType(TypeEnum.BOOL, false, false);
    }
    else if(thisId == 9) {
      return e.typeCheck();
    }
    else if(thisId == 11) {
      return new FullType(t.toEnum(), false, false);
    }
    else if(thisId == 1) {
      FullType thisType = varTable.getType(n.id);
      return thisType;
    }
    else if(thisId == 2) {
      if(methodTable.getMethodType(id).noParameters())
        return methodTable.getMethodType(id).getReturnType();
      else
        throw new ExampleException("Method needs parameters!");
    }
    else if(thisId == 3) {
      return a.typeCheck(methodTable.getMethodType(id), 0);
    }
    else {
      return new FullType(TypeEnum.VOID, false, false);
    }
  }
}