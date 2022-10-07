import java.util.HashMap;
class MethodTable extends ExampleToken {
  HashMap<String, MethodType> methodTable;
  static MethodType currMethod;
  
  public MethodTable() {
    methodTable = new HashMap<String, MethodType>();
  }
  
  public boolean add(String methodName, MethodType type) throws ExampleException {
    if(methodTable.containsKey(methodName)) {
      throw new ExampleException("Cannot redeclare method!");
    }
    else {
      methodTable.put(methodName, type);
    }
    return true;
  }
  
  public MethodType getMethodType(String methodName) throws ExampleException {
    if(methodTable.containsKey(methodName)) {
      return methodTable.get(methodName);
    }
    else
      throw new ExampleException("Method has not been defined!");
  }
}