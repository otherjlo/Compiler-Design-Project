import java.util.Vector;
import java.util.HashMap;
class VarTable extends ExampleToken {
  Vector<HashMap<String, FullType>> varTable;
  //int innerMostScope;
  
  public VarTable() {
    this.varTable = new Vector<HashMap<String, FullType>>();
    ExampleToken.currentScope = 0;
    HashMap<String, FullType> firstTable = new HashMap<String, FullType>();
    varTable.add(firstTable);      
  }
  
  public FullType getType(String id) throws ExampleException {
    int thisScope = ExampleToken.currentScope;
    while(thisScope != -1) {
      HashMap<String, FullType> currentTable = varTable.get(currentScope);
      if(currentTable.containsKey(id)) {
        return currentTable.get(id);
      }
      else {
        thisScope--;
      }
    }
    if(thisScope == -1) {
      throw new ExampleException("This variable has not been defined!");
    }
    else
      return null;
  }
  
  public boolean add(String id, FullType type) {
    HashMap<String, FullType> currentTable = varTable.get(ExampleToken.currentScope);
    if(currentTable.containsKey(id)) {
      return false;
    }
    currentTable.put(id, type);
    return true;
  }
  
  public void raiseScope() {
    ExampleToken.currentScope++;
    HashMap<String, FullType> newMap = new HashMap<String, FullType>();
    varTable.add(newMap);
  }
  
  public void exitScope() {
    if(ExampleToken.currentScope == 0) 
      return;
    varTable.remove(ExampleToken.currentScope);
    ExampleToken.currentScope--;
  }
}