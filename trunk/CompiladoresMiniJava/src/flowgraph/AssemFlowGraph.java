package flowgraph;

import java.util.Hashtable;

import assem.Instr;
import assem.InstrList;
import graph.Node;
import graph.NodeList;
import temp.TempList;

public class AssemFlowGraph extends FlowGraph {
	public Hashtable<Node,assem.Instr> instrucoes;
	public Hashtable<Node,assem.LABEL> labels;
	public Hashtable<assem.LABEL,Node> mapeamento;
		
	public Node procurarNo( int n)
	{
		NodeList aux = this.nodes();
		while( aux != null)
		{
			if( aux.head.valor() == n)
			{
				return aux.head;
			}
			aux = aux.tail;
		}
		return null;
	}
	
	public assem.Instr instr(Node n){
		return null;
	}
	public AssemFlowGraph(assem.InstrList instrs){
		
		Node ultimo = null;
		Instr aux_label = null, aux_branch = null;
        for( InstrList a = instrs ; a != null; a = (InstrList) a.tail )
        {
                if ( a.head instanceof assem.LABEL )
                {
                        aux_label = a.head;
                }
                else
                {
                        Node n = this.newNode();
                        aux_branch = a.head;
                        this.instrucoes.put(n,aux_branch);

                        if(aux_label!=null)
                        { 
                        	this.labels.put(n,(assem.LABEL)aux_label);
                        	this.mapeamento.put((assem.LABEL)aux_label,n);

                            aux_label = null;
                        }
                        if( ultimo != null)
                        {
                        	this.addEdge(ultimo, n);
                        }
                        ultimo = n;
                }	
        }
        
        int i = 0;
        for (InstrList a = instrs ; a != null; a = (InstrList) a.tail)
		{
			if( a.head instanceof assem.OPER)
			{
				if( ((assem.OPER)a.head).jump != null)
				{
					temp.LabelList  aux2= ((assem.OPER)a.head).jump.labels;
					
					this.addEdge(this.procurarNo(i), this.mapeamento.get(aux2.head));
					//this.addEdge(ultimo, n);
				}
			}
			i++;
		}
	}
	@Override
	public TempList def(Node node) {
		// TODO Auto-generated method stub
		return this.instrucoes.get(node).def();
	}
	@Override
	public TempList use(Node node) {
		// TODO Auto-generated method stub
		return this.instrucoes.get(node).use();
	}
	@Override
	public boolean isMove(Node node) {
		// TODO Auto-generated method stub
		return this.instrucoes.get(node) instanceof assem.MOVE ;
	}
}
