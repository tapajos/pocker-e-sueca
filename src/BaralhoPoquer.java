/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

/**
 * Um baralho para jogar poquer.
 * Num baralho de poquer, tem 36 cartas: 9 valores (6, 7, 8, 9, 10, valete, dama, rei, as)
 * de 4 naipes (ouros, espadas, copas, paus).
 *
 * 
 */
public class BaralhoPoquer extends Baralho {
    // Factory Method permite criar um novo baralho (com cartas diferentes)
    // sem mudar o cliente do baralho
	
    protected Carta criaCarta(int valor, int naipe) {
        return new CartaPoquer(valor, naipe);
    }

    public int menorValor() {
        return CartaPoquer.menorValor();
    }

    public int maiorValor() {
        return CartaPoquer.maiorValor();
    }
    public void DevolveProBaralho (CartaPoquer carta){
    	this.baralho.add(0, carta);		  	
    }

    


}
