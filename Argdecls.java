class Argdecls implements Token {
  ArgdeclList a;
  
  public Argdecls(ArgdeclList a) {
    this.a = a;
  }
  
  public Argdecls() {
    this.a = null;
  }
  
  public String toString(int t) {
    String ret = "";
    if(a == null) {
    }
    else {
      ret = a.toString(t);
    }
    return ret;
  }
  
  public void typeCheck() throws ExampleException {
  if(a != null)
    a.typeCheck();
  }
}