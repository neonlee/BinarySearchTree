package arvorebinariabusca;

import java.util.ArrayList;

public interface ABB<K extends Comparable<K>, V> extends Iterable<V> {

    public V inserir(K chave, V valor);

    public V buscar(K chave);

    public V remover(K chave);

    public void limpar();

    public int tamanho();

    public boolean contem(K chave);

    public ArrayList<V> obterTodos();

    public ArrayList<V> obterTodosPreOrdem();

    public ArrayList<V> obterTodosPosOrdem();

    public ArrayList<V> obterTodosEmOrdem();

    public ArrayList<V> navegacaoLargura();
}
