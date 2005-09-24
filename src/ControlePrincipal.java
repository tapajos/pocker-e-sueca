import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class ControlePrincipal implements ActionListener{
	
	VistaPrincipal vista;
	ControlePrincipal(){
		vista= new VistaMenu(this);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource()== vista.jogoPoquer){
			try {
				ControlePoquer controle = new ControlePoquer();
			} catch (BaralhoVazioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (evento.getSource()== vista.jogoSueca){
			try {
				ControleSueca controle = new ControleSueca();
			} catch (BaralhoVazioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
