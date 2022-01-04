package modelo;

public class Vendedor extends Pessoa {
	
	private float salario;
	private float comissao;// 10 reais por oculos montado
	private int matricula;	

	public Vendedor(String nome, String cpf, String rg, Endereco endereco, String dataNasc, String sexo,
			String logradouro, String numero, String complemento, String bairro, String cidade, String estado,
			String cep, float salario, float comissao, int matricula) {
		super(nome, cpf, rg, endereco, dataNasc, sexo, logradouro, numero, complemento, bairro, cidade, estado, cep);
		this.salario = salario;
		this.comissao = comissao;
		this.matricula = matricula;
	}

	
	public Vendedor() {
		
	}


	public float getSalario() {
		return salario;
	}

	public void setSalario(float NSalario) {
		this.salario = NSalario;
	}

	public float getComissao() {
		return comissao;
	}

	public void setComissao(float NComissao) {
		this.comissao = NComissao;
	}

	public int getCodigo() {
		return matricula;
	}

	public void setCodigo(int matricula) {
		this.matricula = matricula;
	}
}
