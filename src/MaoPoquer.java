/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.util.*;

/**
 * Uma mão de cartas de poquer. A grande diferença é que o valor da mão
 * é calculado de acordo com as regras do poquer.
 * <P>Em ordem crescente, as mãos são:<br>
 * <ul>
 * <li>Nada: cinco cartas diferentes, sem sequência ou flush.</li>
 * <li>Um par: 2 cartas de mesmo valor.</li>
 * <li>Dois pares.</li>
 * <li>Uma trinca: 3 cartas de mesmo valor.</li>
 * <li>Sequência: cinco cartas de valor consecutivo, mas não sendo de mesmo naipe.</li>
 * <li>Flush: cinco cartas de mesmo naipe.</li>
 * <li>Full house: Uma trinca mais um par.</li>
 * <li>Four: 4 cartas de mesmo valor.</li>
 * <li>Straight Flush: Sequência de mesmo naipe.</li>
 * <li>Royal Straight Flush: Sequência de mesmo naipe terminando em Ás.</li>
 * </ul>
 */
public class MaoPoquer extends Mao {

	// pontuação
    /**
     * Valor de uma mão que não tem nada
     */
    public static final int NADA = 0;
    /**
     * Valor de uma mão que tem um par
     */
    public static final int PAR = 1;
    /**
     * Valor de uma mão que tem dois pares
     */
    public static final int DOIS_PARES = 2;
    /**
     * Valor de uma mão que tem três cartas de mesmo valor
     */
    public static final int TRINCA = 3;
    /**
     * Valor de uma mão que tem uma sequência (straight)
     * (5 cartas seguidas, sem ser do mesmo naipe)
     * AS segue Rei
     */
    public static final int SEQUENCIA = 4;
    /**
     * Valor de uma mão que tem um flush
     * (5 cartas de mesmo naipe)
     */
    public static final int FLUSH = 5;
    /**
     * Valor de uma mão que tem uma mão cheia
     * (uma trinca e um par).
     * Também chamado de full house ou full hand.
     * 
     */
    public static final int FULL_HOUSE = 6;
    /**
     * Valor de uma mão que tem quatro cartas de mesmo valor.
     */
    public static final int FOUR = 7;
    /**
     * Valor de uma mão que tem uma sequência + flush
     */
    public static final int STRAIGHT_FLUSH = 8;
    /**
     * Valor de uma mão que tem uma sequência + flush
     * terminando em AS.
     */
    public static final int ROYAL_STRAIGHT_FLUSH = 9;

    /**
     * Calcula o valor da mão.
     * @return O valor da mão.
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
