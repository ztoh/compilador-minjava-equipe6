package main;

import Semant.Semant;
import Translate.Translate;
import Parse.Parse;

class Main {

 static frame.Frame frame = new <MyTargetMachine>.Frame();

 static void prStmList(tree.Print print, tree.StmList stms) {
     for(tree.StmList l = stms; l!=null; l=l.tail)
	print.prStm(l.head);
 }

 static assem.InstrList codegen(frame.Frame f, tree.StmList stms) {
  	assem.InstrList first=null, last=null;
	    for(tree.StmList s=stms; s!=null; s=s.tail) {
	       assem.InstrList i = f.codegen(s.head);
	       if (last==null) {first=last=i;}
	       else {while (last.tail!=null) last=last.tail;
		     last=last.tail=i;
		    }
	    }
	    return first;
	}
		

 static void emitProc(java.io.PrintStream out, Translate.ProcFrag f) {
     java.io.PrintStream debug = 
          /* new java.io.PrintStream(new NullOutputStream()); */
          out;
     temp.TempMap tempmap= new temp.CombineMap(f.frame,new temp.DefaultMap());
     tree.Print print = new tree.Print(debug, tempmap);
     debug.println("# Before canonicalization: ");
     print.prStm(f.body);
     debug.print("# After canonicalization: ");
     tree.StmList stms = canon.Canon.linearize(f.body);
     prStmList(print,stms);
     debug.println("# Basic Blocks: ");
     canon.BasicBlocks b = new canon.BasicBlocks(stms);
     for(canon.StmListList l = b.blocks; l!=null; l=l.tail) {
       debug.println("#");
       prStmList(print,l.head);
     }       
     print.prStm(new tree.LABEL(b.done));
     debug.println("# Trace Scheduled: ");
     tree.StmList traced = (new canon.TraceSchedule(b)).stms;
     prStmList(print,traced);
     assem.InstrList instrs= codegen(f.frame,traced);
     debug.println("# Instructions: ");
     for(assem.InstrList p=instrs; p!=null; p=p.tail)
	debug.print(p.head.format(tempmap));
 }

 public static void main(String args[]) throws java.io.IOException {
   Parse.Parse parse = new Parse.Parse(args[0]);
   java.io.PrintStream out = 
        new java.io.PrintStream(new java.io.FileOutputStream(args[0] + ".s"));
   Translate.Translate translate = new Translate.Translate(frame);
   Semant semant = new Semant(translate,parse.errorMsg);
   Translate.Frag frags = semant.transProg(parse.absyn);
   for(Translate.Frag f = frags; f!=null; f=f.next)
       if (f instanceof Translate.ProcFrag)
          emitProc(out, (Translate.ProcFrag)f);
       else if (f instanceof Translate.DataFrag)
          out.print(((Translate.DataFrag)f).data);
   out.close();
 }

}

class NullOutputStream extends java.io.OutputStream {
    public void write(int b) {}
}

