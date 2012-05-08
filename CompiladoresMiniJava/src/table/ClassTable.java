package table;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import error.Erro;

import symbol.Symbol;

public class ClassTable extends Table{

	public Hashtable<Symbol, ClassInfo> dict;
	
	public ClassTable()
	{
		dict = new Hashtable<Symbol, ClassInfo>();
	}
	
	@Override
	public void put(Symbol key, Object value) {
		if(dict.get(key) != null)
		{
			Erro.raiseError("Classe " + key.toString() +" ja adicionada");
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
	
	@Override
	public String toString()
	{
		String retorno = "Classes\n";
		Iterator<ClassInfo> aux = dict.values().iterator();
		//ClassInfo aux2;
		int i = 0;
		while(aux.hasNext()) {
			i++;
			//aux2 = aux.next();
			retorno +=aux.next().toString() + "\n";
		}
		System.out.println("Numero de Classes " + i);
		return retorno;
	}

}
