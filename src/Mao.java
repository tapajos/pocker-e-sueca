/*
 * DCC - Departamento de Ci�ncia da Computa��o
 *  Marcos Tapaj�s - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.util.*;

/**
 * Uma m�o de cartas.
*/

public class Mao {
	public Vector cartas;
    /**
     * Construtor de uma m�o (vazia) de cartas.
     */
	public Mao() {
        // Usa um Vector para ter um iterador facilmente
		cartas = new Vector();
    }

    /**
     * Recupera o n�mero de cartas na m�o.
     * @return O n�mero de cartas na m�o.
     */
    public int n�meroDeCartas() {
        return cartas.size();
    }

    /**
     * Recupera um iterador para poder varrer todas as cartas da m�o.
     * @return Um iterador da m�o.
     */
    public Iterator iterator() {
        return cartas.iterator();
    }

    /**
     * Calcula o valor da m�o. O valor da m�o vai depender normalmente
     * de todas as cartas que tem nela. O valor absoluto da m�o poder�
     * ter algum significado ou n�o, dependendo do jogo em particular.
     * O mais importante � que duas m�os podem ser comparadas de acordo com este valor.
     * @return O valor da m�o.
     */
    public int valor() {
        Iterator it = iterator();
        int     resultado = 0;
        while(it.hasNext()) {
            resultado += ((Carta)it.next()).getValor();
        }
        return resultado;
    }

    /**
     * Adiciona uma carta � m�o.
     * @param carta A carta a adicionar.
     */
	public void adicionar(Carta carta) {
		cartas.add(carta);
	}

    /**
     * Recupera a carta que est� numa determinada posi��o na m�o.
     * A carta mais � esquerda � de posi��o 0, e esta posi��o
     * cresce para as cartas � direita.
     * A carta permanece na m�o ap�s este m�todo.
     * @param posi��o A posi��o da carta desejada.
     * @return A carta na posi��o desejada.
     */
    public Carta cartaEm(int posi��o) {
        return posi��o < n�meroDeCartas() ? (Carta)cartas.get(posi��o) : null;
    }

    /**
     * Recupera a carta que est� "no topo" da m�o (isto �, a carta mais � direita).
     * @return A carta desejada.
     */
    public Carta cartaNoTopo() {
        return cartaEm(n�meroDeCartas()-1);
    }

    /**
     * Remove a carta que est� numa determinada posi��o na m�o.
     * A carta mais � esquerda � de posi��o 0, e esta posi��o
     * cresce para as cartas � direita.
     * @param posi��o A posi��o da carta desejada.
     * @return A carta desejada.
     */
    public Carta remover(int i) {
        return i < n�meroDeCartas() ? (Carta)cartas.remove(i) : null;
    }

    /**
     * Remove uma determinada carta da m�o.
     * @param carta A carta a ser removida da m�o.
     * @return true se a carta foi removida, false caso contr�rio.
     */
    public boolean remover(Carta carta) {
        return cartas.remove(carta);
    }

    /**
     * Troca duas cartas da m�o, dadas duas posi��es na m�o.
     * A carta mais � esquerda � de posi��o 0, e esta posi��o
     * cresce para as cartas � direita.
     * @param i A posi��o da primeira carta a trocar.
     * @param j A posi��o da segunda carta a trocar.
     */
    public void trocar(int i, int j) {
        int n = n�meroDeCartas();
        if(i < n && j < n) {
            Carta temp = (Carta)cartas.get(i);
			cartas.set(i, cartas.get(j));
			cartas.set(j, temp);
        }
    }

    /**
     * Troca duas cartas da m�o.
     * @param c1 A primeira carta a trocar.
     * @param c2 A segunda carta a trocar.
     */
    public void trocar(Carta c1, Carta c2) {
        int i1 = cartas.indexOf(c1);
        if(i1 >= 0) {
            int i2 = cartas.indexOf(c2);
            if(i2 >= 0) {
                trocar(i1, i2);
            }
        }
    }

    /**
     * Compare esta m�o a outra de acordo com seu respectivo valor.
     * @param outra A outra m�o a comparar.
     * @return 0 se as m�os forem de mesmo valor;
     * um valor < 0 se esta m�o for de valor menor; e
     * um valor > 0 se esta m�o for de valor menor.
     */
    public int compareTo(Mao outra) {
        return valor() - outra.valor();
    }

    /**
     * Formata a m�o como string.
     * @return Um string que represente a m�o de cartas.
     */
    public String toString() {
        Iterator it = iterator();
        String resultado = "";
        String separador = "";
        while(it.hasNext()) {
            resultado += separador + (Carta)it.next();
            separador = ", ";
        }
        return resultado;
    }
    
    public void ordenaMao(){
    	Collections.sort(this.cartas, new comparaCartas());
    }
    
    public void ViraCartaSuecaParaCima(int indice){
    	((CartaSueca)this.cartas.elementAt(indice)).viraCartaParaCima();
    }
    public void ViraCartaSuecaParaBaixo(int indice){
    	((CartaSueca)this.cartas.elementAt(indice)).viraCartaParaBaixo();
    }
    
    public void ViraCartaPoquerParaCima(int indice){
    	((CartaPoquer)this.cartas.elementAt(indice)).viraCartaParaCima();
    }
    public void ViraCartaPoquerParaBaixo(int indice){
    	((CartaPoquer)this.cartas.elementAt(indice)).viraCartaParaBaixo();
    }
    
    public int quantasCartasNaMao(){
    	return this.cartas.size();
    }
    
}
