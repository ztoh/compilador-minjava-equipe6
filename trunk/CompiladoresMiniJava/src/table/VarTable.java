package table;


import java.util.*;
import symbol.Symbol;

public class VarTable extends Table {
	private Hashtable<Symbol,VarInfo> var;
	
	public VarTable()
	{
		var = new Hashtable<Symbol, VarInfo>();
	}
	
	@Override
	public void put(Symbol key, Object value) {
		var.put(key, (VarInfo)value);
		// TODO Auto-generated method stub
	}
	@Override
	public Object get(Symbol key) {
		// TODO Auto-generated method stub
		return var.get(key);
	}

	@Override
	public Set<Symbol> keys() {
		return var.keySet();
		// TODO Auto-generated method stub
	}

}
