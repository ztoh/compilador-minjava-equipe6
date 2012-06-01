package typechecking;

import java.util.Stack;

import error.Erro;

import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;	
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.While;
import table.ClassInfo;
import table.ClassTable;
import table.MethodInfo;
import table.Table;
import table.VarInfo;

public class ConcreteTypeCheckVisitor implements TypeCheckVisitor {

	ClassTable contexto;
	Stack <Table> scope;
	

	public ConcreteTypeCheckVisitor(ClassTable x)
	{
		contexto = x;
	}
	
	@Override
	public ClassTable visit(Program n) {
		scope = new Stack<Table>();
		//ImperativeSymbolTableVisitor vis = new ImperativeSymbolTableVisitor();
		//contexto =(ClassTable) vis.visit(n);
		n.m.accept(this);

		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		return contexto;
	}
	
	public boolean estaNaMain()
	{
		return scope.isEmpty();
	}
	
	public Table beginScope(Symbol key)
	{
		Table t =(Table) this.contexto.get(key);
		scope.push(t);
		return t;
	}
	
	public void endScope()
	{
		scope.pop();
	}

	@Override
	public void visit(MainClass n) {
		n.s.accept(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ClassDeclSimple n) {
		ClassInfo k = (ClassInfo) this.beginScope(Symbol.symbol(n.i.toString()));

		// TODO Auto-generated method stub
		for (int i = 0; i < n.ml.size(); i++) {
			k.setmetodoAtual(Symbol.symbol(n.ml.elementAt(i).id.toString()));
			n.ml.elementAt(i).accept(this);
		}
		this.endScope();

	}

	@Override
	public void visit(ClassDeclExtends n) {
		
		ClassInfo k = (ClassInfo) this.beginScope(Symbol.symbol(n.i.toString()));

		for (int i = 0; i < n.ml.size(); i++) {
			k.setmetodoAtual(Symbol.symbol(n.ml.elementAt(i).id.toString()));
			n.ml.elementAt(i).accept(this);
		}
		
		if((this.contexto.get(Symbol.symbol(n.j.toString()))) == null)
		{
			Erro.raiseError("SuperClasse " + n.j.toString() +" nao encontrada");
		}
		
		this.endScope();
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MethodDecl n) {
		MethodInfo k =(MethodInfo) ((ClassInfo)scope.peek()).getMetodo(Symbol.symbol(n.id.toString()));

		for (int i = 0; i < n.sl.size(); i++) {
			
			n.sl.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub

		if(!k.retorno.equals(n.e.accept(this)))
		{
			Erro.raiseError("Tipo de retorno é incompatível");
		}

	}

	@Override
	public void visit(If n) {
		
		if(!n.e.accept(this).equals(new BooleanType().toString()))
		{
			Erro.raiseError("Tipo booleano nao encontrado no if");
		}
		n.s1.accept(this);
		n.s2.accept(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(While n) {
		if(!n.e.accept(this).equals(new BooleanType().toString()))
		{
			Erro.raiseError("Tipo booleano nao encontrado no while");
		}
		n.s.accept(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Print n) {
		if(!n.e.accept(this).equals(new IntegerType().toString()))
		{
			Erro.raiseError("Tipo inteiro nao encontrado no print");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Assign n) {
		if(estaNaMain())//Esta no metodo principal
		{
			Erro.raiseError("Variavel nao declarada");
		}
		
		else
		{
			if(!n.i.accept(this).equals(n.e.accept(this)))
			{
				Erro.raiseError("Atribuicao de 2 tipos diferentes no Assign");
			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArrayAssign n) {
		if(estaNaMain())//Esta no metodo principal
		{
			Erro.raiseError("Variavel nao declarada");
		}
		else
		{
			if(!n.i.accept(this).equals(new IntArrayType()))
			{
				Erro.raiseError("Indicação de posição de vetor é apenas com inteiros");
			}
			
			if(!n.e1.accept(this).equals(new IntegerType()))
			{
				Erro.raiseError("Indicação de posição de vetor é apenas com inteiros");
			}
			if(!n.e2.accept(this).equals(new IntegerType()))
			{
				Erro.raiseError("Vetor apenas de inteiros");
			}
			
		}
		
		// TODO Auto-generated method stub

	}

	@Override
	public Symbol visit(Formal n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(n.t.toString());
	}

	@Override
	public Symbol visit(And n) {
		// TODO Auto-generated method stub
		if(!n.e1.accept(this).equals(new BooleanType()))
		{
			Erro.raiseError("Tipo booleano esperado na expressão com And");
		}
		
		if(!n.e2.accept(this).equals(new BooleanType()))
		{
			Erro.raiseError("Tipo booleano esperado na expressão com And");
		}
		return Symbol.symbol(new BooleanType().toString());
		
	}

	@Override
	public Symbol visit(LessThan n) {
		// TODO Auto-generated method stub
		if(!n.e1.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com LessThan");
		}
		
		if(!n.e2.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com LessThan");
		}
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(Minus n) {
		// TODO Auto-generated method stub
		if(!n.e1.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com Minus");
		}
		
		if(!n.e2.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com Minus");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(Plus n) {
		// TODO Auto-generated method stub
		if(!n.e1.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com Plus");
		}
				
		if(!n.e2.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com Plus");
		}
			
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(Times n) {
		
		// TODO Auto-generated method stub
		if(!n.e1.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com Times");
		}
			
		if(!n.e2.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com Times");
		}
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(ArrayLength n) {
		
		if(!n.e.accept(this).equals(new IntArrayType()))
		{
			Erro.raiseError("Tipo array de inteiros esperado na expressão com ArrayLength");
		}
		// TODO Auto-generated method stub
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(IntegerLiteral n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(new IntegerType().toString());
	}

	@Override
	public Symbol visit(This n) {
		if(estaNaMain())//esta na main
		{
			Erro.raiseError("Erro: referencia this no metodo principal");
			return Symbol.symbol("");
		}
		else
		{
			ClassInfo k = (ClassInfo)scope.peek();
			return k.id;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public Symbol visit(IdentifierExp n) {
		if(estaNaMain())
		{
			Erro.raiseError("Variavel nao declarada (estamos na main)");
			return Symbol.symbol("");
		}
		ClassInfo k = (ClassInfo) scope.peek();
		MethodInfo j = k.retornaMetodoAtual();
		VarInfo x = (VarInfo)j.paramEntrada.get(Symbol.symbol(n.s));

		if( x == null)
		{
			x = (VarInfo)j.listaDeVariaveis.get(Symbol.symbol(n.s));
			if( x == null)
			{
				x = (VarInfo)k.get(Symbol.symbol(n.s));
				if( x == null)
				{
					return this.checarAtributoSuper(n.s);
				}
			}
			
		}
		// TODO Auto-generated method stub
		return Symbol.symbol(x.toString());
	}

	@Override
	public Symbol visit(True n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(False n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(NewObject n) {
		ClassInfo k = (ClassInfo) contexto.get(Symbol.symbol(n.i.toString()));
		if( k == null)
		{
			Erro.raiseError("Classe nao declarada1");
			return Symbol.symbol("");
			
		}
		// TODO Auto-generated method stub
		return k.id;
	}

	@Override
	public Symbol visit(NewArray n) {
		
		if(!n.e.accept(this).equals(Symbol.symbol(new IntegerType().toString())))
		{
			Erro.raiseError("Int esperado para descrever o tamanho do array");
		}
		// TODO Auto-generated method stub
		return Symbol.symbol(new IntArrayType().toString());
	}

	@Override
	public Symbol visit(ArrayLookup n) {
		
		if(!n.e1.accept(this).equals(new IntArrayType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com ArrayLookUp");
		}
				
		if(!n.e2.accept(this).equals(new IntegerType()))
		{
			Erro.raiseError("Tipo inteiro esperado na expressão com ArrayLookUp");
		}
			
		return Symbol.symbol(new IntegerType().toString());
		// TODO Auto-generated method stub
	}

	@Override
	public Symbol visit(Call n) {
		ClassInfo k = (ClassInfo)contexto.get(n.e.accept(this));
		if(k != null)
		{
			MethodInfo j = verificarMetodo(k,n.i.toString());
			if( j != null)
			{
				if(n.el.size() == j.parametroDeEntrada.size())
				{
					for (int i = 0; i < n.el.size(); i++) {
						
						if(!n.el.elementAt(i).accept(this).equals(j.parametroDeEntrada.get(i)))
						{
							if(contexto.get(j.parametroDeEntrada.get(i)) != null)//Verificar se o parametro é subclasse da classe esperada
							{
								if(!verificarParentesco(n.el.elementAt(i).accept(this),j.parametroDeEntrada.get(i) ))
								{
									Erro.raiseError("Tipo incompativel no parametro");
								}
							}
							else
							{
								Erro.raiseError("Tipo incompativel no parametro");
							}
						}
					}
					
				}
				return j.retorno;
			}
			Erro.raiseError("Metodo nao existe");
			return Symbol.symbol("");
		}
		Erro.raiseError("Classe nao existe");
		return Symbol.symbol("");
	}

	public boolean verificarParentesco(Symbol parametro, Symbol classe) {
		ClassInfo k = (ClassInfo)contexto.get(parametro);
		while( k != null)
		{
			if( k.extendedClass.equals(classe))
			{
				return true;
			}
			k = (ClassInfo)contexto.get(k.extendedClass);
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	public MethodInfo verificarMetodo(ClassInfo x,String n) {
		ClassInfo k = x;
		MethodInfo j;
		do{
			j = (MethodInfo)k.getMetodo(Symbol.symbol(n));
			if( j != null)
			{
				return j;
			}
			k = (ClassInfo)contexto.get(k.extendedClass);
		}while(k != null);
		// TODO Auto-generated method stub
		return j;
	}

	@Override
	public Symbol visit(Not n) {
		if(!n.e.accept(this).equals(new BooleanType()))
		{
			Erro.raiseError("Tipo booleano esperado na expressão com Not");
		}
		// TODO Auto-generated method stub
		return Symbol.symbol(new BooleanType().toString());
	}

	@Override
	public Symbol visit(BooleanType n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(n.toString());
	}

	@Override
	public Symbol visit(IdentifierType n) {
		ClassInfo k = (ClassInfo)contexto.get(Symbol.symbol(n.s));
		if( k  == null)
		{
			Erro.raiseError("Classe nao declarada");
			return Symbol.symbol("");
		}
		// TODO Auto-generated method stub
		return Symbol.symbol(n.s);
	}

	@Override
	public Symbol visit(IntegerType n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(n.toString());
	}

	@Override
	public Symbol visit(IntArrayType n) {
		// TODO Auto-generated method stub
		return Symbol.symbol(n.toString());
	}

	@Override
	public Symbol visit(Identifier n) {//Falta buscar o tipo da variavel ainda
		
		if(estaNaMain())
		{
			Erro.raiseError("Variavel nao declarada (estamos na main)");
			return Symbol.symbol("");
		}
		
		ClassInfo k = (ClassInfo) scope.peek();
		MethodInfo j = k.retornaMetodoAtual();
		VarInfo x = (VarInfo)j.paramEntrada.get(Symbol.symbol(n.s));
		
		if( x == null)
		{
			x = (VarInfo)j.listaDeVariaveis.get(Symbol.symbol(n.s));
			if(x == null)
			{
				x = (VarInfo)k.get(Symbol.symbol(n.s));
				if(x == null)
				{
					return checarAtributoSuper(n.s);
				}
			}
		}
		
		return Symbol.symbol(x.gettype().toString());
		// TODO Auto-generated method stub
	}

	public Symbol checarAtributoSuper(String n) {
		ClassInfo k = (ClassInfo)scope.peek();
		VarInfo x;
		while(!k.extendedClass.equals(Symbol.symbol("")))
		{
			k = (ClassInfo)beginScope(k.extendedClass);
			x = (VarInfo)k.get(Symbol.symbol(n));
			endScope();
			if( x!= null)
			{
				return Symbol.symbol(x.gettype().toString());
			}
		}
		Erro.raiseError("Variavel "+n+" nao declarada checarAtributoSuper");
		// TODO Auto-generated method stub
		return Symbol.symbol("");
	}

	@Override
	public void visit(Block n) {
		
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);	
		}
		// TODO Auto-generated method stub
		
	}

}