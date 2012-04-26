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
	
	public String toString()
	{
		String retorno = "\t";
		Iterator<VarInfo> aux = this.var.values().iterator();
		VarInfo aux2;
		//System.out.println("Tamanho do hash " + var.size());
		while (aux.hasNext()) {
			aux2 = aux.next();
			retorno += " " + aux2.toString();
			//System.out.println("teste3");
			//System.out.println(aux2.toString());
			
		}
		//System.out.println("teste");
		//System.out.println(retorno);
		return retorno;
		
	}

}
