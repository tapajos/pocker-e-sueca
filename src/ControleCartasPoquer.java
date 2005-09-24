import java.util.Iterator;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 * 


	Ao ser criado o novo baralho, ele irá para a MESA e ficará no repositório dela.
 * Quando eu quiser distribuir as cartas aos jogadores, peço as cartas ao
 * repositório da mesa.
 */

public class ControleCartasPoquer {
	private MesaPoquer mesa;
	
	public ControleCartasPoquer() {
		BaralhoPoquer baralho = new BaralhoPoquer();
		baralho.baralhar();
		mesa = new MesaPoquer(baralho);		
	}

	public CartaPoquer PedirUmaCarta() throws BaralhoVazioException{
		
		return mesa.PedirUmaCarta();
	}
	
	public void DevolveProBaralho (CartaPoquer carta){
		mesa.DevolveProBaralho(carta);
	}
	
	public void baralhar(){
		mesa.baralharCartas();
	}
	
	public Iterator Iterator(){
		return  mesa.Iterator();		
	}	
	
	public int NumeroDeCartas(){
		return mesa.NumeroDeCartas();
	}
	
	public float getPool(int jogador)
	{
		return mesa.getAposta(jogador);
	}

	public void setPool(int jogador, float aposta)
	{
		this.mesa.mesaRecebeApostas(jogador,aposta);
	}

	public void zeraPool() {
		this.mesa.zeraPool();
	}
}
