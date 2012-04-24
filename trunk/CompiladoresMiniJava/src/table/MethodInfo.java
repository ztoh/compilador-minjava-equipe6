package table;

//import java.util.ArrayList;
import java.util.Set;

import symbol.Symbol;

public class MethodInfo extends Table{

	//public ArrayList<Symbol> parametroDeEntrada;
	public Symbol retorno;
	public VarTable paramEntrada;
	public VarTable listaDeVariaveis;
	public Symbol id;
	
	public MethodInfo(Symbol retorno, Symbol id)
	{
		this.retorno = retorno;
		paramEntrada = new VarTable();
		listaDeVariaveis = new VarTable();
		this.id = id;
		
	}
	
	@Override
	public void put(Symbol key, Object value) {
		if(isParam())
		{
			if(paramEntrada.get(key) != null)
			{
				//Erro, ja foi adicionado
			}
			else
			{
				paramEntrada.put(key, value);
				//parametroDeEntrada.add(key);
			}
		}
		else
		{
			if(listaDeVariaveis.get(key) != null || paramEntrada.get(key) != null)
			{
				//Erro, ja definido antes no parametro ou no proprio metodo
			}
			else
			{
				listaDeVariaveis.put(key, value);
			}
		}
		
		// TODO Auto-generated method stub
		
	}
	private boolean isParam() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object get(Symbol key) {
		
		Object s;
		if(  (s =paramEntrada.get(key)) != null)
		{
			return s;
		}
		// TODO Auto-generated method stub
		return listaDeVariaveis.get(key);
	}
	@Override
	public Set<Symbol> keys() {
		Set<Symbol> retorno = paramEntrada.keys();
		retorno.addAll(listaDeVariaveis.keys());
		// TODO Auto-generated method stub
		return retorno;
	}
	
}
