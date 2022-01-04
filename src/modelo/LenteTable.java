package modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel; 

public class LenteTable extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private	ArrayList<Lente> lista;
	private String[] colunas = new String[]{"Código","Material","Tipo", "Grau"};
	
	public LenteTable() {
		this.lista = new ArrayList<>();	
	}

	public void add_tabela(Lente lente) {
		this.lista.add(lente);
		fireTableDataChanged();
	}	
			
	public void remover_tabela(int linhaIndice) {
		this.lista.remove(linhaIndice);
		fireTableDataChanged();
	}
	
	public Lente getLente(int linhaIndice) {
		return this.lista.get(linhaIndice);
	}
	
	public ArrayList<Lente> getArray() {
		return lista;
	}
	
	public void setArray(ArrayList<Lente> lentes) {
		this.lista = lentes;
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
		case 1:// coluna material
			return this.lista.get(linha).getMaterial();
		case 2:// coluna Tipo
			return this.lista.get(linha).getTipo();
		case 3:// coluna Grau
			return this.lista.get(linha).getGrau();
		default:
			return this.lista.get(linha);
		}
	}

}
