package syntaxtree;
import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.ClassInfo;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MainClass {
  public Identifier i1,i2;
  public Statement s;

  public MainClass(Identifier ai1, Identifier ai2, Statement as) {
    i1=ai1; i2=ai2; s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

public ClassInfo accept(ImperativeSymbolTableVisitor imperativeSymbolTableVisitor,
		Symbol id) {
	// TODO Auto-generated method stub
	return new ClassInfo(id);
}

public void accept(TypeCheckVisitor concreteTypeCheckVisitor) {
	// TODO Auto-generated method stub
	
}
}

