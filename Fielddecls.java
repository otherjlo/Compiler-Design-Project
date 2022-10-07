class Fielddecls extends ExampleToken implements Token {
  Fielddecl fieldDecl;
  Fielddecls fieldDecls;
  
  public Fielddecls() {
    fieldDecl = null;
    fieldDecls = null;
  }
  
  public Fielddecls(Fielddecl fieldDecl, Fielddecls fieldDecls) {
    this.fieldDecl = fieldDecl;
    this.fieldDecls = fieldDecls;
  }
  
  public String toString(int t) {
    String ret = "";
    if(fieldDecl == null) {
    }
    
    else {
      ret = fieldDecls.toString(t) + fieldDecl.toString(t);
    }
    return ret;
  }
  
  public void typeCheck() throws ExampleException {
    if(fieldDecl != null) {
      fieldDecls.typeCheck();
      fieldDecl.typeCheck();
    }
  }
}