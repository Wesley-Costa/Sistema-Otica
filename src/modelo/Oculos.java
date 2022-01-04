package modelo;

public class Oculos {
	private Armacao armacao;
	private Lente lente;
	private boolean montagem;
	private float valorTotal;
	
	public Oculos(int codigoA, String material, String cor, String formato, String marca, String tamanho, 
			float valorArmacao, String mat, String tipo,String grau , float valorLente, int estoqueLente,
			int estoqueArm, int codigo, float valorTotal, boolean montagem) {

		armacao = new Armacao(codigo, material, cor, formato, marca, tamanho, valorArmacao, estoqueArm);
		lente = new Lente(mat, tipo, grau, valorLente, estoqueLente, codigo);
		this.montagem = montagem;
		this.valorTotal = valorTotal;
	}
	

	public Oculos() {
		
	}

	public Armacao getArmacao() {
	
		return armacao;
	}

	public void setArmacao(Armacao armacao) {
		this.armacao = armacao;
	}

	public Lente getLente() {
		return lente;
	}

	public void setLente(Lente lente) {
		this.lente = lente;
	}

	public boolean isMontagem() {
		return montagem;
	}

	public void setMontagem(boolean montagem) {
		this.montagem = montagem;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float venda( Armacao arm, Lente len, float custoArm, float custoLen)
	{
		int est = arm.getEstoqueArm();
		est -= 1;
		arm.setEstoqueArm(est);
		
		est = len.getEstoqueLente();
		est -= 1;
		len.setEstoqueLente(est);
		
		return custoArm + custoLen;
	}
}