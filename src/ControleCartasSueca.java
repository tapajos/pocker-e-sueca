/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */import java.util.Iterator;


public class ControleCartasSueca {
	private MesaSueca mesa;
	
	public ControleCartasSueca() {
		BaralhoSueca baralho = new BaralhoSueca ();
		mesa = new MesaSueca(baralho);
	}
	
	public void baralhar(){
		mesa.baralharCartas();
	}
	
	public CartaSueca PegaUmaCartaDeBaixo() throws BaralhoVazioException{
		return mesa.pegaUltimaCarta();
	}
	
	public CartaSueca PegaUmaCarta() throws BaralhoVazioException{
		return mesa.pegaCarta();
	}
	

	public CartaSueca RetornaCartasNaMesa(int jogador){
		return this.mesa.RetornaCartasNaMesa(jogador);
	}

	public void fazJogada(int jogador,CartaSueca carta){
		this.mesa.mesaRecebeCartaJogador(jogador,carta);
	}
	
	public Iterator IteratorBaralho(){
		return  mesa.IteratorBaralho();		
	}
	
	public Iterator IteratorRepositorioDupla1(){
		return  mesa.IteratorRepositorioDupla1();		
	}

	public Iterator IteratorRepositorioDupla2(){
		return  mesa.IteratorRepositorioDupla2();
	}
	
	public int NumeroDeCartas(){
		return mesa.NumeroDeCartas();
	}
	
	public int LimpaMesa(int andista){
		return this.mesa.limpaMesa(andista);
	}
	
	public void recebeTrunfo(CartaSueca trunfo){
		this.mesa.recebeTrunfoAposDistribuicao(trunfo);
	}
	
	public CartaSueca retornaTrunfo(){
		return this.mesa.retornaTrunfo();
	}
	
	public int contaPontosRepositorioCartas(String dupla){
		return this.mesa.contaPontosRepositorioCartas(dupla);
	}
	
	public void retornaCartasProBaralho(){
		this.mesa.retornaCartasProBaralho();
	}
}
