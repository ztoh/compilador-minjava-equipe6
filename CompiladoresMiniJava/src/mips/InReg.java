package mips;

import temp.Temp;
import tree.Exp;
import tree.TEMP;
import frame.Access;

public class InReg extends Access{
	Temp temp;
	
	public InReg(Temp temp)
	{
		this.temp = temp;
	}



	@Override
	public Exp exp(Exp framePtr) {
		// TODO Auto-generated method stub
		return new TEMP(temp);
	}
	
}
