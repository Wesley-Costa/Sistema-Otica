package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.FormataMascaras;
import modelo.ValidacaoGeralCamposTexto;
import modelo.VendedorTable;

public class VisaoCadastroVendedor extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel labelCadastroDeVendedor = null;
	private JLabel labelNome = null;
	private ValidacaoGeralCamposTexto textFieldNome = null;
	private JLabel labelCpf = null;
	private JFormattedTextField textFieldCpf = null;
	private JLabel labelRg = null;
	private JFormattedTextField textFieldRg = null;
	private JLabel labelDatadeNascimento = null;
	private JLabel labelSexo = null;
	private JComboBox<String> comboBoxSexo;
	private JComboBox<String> comboBoxEstado;
	private JLabel labelCep = null;
	private JFormattedTextField textFieldCep = null;
	private JLabel labelLogradouro = null;
	private ValidacaoGeralCamposTexto textFieldLogradouro = null;
	private JLabel labelNumero = null;
	private ValidacaoGeralCamposTexto textFieldNumero = null;
	private JLabel labelBairro = null;
	private ValidacaoGeralCamposTexto textFieldBairro = null;
	private JLabel labelComplemento = null;
	private ValidacaoGeralCamposTexto textFieldComplemento = null;
	private JLabel labelCidade = null;
	private ValidacaoGeralCamposTexto textFieldCidade = null;
	private JLabel labelEstado = null;
	private JFormattedTextField textFieldDatadeNascimento = null;
	private JLabel labelEndereco = null;
	private JLabel labelID = null;
	private ValidacaoGeralCamposTexto textFieldID = null;
	private JLabel labelSalario = null;
	private ValidacaoGeralCamposTexto textFieldSalario = null;
	private JLabel labelComissao = null;
	private ValidacaoGeralCamposTexto textFieldComissao = null;
	FormataMascaras fm = new FormataMascaras();
	private JButton btnConfirmar  = null;
	private JButton btnSair  = null;
	private JScrollPane dadosJScrollPane = null;
	private JTable table  = null;
	private JButton btnCancelar  = null;
	private JButton btnEditar  = null;
	private JButton btnInserir  = null;
	private JButton btnBuscar  = null;
	private JComboBox<String> comboBoxPesquisa  = null;
	private JLabel lblPesquisa  = null;
	private JTextField textFieldPesquisa  = null;
	private VendedorTable vendedorTableModel = null;

	public VisaoCadastroVendedor() {
		setLayout(null);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.menu);
		setSize(800, 600);
		add(getLabelCadastroDeVendedor());
		add(getLabelNome());
		add(getTextFieldNome());
		add(getLabelCpf());
		add(getTextFieldCpf());
		add(getLabelRg());
		add(getTextFieldRg());
		add(getLabelDatadeNascimento());
		add(getLabelSexo());
		add(getComboBoxSexo());
		add(getLabelCep());
		add(getTextFieldCep());
		add(getLabelComplemento());
		add(getLabelLogradouro());
		add(getTextFieldLogradouro());
		add(getLabelNumero());
		add(getTextFieldNumero());
		add(getLabelBairro());
		add(getTextFieldBairro());
		add(getLabelCidade());
		add(getTextFieldCidade());
		add(getLabelEstado());
		add(getComboBoxEstado());
		add(getTextFieldComplemento());
		add(getTextFieldDatadeNascimento());
		add(getLabelEndereco());
		add(getLabelID());
		add(getTextFieldID());
		add(getLabelSalario());
		add(getTextFieldSalario());
		add(getLabelComissao());
		add(getTextFieldComissao());
		add(getBtnConfirmar());
		add(getBtnSair());
		add(getTable());
		add(getBtnCancelar());
		add(getBtnEditar());
		add(getBtnInserir());
		add(getBtnBuscar());
		add(getComboBoxPesquisa());
		add(getLblPesquisa());
		add(getTextFieldPesquisa());
		add(getDadosJScrollPane());

	}

	public JLabel getLabelCadastroDeVendedor() {
		if (labelCadastroDeVendedor == null) {
			labelCadastroDeVendedor = new JLabel("  Cadastro de Vendedor");
			labelCadastroDeVendedor.setFont(new Font("Arial", Font.BOLD, 22));
			labelCadastroDeVendedor.setBorder(new LineBorder(new Color(0, 0, 0)));
			labelCadastroDeVendedor.setBounds(0, 0, 900, 32);
		}
		return labelCadastroDeVendedor;
	}

	public JLabel getLabelNome() {
		if (labelNome == null) {
			labelNome = new JLabel("Nome:");
			labelNome.setFont(new Font("Arial", Font.PLAIN, 16));
			labelNome.setBounds(30, 77, 56, 16);
		}
		return labelNome;
	}

	public ValidacaoGeralCamposTexto getTextFieldNome() {
		if (textFieldNome == null) {
			textFieldNome = new ValidacaoGeralCamposTexto("1");
			textFieldNome.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldNome.setBounds(86, 75, 459, 20);
			textFieldNome.setColumns(10);
		}
		return textFieldNome;
	}

	public JComboBox<String> getComboBoxSexo() {
		if (comboBoxSexo == null) {
			comboBoxSexo = new JComboBox<String>();
			comboBoxSexo.setFont(new Font("Arial", Font.PLAIN, 16));
			comboBoxSexo.setBounds(639, 75, 127, 20);
			comboBoxSexo
					.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione", "Masculino", "Feminino" }));
		}
		return comboBoxSexo;
	}

	public JLabel getLabelCpf() {
		if (labelCpf == null) {
			labelCpf = new JLabel("CPF:");
			labelCpf.setFont(new Font("Arial", Font.PLAIN, 16));
			labelCpf.setBounds(583, 46, 56, 16);
		}
		return labelCpf;
	}

	public JFormattedTextField getTextFieldCpf() {
		if (textFieldCpf == null) {
			textFieldCpf = new JFormattedTextField((fm.getCPF()));
			textFieldCpf.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldCpf.setBounds(639, 44, 127, 20);
			textFieldCpf.setColumns(10);
		}
		return textFieldCpf;
	}

	public JLabel getLabelRg() {
		if (labelRg == null) {
			labelRg = new JLabel("RG:");
			labelRg.setFont(new Font("Arial", Font.PLAIN, 16));
			labelRg.setBounds(30, 104, 60, 16);
		}
		return labelRg;
	}

	public JFormattedTextField getTextFieldRg() {
		if (textFieldRg == null) {
			textFieldRg = new JFormattedTextField((fm.getRG()));
			textFieldRg.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldRg.setBounds(86, 104, 173, 20);
			textFieldRg.setColumns(10);
		}
		return textFieldRg;
	}

	public JLabel getLabelDatadeNascimento() {
		if (labelDatadeNascimento == null) {
			labelDatadeNascimento = new JLabel("Data Nasc:");
			labelDatadeNascimento.setFont(new Font("Arial", Font.PLAIN, 16));
			labelDatadeNascimento.setBounds(552, 104, 87, 16);
		}
		return labelDatadeNascimento;
	}

	public JFormattedTextField getTextFieldDatadeNascimento() {
		if (textFieldDatadeNascimento == null) {
			textFieldDatadeNascimento = new JFormattedTextField(fm.getData());
			textFieldDatadeNascimento.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldDatadeNascimento.setBounds(639, 104, 127, 20);
			textFieldDatadeNascimento.setColumns(10);
		}
		return textFieldDatadeNascimento;
	}

	public JLabel getLabelSexo() {
		if (labelSexo == null) {
			labelSexo = new JLabel("Sexo:");
			labelSexo.setFont(new Font("Arial", Font.PLAIN, 16));
			labelSexo.setBounds(583, 77, 63, 16);
		}
		return labelSexo;
	}

	public JComboBox<String> getComboBoxEstado() {
		if (comboBoxEstado == null) {
			comboBoxEstado = new JComboBox<String>();
			comboBoxEstado.setFont(new Font("Arial", Font.PLAIN, 16));
			comboBoxEstado.setBounds(639, 159, 127, 20);
			comboBoxEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione", "AC", "AL", "AM", "AP",
					"BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN",
					"RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
		}
		return comboBoxEstado;
	}

	public JLabel getLabelCep() {
		if (labelCep == null) {
			labelCep = new JLabel("CEP:");
			labelCep.setFont(new Font("Arial", Font.PLAIN, 16));
			labelCep.setBounds(30, 161, 56, 16);
		}
		return labelCep;
	}

	public JFormattedTextField getTextFieldCep() {
		if (textFieldCep == null) {
			textFieldCep = new JFormattedTextField(fm.getCEP());
			textFieldCep.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldCep.setBounds(86, 159, 172, 20);
			textFieldCep.setColumns(10);
		}
		return textFieldCep;
	}

	public JLabel getLabelLogradouro() {
		if (labelLogradouro == null) {
			labelLogradouro = new JLabel("Logradouro:");
			labelLogradouro.setFont(new Font("Arial", Font.PLAIN, 16));
			labelLogradouro.setBounds(30, 191, 97, 20);
		}
		return labelLogradouro;
	}

	public ValidacaoGeralCamposTexto getTextFieldLogradouro() {
		if (textFieldLogradouro == null) {
			textFieldLogradouro = new ValidacaoGeralCamposTexto("7");
			textFieldLogradouro.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldLogradouro.setBounds(125, 190, 420, 20);
			textFieldLogradouro.setColumns(10);
		}
		return textFieldLogradouro;
	}

	public JLabel getLabelNumero() {
		if (labelNumero == null) {
			labelNumero = new JLabel("N\u00FAmero:");
			labelNumero.setFont(new Font("Arial", Font.PLAIN, 16));
			labelNumero.setBounds(568, 193, 74, 16);
		}
		return labelNumero;
	}

	public ValidacaoGeralCamposTexto getTextFieldNumero() {
		if (textFieldNumero == null) {
			textFieldNumero = new ValidacaoGeralCamposTexto("7");
			textFieldNumero.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldNumero.setBounds(639, 190, 127, 20);
			textFieldNumero.setColumns(10);
		}
		return textFieldNumero;
	}

	public JLabel getLabelBairro() {
		if (labelBairro == null) {
			labelBairro = new JLabel("Bairro:");
			labelBairro.setFont(new Font("Arial", Font.PLAIN, 16));
			labelBairro.setBounds(30, 225, 56, 16);
		}
		return labelBairro;
	}

	public ValidacaoGeralCamposTexto getTextFieldBairro() {
		if (textFieldBairro == null) {
			textFieldBairro = new ValidacaoGeralCamposTexto("1");
			textFieldBairro.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldBairro.setBounds(125, 222, 277, 20);
			textFieldBairro.setColumns(10);
		}
		return textFieldBairro;
	}

	public JLabel getLabelComplemento() {
		if (labelComplemento == null) {
			labelComplemento = new JLabel("Complemento:");
			labelComplemento.setFont(new Font("Arial", Font.PLAIN, 16));
			labelComplemento.setBounds(429, 225, 116, 16);
		}
		return labelComplemento;
	}

	public ValidacaoGeralCamposTexto getTextFieldComplemento() {
		if (textFieldComplemento == null) {
			textFieldComplemento = new ValidacaoGeralCamposTexto("7");
			textFieldComplemento.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldComplemento.setBounds(544, 222, 222, 20);
			textFieldComplemento.setColumns(10);
		}
		return textFieldComplemento;
	}

	public JLabel getLabelCidade() {
		if (labelCidade == null) {
			labelCidade = new JLabel("Cidade:");
			labelCidade.setFont(new Font("Arial", Font.PLAIN, 16));
			labelCidade.setBounds(269, 161, 56, 16);
		}
		return labelCidade;
	}

	public JLabel getLabelEstado() {
		if (labelEstado == null) {
			labelEstado = new JLabel("Estado:");
			labelEstado.setFont(new Font("Arial", Font.PLAIN, 16));
			labelEstado.setBounds(568, 161, 56, 16);
		}
		return labelEstado;
	}

	public ValidacaoGeralCamposTexto getTextFieldCidade() {
		if (textFieldCidade == null) {
			textFieldCidade = new ValidacaoGeralCamposTexto("1");
			textFieldCidade.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldCidade.setBounds(335, 159, 210, 20);
			textFieldCidade.setColumns(10);
		}
		return textFieldCidade;
	}

	public JLabel getLabelEndereco() {
		if (labelEndereco == null) {
			labelEndereco = new JLabel("Endere\u00E7o:");
			labelEndereco.setFont(new Font("Arial", Font.PLAIN, 18));
			labelEndereco.setBounds(30, 131, 136, 16);
		}
		return labelEndereco;
	}

	public JLabel getLabelID() {
		if (labelID == null) {
			labelID = new JLabel("C\u00F3digo");
			labelID.setFont(new Font("Arial", Font.PLAIN, 16));
			labelID.setBounds(30, 46, 74, 16);
		}
		return labelID;
	}

	public ValidacaoGeralCamposTexto getTextFieldID() {
		if (textFieldID == null) {
			textFieldID = new ValidacaoGeralCamposTexto("4");
			textFieldID.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldID.setBounds(86, 44, 173, 20);
			textFieldID.setColumns(10);
		}
		return textFieldID;
	}

	public JLabel getLabelComissao() {
		if (labelComissao == null) {
			labelComissao = new JLabel("Comiss\u00E3o: R$");
			labelComissao.setFont(new Font("Arial", Font.PLAIN, 16));
			labelComissao.setBounds(269, 104, 116, 16);
		}
		return labelComissao;
	}

	public ValidacaoGeralCamposTexto getTextFieldComissao() {
		if (textFieldComissao == null) {
			textFieldComissao = new ValidacaoGeralCamposTexto("10");
			textFieldComissao.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldComissao.setBounds(386, 104, 159, 20);
			textFieldComissao.setColumns(10);
		}
		return textFieldComissao;
	}

	public JLabel getLabelSalario() {
		if (labelSalario == null) {
			labelSalario = new JLabel("Sal\u00E1rio: R$");
			labelSalario.setFont(new Font("Arial", Font.PLAIN, 16));
			labelSalario.setBounds(269, 46, 119, 16);
		}
		return labelSalario;
	}

	public ValidacaoGeralCamposTexto getTextFieldSalario() {
		if (textFieldSalario == null) {
			textFieldSalario = new ValidacaoGeralCamposTexto("10");
			textFieldSalario.setFont(new Font("Arial", Font.PLAIN, 16));
			textFieldSalario.setBounds(386, 44, 159, 20);
			textFieldSalario.setColumns(10);
		}
		return textFieldSalario;
	}

	public VendedorTable getVendedorTableModel(boolean consulta) {
		if(consulta == true) {
			return vendedorTableModel;
		}
		else{
			vendedorTableModel = new VendedorTable();
			
			return vendedorTableModel;
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
							"Código","CPF","Nome"
					}
				));
			table.getColumnModel().getColumn(0).setPreferredWidth(128);
			table.getColumnModel().getColumn(1).setPreferredWidth(231);
			table.getColumnModel().getColumn(2).setPreferredWidth(241);
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
					new String[] { "Selecione", "Código", "Nome", "Cpf"}));
			comboBoxPesquisa.setBounds(30, 288, 130, 30);
		}
		return comboBoxPesquisa;
	}

	public JLabel getLblPesquisa() {
		if (lblPesquisa == null) {
			lblPesquisa = new JLabel("Pesquisa:");
			lblPesquisa.setFont(new Font("Arial", Font.BOLD, 16));
			lblPesquisa.setBounds(30, 264, 130, 23);
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