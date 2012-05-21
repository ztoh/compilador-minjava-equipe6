package mips;

import temp.Temp;
import tree.Exp;
import frame.Access;

public class InFrame extends Access{
	int offset;
	
	public InFrame(int offset)
	{
		this.offset = offset;
	}

	@Override
	public void exp(Temp temp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Exp exp(Exp framePtr) {
		// TODO Auto-generated method stub
		return null;
	}
}
