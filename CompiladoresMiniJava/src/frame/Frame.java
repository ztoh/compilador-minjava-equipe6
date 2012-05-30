package frame;

import java.util.LinkedList;
import java.util.List;

import assem.Instr;

import symbol.Symbol;
import temp.Label;


public abstract class Frame {
	public Label name;
	public int wordSize;
	public LinkedList<Access> formals;
	abstract public Frame newFrame(Symbol name,List <Boolean> formals);
	abstract public void procEntryExit1(List<tree.Stm> body);
    public abstract void procEntryExit2(java.util.List<Instr> body);
    public abstract void procEntryExit3(java.util.List<Instr> body);
	abstract public temp.Temp FP();
	abstract public int wordSize();
	abstract public tree.Exp externalCall(String func, List<tree.Exp> args);
	abstract public temp.Temp RV();
	abstract public Access allocLocal(boolean escape);
	public abstract temp.Temp[] registers();
	public abstract String tempMap(temp.Temp Temp);
	//abstract public Assem.InstrList codegen(Tree.Stm stm);


	

}
