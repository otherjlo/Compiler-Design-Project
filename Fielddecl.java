class Fielddecl extends ExampleToken implements Token {
  
  boolean hasFinal, hasExpr;
  //Optionalfinal f;
  Expr e;
  Type t;
  String id;
  int arraySize;
  boolean isArray;
  
  public Fielddecl(boolean hasFinal, Type t, String id, Expr e) {
    this.hasFinal = hasFinal;
    this.t = t;
    this.id = id;
    this.e = e;
    this.isArray = false;
    this.hasExpr = true;
  }
  
  public Fielddecl(boolean hasFinal, Type t, String id) {
    this.hasFinal = hasFinal;
    this.t = t;
    this.id = id;
    this.isArray = false;
    this.hasExpr = false;
  }
  
  public Fielddecl(Type t, String id, int arraySize) {
    this.t = t;
    this.id = id;
    this.arraySize = arraySize;
    this.isArray = true;
  }
  
  public String toString(int t) {
    String ret = "";
    for(int i = 0; i < t; i++) {
      ret += "\t";
    }
    if(!isArray) {
      if(hasFinal) {
        if(hasExpr) {
          ret += "final" + " " + this.t.toString(t) + " " + id + " = " + e.toString(t) + ";\n";
        }
        else {
          ret += "final" + " " + this.t.toString(t) + " " + id + ";\n";
        }
      }
      else {
        if(hasExpr) {
          ret += this.t.toString(t) + " " + id + " = " + e.toString(t) + ";\n";
        }
        else {
          ret += this.t.toString(t) + " " + id + ";\n";
        }
      }
     // ret = f.toString(t) + " " + this.t.toString(t) + " " + id + e.toString(t) + ";\n";
    }
    else {
      ret += this.t.toString(t) + " " + id + "[" + Integer.toString(arraySize).trim() + "];\n";
    }
    return ret;
  }
  
  public void typeCheck() throws ExampleException {
    FullType thisType = new FullType(t.toEnum(), hasFinal, isArray);
    boolean added = varTable.add(this.id, thisType);
    if(!added) {
      throw new ExampleException("Cannot redeclare variable!");
    }
    if(hasExpr) {
      if(!e.typeCheck().isCoercible(thisType))
        throw new ExampleException("Assignment type is not coercible to declared type!");
    }
  }
}