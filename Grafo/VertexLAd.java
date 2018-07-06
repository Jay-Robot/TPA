package Grafo;

import Hash.tadDicionario.TDicChain;
import Hash.TabH.TDic;

import java.util.LinkedList;

public class VertexLAd extends Vertex {

    private TDic dicEdgesIN = new TDicChain();
    private TDic dicEdgesOUT = new TDicChain();




    public VertexLAd(Object d) {
        super(d);
    }
    
    public VertexLAd(int id, String label, Object dado){
        super(id, label, dado);
    }
    

    public int meuINDegree(){
        return dicEdgesIN.size();
    }

    public int meuOUTDegree(){
        return dicEdgesOUT.size();
    }

    public int meuDegree(){
        return meuINDegree() + meuOUTDegree();
    }

    public LinkedList<EdgeLAd> getEdgesIN(){
        LinkedList<Object> lstIN = dicEdgesIN.keys();
        LinkedList<EdgeLAd>lstEdge = new LinkedList<EdgeLAd>();

        for(int i = 0 ; i <lstIN.size(); i++){
            lstEdge.add((EdgeLAd)dicEdgesIN.findElement(lstIN.get(i)));
        }

        return lstEdge;

    }

    public LinkedList<EdgeLAd> getEdgesOUT(){
        LinkedList<Object> lstOUT = dicEdgesOUT.keys();
        LinkedList<EdgeLAd>lstEdge = new LinkedList<EdgeLAd>();

        for(int i = 0 ; i <lstOUT.size(); i++){
            //EdgeLAd edgeLAd = new EdgeLAd();

            lstEdge.add((EdgeLAd)dicEdgesOUT.findElement(lstOUT.get(i)));
        }

        return lstEdge;

    }

    public LinkedList<EdgeLAd> getEdges(){
        LinkedList<EdgeLAd> lstEdgeOUT = this.getEdgesOUT();
        LinkedList<EdgeLAd> lstEdgeIN = this.getEdgesIN();
        LinkedList<EdgeLAd>lstEdge = new LinkedList<EdgeLAd>();

        lstEdge.addAll(lstEdgeIN);
        lstEdge.addAll(lstEdgeOUT);

        return lstEdge;

    }

    public EdgeLAd addEdgeIN(EdgeLAd e){
        dicEdgesIN.insertItem(e.getLabel(),e);
        return e;
    }

    public EdgeLAd addEdgeOUT(EdgeLAd e){
        dicEdgesOUT.insertItem(e.getLabel(),e);
        return e;
    }


    public boolean isEndPoint(EdgeLAd e){
        boolean resposta = (dicEdgesIN.findElement(e.getLabel()) != null) || (dicEdgesOUT.findElement(e.getLabel())!=null);

        return resposta;
    }

    public boolean isOrigem(EdgeLAd e){
        boolean resposta = (dicEdgesOUT.findElement(e.getLabel())!=null);

        return resposta;
    }

    public boolean isDestiny(EdgeLAd e){
        boolean resposta = (dicEdgesIN.findElement(e.getLabel())!=null);

        return resposta;
    }
}
