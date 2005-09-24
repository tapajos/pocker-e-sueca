/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

/**
 * Uma carta de um baralho para jogar sueca.
 *  
 */
public class CartaSueca extends Carta {
    
	public static final int AS = 1;
	public static final int VALETE = 11;
	public static final int RAINHA = 12;
	public static final int REI = 13;
	
	

    /**
     * Construtor de uma carta de poquer
     * @param valor O valor da carta (2,3,4,5,6, VALETE, DAMA, REI,SETE, AS).
     * @param naipe O naipe da carta (PAUS, OUROS, COPAS, ESPADAS).
     */
	public CartaSueca(int valor, int naipe) {
        super(valor, naipe);
	}

    public static int menorValor() {
        return DOIS;
    }

    public static int maiorValor() {
        return AS;
    }


	public int getPonto() {
	  	switch ( this.valor){
	  	    case AS:return 11;
	  	    case 7:return 10;
		    case REI: return 4;		   		
		    case VALETE: return 3;
		    case DAMA: return 2;
		    default: return 0;
		}
	}
}
