package flowgraph;

import java.util.Hashtable;

import assem.Instr;
import assem.InstrList;
import graph.Node;
import graph.NodeList;
import temp.TempList;

public class AssemFlowGraph extends FlowGraph {
	public Hashtable<Node,assem.Instr> instrucoes;
	public Hashtable<Node,temp.Label> labels;
	public Hashtable<temp.Label,Node> mapeamento;
		
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
		this.instrucoes = new Hashtable<Node, Instr>();
		this.labels = new Hashtable<Node, temp.Label>();
		this.mapeamento = new Hashtable<temp.Label, Node>();
		
		Node n = null;
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
                	 /*if(a.head instanceof assem.MOVE)
                     {
                     	if(a.head.jumps() == null)
                     	{
                     		System.out.println("TESTE");
                     	}
                     }*/
                        n = this.newNode();
                        aux_branch = a.head;
                        this.instrucoes.put(n,aux_branch);

                        if(aux_label!=null)
                        { 
                        	this.labels.put(n,((assem.LABEL)aux_label).label);
                        	this.mapeamento.put(((assem.LABEL)aux_label).label,n);
                        	//System.out.println("AQUI");
                        	//System.out.println(((assem.LABEL)aux_label).label);

                            aux_label = null;
                        }
                        if( ultimo != null)
                        {
                        	if(a.head instanceof assem.MOVE)
                        		System.out.println("AQUI");
                        	
                    		if(aux_branch.jumps() == null)
                        		this.addEdge(ultimo, n);
                        	
                        }
                        ultimo = n;
                }	
        }
        n = this.newNode();
        this.addEdge(ultimo,n);
        ultimo = n;
        this.instrucoes.put(ultimo, aux_label);
        int i = 0;
        for (InstrList a = instrs ; a != null; a = (InstrList) a.tail)
		{
			if( a.head instanceof assem.OPER)
			{
				if( ((assem.OPER)a.head).jump != null)
				{
					temp.LabelList  aux2= ((assem.OPER)a.head).jump.labels;
					while(aux2 != null){
						/*if( aux2 == null)
						{
							System.out.println("TESTE3");
						}
						System.out.println(i);
						if(this.procurarNo(i) == null)
						{
							System.out.println("TESTE");
						}*/
						if(this.mapeamento.get(aux2.head) == null)
						{
							this.addEdge(this.procurarNo(i), ultimo);
							//System.out.println(aux2.head);
							//System.out.println("TESTE2");
						}
						else
						{
							this.addEdge(this.procurarNo(i), this.mapeamento.get(aux2.head));
							//this.addEdge(ultimo, n);
						}
						aux2 = aux2.tail;
					}
				}
				i++;
			}
			
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
