
package modelo;

public class Armacao
{
    private int codigo;
    private String material;
    private String cor;
    private String formato;
    private String marca;
    private String tamanho;
    private float valor;
    private int estoque;
    
    public Armacao(int codigo, String material, String cor,String formato,String marca,String tamanho, float valor, int estoque){
    	this.codigo = codigo;
    	this.material = material;
        this.cor = cor;
        this.formato = formato;
        this.marca = marca;
        this.tamanho = tamanho;
        this.valor = valor;
        this.estoque = estoque;
    }
    
    public Armacao() {
    	
    }
    
    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setMaterial (String material){
        this.material = material;
    }
    
    public String getMaterial (){     
        return material;
    }
    
    public void setCor (String cor){
        this.cor = cor;
    }
    
    public String getCor (){    
        return cor;
    }
    
    public void setMarca (String marca){
        this.marca = marca;
    }
    
    public String getFormato (){       
        return formato;
    }
    
    public void setFormato (String formato){
        this.formato = formato;
    }
    
    public String getMarca (){       
        return marca;
    }
    
    public void setTamanho (String tamanho){
        this.tamanho = tamanho; 
    }
    
    public String getTamanho (){        
        return tamanho;
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

	public int getEstoqueArm() {
		return estoque;
	}

	public void setEstoqueArm(int estoque) {
		this.estoque = estoque;
	}
    
}
