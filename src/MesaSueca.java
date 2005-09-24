/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
import java.util.Iterator;
import java.util.Vector;


public class MesaSueca {
		
	private BaralhoSueca repositorio;
	private CartaSueca CartaNaMesaJogador1;
	private CartaSueca CartaNaMesaJogador2;
	private CartaSueca CartaNaMesaJogador3;
	private CartaSueca CartaNaMesaJogador4;
	private Vector repositorioDupla1;
	private Vector repositorioDupla2;
	private CartaSueca CartaTrunfo;
	
	public MesaSueca(BaralhoSueca baralho){
		this.repositorio = baralho;
		this.repositorioDupla1 = new Vector();
		this.repositorioDupla2 = new Vector();
	}

	
	public CartaSueca pegaCarta() throws BaralhoVazioException{		
		return (CartaSueca) this.repositorio.pegaCarta();
	}
	
	public CartaSueca pegaUltimaCarta() throws BaralhoVazioException{		
		return (CartaSueca) this.repositorio.pegaUltimaCarta();
	}
	
	public void baralharCartas(){
		this.repositorio.baralhar();
	}
	
	public Iterator IteratorBaralho(){
		return (Iterator) this.repositorio.iterator();		
	}
	
	public int NumeroDeCartas(){
		return this.repositorio.númeroDeCartas();
	}
	
	public void mesaRecebeCartaJogador(int jogador,CartaSueca carta)
	{
		if (jogador==1)
		{
			this.CartaNaMesaJogador1 = carta; 
		}
		else if (jogador==2)
		{
			this.CartaNaMesaJogador2 = carta;
		}
		else if (jogador==3)
		{
			this.CartaNaMesaJogador3 = carta;	
		}
		else if (jogador==4)
		{
			this.CartaNaMesaJogador4 = carta;
		}
	}
	
	public CartaSueca RetornaCartasNaMesa(int jogador){
		if(jogador == 1) return this.CartaNaMesaJogador1;
		else if (jogador == 2) return this.CartaNaMesaJogador2;
		else if (jogador == 3) return this.CartaNaMesaJogador3;
		else if (jogador == 4) return this.CartaNaMesaJogador4;
		return null;
	}
	
	public int limpaMesa(int andista){
		int vencedor = 0;
		if(CartaNaMesaJogador1!=null && CartaNaMesaJogador2!=null
			&& CartaNaMesaJogador3!=null && CartaNaMesaJogador4!=null){
			vencedor = this.getVencedorRodada(andista);
			
			if (vencedor==1 || vencedor==3) {
				this.repositorioDupla1.add(CartaNaMesaJogador1);
				this.repositorioDupla1.add(CartaNaMesaJogador2);
				this.repositorioDupla1.add(CartaNaMesaJogador3);
				this.repositorioDupla1.add(CartaNaMesaJogador4);
				CartaNaMesaJogador1 = null;
				CartaNaMesaJogador2 = null;
				CartaNaMesaJogador3 = null;
				CartaNaMesaJogador4 = null;
			}
			else if (vencedor==2 || vencedor==4) {
				this.repositorioDupla2.add(CartaNaMesaJogador1);
				this.repositorioDupla2.add(CartaNaMesaJogador2);
				this.repositorioDupla2.add(CartaNaMesaJogador3);
				this.repositorioDupla2.add(CartaNaMesaJogador4);
				CartaNaMesaJogador1 = null;
				CartaNaMesaJogador2 = null;
				CartaNaMesaJogador3 = null;
				CartaNaMesaJogador4 = null;
			}
		}	
		return vencedor;
	}
	
	public Iterator IteratorRepositorioDupla1() {
	    return repositorioDupla1.iterator();
	}
	
	public Iterator IteratorRepositorioDupla2() {
	    return repositorioDupla2.iterator();
	}
		

