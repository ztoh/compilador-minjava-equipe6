package table;

import error.*;
import java.util.Set;

import symbol.Symbol;

public class ClassInfo extends Table{
	public Symbol id;
	public Symbol extendedClass;
	public Symbol metodoAtual;
	public VarTable atributos;
	public MethodTable metodos;
	public boolean atualmenteMetodo;
	
	public ClassInfo(Symbol id)
	{
		atributos = new VarTable();
		metodos = new MethodTable();
		this.id = id;
		this.atualmenteMetodo = true;
		
	}
	
	public ClassInfo(Symbol id, Symbol extendedClass)
	{
		this(id);
		this.extendedClass = extendedClass;
	}
	public void setmetodoAtual( Symbol x)
	{
		this.metodoAtual = x;
	}
	
	public Symbol getmetodoAtual()
	{
		return this.metodoAtual;
	}
	
	public MethodInfo retornaMetodoAtual ()
	{
		return (MethodInfo)this.getMetodo(this.getmetodoAtual());
	}
	
	public void setatualmenteMetodo(boolean x)
	{
		this.atualmenteMetodo = x;
	}

	@Override
	public void put(Symbol key, Object value) {
		if(this.atualmenteMetodo)
		{
			if( metodos.get(key) != null)
			{
				Erro.raiseError("Metodo " + key.toString() +" ja declarado");
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
				Erro.raiseError("Variavel " + key.toString() +" ja declarada");
				//Erro, ja foi adicionado
			}
			else
			{
				atributos.put(key, value);
			}
		}
		
		
		// TODO Auto-generated method stub
		
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
