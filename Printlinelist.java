class Printlinelist implements Token {
  
  Printlist printList;
  
  public Printlinelist() {
    printList = null;
  }
  
  public Printlinelist(Printlist printList) {
    this.printList = printList;
  }
  
  public String toString(int t) {
    String ret = "";
    if(printList == null) {
    }
    else {
      ret = printList.toString(t);
    }
    return ret;
  }
  
  public FullType typeCheck() throws ExampleException {
    return printList.typeCheck();
  }
}