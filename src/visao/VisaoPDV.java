package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.Label;

public class VisaoPDV extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel labelVendas = null;
	private JLabel labelLente = null;
	private JLabel labelArmacao = null;
	private JLabel labelValorTotal = null;
	private JLabel labelMontagem = null;
	private JLabel labelValorLente = null;
	private JLabel labelValorArmacao = null;
	private JTextField textFieldValorLente = null;
	private JTextField textFieldValorArmacao = null;
	private JTextField textFieldValorTotal = null;
	private JSeparator separador_1;
	private JSeparator separador_2;
	private JSeparator separador_3;
	private JButton btnConfirmar;
	private JButton btnSair;
	private JComboBox<String> comboBoxMontagem;
	private JTextField textFieldCodigoLente;
	private JTextField textFieldDescricaoLente;
	private JTextField textFieldDescricaoArmacao;
	private JTextField textFieldCodigoArmacao;
	private Label label;
	private Label label_1;
	private JLabel lblVendedor;
	private JTextField textFieldCodigoVendedor;
	private Label label_3;
	private JTextField textFieldDescricaoVendedor;

	public VisaoPDV() {

		setLayout(null);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.menu);
		setSize(800, 600);
		add(getLabelVendas());
		add(getLabelLente());
		add(getLabelArmacao());
		add(getLabelValorTotal());
		add(getLabelMontagem());
		add(getLabelValorLente());
		add(getLabelValorArmacao());
		add(getSeparador_1());
		add(getSeparador_2());
		add(getSeparador_3());
		add(getTextFieldValorLente());
		add(getTextFieldValorArmacao());
		add(getTextFieldValorTotal());
		add(getComboBoxMontagem());
		add(getBtnConfirmar());
		add(getBtnSair());
		add(getTextFieldCodigoLente());
		add(getTextFieldDescricaoLente());
		add(getTextFieldDescricaoArmacao());
		add(getTextFieldCodigoArmacao());
		add(getLabel());
		add(getLabel_1());
		add(getLblVendedor());
		add(getTextFieldCodigoVendedor());
		add(getLabel_3());
		add(getTextFieldDescricaoVendedor());

	}

	public JLabel getLabelVendas() {
		if (labelVendas == null) {
			labelVendas = new JLabel("  Vendas");
			labelVendas.setFont(new Font("Arial", Font.BOLD, 27));
			labelVendas.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			labelVendas.setBounds(0, 0, 800, 50);
		}
		return labelVendas;
	}

	public JLabel getLabelLente() {
		if (labelLente == null) {
			labelLente = new JLabel("Lente:");
			labelLente.setFont(new Font("Arial", Font.PLAIN, 20));
			labelLente.setBounds(33, 182, 76, 21);
		}
		return labelLente;
	}

	public JLabel getLabelArmacao() {
		if (labelArmacao == null) {
			labelArmacao = new JLabel("Armação:");
			labelArmacao.setFont(new Font("Arial", Font.PLAIN, 20));
			labelArmacao.setBounds(33, 296, 97, 35);
		}
		return labelArmacao;
	}

	public JLabel getLabelValorTotal() {
		if (labelValorTotal == null) {
			labelValorTotal = new JLabel("Total R$:");
			labelValorTotal.setFont(new Font("Arial", Font.PLAIN, 20));
			labelValorTotal.setBounds(33, 466, 124, 21);
		}
		return labelValorTotal;
	}

	public JLabel getLabelMontagem() {
		if (labelMontagem == null) {
			labelMontagem = new JLabel("Montagem: ");
			labelMontagem.setFont(new Font("Arial", Font.PLAIN, 20));
			labelMontagem.setBounds(33, 418, 111, 24);
		}
		return labelMontagem;
	}

	public JLabel getLabelValorLente() {
		if (labelValorLente == null) {
			labelValorLente = new JLabel("Pre\u00E7o R$:");
			labelValorLente.setFont(new Font("Arial", Font.PLAIN, 20));
			labelValorLente.setBounds(33, 220, 97, 24);
		}
		return labelValorLente;
	}

	public JLabel getLabelValorArmacao() {
		if (labelValorArmacao == null) {
			labelValorArmacao = new JLabel("Pre\u00E7o R$:");
			labelValorArmacao.setFont(new Font("Arial", Font.PLAIN, 20));
			labelValorArmacao.setBounds(33, 342, 116, 24);
		}
		return labelValorArmacao;
	}

	public JComboBox<String> getComboBoxMontagem() {
		if (comboBoxMontagem == null) {
			comboBoxMontagem = new JComboBox<String>();
			comboBoxMontagem.setFont(new Font("Arial", Font.PLAIN, 16));
			comboBoxMontagem.setBounds(147, 420, 135, 24);
			comboBoxMontagem.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecione", "Sim", "Não" }));
		}
		return comboBoxMontagem;
	}

	public JTextField getTextFieldValorLente() {
		if (textFieldValorLente == null) {
			textFieldValorLente = new JTextField();
			textFieldValorLente.setFont(new Font("Arial", Font.BOLD, 16));
			textFieldValorLente.setBounds(132, 221, 120, 24);
			textFieldValorLente.setColumns(10);
		}
		return textFieldValorLente;
	}

	public JTextField getTextFieldValorArmacao() {
		if (textFieldValorArmacao == null) {
			textFieldValorArmacao = new JTextField();
			textFieldValorArmacao.setFont(new Font("Arial", Font.BOLD, 16));
			textFieldValorArmacao.setBounds(132, 341, 120, 24);
			textFieldValorArmacao.setColumns(10);
		}
		return textFieldValorArmacao;
	}
	
	public JTextField getTextFieldValorTotal() {
		if (textFieldValorTotal == null) {
			textFieldValorTotal = new JTextField();
			textFieldValorTotal.setFont(new Font("Arial", Font.BOLD, 16));
			textFieldValorTotal.setBounds(147, 465, 135, 24);
			textFieldValorTotal.setColumns(10);
		}
		return textFieldValorTotal;
	}

	private JSeparator getSeparador_1() {
		if (separador_1 == null) {
			separador_1 = new JSeparator();
			separador_1.setBounds(30, 261, 682, 2);
		}
		return separador_1;
	}

	private JSeparator getSeparador_2() {
		if (separador_2 == null) {
			separador_2 = new JSeparator();
			separador_2.setBounds(33, 144, 682, 2);
		}
		return separador_2;
	}

	private JSeparator getSeparador_3() {
		if (separador_3 == null) {
			separador_3 = new JSeparator();
			separador_3.setBounds(33, 399, 689, 8);
		}
		return separador_3;
	}

	public JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("CONFIRMAR");
			btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
			btnConfirmar.setBounds(450, 520, 140, 35);
		}
		return btnConfirmar;
	}

	public JButton getBtnSair() {
		if (btnSair == null) {
			btnSair = new JButton("SAIR");
			btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
			btnSair.setBounds(620, 520, 140, 35);
		}
		return btnSair;
	}
	public JTextField getTextFieldCodigoLente() {
		if (textFieldCodigoLente == null) {
			textFieldCodigoLente = new JTextField();
			textFieldCodigoLente.setBounds(132, 183, 52, 24);
			textFieldCodigoLente.setColumns(10);
		}
		return textFieldCodigoLente;
	}
	public JTextField getTextFieldDescricaoLente() {
		if (textFieldDescricaoLente == null) {
			textFieldDescricaoLente = new JTextField();
			textFieldDescricaoLente.setBackground(Color.WHITE);
			textFieldDescricaoLente.setForeground(Color.LIGHT_GRAY);
			textFieldDescricaoLente.setBounds(200, 182, 447, 24);
			textFieldDescricaoLente.setColumns(10);
		}
		return textFieldDescricaoLente;
	}
	public JTextField getTextFieldDescricaoArmacao() {
		if (textFieldDescricaoArmacao == null) {
			textFieldDescricaoArmacao = new JTextField();
			textFieldDescricaoArmacao.setForeground(Color.LIGHT_GRAY);
			textFieldDescricaoArmacao.setColumns(10);
			textFieldDescricaoArmacao.setBackground(Color.WHITE);
			textFieldDescricaoArmacao.setBounds(200, 303, 447, 24);
		}
		return textFieldDescricaoArmacao;
	}
	public JTextField getTextFieldCodigoArmacao() {
		if (textFieldCodigoArmacao == null) {
			textFieldCodigoArmacao = new JTextField();
			textFieldCodigoArmacao.setColumns(10);
			textFieldCodigoArmacao.setBounds(132, 303, 52, 24);
		}
		return textFieldCodigoArmacao;
	}
	public Label getLabel() {
		if (label == null) {
			label = new Label("Insira o c\u00F3digo da Lente");
			label.setFont(new Font("Dialog", Font.PLAIN, 11));
			label.setBounds(33, 161, 219, 15);
		}
		return label;
	}
	public Label getLabel_1() {
		if (label_1 == null) {
			label_1 = new Label("Insira o c\u00F3digo da Arma\u00E7\u00E3o");
			label_1.setFont(new Font("Dialog", Font.PLAIN, 11));
			label_1.setBounds(33, 279, 219, 21);
		}
		return label_1;
	}
	public JLabel getLblVendedor() {
		if (lblVendedor == null) {
			lblVendedor = new JLabel("Vendedor:");
			lblVendedor.setFont(new Font("Arial", Font.PLAIN, 18));
			lblVendedor.setBounds(33, 99, 97, 21);
		}
		return lblVendedor;
	}
	public JTextField getTextFieldCodigoVendedor() {
		if (textFieldCodigoVendedor == null) {
			textFieldCodigoVendedor = new JTextField();
			textFieldCodigoVendedor.setColumns(10);
			textFieldCodigoVendedor.setBounds(132, 99, 52, 24);
		}
		return textFieldCodigoVendedor;
	}
	public Label getLabel_3() {
		if (label_3 == null) {
			label_3 = new Label("Insira o c\u00F3digo do Vendedor");
			label_3.setFont(new Font("Dialog", Font.PLAIN, 11));
			label_3.setBounds(33, 76, 219, 20);
		}
		return label_3;
	}
	public JTextField getTextFieldDescricaoVendedor() {
		if (textFieldDescricaoVendedor == null) {
			textFieldDescricaoVendedor = new JTextField();
			textFieldDescricaoVendedor.setForeground(Color.LIGHT_GRAY);
			textFieldDescricaoVendedor.setColumns(10);
			textFieldDescricaoVendedor.setBackground(Color.WHITE);
			textFieldDescricaoVendedor.setBounds(200, 99, 447, 24);
		}
		return textFieldDescricaoVendedor;
	}
}
