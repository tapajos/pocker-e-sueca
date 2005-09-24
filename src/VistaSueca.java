import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

public class VistaSueca extends VistaPrincipal {
	
	private static final int NOME_JOGADOR_4_ALTURA = 25;
	private static final int NOME_JOGADOR_4_LARGURA = 100;
	private static final int NOME_JOGADOR_4_Y = 120;
	private static final int NOME_JOGADOR_4_X = 3;
	private static final int NOME_JOGADOR_3_ALTURA = 25;
	private static final int NOME_JOGADOR_3_LARGURA = 100;
	private static final int NOME_JOGADOR_3_Y = 10;
	private static final int NOME_JOGADOR_3_X = 370;
	private static final int NOME_JOGADOR_2_ALTURA = 25;
	private static final int NOME_JOGADOR_2_LARGURA = 100;
	private static final int NOME_JOGADOR_2_y = 120;
	private static final int NOME_JOGADOR_2_X = 400;
	private static final int NOME_JOGADOR_1_ALTURA = 25;
	private static final int NOME_JOGADOR_1_LARGURA = 100;
	private static final int NOME_JOGADOR_1_Y = 380;
	private static final int NOME_JOGADOR_1_X = 250;
	private static final int LABEL_TRUNFO_ALTURA = 25;
	private static final int LABEL_TRUNFO_LARGURA = 100;
	private static final int LABEL_TRUNFO_X = 540;
	private static final int LABEL_TRUNFO_Y = 10;
	private static final int BOTAO_DISTRIBUI_ALTURA = 25;
	private static final int BOTAO_DISTRIBUI_LARGURA = 200;
	private static final int BOTAO_DISTRIBUI_HORARIO_Y = 75;
	private static final int BOTAO_DISTRIBUI_HORARIO_X = 100;
	private static final int BOTAO_DISTRIBUI_ANTI_HORARIO_Y = 50;
	private static final int BOTAO_DISTRIBUI_ANTI_HORARIO_X = 100;
	private ActionListener al;
	private Vector cartas;
	public  JPanel contentPane;
	public  JButton botaoTrocarCartas;
	public  JButton botaoDistribuiHorario;
	public  JButton botaoDistribuiAntiHorario;
	public  JButton botaoBaralhar;
	public  JLabel labelNomeJogador1;
	public  JLabel labelNomeJogador2;
	public  JLabel labelNomeJogador3;
	public  JLabel labelNomeJogador4;
	public  JLabel labelPontuacao;
	public  JLabel labelPontuacaoDupla1;
	public  JLabel labelPontuacaoDupla2;
	public  JLabel labelPontosDupla1;
	public  JLabel labelPontosDupla2;
	public  JLabel labelRepositorioDupla1;
	public  JLabel labelRepositorioDupla2;
	public  JLabel labelTrunfo;
	public JOptionPane alerta;
	
	public VistaSueca(ActionListener al) {
		super();	
		this.al = al;
		this.setTitle("Jogo de Sueca");
		this.InicializaComponentesTelaSueca();
	}
	
	public void InicializaComponentesTelaSueca(){
        
		/*Crio o painel(verde) que vai conter as cartas e tudo mais*/
        contentPane = new JPanel();
        contentPane.setBackground(Color.GREEN);        
        contentPane.setPreferredSize(new Dimension(600,600));
        
        cartas = new Vector();
        botaoDistribuiHorario = new JButton("Distribuir no Sentido Horário");
        botaoDistribuiAntiHorario = new JButton("Distribuir no Sentido Anti-Horário");
        botaoBaralhar = new JButton("Baralhar");
        labelNomeJogador1 = new JLabel();
        labelNomeJogador2 = new JLabel();
        labelNomeJogador3 = new JLabel();
        labelNomeJogador4 = new JLabel();
        labelPontuacao = new JLabel("Pontuação:");
        labelPontuacaoDupla1 = new JLabel("Dupla 1:");
        labelPontuacaoDupla2 = new JLabel("Dupla 2:");
        labelTrunfo = new JLabel("Trunfo:");
        labelPontosDupla1 = new JLabel("0");
        labelPontosDupla2 = new JLabel("0");
        labelRepositorioDupla1 = new JLabel("Repositório Dupla 1");
        labelRepositorioDupla2 = new JLabel("Repositório Dupla 2");
        alerta = new JOptionPane();
       
        botaoBaralhar.setVisible(true);
        botaoDistribuiHorario.setVisible(false);
        botaoDistribuiAntiHorario.setVisible(false);
        labelPontuacao.setVisible(true);
        labelPontuacaoDupla1.setVisible(true);
        labelPontuacaoDupla2.setVisible(true);
        labelPontosDupla1.setVisible(true);
        labelPontosDupla2.setVisible(true);
        labelNomeJogador1.setVisible(true);
        labelNomeJogador2.setVisible(true);
        labelNomeJogador3.setVisible(true);
        labelNomeJogador4.setVisible(true);
        labelTrunfo.setVisible(false);
     
        addComponent(contentPane,labelRepositorioDupla1,0,0,NOME_JOGADOR_1_LARGURA,15);
        addComponent(contentPane,labelRepositorioDupla1,0,0,NOME_JOGADOR_1_LARGURA,15);
        addComponent(contentPane,labelRepositorioDupla2,400,380,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        addComponent(contentPane,botaoBaralhar,110,70,80,BOTAO_DISTRIBUI_ALTURA);
        addComponent(contentPane,botaoDistribuiHorario,BOTAO_DISTRIBUI_HORARIO_X,BOTAO_DISTRIBUI_HORARIO_Y,BOTAO_DISTRIBUI_LARGURA,BOTAO_DISTRIBUI_ALTURA);
        addComponent(contentPane,botaoDistribuiAntiHorario,BOTAO_DISTRIBUI_ANTI_HORARIO_X,BOTAO_DISTRIBUI_ANTI_HORARIO_Y,BOTAO_DISTRIBUI_LARGURA,BOTAO_DISTRIBUI_ALTURA);
        
        addComponent(contentPane,labelPontuacao,450,0,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        addComponent(contentPane,labelPontuacaoDupla1,450,20,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        addComponent(contentPane,labelPontuacaoDupla2,450,40,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        addComponent(contentPane,labelPontosDupla1,500,20,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        addComponent(contentPane,labelPontosDupla2,500,40,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        
        addComponent(contentPane,labelNomeJogador1,NOME_JOGADOR_1_X,NOME_JOGADOR_1_Y,NOME_JOGADOR_1_LARGURA,NOME_JOGADOR_1_ALTURA);
        addComponent(contentPane,labelNomeJogador2,NOME_JOGADOR_2_X,NOME_JOGADOR_2_y,NOME_JOGADOR_2_LARGURA,NOME_JOGADOR_2_ALTURA);
        addComponent(contentPane,labelNomeJogador3,NOME_JOGADOR_3_X,NOME_JOGADOR_3_Y,NOME_JOGADOR_3_LARGURA,NOME_JOGADOR_3_ALTURA);
        addComponent(contentPane,labelNomeJogador4,NOME_JOGADOR_4_X,NOME_JOGADOR_4_Y,NOME_JOGADOR_4_LARGURA,NOME_JOGADOR_4_ALTURA);
        addComponent(contentPane,labelTrunfo,LABEL_TRUNFO_X,LABEL_TRUNFO_Y,LABEL_TRUNFO_LARGURA,LABEL_TRUNFO_ALTURA);

        botaoBaralhar.addActionListener(al);
        botaoDistribuiHorario.addActionListener(al);
        botaoDistribuiAntiHorario.addActionListener(al);
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
	}
	
	
}
