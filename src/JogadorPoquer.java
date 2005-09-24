/*
 * DCC - Departamento de Ci�ncia da Computa��o
 *  Marcos Tapaj�s - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class JogadorPoquer extends Jogador {
	
	float dinheiro;
	boolean amarelou = false;
	MaoPoquer maoJogador = new MaoPoquer();
	
	public void AdicionaDinheiro (float bolsa){
		//this.dinheiro = dinheiro;
		this.dinheiro += bolsa;
	}
	
	public float verificaDinheiro(){
		return this.dinheiro;
	}
	
	public boolean podeRemoverDinheiro (float dinheiro){
		if (verificaDinheiro() >= dinheiro){
			return true;
		}
		else return false;
	}
	
	public void removeDinheiro(float aposta)
	{
		this.dinheiro -= aposta;
	}
	
	public void setAmarelou(boolean amarelar)
	{
		this.amarelou = amarelar;
	}
	
	public boolean getAmarelou()
	{
		return this.amarelou;
	}
	
	// O Situa��o retorna -1 se o usu�rio amarelou e o cacife dele se ele n�o amarelou
	public float situacao(){
		if (this.amarelou) return -1;
		else return this.dinheiro;
	}
	
	public MaoPoquer getMaoJogador()
	{
		return this.maoJogador;
	}

}
