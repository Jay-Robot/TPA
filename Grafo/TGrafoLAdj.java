package Grafo;


import Hash.TabH.TDic;
import Hash.tadDicionario.Documento.ArquivoTxt;
import Hash.tadDicionario.TDicChain;

import java.util.LinkedList;

public class TGrafoLAdj extends Grafo {
    protected int globalVertexID = 0;
    protected int globalEdgeID = 0;
    protected TDic dicEdges;
    protected TDic dicVertexes;

    public TGrafoLAdj() {
        dicEdges = new TDicChain();
        dicVertexes = new TDicChain();
    }


    @Override
    public int numVertices() {
        return dicVertexes.size();
    }

    @Override
    public LinkedList<Vertex> vertices() {
        LinkedList<Vertex> lstVertices = new LinkedList<Vertex>();

        for (int i=0; i<dicVertexes.elements().size(); i++){
            Vertex vertex = (Vertex) dicVertexes.elements().get(i);
            lstVertices.add(vertex);
        }
        return lstVertices;
    }

    @Override
    public int numEdges() {
        return dicEdges.size();
    }

    @Override
    public LinkedList<Edge> edges() {
        LinkedList<Edge> lstEdges = new LinkedList<Edge>();

        for (int i=0; i<dicEdges.elements().size(); i++){
            Edge edge = (Edge) dicEdges.elements().get(i);
            lstEdges.add(edge);
        }
        return lstEdges;
    }

    @Override
    public Edge getEdge(String vertex1, String vertex2) {

        VertexLAd vertexObj1 = findVertices(vertex1);
        VertexLAd vertexObj2 = findVertices(vertex2);

        if(vertexObj1 != null && vertexObj2 != null)  {

            for (EdgeLAd edgeLad : vertexObj1.getEdges()) {
                if (edgeLad.isEndPoint(vertexObj2)) {
                    return edgeLad;
                }
            }
        }

        return null;
    }

    private VertexLAd findVertices(String vertex)
    {
        for (VertexLAd vertice : (LinkedList<VertexLAd>)dicVertexes.elements()) {
            if(vertice.getLabel().equals(vertex))
            {
                return vertice;
            }
        }

        return null;
    }

    private EdgeLAd findEdges(String edge)
    {
        for (EdgeLAd edgeLad : (LinkedList<EdgeLAd>)dicEdges.elements()) {
            if(edgeLad.getLabel().equals(edge))
            {
                return edgeLad;
            }
        }

        return null;
    }

    @Override
    public String[] endVerticesString(String edge) {
        EdgeLAd edgeLad = findEdges(edge);
        return new String[] { edgeLad.getOrigem().getLabel(),edgeLad.getDestino().getLabel()};
    }

    @Override
    public LinkedList<Vertex> endVertices(Edge edge) {
        EdgeLAd edgeLad = (EdgeLAd)dicEdges.findElement(edge.getId());
        LinkedList<Vertex> lst = new LinkedList<>();
        lst.add(edgeLad.getOrigem());
        lst.add(edgeLad.getDestino());


        return lst;
    }

    @Override
    public LinkedList<Vertex> endVertices(String edge) {

        EdgeLAd edgeLad = findEdges(edge);

        LinkedList<Vertex> lst = new LinkedList<>();
        lst.add(edgeLad.getOrigem());
        lst.add(edgeLad.getDestino());
        return lst;
    }



    @Override
    public String opossite(String vertex, String edge) {
        VertexLAd vertexLad = findVertices(vertex);
        EdgeLAd edgeLad = findEdges(edge);

        for (EdgeLAd edgeObj : vertexLad.getEdges()) {
            if(edgeObj == edgeLad)
            {
                return edgeObj.meuOpposite(vertexLad).getLabel();
            }
        }

        return null;
    }

    public Vertex opossite(Vertex vertex, Edge edge) {
        VertexLAd vertexLad = (VertexLAd) vertex;
        EdgeLAd edgeLad = (EdgeLAd)edge;

        for (EdgeLAd edgeObj : vertexLad.getEdges()) {
            if(edgeObj == edgeLad)
            {
                return edgeObj.meuOpposite(vertexLad);
            }
        }

        return null;
    }

    @Override
    public Vertex insertVertex(Object dado) {
        Integer id = generateVertexId();
        String label = id.toString();

        VertexLAd vertexLad = new VertexLAd(id,label, dado);

        dicVertexes.insertItem(id,vertexLad);

        return vertexLad;
    }

