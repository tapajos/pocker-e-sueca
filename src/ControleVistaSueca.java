/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.UIManager;


public class ControleVistaSueca implements ActionListener {

	private boolean novaRodada = false;
	private VistaSueca vista;
    private ControleCartasSueca ControleCartas;
    private ControleJogadoresSueca ControleJogadores;
    private ControleSueca ControleSueca;
    private Vector cartasRepositorio; 
    public Vector j1 ;
    public Vector j2 ;
    public Vector j3 ;
    public Vector j4 ;
    private int tamXcarta = 60;
    private int tamYcarta = 80;
    private int posXbaralho = 500;
    private int posYbaralho = 100;
    private boolean cartasDistribuidas = false;
    private boolean baralhou = false;
	
    public ControleVistaSueca(ControleCartasSueca ControleCartasSueca,
    						   ControleJogadoresSueca ControleJogadoresSueca,
							   ControleSueca ControleSueca
							   ){
    	vista = new VistaSueca(this);
    	this.ControleCartas = ControleCartasSueca;
    	this.ControleJogadores = ControleJogadoresSueca;
    	this.ControleSueca = ControleSueca;
    	configuraInterface();
    }
    
    /**
	 * Configura a interface ativando o Look and Feel
	 */
	private void configuraInterface() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try	{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex) {
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
	}

            
	public void actionPerformed(ActionEvent evento) {
		
		if (evento.getSource()== vista.botaoBaralhar)
		{
			ControleSueca.baralhar();
			vista.botaoDistribuiHorario.setVisible(true);
			vista.botaoDistribuiAntiHorario.setVisible(true);
			vista.botaoBaralhar.setVisible(false);
			this.baralhou = true;
		}
		else if (evento.getSource()== vista.botaoDistribuiHorario)
		{
			try {
				//Aqui eu tenho que receber o andista
				ControleSueca.distribuiCartasHorario(1);
				CartaSueca carta = ControleSueca.retornaTrunfo();
			} catch (BaralhoVazioException e) {
				e.printStackTrace();
			}	
			this.setCartasDistribuidas();
			this.desenhaTela();
			vista.botaoDistribuiHorario.setVisible(false);
			vista.botaoDistribuiAntiHorario.setVisible(false);
			vista.botaoBaralhar.setVisible(false);
		}
		
		else if (evento.getSource()== vista.botaoDistribuiAntiHorario)
		{
			try {
				//Aqui eu tenho que receber o andista
				ControleSueca.distribuiCartasAntiHorario(1);
				CartaSueca carta = ControleSueca.retornaTrunfo();
			} catch (BaralhoVazioException e) {
				e.printStackTrace();
			}	
			this.setCartasDistribuidas();
			this.desenhaTela();
			vista.botaoDistribuiHorario.setVisible(false);
			vista.botaoDistribuiAntiHorario.setVisible(false);
			vista.botaoBaralhar.setVisible(false);
		}
		
		else {
			// Só vou permitir o clique da carta se o jogador for o andista
			if ( ((ImagemCarta) evento.getSource()).getDono()== ControleSueca.getAndista()){
				ControleSueca.cliqueCarta((ImagemCarta) evento.getSource());
				this.desenhaTela();
				vista.botaoBaralhar.setVisible(false);
			}
		}
		
	}

