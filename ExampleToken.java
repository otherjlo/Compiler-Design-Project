abstract class ExampleToken implements Token{

  static VarTable varTable = new VarTable();
  static MethodTable methodTable = new MethodTable();
  public static int currentScope;
  enum TypeEnum {STRING, INT, BOOL, CHAR, VOID, FLOAT, INTARRAY, STRINGARRAY, BOOLARRAY, FLOATARRAY, CHARARRAY;}
  public String T(int t)
  {
  	String tabs = "";
  	for (int i = 0; i < t; i++)
  		tabs = tabs + "\t";
  	return tabs;
  }

  public String toString(int t)
  {
  	return "";
  }
  
  public TypeEnum getEnum(Type type) {
    if(type.equals("bool")) {
      return TypeEnum.BOOL;
    }
    else if(type.equals("int")) {
      return TypeEnum.INT;
    }
    else if(type.equals("float")) {
      return TypeEnum.FLOAT;
    }
    else if(type.equals("char")) {
      return TypeEnum.CHAR;
    }
    else {
      return TypeEnum.VOID;
    }
  }
   public boolean isCoercible(TypeEnum thisType, TypeEnum other) throws ExampleException {
    if(other == TypeEnum.STRING) {
        return true;
    }
    else if(thisType == TypeEnum.INT) {
      if(other == TypeEnum.BOOL || other == TypeEnum.FLOAT || other == TypeEnum.INT) {
        return true;
      }
    }
    else if(thisType == other) {
      return true;
    }
    else {
      throw new ExampleException("This type is not coercible!");
    }
    return false;
  }
}