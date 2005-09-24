/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

/**
 * Uma carta de um baralho para jogar poquer.
 * Uma diferença com uma carta comum é que o AS vale mais que o Rei no poquer.
 * A outra diferença é que cartas de poquer não podem ter valor 2, 3, 4, 5.
 *
 * 
 */
public class CartaPoquer extends Carta {
    /**
     * Valor da carta AS. Usado para construir uma carta: new Carta(Carta.AS, Carta.PAUS)
     * O AS no poquer vale mais que o REI
     */
	//public static final int AS = 14;

    /**
     * Construtor de uma carta de poquer
     * @param valor O valor da carta (6, 7, 8, 9, 10, VALETE, DAMA, REI, AS).
     * @param naipe O naipe da carta (PAUS, OUROS, COPAS, ESPADAS).
     */
	public CartaPoquer(int valor, int naipe) {
        super(valor, naipe);
	}

  	

    public static int menorValor() {
        return 6;
    }

    public static int maiorValor() {
        return AS;
    }

    	
	public int getValor() {
	  	switch ( this.valor){
	  	    case AS:return 1;   			
		    case VALETE: return 11;  				
		    case REI: return 13;		   		
		   	case DAMA: return 12;
		    default: return this.valor;
		}
	}
}
