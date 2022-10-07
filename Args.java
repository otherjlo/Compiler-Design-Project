class Args extends ExampleToken implements Token {
  Expr e;
  Args a;
  
  public Args(Expr e, Args a) {
    this.e = e;
    this.a = a;
  }
  
  public Args(Expr e) {
    this.e = e;
    a = null;
  }
  
  public String toString(int t) {
    if(a == null) {
      return e.toString(t);
    }
    else {
      return e.toString(t) + " , " + a.toString(t);
    } 
  }
  
  public FullType typeCheck(MethodType currMethod, int currParameter) throws ExampleException {
    if(currMethod.parameterCount() == currParameter + 1) {
      if(!e.typeCheck().isCoercible(currMethod.getParameterType(currParameter))) {
        throw new ExampleException("Argument type does not match parameter!");
      }
      return currMethod.getReturnType();
    }
    if(!e.typeCheck().isCoercible(currMethod.getParameterType(currParameter))) {
      throw new ExampleException("Argument type does not match parameter!");
    }
    return a.typeCheck(currMethod, currParameter + 1);
  }
}