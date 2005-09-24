import java.util.Iterator;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class MesaPoquer {
	private BaralhoPoquer repositorio;
	private float[] poolApostas = new float[5];

	public MesaPoquer(BaralhoPoquer baralho){
		repositorio = baralho;	
		for (int i = 1; i < 5; i++)
		{
			this.poolApostas[i] = 0;
		}
	}
	
	
	public CartaPoquer PedirUmaCarta() throws BaralhoVazioException{
		
		return (CartaPoquer) this.repositorio.pegaCarta();
	}
	
	public void DevolveProBaralho (CartaPoquer carta){
		this.repositorio.DevolveProBaralho(carta);
	}
	
	public void baralharCartas(){
		this.repositorio.baralhar();
	}
	
	public Iterator Iterator(){
		return (Iterator) this.repositorio.iterator();		
	}	
	
	public int NumeroDeCartas(){
		return this.repositorio.númeroDeCartas();
	}
	
	public void mesaRecebeApostas(int jogador, float aposta)
	{
		poolApostas[jogador] += aposta;
	}
	
	public float getAposta(int jogador)
	{
		return poolApostas[jogador];
	}

	public void zeraPool() {
		for (int i = 1; i < 5; i++)
		{
			this.poolApostas[i] = 0;
		}
	}
}
