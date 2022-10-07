class Stmts implements Token {
  Stmt stmt;
  Stmts stmts;
  
  public Stmts(Stmt stmt, Stmts stmts) {
    this.stmt = stmt;
    this.stmts = stmts;
  }
  
  public Stmts() {
    this.stmt = null;
    this.stmts = null;
  }
  
  public String toString(int t) {
    String ret = "";
    if(stmt == null) {
    }
    
    else {
      ret = stmt.toString(t) + " " + stmts.toString(t);
    }
    return ret;
  }
  public void typeCheck() throws ExampleException {
    if(stmt != null) {
      stmt.typeCheck();
      stmts.typeCheck();
    }
  }
}