package frame;

import java.util.LinkedList;
import java.util.List;

import symbol.Symbol;
import temp.Label;


public abstract class Frame {
	public Label name;
	public LinkedList<Access> formals;
	abstract public Frame newFrame(Symbol name,List <Boolean> formals);
	//abstract public tree.Stm procEntryExit1(List<tree.Stm> body);
    
    //public abstract void procEntryExit2(java.util.List<Instr> body);
    
    //public abstract void procEntryExit3(java.util.List<Instr> body);
	abstract public temp.Temp FP();
	abstract public int wordSize();
	abstract public tree.Exp externalCall(String func, List<tree.Exp> args);
	abstract public temp.Temp RV();
	abstract public Access allocLocal(boolean escape);
	public abstract temp.Temp[] registers();





	

}
