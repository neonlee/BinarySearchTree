package arvorebinariabusca;

public class Node<Chave extends Comparable<Chave>, Valor> {

    private Chave chave;
    private Valor valor;
    private Node<Chave, Valor> pai;
    private Node<Chave, Valor> filhoEsquerdo;
    private Node<Chave, Valor> filhoDireito;

    public Node() {
    }

    public Node(Chave chave, Valor valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public Node(Chave chave, Valor valor, Node<Chave, Valor> pai) {
        this.chave = chave;
        this.valor = valor;
        this.pai = pai;
    }

    
    public Node(Chave chave, Valor valor, Node<Chave, Valor> pai, Node<Chave, Valor> filhoEsquerdo, Node<Chave, Valor> filhoDireito) {
        this.chave = chave;
        this.valor = valor;
        this.pai = pai;
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public Node<Chave, Valor> getPai() {
        return pai;
    }

    public void setPai(Node<Chave, Valor> pai) {
        this.pai = pai;
    }

    public Node<Chave, Valor> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(Node<Chave, Valor> filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public Node<Chave, Valor> getFilhoDireito() {
        return filhoDireito;
    }

    public void setFilhoDireito(Node<Chave, Valor> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }

}
