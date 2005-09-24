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
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class ControleVistaPoquer implements ActionListener{

    private VistaPoquer vista;
    private ControleCartasPoquer controleCartas;
    private ControleJogadoresPoquer controleJogadores;
    private ControlePoquer controlePoquer;
    private  Vector cartasRepositorio; 
    public Vector j1 ;
    public Vector j2 ;
    public Vector j3 ;
    public Vector j4 ;
    private int tamXcarta = 60;
    private int tamYcarta = 80;
    private int posXbaralho = 500;
    private int posYbaralho = 100;
    private boolean cartasDistribuidas = false;
    private boolean inicializado = true;
   	
	
    public ControleVistaPoquer(ControleCartasPoquer controleCartas,
    						   ControleJogadoresPoquer controleJogadores,
							   ControlePoquer controlePoquer){
    	vista = new VistaPoquer(this);
    	this.controleCartas = controleCartas;
    	this.controleJogadores = controleJogadores;
    	this.controlePoquer = controlePoquer;
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
			System.out.println("Falhou o carregamento do L&F: ");
			System.out.println(ex);
		}
	}

	public void actionPerformed(ActionEvent evento) {
		
		if (evento.getSource()== vista.botaoTrocarCartas){
			controlePoquer.trocarCartasPoquerModelo(controleJogadores.getAndista());
			this.desenhaTela();
		}
		else if (evento.getSource() == vista.botaoApostar)
		{
			Float temp = new Float(0);
			try
			{
				temp = Float.valueOf(vista.textoAposta.getText());
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				if (e.getMessage().equals("empty String"))
					vista.alerta.showMessageDialog(null, "Digite sua aposta", "Alerta", JOptionPane.ERROR_MESSAGE);
				else
					vista.alerta.showMessageDialog(null, "Numero inválido", "Alerta", JOptionPane.ERROR_MESSAGE);
			}

			controlePoquer.apostas(controleJogadores.getAndista(), temp.floatValue(), false);
			//this.desenhaTela();
		}
		else if (evento.getSource() == vista.botaoMesa)
		{
			controlePoquer.apostas(controleJogadores.getAndista(), 0 , false);
		}
		else if (evento.getSource() == vista.botaoSairRodada)
		{
			controlePoquer.sairDaRodada(controleJogadores.getAndista());
			//this.desenhaTela();
		}
		else if (evento.getSource() == vista.botaoDistribui)
		{
			try {
				controlePoquer.distribuiCartas();
			} catch (BaralhoVazioException e) {
				e.printStackTrace();
			}	
			this.setCartasDistribuidas(true);
			controlePoquer.vezJogador(controleJogadores.getAndistaInicial());
			vista.setDistribuido(true);
			vista.setFaseApostas(true);
			vista.setFaseTroca(false);
			this.desenhaTela();
		}
		else if (evento.getSource() == vista.botaoNovaRodada)
		{
			setCartasDistribuidas(false);
			vista.setFaseApostas(false);
			vista.setFaseTroca(false);
			vista.setFasePremiacao(false);
			vista.setDistribuido(false);
			
			controlePoquer.novaRodada();
			
			this.desenhaTela();
		}
		else if (evento.getSource() == vista.botaoSairRodada)
		{
			controleJogadores.sairDaRodada(controleJogadores.getAndista(), controleJogadores);
			controleJogadores.proximo(controleJogadores.getAndista());
		}
		else {
			this.BotaCartaNaMesa((ImagemCarta) evento.getSource());
			controlePoquer.cliqueCarta((ImagemCarta) evento.getSource());
		}
		
	}
		
	private void BotaCartaNaMesa(ImagemCarta carta){
		if(carta.getDono()==1){
			if(carta.getEstadoTroca()) {
				carta.setLocation(carta.getLocation().x,carta.getLocation().y+60);
				carta.setTroca();				
			}
			else {
				carta.setLocation(carta.getLocation().x,carta.getLocation().y-60);
				carta.setTroca();
			}
		}
		else if (carta.getDono()==2){
			if(carta.getEstadoTroca()) {
				carta.setLocation(carta.getLocation().x+60,carta.getLocation().y);
				carta.setTroca();
			}
			else {
				carta.setLocation(carta.getLocation().x-60,carta.getLocation().y);
				carta.setTroca();
			}
		}
		else if (carta.getDono()==3){
			if(carta.getEstadoTroca()){
				carta.setLocation(carta.getLocation().x,carta.getLocation().y-60);
				carta.setTroca();
			}
			else {
				carta.setLocation(carta.getLocation().x,carta.getLocation().y+60);
				carta.setTroca();
			}
		}
		else if (carta.getDono()==4){
			if(carta.getEstadoTroca()) {
				carta.setLocation(carta.getLocation().x-60,carta.getLocation().y);
				carta.setTroca();
			}
			else {
				carta.setLocation(carta.getLocation().x+60,carta.getLocation().y);
				carta.setTroca();
			}
		}
		
	}

	public void desenhaTela(){
		vista.remove(vista.contentPane);
		j1 = new Vector();
		j2 = new Vector();
		j3 = new Vector();
		j4 = new Vector();
		vista.InicializaComponentesTelaPoquer();
		
		cartasRepositorio = new Vector();
		CartaPoquer carta;
		ImagemCarta imagem;
		Iterator iterator1 = null;
		iterator1 = this.controleCartas.Iterator();
	/*	for (int i=0;i<this.controleCartas.NumeroDeCartas();i++){
			carta = (CartaPoquer) iterator1.next();
			imagem = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
			if (!carta.viradaPraCima()) imagem.viraCartaPraBaixo();
			imagem.setDono("mesa");
			cartasRepositorio.add(imagem);
			//vista.AdicionaCarta( (ImagemCarta)cartasRepositorio.get(i),300-(i*10),posYbaralho,tamXcarta,tamYcarta);		
		}*/
		
		if (cartasDistribuidas)
		{
			// desenha Mão dos Jogadores
			// JOGADOR 1
			Iterator iterator = null;
			iterator = this.controleJogadores.cartasNaMaoJogador1();
			ImagemCarta imagemCarta;
			for(int i=4;i>=0;i--){
				carta = (CartaPoquer) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j1.add(imagemCarta);
				imagemCarta.setDono(1);
				vista.AdicionaCarta( imagemCarta,200+i*12,280,tamXcarta,tamYcarta);
			}
			
	
			
			// JOGADOR 2
	
			iterator = this.controleJogadores.cartasNaMaoJogador2();
			
			for(int i=4;i>=0;i--){
				carta = (CartaPoquer) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j2.add(imagemCarta);
				imagemCarta.setDono(2);
				vista.AdicionaCarta( imagemCarta,400,150+i*14,tamXcarta,tamYcarta);
			}
			
			// JOGADOR 3
	
			iterator = this.controleJogadores.cartasNaMaoJogador3();
			
			for(int i=4;i>=0;i--){
				carta = (CartaPoquer) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j3.add(imagemCarta);
				imagemCarta.setDono(3);
				vista.AdicionaCarta( imagemCarta,200+i*12,0,tamXcarta,tamYcarta);
			}
			
			// JOGADOR 4
	
			iterator = this.controleJogadores.cartasNaMaoJogador4();
			
			for(int i=4;i>=0;i--){
				carta = (CartaPoquer) iterator.next();
				imagemCarta = new ImagemCarta(String.valueOf(carta.getNaipe()+1)+String.valueOf(carta.getValor()));
				if (!carta.viradaPraCima()) imagemCarta.viraCartaPraBaixo();
				this.j4.add(imagemCarta);
				imagemCarta.setDono(4);
				vista.AdicionaCarta( imagemCarta,0,150+i*14,tamXcarta,tamYcarta);
			}
		}
		
		//Desenha Nomes dos Jogadores e seu dinheiro
		vista.labelNomeJogador1.setText(controleJogadores.getNomeJogador(1)); 
		vista.labelNomeJogador2.setText(controleJogadores.getNomeJogador(2));
		vista.labelNomeJogador3.setText(controleJogadores.getNomeJogador(3));
		vista.labelNomeJogador4.setText(controleJogadores.getNomeJogador(4));
		vista.labelDinheiroJogador1.setText(String.valueOf(controleJogadores.getDinheiroJogador(1)));
		vista.labelDinheiroJogador2.setText(String.valueOf(controleJogadores.getDinheiroJogador(2)));
		vista.labelDinheiroJogador3.setText(String.valueOf(controleJogadores.getDinheiroJogador(3)));
		vista.labelDinheiroJogador4.setText(String.valueOf(controleJogadores.getDinheiroJogador(4)));
		vista.labelPoolJogador1.setText(String.valueOf(controleCartas.getPool(1)));
		vista.labelPoolJogador2.setText(String.valueOf(controleCartas.getPool(2)));
		vista.labelPoolJogador3.setText(String.valueOf(controleCartas.getPool(3)));
		vista.labelPoolJogador4.setText(String.valueOf(controleCartas.getPool(4)));
		
		vista.ConfiguraTela();
	}

	public boolean getCartasDistribuidas()
	{
		return cartasDistribuidas;
	}
	
	public void setCartasDistribuidas(boolean distribuidas)
	{
		cartasDistribuidas = distribuidas;
	}
	
	public VistaPoquer getVistaPoquer()
	{
		return vista;
	}
}
