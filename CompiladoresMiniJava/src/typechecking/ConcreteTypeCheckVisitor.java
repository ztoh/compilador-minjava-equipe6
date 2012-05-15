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
//import table.ClassInfo;
import table.ClassInfo;
import table.ClassTable;
import table.MethodInfo;
import table.Table;
import table.VarInfo;

public class ConcreteTypeCheckVisitor implements TypeCheckVisitor {

	ClassTable contexto;
	Stack <Table> scope;
	
	@Override
	public ClassTable visit(Program n) {
		//System.out.println("TESTE");
		scope = new Stack<Table>();
		ImperativeSymbolTableVisitor vis = new ImperativeSymbolTableVisitor();
		//System.out.println("TESTE2");
		contexto =(ClassTable) vis.visit(n);
		//System.out.println("TESTE3");
		n.m.accept(this);
		//System.out.println("Numero de classes "+ n.cl.size());
		//System.out.println("Nome da classe "+ n.cl.elementAt(0).toString());
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		//System.out.println("TESTE4");
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
		//System.out.println("C1");
		n.s.accept(this);
		//System.out.println("C2");
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ClassDeclSimple n) {
		//System.out.println("Declaracao de classe simples");
		ClassInfo k = (ClassInfo) this.beginScope(Symbol.symbol(n.i.toString()));
		//System.out.println(k.id.toString());
		//System.out.println("Declaracao de classe simples2");
		//System.out.println(n.ml.size());
		//System.out.println(n.ml.elementAt(0).toString());
		// TODO Auto-generated method stub
		for (int i = 0; i < n.ml.size(); i++) {
			//System.out.println("Declaracao de classe simples3");
			k.setmetodoAtual(Symbol.symbol(n.ml.elementAt(i).id.toString()));
			n.ml.elementAt(i).accept(this);
			//System.out.println("Declaracao de classe simples4");
		}
		//System.out.println("Declaracao de classe simples5");
		this.endScope();

	}

	@Override
	public void visit(ClassDeclExtends n) {
		System.out.println("Declaracao de classe com extensao");
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
		//System.out.println("method decl");
		//System.out.println(k.id.toString());
		//System.out.println(n.sl.size());
		for (int i = 0; i < n.sl.size(); i++) {
			
			n.sl.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		
		if(!k.retorno.toString().equals(n.t.toString()))
		{
			Erro.raiseError("Tipo de retorno é incompatível");
		}

	}

	@Override
	public void visit(If n) {
		System.out.println("if");
		if(!n.e.accept(this).equals(new BooleanType().toString()))
		{
			Erro.raiseError("Tipo booleano nao encontrado");
		}
		n.s1.accept(this);
		n.s2.accept(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(While n) {
		if(!n.e.accept(this).equals(new BooleanType().toString()))
		{
			Erro.raiseError("Tipo booleano nao encontrado");
		}
		n.s.accept(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Print n) {
		System.out.println("print");
		if(!n.e.accept(this).equals(new IntegerType().toString()))
		{
			Erro.raiseError("Tipo inteiro nao encontrado");
		}
		//System.out.println("AQUI2");
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Assign n) {
		System.out.println("Assign");
		System.out.println(n.i.toString());
		System.out.println(n.e.accept(this).toString());
		if(estaNaMain())//Esta no metodo principal
		{
			Erro.raiseError("Variavel nao declarada");
		}
		
		else
		{
			if(!n.i.accept(this).equals(n.e.accept(this)))
			{
				Erro.raiseError("Atribuicao de 2 tipos diferentes");
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
		return n.accept(this);
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
		System.out.println("times");
		//System.out.println(n.e1.accept(this));
		//System.out.println(n.e2.accept(this));
		//System.out.println("TESTE 10");
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
		//System.out.println(k.toString());
		//System.out.println(j.toString());
		//System.out.println(n.s);
		VarInfo x = (VarInfo)j.paramEntrada.get(Symbol.symbol(n.s));
		for (int i = 0; i < j.parametroDeEntrada.size(); i++) {
			//System.out.println(j.parametroDeEntrada.get(i));
		}
		//System.out.println(x);
		//System.out.println("CHEGOU EXATAMANTE AQUI");
		if( x == null)
		{
			//System.out.println(x);
			x = (VarInfo)j.listaDeVariaveis.get(Symbol.symbol(n.s));
			if( x == null)
			{
				//System.out.println(x);
				x = (VarInfo)k.get(Symbol.symbol(n.s));
				if( x == null)
				{
					//System.out.println(x);
					Erro.raiseError("Variavel nao declarada");
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
		System.out.println("new object");
		ClassInfo k = (ClassInfo) contexto.get(Symbol.symbol(n.i.toString()));
		if( k == null)
		{
			Erro.raiseError("Classe nao declarada1");
			return Symbol.symbol("");
			
		}
		//System.out.println("new object2");
		//System.out.println(k.id);
		// TODO Auto-generated method stub
		return k.id;
	}

	@Override
	public Symbol visit(NewArray n) {
		if(n.e.accept(this).equals(new IntegerType()))
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
		System.out.println("call");
		ClassInfo k = (ClassInfo)contexto.get(n.e.accept(this));
		if(k != null)
		{
			MethodInfo j = (MethodInfo)k.getMetodo(Symbol.symbol(n.i.toString()));
			if( j != null)
			{
				if(n.el.size() == j.parametroDeEntrada.size())
				{
					for (int i = 0; i < n.el.size(); i++) {
						if(!n.el.elementAt(i).accept(this).equals(j.parametroDeEntrada.get(i)))
						{
							Erro.raiseError("Tipo incompativel no parametro");
						}
					}
					//System.out.println(j.retorno);
					return j.retorno;
				}
			}
		}
		return Symbol.symbol("");
		
		
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
		System.out.println(n.s);
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
		return Symbol.symbol(n.s);
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Block n) {
		
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);	
		}
		// TODO Auto-generated method stub
		
	}

}