/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinariabusca;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreIterator<K, V> implements Iterator<V> {

    private Queue<Node<? super K, V>> fila;

    public ArvoreIterator(ArvoreBinariaBusca<? super K, V> arvore) {
        this.fila = new LinkedList<Node<? super K, V>>();
        fila.add(arvore.getRaiz());
    }

    @Override
    public boolean hasNext() {
        return !fila.isEmpty();
    }

    @Override
    public V next() {
        Node<? super K, V> no = fila.poll();
        if (no != null) {
            if (no.getFilhoEsquerdo() != null) {
                fila.add(no.getFilhoEsquerdo());
            }
            if (no.getFilhoDireito() != null) {
                fila.add(no.getFilhoDireito());
            }
            return no.getValor();
        }
        return null;
    }
}