	public void desenhaTela(){
		vista.remove(vista.contentPane);
		j1 = new Vector();
		j2 = new Vector();
		j3 = new Vector();
		j4 = new Vector();
		vista.InicializaComponentesTelaSueca();
		
		cartasRepositorio = new Vector();
		CartaSueca carta;
		ImagemCarta imagem;
		Iterator iterator1 = null;
		iterator1 = this.ControleCartas.IteratorBaralho();
		
		//Desenho o baralho na mesa
		for (int i=0;i<this.ControleCartas.NumeroDeCartas();i++){
			carta = (CartaSueca) iterator1.next();
			imagem = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
			imagem.viraCartaPraBaixo();
			imagem.setDono(5);
			cartasRepositorio.add(imagem);
			vista.AdicionaCarta( (ImagemCarta)cartasRepositorio.get(i),300-(i*5),posYbaralho,tamXcarta,tamYcarta);
		}

		if (cartasDistribuidas)
		{
			vista.botaoDistribuiHorario.setVisible(false);
			vista.botaoDistribuiAntiHorario.setVisible(false);
			vista.botaoBaralhar.setVisible(false);
			
		// desenha Mão dos Jogadores
					
			// JOGADOR 1
			Iterator iterator = null;
			iterator = this.ControleJogadores.cartasNaMaoJogador1();
			ImagemCarta imagemCarta;
			for(int i=ControleJogadores.QuantidadeDeCartasNaMao(1)-1;i>=0;i--){
				carta = (CartaSueca) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j1.add(imagemCarta);
				imagemCarta.setDono(1);
				vista.AdicionaCarta( imagemCarta,200+i*12,280,tamXcarta,tamYcarta);
			}
			
	
			
			// JOGADOR 2
	
			iterator = this.ControleJogadores.cartasNaMaoJogador2();
			
			for(int i=ControleJogadores.QuantidadeDeCartasNaMao(2)-1;i>=0;i--){
				carta = (CartaSueca) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j2.add(imagemCarta);
				imagemCarta.setDono(2);
				vista.AdicionaCarta( imagemCarta,400,150+i*14,tamXcarta,tamYcarta);
			}
			
			// JOGADOR 3
	
			iterator = this.ControleJogadores.cartasNaMaoJogador3();
			
			for(int i=ControleJogadores.QuantidadeDeCartasNaMao(3)-1;i>=0;i--){
				carta = (CartaSueca) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j3.add(imagemCarta);
				imagemCarta.setDono(3);
				vista.AdicionaCarta( imagemCarta,200+i*12,0,tamXcarta,tamYcarta);
			}
			
			// JOGADOR 4
	
			iterator = this.ControleJogadores.cartasNaMaoJogador4();
			
			for(int i=ControleJogadores.QuantidadeDeCartasNaMao(4)-1;i>=0;i--){
				carta = (CartaSueca) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j4.add(imagemCarta);
				imagemCarta.setDono(4);
				vista.AdicionaCarta( imagemCarta,0,150+i*14,tamXcarta,tamYcarta);
			}
		}

		//Desenho cartas que os jogadores vão jogando na mesa
		CartaSueca cartaMesa = ControleCartas.RetornaCartasNaMesa(1);
		if (cartaMesa != null){
			ImagemCarta imagemCartaMesa = new ImagemCarta(String.valueOf(cartaMesa.getNaipe()+1)+String.valueOf(cartaMesa.getValor()));
			vista.AdicionaCarta( imagemCartaMesa,230,200,tamXcarta,tamYcarta);
		}
		
		cartaMesa = ControleCartas.RetornaCartasNaMesa(2);
		if (cartaMesa != null){
			ImagemCarta imagemCartaMesa = new ImagemCarta(String.valueOf(cartaMesa.getNaipe()+1)+String.valueOf(cartaMesa.getValor()));	
			vista.AdicionaCarta( imagemCartaMesa,310,140,tamXcarta,tamYcarta);;
		}

		cartaMesa = ControleCartas.RetornaCartasNaMesa(3);
		if (cartaMesa != null){
			ImagemCarta imagemCartaMesa = new ImagemCarta(String.valueOf(cartaMesa.getNaipe()+1)+String.valueOf(cartaMesa.getValor()));	
			vista.AdicionaCarta( imagemCartaMesa,230,90,tamXcarta,tamYcarta);;
		}
		
		cartaMesa = ControleCartas.RetornaCartasNaMesa(4);
		if (cartaMesa != null){
			ImagemCarta imagemCartaMesa = new ImagemCarta(String.valueOf(cartaMesa.getNaipe()+1)+String.valueOf(cartaMesa.getValor()));	
			vista.AdicionaCarta( imagemCartaMesa,140,140,tamXcarta,tamYcarta);;
		}
		
		//Desenho os repositórios das Duplas
		Iterator iteratorRepositorioMesa = ControleCartas.IteratorRepositorioDupla1();
		if (iteratorRepositorioMesa.hasNext()){
			int i=0;
			while(iteratorRepositorioMesa.hasNext()){
				cartaMesa = (CartaSueca)iteratorRepositorioMesa.next();
				ImagemCarta imagemCartaMesa = new ImagemCarta(String.valueOf(cartaMesa.getNaipe()+1)+String.valueOf(cartaMesa.getValor()));
				imagemCartaMesa.viraCartaPraBaixo();
				vista.AdicionaCarta( imagemCartaMesa,0+i*3,20,tamXcarta,tamYcarta);
				i ++;
			}
		}
	
		iteratorRepositorioMesa = ControleCartas.IteratorRepositorioDupla2();
		
		if (iteratorRepositorioMesa.hasNext()){
			int i=0;
			while(iteratorRepositorioMesa.hasNext()){
				cartaMesa = (CartaSueca)iteratorRepositorioMesa.next();
				ImagemCarta imagemCartaMesa = new ImagemCarta(String.valueOf(cartaMesa.getNaipe()+1)+String.valueOf(cartaMesa.getValor()));
				imagemCartaMesa.viraCartaPraBaixo();
				vista.AdicionaCarta( imagemCartaMesa,400+i*3,400,tamXcarta,tamYcarta);;
				i++;
			}
		}

		//Desenha o trunfo
		if (ControleSueca.retornaTrunfo()!=null){
			ImagemCarta trunfo = new ImagemCarta(String.valueOf(ControleSueca.retornaTrunfo().getNaipe()+1)+String.valueOf(ControleSueca.retornaTrunfo().getValor()));
			vista.AdicionaCarta( trunfo,530,45,tamXcarta,tamYcarta);
			vista.labelTrunfo.setVisible(true);
		}
		
		//Desenha Nomes dos Jogadores
		vista.labelNomeJogador1.setText(ControleJogadores.getNomeJogador(1)); 
		vista.labelNomeJogador2.setText(ControleJogadores.getNomeJogador(2));
		vista.labelNomeJogador3.setText(ControleJogadores.getNomeJogador(3));
		vista.labelNomeJogador4.setText(ControleJogadores.getNomeJogador(4));			
		
		vista.ConfiguraTela();
		
		if (this.baralhou) vista.botaoBaralhar.setVisible (false);
		else vista.botaoBaralhar.setVisible (true);
		
		if(this.novaRodada){
			vista.botaoDistribuiAntiHorario.setVisible(false);
			vista.botaoDistribuiHorario.setVisible(false);
			vista.botaoBaralhar.setVisible(true);
			this.novaRodada = false;
			this.baralhou = false;
		}
		
		vista.labelPontosDupla1.setText(String.valueOf(ControleJogadores.RetornaPontuaçãoDupla(1)));
		vista.labelPontosDupla2.setText(String.valueOf(ControleJogadores.RetornaPontuaçãoDupla(2)));
		
	}

	public boolean getCartasDistribuidas()
	{
		return cartasDistribuidas;
	}
	
	public void setCartasDistribuidas()
	{
		cartasDistribuidas = true;
	}
	
	public void novoJogo(){
		this.novaRodada = true;
		vista.botaoDistribuiAntiHorario.setVisible(false);
		vista.botaoDistribuiHorario.setVisible(false);
	   	vista.botaoBaralhar.setVisible(true);
	}
	
	public void setNaoBaralhou(){
		this.baralhou = false;
	}
	
}
