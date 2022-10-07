class Type extends ExampleToken implements Token {
  String type;
  
  public Type(String type) {
    this.type = type;
  }
  
  public String toString(int t) {
    return type;
  }
  
  public TypeEnum toEnum() {
    if(type.equals("string"))
      return TypeEnum.STRING;
    else if(type.equals("int"))
      return TypeEnum.INT;
    else if(type.equals("float"))
      return TypeEnum.FLOAT;
    else if(type.equals("bool"))
      return TypeEnum.BOOL;
    else if(type.equals("char"))
      return TypeEnum.CHAR;
    else
      return TypeEnum.VOID;
  }
  
}