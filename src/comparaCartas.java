/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

public class comparaCartas implements java.util.Comparator {
    public int compare(Object o1, Object o2) {
         CartaPoquer c1 = (CartaPoquer)o1;
         CartaPoquer c2 = (CartaPoquer)o2;
         return c2.getValor() - c1.getValor();
    }
}

