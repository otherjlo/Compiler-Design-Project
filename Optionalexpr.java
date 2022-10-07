class Optionalexpr implements Token {
  
  boolean exists;
  Expr e;
  public Optionalexpr(Expr e) {
    this.exists = true;
    this.e = e;
  }
  
  public Optionalexpr() {
    exists = false;
  }
  
  public String toString(int t) {
    String ret = "";
    if(exists){
      ret+= " = " + e.toString(t); 
    }
    return ret;
  }
}
