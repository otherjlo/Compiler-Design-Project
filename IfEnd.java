class IfEnd extends ExampleToken implements Token {
  Stmt s;
  
  public IfEnd(Stmt s) {
    this.s = s;
  }
  
  public IfEnd() {
    s = null;
  }
  
  public String toString(int t) {
    String ret = "";
    if(s == null) {
    }
    else {
      for(int i = 0; i < t; i++) {
        ret += "\t";
      }
      ret += "else\n" + s.toString(t+1);
    }
    return ret;
  }  
  
  public void typeCheck() throws ExampleException {
    if(s != null)
      s.typeCheck();
  }
}