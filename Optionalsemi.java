class Optionalsemi implements Token {
  
  boolean exists;
  
  public Optionalsemi(boolean exists) {
    this.exists = exists;
  }
  
  public String toString(int t) {
    String ret = "";
    if(exists) {
      ret = ";";
    }
    return ret;
  }
}