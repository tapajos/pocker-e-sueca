/*
 * DCC- Departamento de Ci�ncia da Computa��o
 *  Marcos Tapaj�s - DRE:101138637
 *  Bernardo Rocha - DRE:
 */


/**
 * Classe de Exce��o quando tenta-se poegar uma carta de um baralho vazio.
 *
 * 
 */
public class BaralhoVazioException extends Exception {

	public BaralhoVazioException(String motivo) {
		super(motivo);
	}
}
