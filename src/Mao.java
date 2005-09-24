/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.util.*;

/**
 * Uma mão de cartas.
*/

public class Mao {
	public Vector cartas;
    /**
     * Construtor de uma mão (vazia) de cartas.
     */
	public Mao() {
        // Usa um Vector para ter um iterador facilmente
		cartas = new Vector();
    }

    /**
     * Recupera o número de cartas na mão.
     * @return O número de cartas na mão.
     */
    public int númeroDeCartas() {
        return cartas.size();
    }

    /**
     * Recupera um iterador para poder varrer todas as cartas da mão.
     * @return Um iterador da mão.
     */
    public Iterator iterator() {
        return cartas.iterator();
    }

    /**
     * Calcula o valor da mão. O valor da mão vai depender normalmente
     * de todas as cartas que tem nela. O valor absoluto da mão poderá
     * ter algum significado ou não, dependendo do jogo em particular.
     * O mais importante é que duas mãos podem ser comparadas de acordo com este valor.
     * @return O valor da mão.
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
     * Adiciona uma carta à mão.
     * @param carta A carta a adicionar.
     */
	public void adicionar(Carta carta) {
		cartas.add(carta);
	}

    /**
     * Recupera a carta que está numa determinada posição na mão.
     * A carta mais à esquerda é de posição 0, e esta posição
     * cresce para as cartas à direita.
     * A carta permanece na mão após este método.
     * @param posição A posição da carta desejada.
     * @return A carta na posição desejada.
     */
    public Carta cartaEm(int posição) {
        return posição < númeroDeCartas() ? (Carta)cartas.get(posição) : null;
    }

    /**
     * Recupera a carta que está "no topo" da mão (isto é, a carta mais à direita).
     * @return A carta desejada.
     */
    public Carta cartaNoTopo() {
        return cartaEm(númeroDeCartas()-1);
    }

    /**
     * Remove a carta que está numa determinada posição na mão.
     * A carta mais à esquerda é de posição 0, e esta posição
     * cresce para as cartas à direita.
     * @param posição A posição da carta desejada.
     * @return A carta desejada.
     */
    public Carta remover(int i) {
        return i < númeroDeCartas() ? (Carta)cartas.remove(i) : null;
    }

    /**
     * Remove uma determinada carta da mão.
     * @param carta A carta a ser removida da mão.
     * @return true se a carta foi removida, false caso contrário.
     */
    public boolean remover(Carta carta) {
        return cartas.remove(carta);
    }

    /**
     * Troca duas cartas da mão, dadas duas posições na mão.
     * A carta mais à esquerda é de posição 0, e esta posição
     * cresce para as cartas à direita.
     * @param i A posição da primeira carta a trocar.
     * @param j A posição da segunda carta a trocar.
     */
    public void trocar(int i, int j) {
        int n = númeroDeCartas();
        if(i < n && j < n) {
            Carta temp = (Carta)cartas.get(i);
			cartas.set(i, cartas.get(j));
			cartas.set(j, temp);
        }
    }

    /**
     * Troca duas cartas da mão.
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
     * Compare esta mão a outra de acordo com seu respectivo valor.
     * @param outra A outra mão a comparar.
     * @return 0 se as mãos forem de mesmo valor;
     * um valor < 0 se esta mão for de valor menor; e
     * um valor > 0 se esta mão for de valor menor.
     */
    public int compareTo(Mao outra) {
        return valor() - outra.valor();
    }

    /**
     * Formata a mão como string.
     * @return Um string que represente a mão de cartas.
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
