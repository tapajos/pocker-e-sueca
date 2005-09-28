import java.util.Iterator;

/*
 * DCC - Departamento de Ciência da Computação
 *  Marcos Tapajós - DRE:101138637
 *  Bernardo Rocha - DRE:101129832
 *  Carlos Eduardo Lopes - DRE:098115491
 */
public class ControleJogadoresPoquer{
	private JogadorPoquer j1;
	private JogadorPoquer j2;
	private JogadorPoquer j3;
	private JogadorPoquer j4;
	int andista;
	int andistaInicial;
	int andistaOrdemGeral;

	public ControleJogadoresPoquer() {	
		j1 = new JogadorPoquer();
		j2 = new JogadorPoquer();
		j3 = new JogadorPoquer();
		j4 = new JogadorPoquer();
		
		j1.setId(1);
		j2.setId(2);
		j3.setId(3);
		j4.setId(4);
		
		j1.DefineNome("Bernardo");
		j2.DefineNome("Tapajós");
		j3.DefineNome("Sírius");
		j4.DefineNome("Tchutchuco");		
	}
			
	public void distribuiCartas(ControleCartasPoquer controleCartas, int qtde) throws BaralhoVazioException {
		int numeroCartas;
		for (numeroCartas = 0 ; numeroCartas < qtde ; numeroCartas++){
			j1.maoJogador.adicionar(controleCartas.PedirUmaCarta());
			j2.maoJogador.adicionar(controleCartas.PedirUmaCarta());
			j3.maoJogador.adicionar(controleCartas.PedirUmaCarta());
			j4.maoJogador.adicionar(controleCartas.PedirUmaCarta());
		}
		j1.maoJogador.ordenaMao();
		j2.maoJogador.ordenaMao();
		j3.maoJogador.ordenaMao();
		j4.maoJogador.ordenaMao();
	}
	
	public void trocarCarta(int jogador, ControleCartasPoquer controleCartas, int PosicaoCarta) throws BaralhoVazioException{
		controleCartas.DevolveProBaralho((CartaPoquer) this.getJogador(jogador).maoJogador.remover(PosicaoCarta));
		this.getJogador(jogador).maoJogador.adicionar(controleCartas.PedirUmaCarta());
	}
	
	public void devolveTodasCartas(ControleCartasPoquer controleCartas) throws BaralhoVazioException{
		for (int i = 1; i < 5; i++)
			for (int j = 0; j < 5; j++)
				{
					controleCartas.DevolveProBaralho((CartaPoquer) this.getJogador(i).maoJogador.remover(0));
				}
	}
	
	 public Iterator cartasNaMaoJogador1(){
		return j1.maoJogador.iterator();	 	
	 }
	 
	 public Iterator cartasNaMaoJogador2(){
		return j2.maoJogador.iterator();	 	
	 }

	 public Iterator cartasNaMaoJogador3(){
		return j3.maoJogador.iterator();	 	
	 }

	 public Iterator cartasNaMaoJogador4(){
		return j4.maoJogador.iterator();	 	
	 }
	
	 public JogadorPoquer getJogador(int jogador){
	 	if (jogador == 1 ) return this.j1;
		else if (jogador== 2 ) return this.j2;
		else if (jogador== 3 ) return this.j3;
		else return this.j4;
	 }
	 
	 public int getIntJogador(JogadorPoquer jogador){
	 	if (jogador.equals(this.j1)) return 1;
		else if (jogador.equals(this.j2)) return 2;
		else if (jogador.equals(this.j3)) return 3;
		else return 4;
	 }
	 
	 public void setarCacife(float cacife){
	 	j1.AdicionaDinheiro(cacife);
	 	j2.AdicionaDinheiro(cacife);
	 	j3.AdicionaDinheiro(cacife);
	 	j4.AdicionaDinheiro(cacife);
	
	 }
	 
	 public boolean apostar(float aposta, int jogador){
	 	if (this.getJogador(jogador).podeRemoverDinheiro(aposta)) return true;
	 	else return false;
	 }
	 
	 public void ordenaCartasMao(){
	 	this.j1.maoJogador.ordenaMao();
	 	this.j2.maoJogador.ordenaMao();
	 	this.j3.maoJogador.ordenaMao();
	 	this.j4.maoJogador.ordenaMao();	 	
	 }
	 
	public int descobreAndista(ControleJogadoresPoquer controle) throws BaralhoVazioException {
		//TODO
		return 3;
	}
	
	public int getAndista()
	{
		return andista;
	}
	
	public void setAndista(int jogador)
	{
		andista = jogador;
	}
	
	public int getAndistaInicial()
	{
		return andistaInicial;
	}
	
	public void setAndistaInicial(int jogador)
	{
		andistaInicial = jogador;
	}
	
	public int getAndistaOrdemGeral()
	{
		return andistaOrdemGeral;
	}
	
	public void setAndistaOrdemGeral(int jogador)
	{
		andistaOrdemGeral = jogador;
	}
	
	public int proximo(int jogador)
	{
		int retorno = 0;
		
		if (jogador == 1)
			retorno = 2;
		else if (jogador == 2)
			retorno = 3;
		else if (jogador == 3)
			retorno = 4;
		else if (jogador == 4)
			retorno = 1;
		
		if (this.getJogador(retorno).getAmarelou())
			return this.proximo(retorno);
		else		
			return retorno; 
	}
	
	public void proximoAndista()
	{
		andista = this.proximo(andista);
	}
	
	public String getNomeJogador(int jogador){
		return getJogador(jogador).PegaNome();
	}

	public float getDinheiroJogador(int jogador){
		return getJogador(jogador).verificaDinheiro();
	}
	
	
	public void viraCartasJogadorParaBaixo(int jogador){
		for (int i=0;i<this.j1.maoJogador.númeroDeCartas();i++){
			this.getJogador(jogador).maoJogador.ViraCartaPoquerParaBaixo(i);
		}
	}
	
	public void viraCartasJogadorParaCima(int jogador){
		for (int i=0;i<this.j1.maoJogador.númeroDeCartas();i++){
			this.getJogador(jogador).maoJogador.ViraCartaPoquerParaCima(i);
		}
	}
	
	public void sairDaRodada(int jogador, ControleJogadoresPoquer controleJogador){
		controleJogador.getJogador(jogador).setAmarelou(true);
	}
	
	public int numeroDeAmarelados()
	{
		int numeroAmarelados = 0;
		
		for (int i = 1; i<5 ; i++)
		{
			if (getJogador(i).getAmarelou())
				numeroAmarelados++;
		}
		return numeroAmarelados;
	}
}
