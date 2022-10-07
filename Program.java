class Program implements Token {
  
  private Memberdecls m;
  private String id;
  
  public Program(Memberdecls m, String id) {
    this.m = m;
    this.id = id;
  }
  
  public String toString(int t) {
    String ret = "class " + id + " {\n" + m.toString(t) + " }";
    return ret;
  }
  
  public void typeCheck() throws ExampleException {
    m.typeCheck();
  }
}