class Methoddecl extends ExampleToken implements Token {
  Type r;
  String id;
  Argdecls a;
  Fielddecls f;
  Stmts s;
  boolean hasSemi;
  boolean isEmpty;
  
  public Methoddecl(Type r, String id, Argdecls a, Fielddecls f, Stmts s, boolean hasSemi) {
    this.r = r;
    this.id = id;
    this.a = a;
    this.f = f;
    this.s = s;
    //this.o = o;
    this.isEmpty = false;
  }
  
  public Methoddecl() {
    this.isEmpty = true;
  }
  
  public String toString(int t) {
    String ret = "";
    if(isEmpty) {
    }
    else {
      if(hasSemi) {
        ret = r.toString(t) + " " + id + "(" + a.toString(0) + ")\n" + "{\n" + f.toString(t+1) + s.toString(t+1) + "};\n";
      }
      else {
        ret = r.toString(t) + " " + id + "(" + a.toString(0) + ")\n" + "{\n" + f.toString(t+1) + s.toString(t+1) + "}\n";
      }
    }
    return ret;
  }
  
  public void typeCheck() throws ExampleException {
    FullType returnType = new FullType(r.toEnum(), false, false);
    MethodType thisType = new MethodType(returnType);
    methodTable.add(id, thisType);
    methodTable.currMethod = thisType;
    varTable.raiseScope();
    a.typeCheck();
    f.typeCheck();
    s.typeCheck();
    if(!thisType.getReturn())
      if(!(returnType.getType() == TypeEnum.VOID))
        throw new ExampleException("Method must have return!");
    varTable.exitScope();
  }
}