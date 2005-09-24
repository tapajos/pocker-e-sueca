import java.awt.event.ActionListener;

/*
 * DCC - Departamento de Ci�ncia da Computa��o
 *  Marcos Tapaj�s - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class VistaMenu extends VistaPrincipal {

	private ActionListener al;
	
	public VistaMenu(ActionListener al) {
		super();	
		this.al = al;
		this.setTitle("Trabalho de POO");
		this.InicializaMenu();
	}

	private void InicializaMenu(){
		super.jogoPoquer.addActionListener(this.al);
		super.jogoSueca.addActionListener(this.al);
	}
	
	
}
