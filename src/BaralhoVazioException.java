/*
 * DCC- Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:
 */


/**
 * Classe de Exceção quando tenta-se poegar uma carta de um baralho vazio.
 *
 * 
 */
public class BaralhoVazioException extends Exception {

	public BaralhoVazioException(String motivo) {
		super(motivo);
	}
}
