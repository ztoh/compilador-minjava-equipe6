package frame;

import java.util.LinkedList;
import java.util.List;

import symbol.Symbol;
import temp.Label;


public abstract class Frame {
	public Label name;
	public LinkedList<Access> formals;
	abstract public Frame newFrame(Symbol name,List <Boolean> formals);
	

}
