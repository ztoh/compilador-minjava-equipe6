package table;

import java.util.Hashtable;
import java.util.Set;

import symbol.Symbol;

public class ClassTable extends Table{

	public Hashtable<Symbol, ClassInfo> dict;
	
	@Override
	public void put(Symbol key, Object value) {
		if(dict.get(key) != null)
		{
			//Erro, classe ja adicionada
		}
		
		else
		{
			dict.put(key, (ClassInfo)value);
		}
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
