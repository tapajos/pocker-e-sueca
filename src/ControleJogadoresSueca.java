/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
import java.util.Iterator;

public class ControleJogadoresSueca {
	
	private JogadorSueca j1;
	private JogadorSueca j2;
	private JogadorSueca j3;
	private JogadorSueca j4;
	private int PontosDupla1, PontosDupla2;
		
	//Dupla 1 = j1 + j3
	//Dupla 2 = j2 + j4
	
	public ControleJogadoresSueca() {
		

		j1 = new JogadorSueca();
		j2 = new JogadorSueca();
		j3 = new JogadorSueca();
		j4 = new JogadorSueca();
		j1.DefineNome("Bernardo");
		j2.DefineNome("Tapajós");
		j3.DefineNome("Sírius");
		j4.DefineNome("Tchutchuco");
		
		PontosDupla1 = 0;
		PontosDupla2 = 0;
		
	}
	public void AdicionaPontuaçãoDupla (int dupla, int pontos){
		 if (dupla ==1 ) this.PontosDupla1 += pontos;
		 else this.PontosDupla2 += pontos;
	}
	
	public void ZeraPontuaçãoDuplas (){
		this.PontosDupla1 = 0;
		this.PontosDupla2 = 0;
	}
	
	public int RetornaPontuaçãoDupla (int dupla){
		if (dupla == 1 ) return this.PontosDupla1;
		else return this.PontosDupla2;
		
	}
	
	public CartaSueca distribuirCartasHorario(ControleCartasSueca controleCartas, int jogador) throws BaralhoVazioException{
		CartaSueca trunfo = controleCartas.PegaUmaCarta();
		JogadorSueca jogador_temp = new JogadorSueca();
		jogador_temp = getJogador(jogador);
		
		jogador_temp.maoJogador.adicionar(trunfo);
		for (int numero_cartas = 0; numero_cartas<9; numero_cartas++){
			jogador_temp.maoJogador.adicionar(controleCartas.PegaUmaCarta());
		}
		
		jogador_temp = pegaProximoJogador(jogador_temp);
		int numeroCartas=30;
		while (numeroCartas > 0){
			for (int conta_cartas = 0; conta_cartas<10; conta_cartas++){
				jogador_temp.maoJogador.adicionar(controleCartas.PegaUmaCarta());
				numeroCartas -= 1;
			}
			jogador_temp = pegaProximoJogador(jogador_temp);
		}
		return trunfo;
	}
	
	public CartaSueca distribuirCartasAntiHorario(ControleCartasSueca controleCartas, int jogador) throws BaralhoVazioException{
		CartaSueca trunfo = controleCartas.PegaUmaCartaDeBaixo();
		JogadorSueca jogador_temp = new JogadorSueca();
		jogador_temp = getJogador(jogador);
		jogador_temp.maoJogador.adicionar(trunfo);

		jogador_temp = pegaAnteriorJogador(jogador_temp);
		int numeroCartas=30;
		while (numeroCartas > 0){
			for (int conta_cartas = 0; conta_cartas<10 ; conta_cartas++){
				jogador_temp.maoJogador.adicionar(controleCartas.PegaUmaCarta());
				numeroCartas--;
			}
			jogador_temp = pegaAnteriorJogador(jogador_temp);
		}
		for (int conta_cartas = 0; conta_cartas<9; conta_cartas++){
			jogador_temp.maoJogador.adicionar(controleCartas.PegaUmaCarta());
		}
		return trunfo;
	}	

	public Iterator cartasNaMaoJogador1(){
		return j1.maoJogador.iterator();	 	
	 }
	 
	 public Iterator cartasNaMaoJogador2(){
		return j2.maoJogador.iterator();	 	
	 }

	 public Iterator cartasNaMaoJogador3(){
		return j3.maoJogador.iterator();	 	
	 }

	 public Iterator cartasNaMaoJogador4(){
		return j4.maoJogador.iterator();	 	
	 }

	 public JogadorSueca getJogador(int jogador){
	 	if (jogador==1) return this.j1;
		else if (jogador==2) return this.j2;
		else if (jogador==3) return this.j3;
		else return this.j4;
	 }
	 
	 public int getIntJogador(JogadorSueca jogador){
	 	if (jogador.equals(this.j1)) return 1;
		else if (jogador.equals(this.j2)) return 2;
		else if (jogador.equals(this.j3)) return 3;
		else return 4;
	 }
	
	 public int proximo(int jogador, ControleJogadoresSueca controleJogador)
	{
		int retorno = 0;
		
		if (jogador==1)
			retorno = 2;
		else if (jogador==2)
			retorno = 3;
		else if (jogador==3)
			retorno = 4;
		else if (jogador==4)
			retorno = 1;
		
		return retorno; 
	}
		
	public String getNomeJogador(int jogador){
		return getJogador(jogador).PegaNome();
	}

	public void viraCartasJogadorParaBaixo(int jogador){
		for (int i=0;i<this.getJogador(jogador).maoJogador.númeroDeCartas();i++){
			this.getJogador(jogador).getMaoJogador().ViraCartaSuecaParaBaixo(i);
		}
	}
		
	public void viraCartasJogadorParaCima(int jogador){
		for (int i=0;i<this.getJogador(jogador).maoJogador.númeroDeCartas();i++){
			this.getJogador(jogador).getMaoJogador().ViraCartaSuecaParaCima(i);
		}
	}			
	
	public JogadorSueca pegaProximoJogador(JogadorSueca jogador){
		if (getIntJogador(jogador)==1) return this.j2;
		else if (getIntJogador(jogador)==2) return this.j3;
		else if (getIntJogador(jogador)==3) return this.j4;
		else if (getIntJogador(jogador)==4) return this.j1;
		else return null;
	}
	
	public int pegaProximoJogador(int jogador){
		if (jogador==1) return 2;
		else if (jogador==2) return 3;
		else if (jogador==3) return 4;
		else if (jogador==4) return 1;
		else return 0;
	}

	public JogadorSueca pegaAnteriorJogador(JogadorSueca jogador){
		if (getIntJogador(jogador)==1) return this.j4;
		else if (getIntJogador(jogador)==2) return this.j1;
		else if (getIntJogador(jogador)==3) return this.j2;
		else if (getIntJogador(jogador)==4) return this.j3;
		else return null;
	}
	
	public CartaSueca removeCartaPraJogada(int jogador, int posicaoCarta){
		return (CartaSueca)this.getJogador(jogador).maoJogador.remover(posicaoCarta);	
	}
	
	public int QuantidadeDeCartasNaMao(int jogador){
		return this.getJogador(jogador).maoJogador.quantasCartasNaMao();
	}

}
