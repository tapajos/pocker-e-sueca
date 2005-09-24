/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class Jogador {
	
	private String Nome;
	//Mao maoJogador = new Mao();
	
	private int id;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int ident)
	{
		this.id = ident;
	}
	
	public void DefineNome (String nome){
		this.Nome = nome;
	}
	public String PegaNome (){
		return this.Nome;		
	}

}
