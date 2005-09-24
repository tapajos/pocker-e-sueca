/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.util.Comparator;
public class comparaMaos implements Comparator{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		JogadorPoquer m1 = (JogadorPoquer)o1; 
		JogadorPoquer m2 = (JogadorPoquer)o2;
		
		return m2.getMaoJogador().valor() - m1.getMaoJogador().valor();
	}

}
