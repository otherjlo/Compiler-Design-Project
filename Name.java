class Name extends ExampleToken implements Token {

  String id; 
  Expr e = null;
  
  public Name(String id) {
    this.id = id;
  }
  
  public Name(String id, Expr e) {
    this.id = id;
    this.e = e;
  }
  
  public String toString(int t) {
    String ret = "";
    
    if(e == null) {
      ret = id;
    }
    else {
      ret = id + "[" + e.toString(t) + "]";
    }
    return ret;
  }
  
  public FullType typeCheck() throws ExampleException {
    if(e == null) {
      return varTable.getType(id);
    }
    else {
      if(e.typeCheck().getType() == TypeEnum.INT && !e.typeCheck().isArray())
        return varTable.getType(id);
      else
        throw new ExampleException("Array access requires int!");
    }
    
  }
}