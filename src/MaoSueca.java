import java.util.Iterator;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class MaoSueca extends Mao{

	public static final int AS = 11;
	public static final int DOIS = 2;
	public static final int TRES = 3;
	public static final int QUATRO = 4;
	public static final int CINCO = 5;
	public static final int SEIS = 6;
	public static final int SETE = 7;
	public static final int DAMA = 8;
	public static final int VALETE = 9;
	public static final int REI = 10;
	
    public int valor() {
        Iterator it = iterator();
        int resultado = 0;
        while(it.hasNext()) {
            resultado += ((Carta)it.next()).getValor();
        }
        return resultado;
    }
	
	
}
