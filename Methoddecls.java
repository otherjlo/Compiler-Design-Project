class Methoddecls implements Token {
  
  Methoddecl methodDecl;
  Methoddecls methodDecls;
  
  public Methoddecls() {
    methodDecl = null;
    methodDecls = null;
  }
  
  public Methoddecls(Methoddecl methodDecl, Methoddecls methodDecls) {
    this.methodDecl = methodDecl;
    this.methodDecls = methodDecls;
  }
  
  public String toString(int t) {
    String ret = "";
    if(methodDecl == null) {
    }
    else {
      ret = methodDecl.toString(t) + methodDecls.toString(t);
    }
    return ret;
  }
  
  public void typeCheck() throws ExampleException {
    if(!(methodDecl == null)) {
      methodDecl.typeCheck();
      methodDecls.typeCheck();
    }
  }
}