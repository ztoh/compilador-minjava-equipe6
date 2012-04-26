package table;

import java.util.Set;

import symbol.Symbol;

public class ClassInfo extends Table{
	public Symbol id;
	public Symbol extendedClass;
	//public Symbol metodoatual;
	public VarTable atributos;
	public MethodTable metodos;
	
	public ClassInfo(Symbol id)
	{
		atributos = new VarTable();
		metodos = new MethodTable();
		this.id = id;
		
	}
	
	public ClassInfo(Symbol id, Symbol extendedClass)
	{
		this(id);
		this.extendedClass = extendedClass;
	}

	@Override
	public void put(Symbol key, Object value) {
		if(atualmenteMetodo())
		{
			if( metodos.get(key) != null)
			{
				//Ja foi adicionado, erro
			}
			else
			{
				metodos.put(key, value);
			}
		}
		
		else
		{
			if(atributos.get(key) != null)
			{
				//Erro, ja foi adicionado
			}
			else
			{
				atributos.put(key, value);
			}
		}
		// TODO Auto-generated method stub
		
	}

	private boolean atualmenteMetodo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(Symbol key) {
		// TODO Auto-generated method stub
		return atributos.get(key);
	}//Procura nos atributos
	
	public Object getMetodo(Symbol key)
	{
		return metodos.get(key);
	}//Procura nos metodos

	@Override
	public Set<Symbol> keys() {
		// TODO Auto-generated method stub
		return atributos.keys();
	}
	
	public Set<Symbol> keysMetodos()
	{
		return metodos.keys();
	}
	
	public String toString()
	{
		//System.out.println("teste");
		String retorno = "Nome "+ this.id;
		if(this.extendedClass != null)
		{
			
			retorno += " " + this.extendedClass;
		}
		//System.out.println("teste2");
		//System.out.println(retorno);
		retorno +=" " + this.atributos.toString();
		retorno +=" " + this.metodos.toString();
		//System.out.println(retorno);
		//System.out.println("teste2");
		return retorno;
	}
	
	public void setatributos(VarTable k)
	{
		this.atributos = k;
	}
	
	public void setmetodos(MethodTable k)
	{
		this.metodos = k;
	}
	
}
