package mips;

import temp.Temp;
import tree.Exp;
import frame.Access;

public class InReg extends Access{
	Temp temp;
	
	public InReg(Temp temp)
	{
		this.temp = temp;
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