    @Override
    public Vertex insertVertex(Object dado, String label) {
        Integer id = generateVertexId();

        VertexLAd vertexLad = new VertexLAd(id,label, dado);

        dicVertexes.insertItem(id,vertexLad);

        return vertexLad;
    }

    @Override
    public Edge insertEdge(Vertex vertice1, Vertex vertice2, Object dado) {
        VertexLAd vertexLad1 = (VertexLAd) dicVertexes.findElement(vertice1.getId());
        VertexLAd vertexLad2 = (VertexLAd) dicVertexes.findElement(vertice2.getId());

        Integer id = globalEdgeID++;
        String label = id.toString();

        EdgeLAd edgeLad = new EdgeLAd(id,label,dado,vertexLad2,vertexLad1);
        vertexLad1.addEdgeIN(edgeLad);
        vertexLad2.addEdgeOUT(edgeLad);

        dicEdges.insertItem(id,edgeLad);

        return edgeLad;
    }

    @Override
    public Edge insertEdge(Vertex vertice1, Vertex vertice2, Object dado, String label) {
        VertexLAd vertexLad1 = (VertexLAd) dicVertexes.findElement(vertice1.getId());
        VertexLAd vertexLad2 = (VertexLAd) dicVertexes.findElement(vertice2.getId());

        Integer id = globalEdgeID++;

        EdgeLAd edgeLad = new EdgeLAd(id,label,dado,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIN(edgeLad);
        vertexLad2.addEdgeOUT(edgeLad);

        dicEdges.insertItem(id,edgeLad);

        return edgeLad;
    }

    @Override
    public Object removeVertex(Vertex vertice) {

        VertexLAd verticeLad = ((VertexLAd)vertice);
        LinkedList<EdgeLAd> edgesLad = verticeLad.getEdges();
        for (Edge edge: edgesLad) {
            Edge edgeFind = (Edge)dicEdges.findElement(edge.getId());
            if( edgeFind != null)
            {
                removeEdge(edge);
            }
        }

        return this.dicVertexes.removeElement(vertice.getId());
    }

    @Override
    public Object removeEdge(Edge edge) {
        return dicEdges.removeElement(edge.getId());
    }

    @Override
    public int degree(Vertex vertice)
    {
        return ((VertexLAd) vertice).meuDegree();
    }

    @Override
    public boolean areAdjacent(Vertex vertice1, Vertex vertice2)
    {
        for (EdgeLAd edge : ((VertexLAd) vertice1).getEdges()) {
            if(edge.isEndPoint(((VertexLAd) vertice2)))
            {
                return true;
            }
        }
        return false;
    }

    public int degree(VertexLAd vertex)
    {
        return vertex.meuDegree();
    }

    public boolean areaAdjacent(VertexLAd vertex1, VertexLAd vertex2)
    {
        for (EdgeLAd edge : vertex1.getEdges()) {
            if(edge.isEndPoint(vertex2))
            {
                return true;
            }
        }
        return false;
    }

    protected int generateVertexId(){
        return globalVertexID++;
    }

    /*
    *  Exemplo de uso:
    *  TGrafoLAdj g = TGrafoLAdj.carrega("nomeArqTGF.txt");
    * */
    protected static void carregaGenerico(TGrafoLAdj  grafo, ArquivoTxt arq){
        /* lendo os vertices */
        String linha = arq.readline();
        while (!linha.trim().equals("#")){
            String[] vector = linha.split(" ", 2);
            if(vector.length>1) {
                grafo.insertVertex(null, vector[1].trim());
            }

            linha = arq.readline();
        }

        /* lendo as arestas */
        linha = arq.readline();
        while (linha!= null){
            String[] edges = linha.split(" ", 3);

            int idVertex1 = Integer.parseInt(edges[0].trim()) - 1;
            int idVertex2 = Integer.parseInt(edges[1].trim()) - 1;

            Vertex vertice1 = (Vertex) grafo.dicVertexes.findElement(idVertex1);
            Vertex vertice2 = (Vertex) grafo.dicVertexes.findElement(idVertex2);

            String label="";

            if(edges.length == 3) {
                label = (edges[2].trim());
            }
            if(label.equals("")) {
                label = ("@#" + (grafo.globalEdgeID+1));
            }

            grafo.insertEdge(vertice1, vertice2, null, label);

            linha = arq.readline();
        }

    }


}
