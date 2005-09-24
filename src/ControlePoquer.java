import java.util.Collections;
import java.util.Vector;

import javax.swing.JOptionPane;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class ControlePoquer {
	ControleCartasPoquer controleCartas;
	ControleJogadoresPoquer controleJogadores;
	ControleVistaPoquer controleVista;
	boolean cartasDistribuidas = false;
	boolean segundaRodada = false;
	int donoRodada = 0;
	float apostaDonoRodada;

	public ControlePoquer() throws BaralhoVazioException {
		controleCartas = new ControleCartasPoquer ();
		controleJogadores = new ControleJogadoresPoquer ();
		controleVista = new ControleVistaPoquer(controleCartas,controleJogadores,this);
		controleVista.desenhaTela();
		this.inicializarJogo(controleJogadores);
	}
	
	public void distribuiCartas() throws BaralhoVazioException {
		controleJogadores.distribuiCartas(controleCartas, 5);
	}
	
	public void baralhar(){
		controleCartas.baralhar();
	}

	 public void cliqueCarta(ImagemCarta Imagemcarta){
	 	
	 }
	 
	 public int getDonoRodada()
	 {
	 	return donoRodada;
	 }
	 
	 public boolean getSegundaRodada()
	 {
	 	return segundaRodada;
	 }
	 
	 public void setSegundaRodada(boolean rodada)
	 {
	 	segundaRodada = rodada;
	 }
	 
	 public void trocarCartasPoquerModelo(int jogador){
		ImagemCarta imagemCarta;
		
		if (jogador == 1)
		{
			for(int i=4;i>=0;i--){
				imagemCarta = (ImagemCarta) controleVista.j1.elementAt(i);
				if (imagemCarta.getEstadoTroca()){
					imagemCarta.setDono(5);
					try {
						this.controleJogadores.trocarCarta(1,controleCartas,i);
					} catch (BaralhoVazioException e) {
						e.printStackTrace();
					}
				}
			}
		}
			
		if (jogador == 2)
		{
			for(int i=4;i>=0;i--){
				imagemCarta = (ImagemCarta) controleVista.j2.elementAt(i);
				if (imagemCarta.getEstadoTroca()){
					imagemCarta.setDono(5);
					try {
						this.controleJogadores.trocarCarta(2,controleCartas,i);
					} catch (BaralhoVazioException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if (jogador == 3)
		{
			for(int i=4;i>=0;i--){
				imagemCarta = (ImagemCarta) controleVista.j3.elementAt(i);
				if (imagemCarta.getEstadoTroca()){
					imagemCarta.setDono(5);
					try {
						this.controleJogadores.trocarCarta(3,controleCartas,i);
					} catch (BaralhoVazioException e) {
						e.printStackTrace();
					}
				}
			}
		}
			
		if (jogador == 4)
		{
			for(int i=4;i>=0;i--){
				imagemCarta = (ImagemCarta) controleVista.j4.elementAt(i);
				if (imagemCarta.getEstadoTroca()){
					imagemCarta.setDono(5);
					try {
						this.controleJogadores.trocarCarta(4,controleCartas,i);
					} catch (BaralhoVazioException e) {
						e.printStackTrace();
					}
				}
			}
		}

		controleJogadores.ordenaCartasMao();
		controleJogadores.proximoAndista();
		
		if (controleJogadores.getAndista() == controleJogadores.getAndistaInicial())
		{
			mudaParaSegundaRodadaApostas();
		}
		
		this.vezJogador(controleJogadores.getAndista());
		controleVista.desenhaTela();
	 }

	public void inicializarJogo(ControleJogadoresPoquer controleJogadores) throws BaralhoVazioException
	{
		controleJogadores.setarCacife(10);
		controleJogadores.setAndistaOrdemGeral(controleJogadores.descobreAndista(controleJogadores));
		controleJogadores.setAndistaInicial(controleJogadores.getAndistaOrdemGeral());
		controleJogadores.setAndista(controleJogadores.getAndistaInicial());
		for (int i = 1; i < 5; i++)
		{
			this.controleJogadores.getJogador(i).removeDinheiro(1);
			controleCartas.setPool(i, 1);
		}
	}
	
	public void sairDaRodada(int jogador)
	{
		controleJogadores.sairDaRodada(jogador, controleJogadores);
		
		int count= 0;
		
		for (int i = 1; i < 5; i++)
		{
			if (controleJogadores.getJogador(i).getAmarelou())
			{
				count++;
			}
		}
		
		if (count == 3)
		{
			for (int i = 1; i < 5; i++)
			{
				if (!controleJogadores.getJogador(i).getAmarelou())
				{
					controleVista.getVistaPoquer().setFaseApostas(false);
					controleVista.getVistaPoquer().setFaseTroca(false);
					vencedor(i);
					return;
				}
			}
		}
		
		controleJogadores.proximoAndista();
		
		System.out.println(controleJogadores.getAndista());

		if (controleJogadores.getAndista() == donoRodada && !segundaRodada)
		{
			mudaParaTroca();
			return;
		}
		if (controleJogadores.getAndista() == donoRodada && segundaRodada)
		{
			this.verificaVencedor();
			return;
		}
		
		this.vezJogador(controleJogadores.getAndista());
	}
	
	public void apostas(int jogador, float aposta, boolean segunda)
	{
		float apostaTotal;
		apostaTotal = controleCartas.getPool(jogador) + aposta;
		
		if (this.controleJogadores.getJogador(jogador).podeRemoverDinheiro(aposta))
		{
			apostaTotal = controleCartas.getPool(jogador) + aposta;
			
			if(donoRodada==0)
			{
				donoRodada = jogador;
				apostaDonoRodada = apostaTotal;
				this.controleJogadores.getJogador(jogador).removeDinheiro(aposta);
				controleCartas.setPool(jogador, aposta);
				controleJogadores.proximoAndista();
				this.vezJogador(controleJogadores.getAndista());
			}
			else if (apostaTotal < apostaDonoRodada){
				controleVista.getVistaPoquer().alerta.showMessageDialog(null, "Você deve apostar o teto da rodada", "Alerta", JOptionPane.ERROR_MESSAGE);
			}
			else if (apostaTotal > apostaDonoRodada){
				this.donoRodada = jogador;
				this.apostaDonoRodada = apostaTotal;
				this.controleJogadores.getJogador(jogador).removeDinheiro(aposta);
				controleCartas.setPool(jogador, aposta);
				controleJogadores.proximoAndista();
				this.vezJogador(controleJogadores.getAndista());
			}
			else 
			{
				this.controleJogadores.getJogador(jogador).removeDinheiro(aposta);
				controleCartas.setPool(jogador, aposta);
				controleJogadores.proximoAndista();
				this.vezJogador(controleJogadores.getAndista());
			}
		}
		else
		{
			controleVista.getVistaPoquer().alerta.showMessageDialog(null, "Você não tem esse dinheiro todo!", "Alerta", JOptionPane.ERROR_MESSAGE);
		}
		
		if (controleJogadores.getAndista() == donoRodada && !segundaRodada)
		{
			mudaParaTroca();
			return;
		}
		
		if (controleJogadores.getAndista() == donoRodada && segundaRodada)
		{
			this.verificaVencedor();
		}
	}


	private void mudaParaTroca() {
		//muda da fase de apostas pra fase de troca
		controleVista.getVistaPoquer().setFaseApostas(false);
		controleVista.getVistaPoquer().setFaseTroca(true);
		controleJogadores.setAndista(controleJogadores.getAndistaInicial());
		donoRodada = 0;
		apostaDonoRodada = 0;

		while (controleJogadores.getJogador(controleJogadores.getAndista()).getAmarelou()) 
		{
			controleJogadores.proximoAndista();
		}
		
		controleJogadores.setAndistaInicial(controleJogadores.getAndista());
		this.vezJogador(controleJogadores.getAndista());
	}
	
	private void mudaParaSegundaRodadaApostas() {
		segundaRodada = true;
		controleVista.getVistaPoquer().setFaseApostas(true);
		controleVista.getVistaPoquer().setFaseTroca(false);
		while (controleJogadores.getJogador(controleJogadores.getAndista()).getAmarelou()) 
		{
			controleJogadores.proximoAndista();
		}
	}


	private void verificaVencedor() {
			
		Vector jogadores;
		jogadores = new Vector();
				
		controleVista.getVistaPoquer().setFaseApostas(false);
		controleVista.getVistaPoquer().setFaseTroca(false);
		
		for (int i = 1; i < 5; i++)
		{
			if (!controleJogadores.getJogador(i).getAmarelou())
			{
				jogadores.addElement(controleJogadores.getJogador(i));
			}
		}

		Collections.sort(jogadores, new comparaMaos());
		
		JogadorPoquer u1 = new JogadorPoquer();
		JogadorPoquer u2 = new JogadorPoquer();
		JogadorPoquer u3 = new JogadorPoquer();
		JogadorPoquer u4 = new JogadorPoquer();
		
		u1 = (JogadorPoquer) jogadores.firstElement();
		u2 = (JogadorPoquer) jogadores.elementAt(1);
	
		if (u1.getMaoJogador().valor() != u2.getMaoJogador().valor())
			vencedor(u1.getId());
		else
		{
			int vencedor;
			vencedor = verificaEmpateMão(	controleJogadores.getJogador(u1.getId()),
											controleJogadores.getJogador(u2.getId()));
			vencedor(controleJogadores.getJogador(vencedor).getId());			
		}
		
	
		controleVista.desenhaTela();
	}

	/**
	 * @param jogador1
	 * @param jogador2
	 */
	private int verificaEmpateMão(JogadorPoquer jogador1, JogadorPoquer jogador2) {
		
		float pontos1 = jogador1.getMaoJogador().valor();
		
		if (pontos1 == 0) //NADA
		{
			for (int i = 0; i < 5; i++)
			{
				int carta = jogador1.getMaoJogador().cartaEm(i).compareTo(jogador2.getMaoJogador().cartaEm(i));
				
				if (carta < 0)
					return jogador2.getId();
				
				if (carta > 0)
					return jogador1.getId();

			}
			//se terminar o for, todas as cartas são iguais. Recorrer para o naipe
			if (jogador1.getMaoJogador().cartaEm(0).getNaipe() > jogador2.getMaoJogador().cartaEm(0).getNaipe())
				return jogador1.getId();
			else
				return jogador2.getId();
		}
		if (pontos1 == 1) //UM PAR
		{
			int cartaDoPar1 = 0;
			int cartaDoPar2 = 0;
			
			for (int i = 0; i < 4; i++)
			{
				int temp = i+1;
				int carta1 = jogador1.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador1.getMaoJogador().cartaEm(temp).getValor();
				
				if (carta1 == carta2)
				{
					cartaDoPar1 = carta1;
					break;
				}
			}
			
			for (int i = 0; i < 4; i++)
			{
				int temp = i+1;
				int carta1 = jogador2.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador2.getMaoJogador().cartaEm(temp).getValor();
				
				if (carta1 == carta2)
				{
					cartaDoPar2 = carta1;
					break;
				}
			}

			if (cartaDoPar1 > cartaDoPar2)
				return jogador1.getId();
			else if (cartaDoPar1 < cartaDoPar2)
				return jogador1.getId();
			else
			{
				for (int i = 0; i < 5; i++)
				{
					int carta = jogador1.getMaoJogador().cartaEm(i).compareTo(jogador2.getMaoJogador().cartaEm(i));
					
					if (carta < 0)
						return jogador2.getId();
					
					if (carta > 0)
						return jogador1.getId();
				}
			}
			//se terminar o for, todas as cartas são iguais. Recorrer para o naipe
			if (jogador1.getMaoJogador().cartaEm(0).getNaipe() > jogador2.getMaoJogador().cartaEm(0).getNaipe())
				return jogador1.getId();
			else
				return jogador2.getId();
		}
		
		if (pontos1 == 2) //DOIS PARES
		{
			int cartaDoPar1 = 0;
			int cartaDoPar2 = 0;
			int cartaDoPar3 = 0;
			int cartaDoPar4 = 0;
			
			for (int i = 0; i < 4; i++)
			{
				int temp = i+1;
				int carta1 = jogador1.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador1.getMaoJogador().cartaEm(temp).getValor();
				
				if (carta1 == carta2)
					cartaDoPar3 = carta1;
			}
			
			for (int i = 0; i < 4; i++)
			{
				int temp = i+1;
				int carta1 = jogador2.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador2.getMaoJogador().cartaEm(temp).getValor();
				
				if (carta1 == carta2)
					cartaDoPar4 = carta1;
			}
			
			for (int i = 0; i < 4; i++)
			{
				int temp = i+1;
				int carta1 = jogador1.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador1.getMaoJogador().cartaEm(temp).getValor();
				
				if (carta1 == carta2)
				{
					cartaDoPar1 = carta1;
					break;
				}
			}
			
			for (int i = 0; i < 4; i++)
			{
				int temp = i+1;
				int carta1 = jogador2.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador2.getMaoJogador().cartaEm(temp).getValor();
				
				if (carta1 == carta2)
				{
					cartaDoPar2 = carta1;
					break;
				}
			}
			
			if (cartaDoPar1 > cartaDoPar2)
				return jogador1.getId();
			else if (cartaDoPar1 < cartaDoPar2)
				return jogador1.getId();
			else if (cartaDoPar3 > cartaDoPar4)
				return jogador1.getId();
			else if (cartaDoPar3 < cartaDoPar4)
				return jogador2.getId();
			else
			{
				for (int i = 0; i < 5; i++)
				{
					int carta = jogador1.getMaoJogador().cartaEm(i).compareTo(jogador2.getMaoJogador().cartaEm(i));
					
					if (carta < 0)
						return jogador2.getId();
					
					if (carta > 0)
						return jogador1.getId();
				}
			}
		}
		
		if (pontos1 == 3) //TRINCA
		{
			int cartaTrinca1 = 0;
			int cartaTrinca2 = 0;
			
			for (int i = 0; i < 3; i++)
			{
				int temp1 = i+1;
				int temp2 = i+2;
				int carta1 = jogador1.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador1.getMaoJogador().cartaEm(temp1).getValor();
				int carta3 = jogador1.getMaoJogador().cartaEm(temp2).getValor();
				
				if (carta1 == carta2)
					if (carta2 == carta3)
						cartaTrinca1 = carta1;
			}
			
			for (int i = 0; i < 3; i++)
			{
				int temp1 = i+1;
				int temp2 = i+2;
				int carta1 = jogador2.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador2.getMaoJogador().cartaEm(temp1).getValor();
				int carta3 = jogador2.getMaoJogador().cartaEm(temp2).getValor();
				
				if (carta1 == carta2)
					if (carta2 == carta3)
						cartaTrinca2 = carta1;
			}
			
			if (cartaTrinca1 > cartaTrinca2)
				return jogador1.getId();
			else
				return jogador2.getId();
			
		}
		
		if (pontos1 == 4) //SEQUENCIA
		{
			for (int i = 0; i < 5; i++)
			{
				int carta = jogador1.getMaoJogador().cartaEm(i).compareTo(jogador2.getMaoJogador().cartaEm(i));
				
				if (carta < 0)
					return jogador2.getId();
				
				if (carta > 0)
					return jogador1.getId();
			}
			
			if (jogador1.getMaoJogador().cartaEm(0).getNaipe() > jogador2.getMaoJogador().cartaEm(0).getNaipe())
				return jogador1.getId();
			else
				return jogador2.getId();
		}
		
		if (pontos1 == 5) //FLUSH
		{
			for (int i = 0; i < 5; i++)
			{
				int carta = jogador1.getMaoJogador().cartaEm(i).compareTo(jogador2.getMaoJogador().cartaEm(i));
				
				if (carta < 0)
					return jogador2.getId();
				
				if (carta > 0)
					return jogador1.getId();
			}
			
			if (jogador1.getMaoJogador().cartaEm(0).getNaipe() > jogador2.getMaoJogador().cartaEm(0).getNaipe())
				return jogador1.getId();
			else
				return jogador2.getId();
		}
		
		if (pontos1 == 6) //FULL HOUSE
		{
			int cartaTrinca1 = 0;
			int cartaTrinca2 = 0;
			
			for (int i = 0; i < 3; i++)
			{
				int temp1 = i+1;
				int temp2 = i+2;
				int carta1 = jogador1.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador1.getMaoJogador().cartaEm(temp1).getValor();
				int carta3 = jogador1.getMaoJogador().cartaEm(temp2).getValor();
				
				if (carta1 == carta2)
					if (carta2 == carta3)
						cartaTrinca1 = carta1;
			}
			
			for (int i = 0; i < 3; i++)
			{
				int temp1 = i+1;
				int temp2 = i+2;
				int carta1 = jogador2.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador2.getMaoJogador().cartaEm(temp1).getValor();
				int carta3 = jogador2.getMaoJogador().cartaEm(temp2).getValor();
				
				if (carta1 == carta2)
					if (carta2 == carta3)
					{
						cartaTrinca2 = carta1;
						break;
					}
			}
			
			if (cartaTrinca1 > cartaTrinca2)
				return jogador1.getId();
			else
				return jogador2.getId();
			
		}
		
		if (pontos1 == 7) //FOUR
		{
			int cartaFour1 = 0;
			int cartaFour2 = 0;
			
			for (int i = 0; i < 2; i++)
			{
				int temp1 = i+1;
				int temp2 = i+2;
				int temp3 = i+3;
				
				int carta1 = jogador1.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador1.getMaoJogador().cartaEm(temp1).getValor();
				int carta3 = jogador1.getMaoJogador().cartaEm(temp2).getValor();
				int carta4 = jogador1.getMaoJogador().cartaEm(temp3).getValor();
				
				if (carta1 == carta2)
					if (carta2 == carta3)
						if (carta3 == carta4)
						{
							cartaFour1 = carta1;
							break;
						}
			}
			
			for (int i = 0; i < 2; i++)
			{
				int temp1 = i+1;
				int temp2 = i+2;
				int carta1 = jogador2.getMaoJogador().cartaEm(i).getValor();
				int carta2 = jogador2.getMaoJogador().cartaEm(temp1).getValor();
				int carta3 = jogador2.getMaoJogador().cartaEm(temp2).getValor();
				int carta4 = jogador2.getMaoJogador().cartaEm(temp2).getValor();
				
				if (carta1 == carta2)
					if (carta2 == carta3)
						if (carta3 == carta4)
						{
							cartaFour2 = carta1;
							break;
						}
			}
			
			if (cartaFour1 > cartaFour2)
				return jogador1.getId();
			else
				return jogador2.getId();
		}

		if (pontos1 == 8)//STRAIGHT FLUSH
		{
			for (int i = 0; i < 5; i++)
			{
				int carta = jogador1.getMaoJogador().cartaEm(i).compareTo(jogador2.getMaoJogador().cartaEm(i));
				
				if (carta < 0)
					return jogador2.getId();
				
				if (carta > 0)
					return jogador1.getId();
			}
			
			if (jogador1.getMaoJogador().cartaEm(0).getNaipe() > jogador2.getMaoJogador().cartaEm(0).getNaipe())
				return jogador1.getId();
			else
				return jogador2.getId();
		}
		
		if (pontos1 == 9)//ROYAL STRAIGHT FLUSH
		{
			if (jogador1.getMaoJogador().cartaEm(0).getNaipe() > jogador2.getMaoJogador().cartaEm(0).getNaipe())
				return jogador1.getId();
			else
				return jogador2.getId();
		}
		return 0;
	}

	private void vencedor(int jogador) {
		for (int i = 1 ; i < 5 ; i++)
		{
			this.controleJogadores.getJogador(jogador).AdicionaDinheiro(controleCartas.getPool(i));
		}
		
		for (int i = 1; i < 5; i++)
		{
			controleJogadores.viraCartasJogadorParaBaixo(i);
			if (!controleJogadores.getJogador(i).getAmarelou())
			{
				controleJogadores.viraCartasJogadorParaCima(i);
			}
		}
		
		controleVista.getVistaPoquer().setFasePremiacao(true);
		
		controleVista.desenhaTela();
	}

	public void vezJogador(int jogador) {
		controleJogadores.viraCartasJogadorParaBaixo(1);
		controleJogadores.viraCartasJogadorParaBaixo(2);
		controleJogadores.viraCartasJogadorParaBaixo(3);
		controleJogadores.viraCartasJogadorParaBaixo(4);
		controleJogadores.viraCartasJogadorParaCima(jogador);

		controleVista.desenhaTela();
	}
	
	public void novaRodada()
	{
		controleCartas.zeraPool();
		int counter = 0;
		
		for (int i = 1; i < 5; i++)
		{
			if (controleJogadores.getJogador(i).verificaDinheiro() != 0)
			{
				this.controleJogadores.getJogador(i).removeDinheiro(1);
				controleCartas.setPool(i, 1);
			}
		}
		
		for (int i = 1; i < 5; i++)
		{
			if (controleJogadores.getJogador(i).verificaDinheiro() != 0)
			{
				controleJogadores.getJogador(i).setAmarelou(false);
			}
			else
			{
				controleJogadores.getJogador(i).setAmarelou(true);
				counter++;
			}
			controleJogadores.viraCartasJogadorParaCima(i);
		}

		if (counter == 3)
		{
			controleVista.getVistaPoquer().alerta.showMessageDialog(null, "Fim de Jogo", "Alerta", JOptionPane.ERROR_MESSAGE);
			controleVista.getVistaPoquer().setDistribuido(true);
			controleVista.getVistaPoquer().setFasePremiacao(false);
			controleVista.getVistaPoquer().setFaseApostas(false);
			controleVista.getVistaPoquer().setFaseTroca(false);
			
			
			return;
		}
		
		try {
			controleJogadores.devolveTodasCartas(controleCartas);
		} catch (BaralhoVazioException e) {
			e.printStackTrace();
		}
		
		controleCartas.baralhar();
		
		controleJogadores.setAndistaOrdemGeral(controleJogadores.proximo(controleJogadores.getAndistaOrdemGeral()));
		System.out.println(controleJogadores.getAndistaOrdemGeral());
		controleJogadores.setAndista(controleJogadores.getAndistaOrdemGeral());
		controleJogadores.setAndistaInicial(controleJogadores.getAndistaOrdemGeral());
		
		donoRodada = 0;
		apostaDonoRodada = 0;
		segundaRodada = false;
	}
	
}
