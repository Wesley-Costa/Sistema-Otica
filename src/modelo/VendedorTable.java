package modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class VendedorTable extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private	ArrayList<Vendedor> lista;
	private String[] colunas = new String[]{"Código","CPF","Nome"};
	
	public VendedorTable() {
		this.lista = new ArrayList<>();	
	}

	public void add_tabela(Vendedor vendedor) {
		this.lista.add(vendedor);
		fireTableDataChanged();
	}	
			
	public void remover_tabela(int linhaIndice) {
		this.lista.remove(linhaIndice);
		fireTableDataChanged();
	}
	
	public Vendedor getVendedor(int linhaIndice) {
		return this.lista.get(linhaIndice);
	}
	
	public ArrayList<Vendedor> getArray() {
		return lista;
	}
	
	public void setArray(ArrayList<Vendedor> vendedores) {
		this.lista = vendedores;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.colunas[column];
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.lista.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:// coluna código
			return this.lista.get(linha).getCodigo();
		case 1:// coluna nome
			return this.lista.get(linha).getNome();
		case 2:// coluna cpf
			return this.lista.get(linha).getCpf();
	
		default:
			return this.lista.get(linha);
		}
	}

}
