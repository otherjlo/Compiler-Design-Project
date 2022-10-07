class Argdecl extends ExampleToken implements Token {
  
  Type t;
  String id;
  boolean isArray;
  
  public Argdecl(Type t, String id, boolean isArray) {
    this.t = t;
    this.id = id;
    this.isArray = isArray;
  }
  
  public String toString(int t) {
    if(isArray) {
      return this.t.toString(t) + " " + id + "[]";
    }
    
    else {
      return this.t.toString(t) + " " + id;
    }
  }
  
  public void typeCheck() throws ExampleException {
    FullType thisType = new FullType(t.toEnum(), false, isArray);
    
    if(!varTable.add(id, thisType)) {
      throw new ExampleException("Cannot redeclare argument!");
    }
    methodTable.currMethod.addParameter(thisType);
  }
}
