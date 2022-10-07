class Printlist extends ExampleToken implements Token {
  Expr e;
  Printlist p;
  
  public Printlist(Expr e, Printlist p) {
    this.e = e;
    this.p = p;
  }
  
  public Printlist(Expr e) {
    this.p = null;
    this.e = e;
  }
  
  public String toString(int t) {
    if(p == null) {
      return e.toString(t) + "";
    }
    else {
      return p.toString(t) + ", " + e.toString(t) + "";
    }
  }
  
  public FullType typeCheck() throws ExampleException {
    FullType ret = null;
    //Literals
    if(e.thisId == 4 || e.thisId == 5 || e.thisId == 6 || e.thisId == 7 || e.thisId == 8) {
      ret = e.typeCheck();
    }
    //Function call
    else if(e.thisId == 2 || e.thisId == 3) {
      if(methodTable.getMethodType(e.id).getReturnType().getType() == TypeEnum.VOID) {
        throw new ExampleException("Cannot print method with void return!");
      }
      ret = e.typeCheck();
    }
    //Variable
    else if(e.thisId == 1) {
      if(varTable.getType(e.n.id).isArray())  {
        throw new ExampleException("Cannot print array!");
      }
      ret = e.typeCheck();
    }
    return ret;
  }
}