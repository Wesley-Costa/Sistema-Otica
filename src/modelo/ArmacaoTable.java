package modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ArmacaoTable extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private	ArrayList<Armacao> lista;
	private String[] colunas = new String[]{"Código","Material", "Formato", "Marca"};
	
	public ArmacaoTable() {
		this.lista = new ArrayList<>();	
	}

	public void add_tabela(Armacao armacao) {
		this.lista.add(armacao);
		fireTableDataChanged();
	}	
			
	public void remover_tabela(int linhaIndice) {
		this.lista.remove(linhaIndice);
		fireTableDataChanged();
	}
	
	public Armacao getArmacao(int linhaIndice) {
		return this.lista.get(linhaIndice);
	}
	
	public ArrayList<Armacao> getArray() {
		return lista;
	}
	
	public void setArray(ArrayList<Armacao> armacoes) {
		this.lista = armacoes;
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
		case 2:// coluna Formato
			return this.lista.get(linha).getFormato();
		case 3:// coluna Marca
			return this.lista.get(linha).getMarca();
		default:
			return this.lista.get(linha);
		}
	}

}
