package Grafo;

public class Vertex{
    private int id;
    private String label;
    private Object dado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }

    public Vertex(Object dado){
        this.setDado(d);
    }

    public Vertex(int id, String label, Object dado){
        this.id = id;
        this.label= label;
        this.dado = dado;
    }
    
    /*
    @Override
    public String toString() {
        return "ID: " + id + "; " + "Label: " + label + "; " + "Value: " + dado;
    }
    */


}
