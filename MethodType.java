import java.util.ArrayList;
class MethodType extends ExampleToken {
  boolean hasReturn = false;
  FullType returnType;
  ArrayList<FullType> parameters = new ArrayList<FullType>();
  public MethodType(FullType returnType) {
    this.returnType = returnType;
  }
  
  public void addParameter(FullType type) {
    parameters.add(type);
  }
  
  public FullType getParameterType(int index) throws ExampleException {
    if(parameters.size()-1 < index) {
      throw new ExampleException("Parameter does not exist!");
    }
    else
      return parameters.get(index);
  }
  
  public FullType getReturnType() {
    return this.returnType;
  }
  
  public boolean noParameters() {
    return parameters.isEmpty();
  }
  public int parameterCount() {
    return parameters.size();
  }
  
  public void setReturn() {
    this.hasReturn = true;
  }
  public boolean getReturn() {
    return this.hasReturn;
  }
}