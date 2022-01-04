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
import modelo.LenteTable;
import modelo.ValidacaoGeralCamposTexto;

public class VisaoCadastroLente extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel labelCadastroDeLente = null;
	private ValidacaoGeralCamposTexto textFieldCodigo = null;
	private ValidacaoGeralCamposTexto textFieldMaterial = null;
	private ValidacaoGeralCamposTexto textFieldGrau = null;
	private ValidacaoGeralCamposTexto textFieldTipo = null;
	private ValidacaoGeralCamposTexto textFieldEstoque = null;
	private ValidacaoGeralCamposTexto textFieldPreco = null;
	private JButton btnConfirmar = null;
	private JButton btnSair = null;
	private JTable table = null;
	private JScrollPane dadosJScrollPane = null;
	private JButton btnCancelar = null;
	private JButton btnEditar = null;
	private JButton btnInserir = null;
	private JButton btnBuscar = null;
	private JComboBox<String> comboBoxPesquisa = null;
	private JLabel lblPesquisa = null;
	private JTextField textFieldPesquisa = null;
	private LenteTable lenteTableModel = null;

	public VisaoCadastroLente() {

		setLayout(null);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.menu);
		setSize(800, 600);
		add(getLabelCadastroDeLente());
		add(getBtnConfirmar());
		add(getBtnSair());
		add(getTextFieldCodigo());
		add(getTextFieldMaterial());
		add(getTextFieldGrau());
		add(getTextFieldTipo());
		add(getTextFieldEstoque());
		add(getTextFieldPreco());
		add(getTable());
		add(getDadosJScrollPane());
		add(getBtnCancelar());
		add(getBtnEditar());
		add(getBtnInserir());
		add(getBtnBuscar());
		add(getComboBoxPesquisa());
		add(getLblPesquisa());
		add(getTextFieldPesquisa());

	}

	public JLabel getLabelCadastroDeLente() {
		if (labelCadastroDeLente == null) {
			labelCadastroDeLente = new JLabel(" Cadastro de Lente");
			labelCadastroDeLente.setBorder(new LineBorder(new Color(0, 0, 0)));
			labelCadastroDeLente.setFont(new Font("Arial", Font.BOLD, 22));
			labelCadastroDeLente.setBounds(0, 0, 800, 32);
		}
		return labelCadastroDeLente;
	}

	public ValidacaoGeralCamposTexto getTextFieldCodigo() {
		if (textFieldCodigo == null) {
			textFieldCodigo = new ValidacaoGeralCamposTexto("4");
			textFieldCodigo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "C\u00F3digo:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldCodigo.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldCodigo.setBounds(30, 70, 170, 50);
			textFieldCodigo.setColumns(10);
		}
		return textFieldCodigo;
	}

	public ValidacaoGeralCamposTexto getTextFieldMaterial() {
		if (textFieldMaterial == null) {
			textFieldMaterial = new ValidacaoGeralCamposTexto("1");
			textFieldMaterial.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldMaterial.setColumns(10);
			textFieldMaterial.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Material:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldMaterial.setBounds(30, 131, 350, 50);
		}
		return textFieldMaterial;
	}

	public ValidacaoGeralCamposTexto getTextFieldGrau() {
		if (textFieldGrau == null) {
			textFieldGrau = new ValidacaoGeralCamposTexto("10");
			textFieldGrau.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldGrau.setColumns(10);
			textFieldGrau.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Grau:", TitledBorder.LEADING,
					TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldGrau.setBounds(395, 70, 170, 50);
		}
		return textFieldGrau;
	}

	public ValidacaoGeralCamposTexto getTextFieldTipo() {
		if (textFieldTipo == null) {
			textFieldTipo = new ValidacaoGeralCamposTexto("7");
			textFieldTipo.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldTipo.setColumns(10);
			textFieldTipo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo:", TitledBorder.LEADING,
					TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldTipo.setBounds(395, 131, 350, 50);
		}
		return textFieldTipo;
	}

	public ValidacaoGeralCamposTexto getTextFieldEstoque() {
		if (textFieldEstoque == null) {
			textFieldEstoque = new ValidacaoGeralCamposTexto("4");
			textFieldEstoque.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldEstoque.setColumns(10);
			textFieldEstoque.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Estoque:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldEstoque.setBounds(210, 70, 170, 50);
		}
		return textFieldEstoque;
	}

	public ValidacaoGeralCamposTexto getTextFieldPreco() {
		if (textFieldPreco == null) {
			textFieldPreco = new ValidacaoGeralCamposTexto("10");
			textFieldPreco.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldPreco.setColumns(10);
			textFieldPreco.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Pre\u00E7o R$:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldPreco.setBounds(575, 70, 170, 50);
		}
		return textFieldPreco;
	}
	
	public LenteTable getLenteTableModel(boolean consulta) {
		if(consulta == true) {
			return lenteTableModel;
		}
		else{
			lenteTableModel = new LenteTable();
			
			return lenteTableModel;
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
							"Código","Material","Tipo", "Grau"
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
					new String[] { "Selecione", "Código", "Material", "Tipo", "Grau"}));
			comboBoxPesquisa.setBounds(30, 288, 130, 30);
		}
		return comboBoxPesquisa;
	}

	public JLabel getLblPesquisa() {
		if (lblPesquisa == null) {
			lblPesquisa = new JLabel("Pesquisa:");
			lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 16));
			lblPesquisa.setBounds(30, 253, 130, 23);
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