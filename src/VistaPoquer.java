import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class VistaPoquer extends VistaPrincipal {
	
	private ActionListener al;
	private Vector cartas;
	public  JPanel contentPane;
	public  JButton botaoTrocarCartas;
	public  JButton botaoApostar;
	public  JButton botaoDistribui;
	public  JButton botaoSairRodada;
	public 	JButton botaoMesa;
	public 	JButton botaoNovaRodada;
	public  JTextField textoAposta;
	public  JLabel labelNomeJogador1;
	public  JLabel labelNomeJogador2;
	public  JLabel labelNomeJogador3;
	public  JLabel labelNomeJogador4;
	public  JLabel labelDinheiroJogador1;
	public  JLabel labelDinheiroJogador2;
	public  JLabel labelDinheiroJogador3;
	public  JLabel labelDinheiroJogador4;
	public  JLabel labelPoolJogador1;
	public  JLabel labelPoolJogador2;
	public  JLabel labelPoolJogador3;
	public  JLabel labelPoolJogador4;
	public  JLabel labelDinheiroNaMesa;
	public JOptionPane alerta;
	private boolean distribuido = false;
	private boolean faseApostas = false;
	private boolean faseTroca = false;
	private boolean fasePremiacao= false;
	
	
	public VistaPoquer(ActionListener al) {
		super();	
		this.al = al;
		this.setTitle("Jogo de Poquer");
		this.InicializaComponentesTelaPoquer();
	}
	
	public void InicializaComponentesTelaPoquer(){
        
		/*Crio o painel(verde) que vai conter as cartas e tudo mais*/
        contentPane = new JPanel();		
        contentPane.setBackground(Color.GREEN);        
        contentPane.setPreferredSize(new Dimension(600, 500));
        
        cartas = new Vector();
        botaoTrocarCartas = new JButton("Troca Cartas!");
        botaoApostar = new JButton("Apostar");
        botaoDistribui = new JButton("Distribuir");
        botaoNovaRodada = new JButton("Nova Rodada");
        botaoSairRodada = new JButton("Sair da Rodada");
        botaoMesa = new JButton("Mesa");
        labelNomeJogador1 = new JLabel();
        labelNomeJogador2 = new JLabel();
        labelNomeJogador3 = new JLabel();
        labelNomeJogador4 = new JLabel();
        labelDinheiroJogador1 = new JLabel();
        labelDinheiroJogador2 = new JLabel();
        labelDinheiroJogador3 = new JLabel();
        labelDinheiroJogador4 = new JLabel();
        labelPoolJogador1 = new JLabel();
        labelPoolJogador2 = new JLabel();
        labelPoolJogador3 = new JLabel();
        labelPoolJogador4 = new JLabel();
        labelDinheiroNaMesa = new JLabel();
        alerta = new JOptionPane();
        textoAposta = new JTextField();
       
        labelNomeJogador1.setVisible(true);
        labelNomeJogador2.setVisible(true);
        labelNomeJogador3.setVisible(true);
        labelNomeJogador4.setVisible(true);
        labelPoolJogador1.setVisible(true);
        labelPoolJogador2.setVisible(true);
        labelPoolJogador3.setVisible(true);
        labelPoolJogador4.setVisible(true);
        labelDinheiroJogador1.setVisible(true);
        labelDinheiroJogador2.setVisible(true);
        labelDinheiroJogador3.setVisible(true);
        labelDinheiroJogador4.setVisible(true);
        labelDinheiroNaMesa.setVisible(true);
        
        if (distribuido)
        {
         	botaoDistribui.setVisible(false);
        }
        else
        {
         	botaoDistribui.setVisible(true);
        }
         
        if (faseApostas)
        {
        	botaoApostar.setVisible(true);
        	botaoSairRodada.setVisible(true);
        	botaoMesa.setVisible(true);
        	textoAposta.setVisible(true);
        }
        else
        {
        	botaoApostar.setVisible(false);
        	botaoSairRodada.setVisible(false);
        	botaoMesa.setVisible(false);
        	textoAposta.setVisible(false);
        }
        
        if (faseTroca)
        {
        	botaoTrocarCartas.setVisible(true);
        }
        else
        {
        	botaoTrocarCartas.setVisible(false);
        }
        
        if (fasePremiacao)
        {
        	botaoNovaRodada.setVisible(true);
        }
        else
        {
        	botaoNovaRodada.setVisible(false);
        }
        
        addComponent(contentPane,botaoTrocarCartas,0,0,100,25);
        addComponent(contentPane,botaoMesa,0,0,100,25);
        addComponent(contentPane,botaoApostar,0,25,100,25);
        addComponent(contentPane,botaoDistribui,0,50,100,25);
        addComponent(contentPane,botaoNovaRodada,0,50,100,25);
        addComponent(contentPane,botaoSairRodada,0,50,100,25);
         
        addComponent(contentPane,labelNomeJogador1,350,310,100,25);
        addComponent(contentPane,labelNomeJogador2,440,80,100,25);
        addComponent(contentPane,labelNomeJogador3,350,3,100,25);
        addComponent(contentPane,labelNomeJogador4,3,80,100,25);
        addComponent(contentPane,labelDinheiroJogador1,350,330,100,25);
        addComponent(contentPane,labelDinheiroJogador2,440,100,100,25);
        addComponent(contentPane,labelDinheiroJogador3,350,23,100,25);
        addComponent(contentPane,labelDinheiroJogador4,3,100,100,25);   
        addComponent(contentPane,labelDinheiroNaMesa,350,280,100,25);
        addComponent(contentPane,labelPoolJogador1,350,350,100,25);
        addComponent(contentPane,labelPoolJogador2,440,120,100,25);
        addComponent(contentPane,labelPoolJogador3,350,43,100,25);
        addComponent(contentPane,labelPoolJogador4,3,120,100,25);   
    
        
        addComponent(contentPane,textoAposta,101,25,50,25);

        botaoTrocarCartas.addActionListener(al);
        botaoMesa.addActionListener(al);
        botaoApostar.addActionListener(al);
        botaoDistribui.addActionListener(al);
        botaoSairRodada.addActionListener(al);
        botaoNovaRodada.addActionListener(al);

	}

	public void AdicionaCarta(ImagemCarta carta,int posX,int posY,int tamX,int tamY){
		addComponent(contentPane,carta,posX,posY, 60,80);
		carta.addActionListener(al);
	}
	
	public void ConfiguraTela(){
        contentPane.setOpaque(true);
        contentPane.setLayout(null);
        this.getContentPane().add(contentPane, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(600, 500));
	}
	
	public boolean getDistribuido()
	{
		return distribuido;
	}
	
	public boolean getFaseApostas()
	{
		return faseApostas;
	}
	
	public boolean getFaseTroca()
	{
		return faseTroca;
	}
	
	public boolean getFasePremiacao()
	{
		return fasePremiacao;
	}
	
	public void setDistribuido(boolean distribui)
	{
		distribuido = distribui;
	}
	
	public void setFaseApostas(boolean apostas)
	{
		faseApostas = apostas;
	}
	
	public void setFaseTroca(boolean troca)
	{
		faseTroca = troca;
	}
	
	public void setFasePremiacao(boolean premiacao)
	{
		fasePremiacao = premiacao;
	}
	
	
}
