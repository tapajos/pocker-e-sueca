/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;

/* 1 - Copas
 * 2 - Ouros
 * 3 - Espadas
 * 4 - Paus
 * 
 * 01 - As
 * 02 - Dois
 * 03 - Três
 * 04 - Quatro
 * 05 - Cinco
 * 06 - Seis
 * 07 - Sete
 * 08 - Oito
 * 09 - Nove
 * 10 - Dez
 * 11 - Valete
 * 12 - Dama
 * 13 - Rei
 */ 


public class ImagemCarta extends JButton {
	static String diretorioImagens = "imagens/";
	private String nome;
	private int dono;
	private boolean cartaViradaPraCima;
	private boolean troca;
		
	public ImagemCarta(String nome) {
		this.setIcon(new ImageIcon(diretorioImagens + nome + ".GIF"));
		this.nome = nome;
		this.dono=5;
	}
	
	public void InverteCarta(){
		if(this.cartaViradaPraCima)
			this.viraCartaPraBaixo();
		else	
			this.viraCartaPraCima();		
	}
	
	public void viraCartaPraCima(){
			this.setIcon(new ImageIcon(diretorioImagens +this.nome+".gif"));
			this.cartaViradaPraCima = true;		
	}
	
	public void viraCartaPraBaixo(){
			this.setIcon(new ImageIcon(diretorioImagens +"fundo.gif"));
			this.cartaViradaPraCima = false;		
	}
	
	public boolean cartaViradaPraCima(){
		if (this.cartaViradaPraCima) return true;
		else return false;
	}
	
	public String Nome(){
		return this.nome;
	}
	
	public void setDono(int nomeDono){
		this.dono = nomeDono;
	}
	
	public int getDono(){
		return this.dono;
	}
	
	public void setTroca(){
		this.troca = !this.troca;		
	}
	
	public boolean getEstadoTroca(){
		return this.troca;		
	}
	
	
}
