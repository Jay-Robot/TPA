package Grafo;

import Hash.TabH.TDic;
import Hash.tadDicionario.Documento.ArquivoTxt;
import Hash.tadDicionario.TDicChain;
import java.util.LinkedList;

public class TGrafoDDLAdj  extends TGrafoLAdj implements TGrafoDD {

    /*
    private LinkedList<Edge> edges;                                         // lista de arestas adj
    private LinkedList<Vertex> vertices;                                   // lista de vertices adj

    private TDic dicEdges = new TDicChain();
    private TDic dicVertexes = new TDicChain();
    */
    
       @Override
    public int outDegree(Vertex vertice) {
        VertexLAd  vertexLad = (VertexLAd) this.dicVertexes.findElement(vertice.getId());
        return vertexLad.meuOUTDegree();
    }

    @Override
    public int inDegree(Vertex vertice) {
        VertexLAd  vertexLad = (VertexLAd) this.dicVertexes.findElement(vertice.getId());
        return vertexLad.meuINDegree();
    }

    @Override
    public LinkedList<Edge> inIncidentEdges(Vertex vertice) {
        VertexLAd  vertexLad = (VertexLAd) this.dicVertexes.findElement(vertice.getId());
        return (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesIN();
    }

    @Override
    public LinkedList<Edge> outIncidentEdges(Vertex vertice) {
        VertexLAd  vertexLad = (VertexLAd) this.dicVertexes.findElement(vertice.getId());
        return (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesOUT();
    }

    @Override
    public LinkedList<Edge> incidentEdges(Vertex vertice) {
        LinkedList<Edge> edges = inIncidentEdges(vertice);
        edges.addAll(outIncidentEdges(vertice));

        return edges;
    }

    @Override
    public LinkedList<Vertex> inAdjacentVertices(Vertex vertice) {
        VertexLAd  vertexLad = (VertexLAd) this.dicVertexes.findElement(vertice.getId());
        LinkedList<Vertex> lst = new LinkedList<>();

        for ( EdgeLAd  edgeLad: vertexLad.getEdgesIN()) {
            lst.add(edgeLad.meuOpposite(vertexLad));
        }

        return lst;
    }

    @Override
    public LinkedList<Vertex> outAdjacentVertices(Vertex vertice) {
        VertexLAd  vertexLad = (VertexLAd) this.dicVertexes.findElement(vertice.getId());
        LinkedList<Vertex> lst = new LinkedList<>();

        for ( EdgeLAd  edgeLad: vertexLad.getEdgesOUT()) {
            lst.add(edgeLad.meuOpposite(vertexLad));
        }

        return lst;
    }

    @Override
    public LinkedList<Vertex> adjacentVertices(Vertex vertice) {
        LinkedList<Vertex> edges = inAdjacentVertices(vertice);
        edges.addAll(outAdjacentVertices(vertice));

        return edges;
    }

    @Override
    public Vertex destination(Edge edge) {
        EdgeLAd  edgeLad = (EdgeLAd)dicEdges.findElement(edge.getId());

        return edgeLad.getDestino();
    }

    @Override
    public Vertex origin(Edge edge) {
        EdgeLAd  edgeLad = (EdgeLAd)dicEdges.findElement(edge.getId());

        return edgeLad.getOrigem();
    }

    public static TGrafoDDLAdj carrega(String nome_arq_TGF){
        TGrafoDDLAdj grafo = new TGrafoDDLAdj();
        
        ArquivoTxt arq = ArquivoTxt.open(nome_arq_TGF, "rt");

        assert arq != null;
        carregaGenerico(grafo,arq);

        arq.close();

        return grafo;

    }
}
