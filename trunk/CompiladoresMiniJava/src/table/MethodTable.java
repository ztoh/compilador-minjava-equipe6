package table;

import java.util.Hashtable;
import java.util.Set;

import symbol.Symbol;

public class MethodTable extends Table{

	public Hashtable<Symbol,MethodInfo> dict;
	
	public MethodTable()
	{
		dict = new Hashtable<Symbol, MethodInfo>();
	}
	
	@Override
	public void put(Symbol key, Object value) {
		
		if(dict.get(key) == null)
		{
			dict.put(key, (MethodInfo)value);	
		}
		//Else para indicar que o metodo ja foi definido
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(Symbol key) {
		// TODO Auto-generated method stub
		return dict.get(key);
	}

	@Override
	public Set<Symbol> keys() {
		// TODO Auto-generated method stub
		return dict.keySet();
	}

}
