class ArgdeclList implements Token 
{
  
  ArgdeclList argDeclList;
  Argdecl argDecl;
  
  public ArgdeclList(Argdecl argDecl, ArgdeclList argDeclList) {
    this.argDeclList = argDeclList;
    this.argDecl = argDecl;
  }
  
  public ArgdeclList(Argdecl argDecl) {
    this.argDecl = argDecl;
    this.argDeclList = null;
  }
  
  public String toString(int t) {
    if(argDeclList == null) {
      return argDecl.toString(t);
    }
    else {
      return argDecl.toString(t) + " , " + argDeclList.toString(t);
    }
  }
  
  public void typeCheck() throws ExampleException {
    argDecl.typeCheck();
    if(argDeclList != null)
      argDeclList.typeCheck();
  }
}