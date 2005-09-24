/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

/**
 * Uma carta de um baralho comum.
 * Num baralho comum, tem 52 cartas: 13 valores (AS, 2, 3, ..., 10, valete, dama, rei)
 * de 4 naipes (ouros, espadas, copas, paus).
 * Cartas podem ser criadas, comparadas (quanto a seu valor), etc.
 *
 */
public class Carta {
  /**
   * Valor da carta AS. Usado para construir uma carta: new Carta(Carta.AS, Carta.PAUS)
   */
  public static final int AS = 14;
  public static final int DOIS = 2;
  /**
   * Valor da carta VALETE. Usado para construir uma carta: new Carta(Carta.VALETE, Carta.PAUS)
   */
  public static final int VALETE = 11;
  /**
   * Valor da carta DAMA. Usado para construir uma carta: new Carta(Carta.DAMA, Carta.PAUS)
   */
  public static final int DAMA = 12;
  /**
   * Valor da carta REI. Usado para construir uma carta: new Carta(Carta.REI, Carta.PAUS)
   */
  public static final int REI = 13;

  /**
   * Valor do naipe de PAUS. Usado para construir uma carta: new Carta(Carta.AS, Carta.PAUS)
   */
  public static final int PAUS = 0;
  /**
   * Valor do naipe de OUROS. Usado para construir uma carta: new Carta(Carta.AS, Carta.OUROS)
   */
  public static final int OUROS = 1;
  /**
   * Valor do naipe de COPAS. Usado para construir uma carta: new Carta(Carta.AS, Carta.COPAS)
   */
  public static final int COPAS = 2;
  /**
   * Valor do naipe de ESPADAS. Usado para construir uma carta: new Carta(Carta.AS, Carta.ESPADAS)
   */
  public static final int ESPADAS = 3;

  protected int  valor;
  private int  naipe;
  private boolean viradaParaCima = true;

  /**
   * Construtor de uma carta comum.
   */
  public Carta(int valor, int naipe) {
    this.valor = valor;
    this.naipe = naipe;
  }

  /**
   * Recupera o valor da carta.
   */
  public int getValor() {
  	return this.valor;
	}

  /**
   * Recupera o naipe da carta.
   */
  public int getNaipe() {
    return naipe;
  }

  /**
   * Recupera o valor da menor carta deste tipo que pode ser criada.
   * É possível fazer um laço de menorValor() até maiorValor() para varrer
   * todos os valores possíveis de cartas.
   */
  public static int menorValor() {
      return DOIS;
  }

  /**
   * Recupera o valor da maior carta deste tipo que pode ser criada.
   * É possível fazer um laço de menorValor() até maiorValor() para varrer
   * todos os valores possíveis de cartas.
   */
  public static int maiorValor() {
      return AS;
  }

  /**
   * Recupera o "primeiro naipe" das cartas deste tipo.
   * Ser "primeiro naipe" não significa muita coisa, já que naipes não tem valor
   * (um naipe não é menor ou maior que o outro).
   * Fala-se de "primeiro naipe" e "último naipe" para poder
   * fazer um laço de primeiroNaipe() até últimoNaipe() para varrer
   * todos os naipes possíveis de cartas.
   * @return O primeiro naipe.
   */
  public static int primeiroNaipe() {
      return PAUS;
  }

  /**
   * Recupera o "último naipe" das cartas deste tipo.
   * Ser "último naipe" não significa muita coisa, já que naipes não tem valor
   * (um naipe não é menor ou maior que o outro).
   * Fala-se de "primeiro naipe" e "último naipe" para poder
   * fazer um laço de primeiroNaipe() até últimoNaipe() para varrer
   * todos os naipes possíveis de cartas.
   * @return O primeiro naipe.
   */
  public static int últimoNaipe() {
      return ESPADAS;
  }

  /**
   * Compare esta carta a outra.
   * @param outra A carta a comparar a esta.
   * @return Zero se forem iguais. Um valor < 0 se a carta for menor que a outra carta.
   * Um valor > 0 se a carta for maior que a outra carta.
   */
  public int compareTo(Carta outra) {
      return getValor() - outra.getValor();
  }

  /**
   * Testa a igualdade de um objeto com esta carta.
   * @param objeto O objeto a comparar com esta carta.
   * @return true se o objeto for igual a esta carta, false caso contrário.
   */
  public boolean equals(Object objeto) {
      if(! (objeto instanceof Carta)) {
          return false;
      }
      Carta outra = (Carta)objeto;
      return getValor() == outra.getValor()
              && getNaipe() == outra.getNaipe();
  }

  
  public void viraCartaParaCima(){
  	this.viradaParaCima = true;
  }
  
  public void viraCartaParaBaixo(){
  	this.viradaParaCima = false;
  }
  
  public boolean viradaPraCima(){
  	return this.viradaParaCima;
  }
  
  
}

