package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modelo.ArmacaoTable;
import modelo.ValidacaoGeralCamposTexto;

public class VisaoCadastroArmacao extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel labelTitulo = null;
	private ValidacaoGeralCamposTexto textFieldCodigo = null;
	private ValidacaoGeralCamposTexto textFieldMaterial = null;
	private ValidacaoGeralCamposTexto textFieldCor = null;
	private ValidacaoGeralCamposTexto textFieldFormato = null;
	private ValidacaoGeralCamposTexto textFieldMarca = null;
	private ValidacaoGeralCamposTexto textFieldTamanho = null;
	private ValidacaoGeralCamposTexto textFieldValorUnitario = null;
	private ValidacaoGeralCamposTexto textFieldEstoque = null;
	private JButton btnConfirmar = null;
	private JButton btnSair = null;
	private JScrollPane dadosJScrollPane = null;
	private JTable table = null;
	private JButton btnCancelar = null;
	private JButton btnEditar = null;
	private JButton btnInserir = null;
	private JButton btnBuscar = null;
	private JComboBox<String> comboBoxPesquisa = null;
	private JLabel lblPesquisa = null;
	private JTextField textFieldPesquisa = null;
	private ArmacaoTable armacaoTableModel = null;

	public VisaoCadastroArmacao() {
		super();
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.menu);
		setSize(800, 600);
		add(getLabelTitulo());
		add(getTextFieldCodigo());
		add(getTextFieldMaterial());
		add(getTextFieldCor());
		add(getTextFieldFormato());
		add(getTextFieldMarca());
		add(getTextFieldTamanho());
		add(getTextFieldValorUnitario());
		add(getTextFieldEstoque());
		add(getBtnConfirmar());
		add(getBtnSair());
		add(getDadosJScrollPane());
		add(getBtnCancelar());
		add(getBtnEditar());
		add(getBtnInserir());
		add(getBtnBuscar());
		add(getComboBoxPesquisa());
		add(getLblPesquisa());
		add(getTextFieldPesquisa());

	}

	public JLabel getLabelTitulo() {
		if (labelTitulo == null) {
			labelTitulo = new JLabel("  Cadastro de Armação");
			labelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
			labelTitulo.setFont(new Font("Arial", Font.BOLD, 22));
			labelTitulo.setBounds(0, 0, 1080, 32);
		}
		return labelTitulo;
	}

	public ValidacaoGeralCamposTexto getTextFieldCodigo() {
		if (textFieldCodigo == null) {
			textFieldCodigo = new ValidacaoGeralCamposTexto("4");
			textFieldCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldCodigo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "C\u00F3digo: ",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldCodigo.setBounds(30, 55, 170, 50);
			textFieldCodigo.setColumns(10);
		}
		return textFieldCodigo;
	}

	public ValidacaoGeralCamposTexto getTextFieldMaterial() {
		if (textFieldMaterial == null) {
			textFieldMaterial = new ValidacaoGeralCamposTexto("7");
			textFieldMaterial.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldMaterial.setColumns(10);
			textFieldMaterial.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Material: ",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldMaterial.setBounds(388, 126, 350, 50);
		}
		return textFieldMaterial;
	}

	public ValidacaoGeralCamposTexto getTextFieldCor() {
		if (textFieldCor == null) {
			textFieldCor = new ValidacaoGeralCamposTexto("1");
			textFieldCor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldCor.setColumns(10);
			textFieldCor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cor:", TitledBorder.LEADING,
					TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldCor.setBounds(30, 192, 348, 50);
		}
		return textFieldCor;
	}

	public ValidacaoGeralCamposTexto getTextFieldFormato() {
		if (textFieldFormato == null) {
			textFieldFormato = new ValidacaoGeralCamposTexto("1");
			textFieldFormato.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldFormato.setColumns(10);
			textFieldFormato.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Formato:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldFormato.setBounds(388, 192, 350, 50);
		}
		return textFieldFormato;
	}

	public ValidacaoGeralCamposTexto getTextFieldMarca() {
		if (textFieldMarca == null) {
			textFieldMarca = new ValidacaoGeralCamposTexto("7");
			textFieldMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldMarca.setColumns(10);
			textFieldMarca.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Marca:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldMarca.setBounds(388, 55, 350, 50);
		}
		return textFieldMarca;
	}

	public ValidacaoGeralCamposTexto getTextFieldTamanho() {
		if (textFieldTamanho == null) {
			textFieldTamanho = new ValidacaoGeralCamposTexto("1");
			textFieldTamanho.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldTamanho.setColumns(10);
			textFieldTamanho.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tamanho:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldTamanho.setBounds(30, 126, 170, 50);
		}
		return textFieldTamanho;
	}

	public ValidacaoGeralCamposTexto getTextFieldValorUnitario() {
		if (textFieldValorUnitario == null) {
			textFieldValorUnitario = new ValidacaoGeralCamposTexto("10");
			textFieldValorUnitario.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldValorUnitario.setColumns(10);
			textFieldValorUnitario.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
					"Valor Unit\u00E1rio R$:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldValorUnitario.setBounds(208, 126, 170, 50);
		}
		return textFieldValorUnitario;
	}

	public ValidacaoGeralCamposTexto getTextFieldEstoque() {
		if (textFieldEstoque == null) {
			textFieldEstoque = new ValidacaoGeralCamposTexto("4");
			textFieldEstoque.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldEstoque.setColumns(10);
			textFieldEstoque.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Estoque:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldEstoque.setBounds(210, 55, 170, 50);
		}
		return textFieldEstoque;
	}

	public ArmacaoTable getArmacaoTableModel(boolean consulta) {
		if(consulta == true) {
			return armacaoTableModel;
		}
		else{
			armacaoTableModel = new ArmacaoTable();
			
			return armacaoTableModel;
		}		
	}

	public JScrollPane getDadosJScrollPane() {
		if (dadosJScrollPane == null) {
			dadosJScrollPane = new JScrollPane(getTable());
			dadosJScrollPane.setBounds(30, 335, 715, 168);
		}
		return dadosJScrollPane;
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setBounds(30, 335, 715, 168);
			table.setModel(new DefaultTableModel(
					new Object[][] {
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
//						{null, null, null, null},
					},
					new String[] {
							"Código","Material", "Formato", "Marca"
					}
				));
			table.getColumnModel().getColumn(0).setPreferredWidth(128);
			table.getColumnModel().getColumn(1).setPreferredWidth(231);
			table.getColumnModel().getColumn(2).setPreferredWidth(241);
			table.getColumnModel().getColumn(3).setPreferredWidth(124);
		}
		return table;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
			btnCancelar.setBounds(330, 520, 130, 35);
		}
		return btnCancelar;
	}

	public JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("EDITAR");
			btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
			btnEditar.setBounds(180, 520, 130, 35);
		}
		return btnEditar;
	}

	public JButton getBtnInserir() {
		if (btnInserir == null) {
			btnInserir = new JButton("INSERIR");
			btnInserir.setFont(new Font("Arial", Font.PLAIN, 16));
			btnInserir.setBounds(30, 520, 130, 35);
		}
		return btnInserir;
	}

	public JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("BUSCAR");
			btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
			btnBuscar.setBounds(615, 284, 130, 35);
		}
		return btnBuscar;
	}

	public JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("CONFIRMAR");
			btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
			btnConfirmar.setBounds(470, 520, 130, 35);
		}
		return btnConfirmar;
	}

	public JButton getBtnSair() {
		if (btnSair == null) {
			btnSair = new JButton("SAIR");
			btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
			btnSair.setBounds(615, 520, 130, 35);
		}
		return btnSair;
	}

	public JComboBox<String> getComboBoxPesquisa() {
		if (comboBoxPesquisa == null) {
			comboBoxPesquisa = new JComboBox<String>();
			comboBoxPesquisa.setBackground(Color.WHITE);
			comboBoxPesquisa.setForeground(Color.BLACK);
			comboBoxPesquisa.setFont(new Font("Arial", Font.PLAIN, 16));
			comboBoxPesquisa.setModel(new DefaultComboBoxModel<String>(
					new String[] { "Selecione", "Código", "Material", "Formato", "Marca"}));
			comboBoxPesquisa.setBounds(30, 288, 130, 30);
		}
		return comboBoxPesquisa;
	}

	public JLabel getLblPesquisa() {
		if (lblPesquisa == null) {
			lblPesquisa = new JLabel("Pesquisa:");
			lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 16));
			lblPesquisa.setBounds(30, 265, 130, 23);
		}
		return lblPesquisa;
	}

	public JTextField getTextFieldPesquisa() {
		if (textFieldPesquisa == null) {
			textFieldPesquisa = new JTextField();
			textFieldPesquisa.setBounds(180, 286, 420, 35);
			textFieldPesquisa.setColumns(10);
		}
		return textFieldPesquisa;
	}
}