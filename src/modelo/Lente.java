package modelo;

public class Lente {
	
	private String material;
	private String tipo;
	private String grau;
	private float valorLente;
	private int estoqueLente;
	private int codigo;

	public Lente (String material, String tipo, String grau, float valorLente, int estoqueLente, 
			int codigo) {
		
		this.material = material;
		this.tipo = tipo;
		this.grau = grau;
		this.valorLente = valorLente;
		this.estoqueLente = estoqueLente;
		this.codigo = codigo;
		
	}
	
	public Lente() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public String getGrau() {
		return grau;
	}

	public float getValorLente() {
		return valorLente;
	}

	public void setValorLente(float valorLente) {
		this.valorLente = valorLente;
	}

	public int getEstoqueLente() {
		return estoqueLente;
	}

	public void setEstoqueLente(int estoqueLente) {
		this.estoqueLente = estoqueLente;
	}
}
