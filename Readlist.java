class Readlist extends ExampleToken implements Token {
  Readlist readList = null;
  Name name;
  public Readlist(Name name, Readlist readList) {
    this.name = name;
    this.readList = readList;
  }
  
  public Readlist(Name name) {
    this.name = name;
  }

  public String toString(int t) {
    String ret = "";
    if(readList == null) {
      ret += name.toString(0); 
    }
    else {
      ret += name.toString(0) + ", " + readList.toString(t);
    }
    return ret;
  }
  
  public FullType typeCheck() throws ExampleException {
    name.typeCheck();
    FullType thisType = varTable.getType(name.id);
    if(thisType.isArray()) {
      throw new ExampleException("Cannot read array!");
    }
    if(thisType.isFinal()) {
      throw new ExampleException("Cannot read final type!");
    }
    if(methodTable.getMethodType(name.id) != null) {
      throw new ExampleException("Cannot read a function");
    }
    if(readList != null) {
      readList.typeCheck();
    }
    return thisType;
  }
}