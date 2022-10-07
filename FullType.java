class FullType extends ExampleToken {
  TypeEnum type;
  boolean isFinal;
  boolean isArray;
  
  public FullType(TypeEnum type, boolean isFinal, boolean isArray) {
    this.type = type;
    this.isFinal = isFinal;
    this.isArray = isArray;
    /*
    if(isArray) {
      if(type == TypeEnum.INT)
        type = TypeEnum.INTARRAY;
      else if(type == TypeEnum.CHAR)
        type = TypeEnum.CHARARRAY;
      else if(type == TypeEnum.FLOAT) {
        type = TypeEnum.FLOATARRAY;
      }
      else if(type == TypeEnum.BOOL)
        type = TypeEnum.BOOLARRAY;
    }*/
  }
  
  public boolean isFinal() {
    return this.isFinal;
  }
  
  public TypeEnum getType() {
    return this.type;
  }
  public boolean isArray() {
    return this.isArray;
  }
  
  public boolean isCoercible(FullType other) throws ExampleException {
    //Handle arrays
    if(other.isArray() && this.isArray) {
      if(other.getType() == TypeEnum.STRING)
        return true;
      else if(this.type == TypeEnum.INT) {
        if(other.getType() == TypeEnum.BOOL || other.getType() == TypeEnum.FLOAT || other.getType() == TypeEnum.INT)
          return true;
      }
      else if(this.type == other.getType())
        return true;
      else {
        throw new ExampleException("Types not coercible!");
      }
    }
    else if(other.isArray() && !this.isArray)
      return false;
    else if(!other.isArray() && this.isArray)
      return false;
    //Both are not arrays;
    else {
      if(other.getType() == TypeEnum.STRING)
        return true;
      else if(this.type == TypeEnum.INT) {
        if(other.getType() == TypeEnum.BOOL || other.getType() == TypeEnum.FLOAT)
          return true;
      }
      else if(this.type == other.getType())
        return true;
      else {
        throw new ExampleException("Types not coercible!");
      }
    }
    return false;
  }
  
  public boolean isCoercible(TypeEnum other) throws ExampleException {
    if(other == TypeEnum.STRING) {
      if(!isArray) {
        return true;
      }
    }
    else if(this.type == TypeEnum.INT) {
      if(other == TypeEnum.BOOL || other == TypeEnum.FLOAT || other == TypeEnum.INT) {
        return true;
      }
    }
    else if(this.type == other) {
      return true;
    }
    else {
      throw new ExampleException("This type is not coercible!");
    }
    return false;
  }
}