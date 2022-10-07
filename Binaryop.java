class Binaryop extends ExampleToken implements Token {
  Expr left, right;
  String op;
  
  public Binaryop(Expr l, String o, Expr r) {
    left = l;
    right = r;
    op = o;
  }
  
  public String toString(int t) {
    return left.toString(t) + " " + op + " " + right.toString(t);
  }
  
  public FullType typeCheck() throws ExampleException {
    if(op.equals("*")) {
      if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.INT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      } 
      else {
        throw new ExampleException("Cannot multiply incompatible types!");
      }
    }
    else if(op.equals("+")) {
      if(left.typeCheck().getType() == TypeEnum.STRING && right.typeCheck().getType() == TypeEnum.STRING) {
        return new FullType(TypeEnum.STRING, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.INT, false, false);
      }
      else {
        throw new ExampleException("Adding incompatible types!");
      }
    }
    else if(op.equals("-")) {
      if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.INT, false, false);
      }
      else {
        throw new ExampleException("Subtracting incompatible types!");
      }
    }
    else if(op.equals("/")) {
      if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.FLOAT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.FLOAT, false, false);
      }
      else if(left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.INT) {
        return new FullType(TypeEnum.INT, false, false);
      }
      else {
        throw new ExampleException("Dividing incompatible types!");
      }
    }
    else if(op.equals("<") || op.equals(">") || op.equals("<=") || op.equals(">=") || op.equals("==") || op.equals("<>")) {
      if((left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.FLOAT) || 
      (left.typeCheck().getType() == TypeEnum.FLOAT && right.typeCheck().getType() == TypeEnum.INT) ||
      (left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.FLOAT) ||
      (left.typeCheck().getType() == TypeEnum.INT && right.typeCheck().getType() == TypeEnum.INT)) {
        return new FullType(TypeEnum.BOOL, false, false);
      }
      else {
        throw new ExampleException("Comparing incompatible types!");
      }
    }
    else if(op.equals("||") || op.equals("&&")) {
      if(isCoercible(left.typeCheck().getType(), TypeEnum.BOOL) && isCoercible(right.typeCheck().getType(), TypeEnum.BOOL)) {
        return new FullType(TypeEnum.BOOL, false, false);
      }
      else {
        throw new ExampleException("Types must be bool or coercible to bool!");
      }
    }
    return new FullType(TypeEnum.VOID, false, false);
  }
}