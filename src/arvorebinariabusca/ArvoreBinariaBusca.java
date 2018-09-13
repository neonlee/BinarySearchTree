package arvorebinariabusca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinariaBusca<K extends Comparable<K>, V> implements ABB<K, V> {

    private Node<K, V> raiz;
    private int tamanho;
    private ArrayList<V> collection;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    @Override
    public V inserir(K chave, V valor) {
        Node<K, V> novoNo = new Node<>(chave, valor);
        if (raiz == null) {
            raiz = novoNo;
        } else {
            Node<K, V> aux = raiz;
            while (aux.getChave() != null || !aux.getChave().equals(chave)) {
                if (chave.compareTo(aux.getChave()) > 0 && aux.getFilhoDireito() == null) {
                    aux.setFilhoDireito(novoNo);
                    novoNo.setPai(aux);
                    break;
                } else if (chave.compareTo(aux.getChave()) < 0 && aux.getFilhoEsquerdo() == null) {
                    aux.setFilhoEsquerdo(novoNo);
                    novoNo.setPai(aux);
                    break;
                }

                if (chave.equals(aux.getChave())) {
                    aux = novoNo;
                    novoNo.setPai(aux.getPai());
                    break;
                } else if (chave.compareTo(aux.getChave()) > 0) {
                    aux = aux.getFilhoDireito();
                } else {
                    aux = aux.getFilhoEsquerdo();
                }
            }
        }
        tamanho++;

        return novoNo.getValor();
    }

    @Override
    public V buscar(K chave) {
        Node<K, V> no = obterNo(chave);
        return no.getValor();
    }

    @Override
    public V remover(K chave) {
        Node<K, V> no = obterNo(chave);
        if (no == null) {
            return null;
        } else {
            //remocao do no folha
            if (no.getFilhoDireito() == null && no.getFilhoEsquerdo() == null) {
                if (no.getPai().getFilhoDireito() != null) {
                    no.getPai().setFilhoDireito(null);
                } else if (no.getPai().getFilhoEsquerdo() != null) {
                    no.getPai().setFilhoEsquerdo(null);
                }
            }
            //remocao do no pai, ligando filho-avo
            if (no.getFilhoDireito() != null && no.getFilhoEsquerdo() == null) {
                //quando a raiz so possui e no folha sendo ele do lado direito
                if (no == raiz) {
                    raiz = no.getFilhoDireito();
                } else {
                    //para qualquer no
                    no.getPai().setFilhoDireito(no.getFilhoDireito());
                }
                no.setFilhoDireito(null);
            }
            if (no.getFilhoEsquerdo() != null && no.getFilhoDireito() == null) {
                //quando a raiz so possui e no folha sendo ele do lado esquerdo
                if (no == raiz) {
                    raiz = no.getFilhoEsquerdo();
                } else {
                    //para qualquer no
                    no.getPai().setFilhoEsquerdo(no.getFilhoEsquerdo());
                }
                no.setFilhoEsquerdo(null);
            }
            //remocao com dois nos filhos
            if (no.getFilhoDireito() != null && no.getFilhoEsquerdo() != null) {
                Node<K, V> consec = no.getFilhoDireito();
                while (consec.getFilhoEsquerdo() != null) {
                    consec = consec.getFilhoEsquerdo();
                }
                K k = consec.getChave();
                V v = consec.getValor();
                remover(consec.getChave());
                no.setChave(k);
                no.setValor(v);
            }
            tamanho--;
        }
        return no.getValor();
    }

    @Override
    public void limpar() {
        this.raiz = null;
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public boolean contem(K chave) {
        V no = buscar(chave);
        return no != null;
    }

    @Override
    public Iterator<V> iterator() {
        ArvoreIterator<K, V> it = new ArvoreIterator<>(this);
        return (Iterator<V>) it;
    }

    public Node<K, V> getRaiz() {
        return raiz;
    }

    @Override
    public ArrayList<V> obterTodos() {
        return this.obterTodosEmOrdem();
    }

    @Override
    public ArrayList<V> obterTodosPreOrdem() {
        this.collection.clear();

        this.preOrdem(this.raiz);

        ArrayList<V> listaRetorno = this.collection;

        return listaRetorno;
    }

    @Override
    public ArrayList<V> obterTodosPosOrdem() {
        this.collection.clear();

        this.posOrdem(this.raiz);

        ArrayList<V> listaRetorno = this.collection;

        return listaRetorno;
    }

    @Override
    public ArrayList<V> obterTodosEmOrdem() {
        this.collection.clear();

        this.emOrdem(this.raiz);

        ArrayList<V> listaRetorno = this.collection;

        return listaRetorno;
    }

    @Override
    public ArrayList<V> navegacaoLargura() {

        if (raiz == null) {
            return null;
        }

        this.collection.clear();
        Queue<Node<K, V>> fila = new LinkedList<>();

        Node<K, V> noCorrente = this.raiz;

        fila.add(noCorrente);

        while (!fila.isEmpty()) {

            noCorrente = fila.poll();

            this.collection.add(noCorrente.getValor());

            if (noCorrente.getFilhoEsquerdo() != null) {
                fila.add(noCorrente.getFilhoEsquerdo());
            }

            if (noCorrente.getFilhoDireito() != null) {
                fila.add(noCorrente.getFilhoDireito());
            }

        }

        return this.collection;

    }

    private Node<K, V> obterNo(K chave) {
        Node<K, V> no = raiz;
        if (no == null) {
            return null;
        }
        while (no != null && !chave.equals(no.getChave())) {
            if (chave.compareTo(no.getChave()) > 0) {
                no = no.getFilhoDireito();
            } else if (chave.compareTo(no.getChave()) < 0) {
                no = no.getFilhoEsquerdo();
            }
        }
        return no;
    }

    private void preOrdem(Node<K, V> no) {

        if (no == null) {
            return;
        }

        collection.add(no.getValor());

        if (no.getFilhoEsquerdo() != null) {
            this.preOrdem(no.getFilhoEsquerdo());
        }

        if (no.getFilhoDireito() != null) {
            this.preOrdem(no.getFilhoDireito());
        }

    }

    private void posOrdem(Node<K, V> no) {
        if (no == null) {
            return;
        }

        if (no.getFilhoEsquerdo() != null) {
            this.posOrdem(no.getFilhoEsquerdo());
        }

        if (no.getFilhoDireito() != null) {
            this.posOrdem(no.getFilhoDireito());
        }

        this.collection.add(no.getValor());
    }

    private void emOrdem(Node<K, V> no) {

        if (no == null) {
            return;
        }

        if (no.getFilhoEsquerdo() != null) {
            this.emOrdem(no.getFilhoEsquerdo());
        }

        this.collection.add(no.getValor());

        if (no.getFilhoDireito() != null) {
            this.emOrdem(no.getFilhoDireito());
        }

    }

}
