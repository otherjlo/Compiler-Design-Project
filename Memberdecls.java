class Memberdecls implements Token {
  
  private Fielddecls f;
  private Methoddecls m;
  
  public Memberdecls(Fielddecls f, Methoddecls m) {
    this.f = f;
    this.m = m;
  }
  /*
  public Memberdecls(Methoddecls method) {
    this.method = method;
  }
  */
  public String toString(int t) {
    return f.toString(t) + m.toString(t);
  }
  
  public void typeCheck() throws ExampleException {
     f.typeCheck();
     m.typeCheck(); 
  }
}