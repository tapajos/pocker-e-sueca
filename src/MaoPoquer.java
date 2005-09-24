/*
 * DCC - Departamento de Ci�ncia da Computa��o
 *  Marcos Tapaj�s - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.util.*;

/**
 * Uma m�o de cartas de poquer. A grande diferen�a � que o valor da m�o
 * � calculado de acordo com as regras do poquer.
 * <P>Em ordem crescente, as m�os s�o:<br>
 * <ul>
 * <li>Nada: cinco cartas diferentes, sem sequ�ncia ou flush.</li>
 * <li>Um par: 2 cartas de mesmo valor.</li>
 * <li>Dois pares.</li>
 * <li>Uma trinca: 3 cartas de mesmo valor.</li>
 * <li>Sequ�ncia: cinco cartas de valor consecutivo, mas n�o sendo de mesmo naipe.</li>
 * <li>Flush: cinco cartas de mesmo naipe.</li>
 * <li>Full house: Uma trinca mais um par.</li>
 * <li>Four: 4 cartas de mesmo valor.</li>
 * <li>Straight Flush: Sequ�ncia de mesmo naipe.</li>
 * <li>Royal Straight Flush: Sequ�ncia de mesmo naipe terminando em �s.</li>
 * </ul>
 */
public class MaoPoquer extends Mao {

	// pontua��o
    /**
     * Valor de uma m�o que n�o tem nada
     */
    public static final int NADA = 0;
    /**
     * Valor de uma m�o que tem um par
     */
    public static final int PAR = 1;
    /**
     * Valor de uma m�o que tem dois pares
     */
    public static final int DOIS_PARES = 2;
    /**
     * Valor de uma m�o que tem tr�s cartas de mesmo valor
     */
    public static final int TRINCA = 3;
    /**
     * Valor de uma m�o que tem uma sequ�ncia (straight)
     * (5 cartas seguidas, sem ser do mesmo naipe)
     * AS segue Rei
     */
    public static final int SEQUENCIA = 4;
    /**
     * Valor de uma m�o que tem um flush
     * (5 cartas de mesmo naipe)
     */
    public static final int FLUSH = 5;
    /**
     * Valor de uma m�o que tem uma m�o cheia
     * (uma trinca e um par).
     * Tamb�m chamado de full house ou full hand.
     * 
     */
    public static final int FULL_HOUSE = 6;
    /**
     * Valor de uma m�o que tem quatro cartas de mesmo valor.
     */
    public static final int FOUR = 7;
    /**
     * Valor de uma m�o que tem uma sequ�ncia + flush
     */
    public static final int STRAIGHT_FLUSH = 8;
    /**
     * Valor de uma m�o que tem uma sequ�ncia + flush
     * terminando em AS.
     */
    public static final int ROYAL_STRAIGHT_FLUSH = 9;

    /**
     * Calcula o valor da m�o.
     * @return O valor da m�o.
     */
    public int valor() {
        int[] contaValor = new int[CartaPoquer.maiorValor()+1];
        int[] contadoresRepeticao = new int[5]; // conta repeticoes de 1, pares, trincas, fours

        for(int i = 0; i < contaValor.length; i++) {
            contaValor[i] = 0;
        }
        for(int i = 0; i < contadoresRepeticao.length; i++) {
            contadoresRepeticao[i] = 0;
        }
        int naipe = -1;
        boolean temFlush = true;
        Iterator it = iterator();
        while(it.hasNext()) {
            Carta carta = (Carta)it.next();
            contaValor[carta.getValor()]++;
            if(naipe < 0) {
                // primeira carta
                naipe = carta.getNaipe();
            } else if(carta.getNaipe() != naipe) {
                temFlush = false;
            }
        }
        //MenorUm = Menor carta sozinha na mao
        int menorUm = CartaPoquer.maiorValor()+1;
        for(int i = 1; i < contaValor.length; i++) {
            int contador = contaValor[i];
            contadoresRepeticao[contador]++;
            if(contador == 1 && i < menorUm) {
                menorUm = i;
            }
        }
        // verifica straight
        boolean temStraight = menorUm <= 10
                              && contaValor[menorUm+1] == 1
                              && contaValor[menorUm+2] == 1
                              && contaValor[menorUm+3] == 1
                              && contaValor[menorUm+4] == 1;
        // calcula pontuacao final
        if(temStraight && temFlush && menorUm == 10) {
            return ROYAL_STRAIGHT_FLUSH;
        } else if(temStraight && temFlush) {
            return STRAIGHT_FLUSH;
        } else if(contadoresRepeticao[4] == 1) {
            return FOUR;
        } else if(contadoresRepeticao[3] == 1
                    && contadoresRepeticao[2] == 1) {
            return FULL_HOUSE;
        } else if(temFlush) {
            return FLUSH;
        } else if(temStraight) {
            return SEQUENCIA;
        } else if(contadoresRepeticao[3] == 1) {
            return TRINCA;
        } else if(contadoresRepeticao[2] == 2) {
            return DOIS_PARES;
        } else if(contadoresRepeticao[2] == 1) {
            return PAR;
        } else {
            return NADA;
        }
    }


 }
