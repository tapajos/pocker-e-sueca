/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.util.Vector;

/**
 * Um baralho para jogar Sueca.
 *  
 */


public class BaralhoSueca extends Baralho {
	
	public BaralhoSueca(){
		baralho = new Vector();
		// enche o baralho
		for(int valor = 1; valor <= 7; valor++) {
			for(int naipe = primeiroNaipe(); naipe <= últimoNaipe(); naipe++) {
		    // chama criaCarta e não "new" para poder fazer override
		    // de criaCarta em baralhos de subclasses e
		    // criar classes diferentes.
		    baralho.add(criaCarta(valor, naipe));
		    }
		}
		for(int valor = 11; valor <= 13; valor++) {
			for(int naipe = primeiroNaipe(); naipe <= últimoNaipe(); naipe++) {
		    baralho.add(criaCarta(valor, naipe));
		    }
		}
		
		
	}
	 
    protected Carta criaCarta(int valor, int naipe) {
        return new CartaSueca(valor, naipe);
    }

    public int menorValor() {
        return CartaSueca.menorValor();
    }

        public int maiorValor() {
        return CartaSueca.maiorValor();
    }
    public Carta pegaUltimaCarta() throws BaralhoVazioException {
         if(númeroDeCartas() == 0) {
         	throw new BaralhoVazioException("Baralho esta vazio");
         }
         return (Carta)baralho.remove(0);
    }
    
    public void recebeCarta(CartaSueca carta){
    	this.baralho.add(carta);
    }
}
