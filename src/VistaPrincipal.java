/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class VistaPrincipal extends JFrame {

	private static final int ALTURA_DO_MENU = 20;
	public static final int ALTURA_TELA = 600;
	public static final int LARGURA_TELA = 600;
	public static final int tamXcarta = 60;
	public static final int tamYcarta = 80;
	public static final int posXbaralho = 500;
	public static final int posYbaralho = 100;
	public JMenuItem jogoSueca;
	public JMenuItem jogoPoquer;
	  
	private JMenuBar Menu;
	public JPanel contentPane;

	public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Crio a barra do menu.
        menuBar = new JMenuBar();
         
        //O primeiro menu.
        menu = new JMenu("Arquivo");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
	       
        submenu = new JMenu("Novo Jogo");
        submenu.setMnemonic(KeyEvent.VK_N);

        menuItem = new JMenuItem("Poquer");
        jogoPoquer = menuItem;
        submenu.add(menuItem);
        
        menuItem = new JMenuItem("Sueca");
        jogoSueca = menuItem;
        submenu.add(menuItem);
        menu.add(submenu);

        //O submenu
        menu.addSeparator();
        menuItem = new JMenuItem("Sair",
                                KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Sair do Programa");
        menu.add(menuItem);

        return menuBar;
    }
		
	public VistaPrincipal(){
		this.setTitle("Trabalho de POO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializaComponentesTela();
	}

	
    private void inicializaComponentesTela() {

    	/*Crio o menu */
    	Menu = createMenuBar();
    	Menu.setOpaque(true);
        Menu.setBackground(Color.lightGray);
        Menu.setPreferredSize(new Dimension(VistaPrincipal.LARGURA_TELA, VistaPrincipal.ALTURA_DO_MENU));


        /*Crio o painel(verde) que vai conter as cartas e tudo mais*/
        contentPane = new JPanel();      
        contentPane.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        contentPane.setOpaque(false);

        
        /*Adiciono o Menu e o Painel ao Frame*/
        this.setJMenuBar(Menu);
        this.getContentPane().add(contentPane, BorderLayout.CENTER);
         
        this.setBackground(Color.GREEN);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
    }
    
	
	/** 
	 * Adiciona um componente sem Layout Manager, usando posição absoluta 
	 */
	protected void addComponent(Container container, Component c, 
							  int x, int y, int width, int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}
	

}