	public int getVencedorRodada (int andista)
	{
		CartaSueca MaiorCarta ;
		if (andista ==1 ) MaiorCarta = CartaNaMesaJogador1;
		else if (andista ==2 ) MaiorCarta = CartaNaMesaJogador2;
		else if (andista ==3 ) MaiorCarta = CartaNaMesaJogador3;
		else MaiorCarta = CartaNaMesaJogador4;
		int JogadorAssociado = 1;
		if (temTrunfoNaMesa()){			
			if (this.CartaNaMesaJogador2.getNaipe() == CartaTrunfo.getNaipe()){
				if (MaiorCarta.getNaipe() == CartaTrunfo.getNaipe()){
					if( this.CartaNaMesaJogador2.getPonto() > MaiorCarta.getPonto() ){
						MaiorCarta = CartaNaMesaJogador2;
						JogadorAssociado = 2;
					}
				}
				else {
					MaiorCarta = CartaNaMesaJogador2;
					JogadorAssociado = 2;
				}
				
			}
			if (this.CartaNaMesaJogador3.getNaipe() == CartaTrunfo.getNaipe()){
				if (MaiorCarta.getNaipe() == CartaTrunfo.getNaipe()){
					if( this.CartaNaMesaJogador3.getPonto() > MaiorCarta.getPonto() ){
						MaiorCarta = CartaNaMesaJogador3;
						JogadorAssociado = 3;
					}
				}
				else {
					MaiorCarta = CartaNaMesaJogador3;
					JogadorAssociado = 3;
				}
				
			}
			if (this.CartaNaMesaJogador4.getNaipe() == CartaTrunfo.getNaipe()){
				if (MaiorCarta.getNaipe() == CartaTrunfo.getNaipe()){
					if( this.CartaNaMesaJogador4.getPonto() > MaiorCarta.getPonto() ){
						MaiorCarta = CartaNaMesaJogador4;
						JogadorAssociado = 4;
					}
				}
				else {
					MaiorCarta = CartaNaMesaJogador4;
					JogadorAssociado = 4;
				}
				
			}
			return JogadorAssociado;
		}
		else {
			if (this.CartaNaMesaJogador2.getPonto() > MaiorCarta.getPonto()){
				MaiorCarta = this.CartaNaMesaJogador2;
				JogadorAssociado = 2;
		    	}
			if (this.CartaNaMesaJogador3.getPonto() > MaiorCarta.getPonto()){
				MaiorCarta = this.CartaNaMesaJogador3;
				JogadorAssociado = 3;
		    	}
			if (this.CartaNaMesaJogador4.getPonto() > MaiorCarta.getPonto()){
				MaiorCarta = this.CartaNaMesaJogador4;
				JogadorAssociado = 4;
		    	}
			return JogadorAssociado;
		}
	}
	
	
	public boolean temTrunfoNaMesa(){
		if (this.CartaNaMesaJogador1.getNaipe() == CartaTrunfo.getNaipe() || 
			this.CartaNaMesaJogador2.getNaipe() == CartaTrunfo.getNaipe() ||
			this.CartaNaMesaJogador3.getNaipe() == CartaTrunfo.getNaipe() ||
			this.CartaNaMesaJogador4.getNaipe() == CartaTrunfo.getNaipe())
			return true;
		else return false;
		
	}
	
	public void recebeTrunfoAposDistribuicao(CartaSueca trunfo){
		this.CartaTrunfo = trunfo;
	}
	
	public CartaSueca retornaTrunfo(){
		return this.CartaTrunfo;
	}
	
	public int contaPontosRepositorioCartas(String dupla){
		int pontos=0;
		if (dupla =="dupla1"){
			for(int i=0;i<repositorioDupla1.size();i++){
				pontos += ((CartaSueca)repositorioDupla1.elementAt(i)).getPonto();
			}
		}
		else {
			for(int i=0;i<repositorioDupla1.size();i++){
				pontos += ((CartaSueca)repositorioDupla1.elementAt(i)).getPonto();
			}
		}
		return pontos;
	}
	
	public void retornaCartasProBaralho(){
		while (repositorioDupla1.size()>0){
			this.repositorio.recebeCarta((CartaSueca) repositorioDupla1.remove(0));
		}
		while (repositorioDupla2.size()>0){
			this.repositorio.recebeCarta((CartaSueca) repositorioDupla2.remove(0));
		}
		this.CartaTrunfo = null;
	}
	
}

