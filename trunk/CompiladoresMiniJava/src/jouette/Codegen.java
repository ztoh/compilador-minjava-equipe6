package jouette;

import frame.Frame;

public class Codegen {
	
	Frame frame;
	private assem.InstrList ilist=null, last=null;
	public Codegen(Frame f) {frame=f;}
	
	
	
	private void emit(assem.Instr inst) {
		if (last!=null)
			last = last.tail = new assem.InstrList(inst,null);
		else last = ilist = new assem.InstrList(inst,null);
	}
	
	void munchStm(tree.Stm s) {  }
	
	temp.Temp munchExp(tree.Exp s) { return null; }
		
	assem.InstrList codegen(tree.Stm s) {
		assem.InstrList l;
		munchStm(s);
		l=ilist;
		ilist=last=null;
		return l;
	}
}
