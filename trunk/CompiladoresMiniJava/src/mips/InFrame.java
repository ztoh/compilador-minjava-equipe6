package mips;

import tree.BINOP;
import tree.CONST;
import tree.Exp;
import tree.MEM;
import frame.Access;

public class InFrame extends Access{
	int offset;
	
	public InFrame(int offset)
	{
		this.offset = offset;
	}

	@Override
	public Exp exp(Exp framePtr) {
		// TODO Auto-generated method stub
		return new MEM(new BINOP(BINOP.PLUS,new CONST(this.offset),framePtr));
	}
	
	public String toString()
	{
		return new Integer(this.offset).toString();
	}
}
