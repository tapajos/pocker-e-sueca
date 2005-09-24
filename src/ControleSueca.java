
/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

public class ControleSueca {
	ControleCartasSueca controleCartas;
	ControleJogadoresSueca controleJogadores;
	ControleVistaSueca controleVista;
	boolean cartasDistribuidas = false;
	String donoRodada;
	int andistaGeral = 1;
	int andistaRodadas = 1;
	int puxadorDaPrimeiraCarta = 1;

	public ControleSueca() throws BaralhoVazioException {
		controleCartas = new ControleCartasSueca ();
		controleJogadores = new ControleJogadoresSueca ();
		controleVista = new ControleVistaSueca(controleCartas,controleJogadores,this);
		controleVista.desenhaTela();
	}
	
	
	// Distribuo as cartas passando o nome do jogador inicial 
	public void distribuiCartasHorario(int jogador) throws BaralhoVazioException{
		CartaSueca trunfo =this.controleJogadores.distribuirCartasHorario(this.controleCartas,jogador);
		this.controleCartas.recebeTrunfo(trunfo);
		this.Workflow();
	}

	public void distribuiCartasAntiHorario(int jogador) throws BaralhoVazioException{
		CartaSueca trunfo =this.controleJogadores.distribuirCartasAntiHorario(this.controleCartas,jogador); 
		this.controleCartas.recebeTrunfo(trunfo);
		this.Workflow();
	}
	
	public void baralhar(){
		controleCartas.baralhar();
	}
	
	public void cliqueCarta(ImagemCarta Imagemcarta){
		int indice=0;

	 	for(int i=0;i<controleJogadores.QuantidadeDeCartasNaMao(1);i++){
	 		if(Imagemcarta.equals(controleVista.j1.elementAt(i))) indice = i;			
	 	}
	 	for(int i=0;i<controleJogadores.QuantidadeDeCartasNaMao(2);i++){
	 		if(Imagemcarta.equals(controleVista.j2.elementAt(i))) indice = i;			
	 	}
	 	for(int i=0;i<controleJogadores.QuantidadeDeCartasNaMao(3);i++){
	 		if(Imagemcarta.equals(controleVista.j3.elementAt(i))) indice = i;			
	 	}
	 	for(int i=0;i<controleJogadores.QuantidadeDeCartasNaMao(4);i++){
	 		if(Imagemcarta.equals(controleVista.j4.elementAt(i))) indice = i;			
	 	}
	 	controleCartas.fazJogada(Imagemcarta.getDono(),controleJogadores.removeCartaPraJogada(Imagemcarta.getDono(),indice));
	 	andistaRodadas = controleJogadores.pegaProximoJogador(andistaRodadas);
	 	int temp = controleCartas.LimpaMesa(puxadorDaPrimeiraCarta);
	 	if (temp >0) {
	 		this.andistaRodadas = temp;
	 		this.puxadorDaPrimeiraCarta = temp;
	 	}
	 	this.cartasVisiveisSomenteAndista();
	 	this.testaFimRodada();
	 }
	 
	 public CartaSueca retornaTrunfo(){
	 	return this.controleCartas.retornaTrunfo();
	 }

	 public void Workflow(){
	 	controleJogadores.getJogador(andistaGeral);
	 	cartasVisiveisSomenteAndista();	
	 }
	 
	 public void cartasVisiveisSomenteAndista(){
	 	controleJogadores.viraCartasJogadorParaBaixo(1);
	 	controleJogadores.viraCartasJogadorParaBaixo(2);
	 	controleJogadores.viraCartasJogadorParaBaixo(3);
	 	controleJogadores.viraCartasJogadorParaBaixo(4);
	 	controleJogadores.viraCartasJogadorParaCima(this.andistaRodadas);
	 }
	 
	 public int getAndista(){
	 	return this.andistaRodadas;
	 }
	 
	 public void testaFimRodada(){
	 	if (controleJogadores.QuantidadeDeCartasNaMao(1)==0&&
	 		controleJogadores.QuantidadeDeCartasNaMao(2)==0&&
			controleJogadores.QuantidadeDeCartasNaMao(3)==0&&
			controleJogadores.QuantidadeDeCartasNaMao(4)==0
			){
	 		if (controleCartas.contaPontosRepositorioCartas("dupla1")>controleCartas.contaPontosRepositorioCartas("dupla2")){
	 			controleJogadores.AdicionaPontuaçãoDupla(1,1);
	 		}
	 		else controleJogadores.AdicionaPontuaçãoDupla(2,1);
	 		
	 		// testa fim de jogo
	 		if (controleJogadores.RetornaPontuaçãoDupla(1)==4||
	 			controleJogadores.RetornaPontuaçãoDupla(2)==4){
	 			controleCartas.retornaCartasProBaralho();
	 			andistaGeral = 1;
	 			andistaRodadas = 1;
	 			controleVista.novoJogo();
	 			controleJogadores.ZeraPontuaçãoDuplas();
	 			controleVista.setNaoBaralhou();
	 		}
	 			
	  		
	 		controleCartas.retornaCartasProBaralho();
	 		andistaGeral = controleJogadores.pegaProximoJogador(andistaGeral);
	 		andistaRodadas = andistaGeral;  
		 	controleVista.novoJogo();
		 	controleVista.setNaoBaralhou();
	 	}
	 }
	 
}
