package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Vendedor;
import visao.VisaoCadastroVendedor;

public class ControladorCadastroVendedor implements ActionListener {

	private VisaoCadastroVendedor telaVendedor;
	private Vendedor vendedor;
	static ArrayList<Vendedor> arrayVendedores = new ArrayList<Vendedor>();

	public ControladorCadastroVendedor(VisaoCadastroVendedor telaVendedor) {

		this.telaVendedor = telaVendedor;
		carregaArrayList();
		inicialCampos();
		addEventos();
	}

	public void addEventos() {

		telaVendedor.getBtnSair().addActionListener(this);
		telaVendedor.getBtnConfirmar().addActionListener(this);
		telaVendedor.getBtnEditar().addActionListener(this);
		telaVendedor.getBtnInserir().addActionListener(this);
		telaVendedor.getBtnBuscar().addActionListener(this);
		telaVendedor.getBtnCancelar().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == telaVendedor.getBtnBuscar()) {

			String busca;
			busca = telaVendedor.getComboBoxPesquisa().getSelectedItem().toString();

			if (busca == "Código" || busca == "CPF" || busca == "Nome" || busca == "Selecione") {
				if (buscaBanco() == true) {
					camposPosBusca();
					selecao();
					mouse();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Preencha o campo de busca!",
						"Selecione", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (e.getSource() == telaVendedor.getBtnSair()) {
			Object[] options = { "NÃO", "SIM" };
			int opcao = JOptionPane.showOptionDialog(null, "Deseja retornar a tela inicial?", "SAIR",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if (opcao == 1) {
				new ControladorFrame();
			}
		}

		if (e.getSource() == telaVendedor.getBtnInserir()) {
			limparPesquisa();
			camposEditar();
		}

		if (e.getSource() == telaVendedor.getBtnCancelar()) {
			telaVendedor.getLabelCadastroDeVendedor().setText("Cadastro de Vendedor");
			inicialCampos();
			limparPesquisa();
			limpaTela();

		}

		if (e.getSource() == telaVendedor.getBtnEditar()) {
			telaVendedor.getLabelCadastroDeVendedor().setText("Cadastro de Vendedor - Editar");
			camposEditar();
			telaVendedor.getTextFieldCpf().setEnabled(false);
			telaVendedor.getTextFieldID().setEnabled(false);
			// limparPesquisa();
		}

		if (e.getSource() == telaVendedor.getBtnConfirmar()) {

			if (telaVendedor.getLabelCadastroDeVendedor().getText().equals("Cadastro de Vendedor - Editar")) {
				Object[] options = { "NÃO", "SIM" };
				int opcao = JOptionPane.showOptionDialog(null, "Deseja confirmar a operação?", "CONFIRMAR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if (opcao == 1) {

					Vendedor vendedorExibicao = new Vendedor();
					int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
					vendedorExibicao = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
					if(validacao() == true) {
						excluiRegistro(vendedorExibicao);
						capturaDados();
						gravaRegistro();
						JOptionPane.showMessageDialog(null, "Dados Alterados");
						inicializaTela();
					}
				}

			} else {

				Object[] options = { "NÃO", "SIM" };
				int opcao = JOptionPane.showOptionDialog(null, "Deseja confirmar a operação?", "CONFIRMAR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				String busca = telaVendedor.getTextFieldID().getText();
				String cpf = telaVendedor.getTextFieldCpf().getText();

				if (opcao == 1) {

					int existe = 0;

					for (int i = 0; i < arrayVendedores.size(); i++) {

						if (String.valueOf(arrayVendedores.get(i).getCodigo()).contains(busca)) {
							JOptionPane.showMessageDialog(null, "Código já Cadastrado");
							existe = 1;
						}

						if (String.valueOf(arrayVendedores.get(i).getCpf()).contains(cpf)) {
							JOptionPane.showMessageDialog(null, "CPF já Cadastrado");
							existe = 1;
						}
					}

					if (existe == 0) {
						if(validacao() == true) {
							capturaDados();
							gravaRegistro();
							JOptionPane.showMessageDialog(null, "Vendedor Cadastrado");
							inicializaTela();
						}
					}
				}
			}
		}

	}

	public void selecao() {
		telaVendedor.getTable().addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Vendedor vendedorExibicao = new Vendedor();
					int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
					vendedorExibicao = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
					atribuiCampos(vendedorExibicao);
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Vendedor vendedorExibicao = new Vendedor();
					int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
					vendedorExibicao = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
					atribuiCampos(vendedorExibicao);
				}
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Vendedor vendedorExibicao = new Vendedor();
					int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
					vendedorExibicao = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
					atribuiCampos(vendedorExibicao);
				}
			}

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Vendedor vendedorExibicao = new Vendedor();
					int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
					vendedorExibicao = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
					atribuiCampos(vendedorExibicao);
				}

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					Object[] options = { "NÃO", "SIM" };
					int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir o vendedor do banco de dados?",
							"Exclusão", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);

					if (opcao == 1) {
						Vendedor vendedorExibido = new Vendedor();
						int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
						vendedorExibido = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
						excluiRegistro(vendedorExibido);
						carregaArrayList();
						JOptionPane.showMessageDialog(null, "Vendedor Excluído");
						inicializaTela();

					}
				}

			}
		});
	}

	public void mouse() {
		telaVendedor.getTable().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent b) {
				if (b.getClickCount() == 1) {
					Vendedor vendedorExibicao = new Vendedor();
					int linhaSelecionada = telaVendedor.getTable().getSelectedRow();
					vendedorExibicao = telaVendedor.getVendedorTableModel(true).getVendedor(linhaSelecionada);
					atribuiCampos(vendedorExibicao);
				}

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void capturaDados() {

		vendedor = new Vendedor();

		this.vendedor.setCodigo(Integer.parseInt(telaVendedor.getTextFieldID().getText()));
		this.vendedor.setComissao(Float.parseFloat(telaVendedor.getTextFieldComissao().getText()));
		this.vendedor.setSalario(Float.parseFloat(telaVendedor.getTextFieldSalario().getText()));
		this.vendedor.setCpf(telaVendedor.getTextFieldCpf().getText());
		this.vendedor.setDataNasc(telaVendedor.getTextFieldDatadeNascimento().getText());
		this.vendedor.setNome(telaVendedor.getTextFieldNome().getText());
		this.vendedor.setRg(telaVendedor.getTextFieldRg().getText());
		this.vendedor.setSexo(telaVendedor.getComboBoxSexo().getSelectedItem().toString());
		this.vendedor.setBairro(telaVendedor.getTextFieldBairro().getText());
		this.vendedor.setCep(telaVendedor.getTextFieldCep().getText());
		this.vendedor.setCidade(telaVendedor.getTextFieldCidade().getText());
		this.vendedor.setComplemento(telaVendedor.getTextFieldComplemento().getText());
		this.vendedor.setLogradouro(telaVendedor.getTextFieldLogradouro().getText());
		this.vendedor.setNumero(telaVendedor.getTextFieldNumero().getText());
		this.vendedor.setEstado(telaVendedor.getComboBoxEstado().getSelectedItem().toString());

		arrayVendedores.add(vendedor);

	}

	private void atribuiCampos(Vendedor vendedorExibicao) {

		telaVendedor.getTextFieldBairro().setText(vendedorExibicao.getBairro());
		telaVendedor.getTextFieldCep().setText(vendedorExibicao.getCep());
		telaVendedor.getTextFieldCidade().setText(vendedorExibicao.getCidade());
		telaVendedor.getTextFieldComissao().setText(String.valueOf(vendedorExibicao.getComissao()));
		telaVendedor.getTextFieldComplemento().setText(vendedorExibicao.getComplemento());
		telaVendedor.getTextFieldCpf().setText(vendedorExibicao.getCpf());
		telaVendedor.getTextFieldDatadeNascimento().setText(vendedorExibicao.getDataNasc());
		telaVendedor.getTextFieldID().setText(String.valueOf(vendedorExibicao.getCodigo()));
		telaVendedor.getTextFieldLogradouro().setText(vendedorExibicao.getLogradouro());
		telaVendedor.getTextFieldNome().setText(vendedorExibicao.getNome());
		telaVendedor.getTextFieldNumero().setText(vendedorExibicao.getNumero());
		telaVendedor.getTextFieldRg().setText(vendedorExibicao.getRg());
		telaVendedor.getTextFieldSalario().setText(String.valueOf(vendedorExibicao.getSalario()));
		telaVendedor.getComboBoxEstado().setSelectedItem(vendedorExibicao.getEstado());
		telaVendedor.getComboBoxSexo().setSelectedItem(vendedorExibicao.getSexo());

	}

	private void gravaRegistro() {
		// grava o novo registro no fim do arquivo txt
		int i = arrayVendedores.size() - 1;
		String[] linha = new String[] { String.valueOf(arrayVendedores.get(i).getCodigo()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getNome()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getSexo()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getCpf()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getDataNasc()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getRg()) + ";" 
				+ String.valueOf(arrayVendedores.get(i).getCep()) + ";" 
				+ String.valueOf(arrayVendedores.get(i).getLogradouro()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getNumero()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getBairro()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getComplemento()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getCidade()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getEstado()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getComissao()) + ";"
				+ String.valueOf(arrayVendedores.get(i).getSalario()) };

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("arquivoVendedor.txt", true))) {

			for (String line : linha) {
				bw.write(line);
				bw.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		limpaTela();
		inicialCampos();
	}

	private void carregaArrayList() {
		// Carrega os dados do arquivo txt no arraylist ao iniciar a tela de cadastro de
		// vendedor
		arrayVendedores.clear();

		BufferedReader brV = null;
		FileReader frV = null;

		try {

			frV = new FileReader("arquivoVendedor.txt");
			brV = new BufferedReader(frV);
			String line = brV.readLine();

			while (line != null) {

				String array[] = line.split(";");
				line = brV.readLine();

				vendedor = new Vendedor();

				this.vendedor.setCodigo(Integer.parseInt(array[0]));
				this.vendedor.setComissao(Float.parseFloat(array[13]));
				this.vendedor.setSalario(Float.parseFloat(array[14]));
				this.vendedor.setCpf(array[3]);
				this.vendedor.setDataNasc(array[4]);
				this.vendedor.setNome(array[1]);
				this.vendedor.setRg(array[5]);
				this.vendedor.setSexo(array[2]);
				this.vendedor.setBairro(array[9]);
				this.vendedor.setCep(array[6]);
				this.vendedor.setCidade(array[11]);
				this.vendedor.setComplemento(array[10]);
				this.vendedor.setLogradouro(array[7]);
				this.vendedor.setNumero(array[8]);
				this.vendedor.setEstado(array[12]);

				arrayVendedores.add(vendedor);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

		} finally {

			try {
				if (brV != null)
					brV.close();
				if (frV != null)
					frV.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private boolean validacao() {

		if (telaVendedor.getTextFieldBairro().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Bairro");
			return false;
		}

		if (telaVendedor.getTextFieldCep().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP");
			return false;
		}

		if (telaVendedor.getTextFieldCidade().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Cidade");
			return false;
		}

		if (telaVendedor.getTextFieldComissao().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Comissão");
			return false;
		}

		if (telaVendedor.getTextFieldComplemento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Complemento");
			return false;
		}

		if (telaVendedor.getTextFieldCpf().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CPF");
			return false;
		}

		if (telaVendedor.getTextFieldDatadeNascimento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Data de Nascimento");
			return false;
		}

		if (telaVendedor.getTextFieldID().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Código");
			return false;
		}

		if (telaVendedor.getTextFieldLogradouro().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Logradouro");
			return false;
		}

		if (telaVendedor.getTextFieldNome().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Nome");
			return false;
		}

		if (telaVendedor.getTextFieldNumero().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Código");
			return false;
		}

		if (telaVendedor.getTextFieldRg().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Código");
			return false;
		}

		if (telaVendedor.getTextFieldSalario().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Código");
			return false;
		}

		if (telaVendedor.getComboBoxEstado().getSelectedItem() == "Selecione") {
			JOptionPane.showMessageDialog(null, "Informe o Estado");
			return false;
		}

		if (telaVendedor.getComboBoxSexo().getSelectedItem() == "Selecione") {
			JOptionPane.showMessageDialog(null, "Informe o Sexo");
			return false;
		}
		else {
			return true;
		}
	}

	private void limpaTela() {

		telaVendedor.getTextFieldBairro().setText("");
		telaVendedor.getTextFieldCep().setText("");
		telaVendedor.getTextFieldCidade().setText("");
		telaVendedor.getTextFieldComissao().setText("");
		telaVendedor.getTextFieldComplemento().setText("");
		telaVendedor.getTextFieldCpf().setText("");
		telaVendedor.getTextFieldDatadeNascimento().setText("");
		telaVendedor.getTextFieldID().setText("");
		telaVendedor.getTextFieldLogradouro().setText("");
		telaVendedor.getTextFieldNome().setText("");
		telaVendedor.getTextFieldNumero().setText("");
		telaVendedor.getTextFieldRg().setText("");
		telaVendedor.getTextFieldSalario().setText("");
		telaVendedor.getComboBoxEstado().setSelectedItem("Selecione");
		telaVendedor.getComboBoxSexo().setSelectedItem("Selecione");

	}

	private void inicialCampos() {

		// campos desabilitados no início
		telaVendedor.getTextFieldBairro().setEnabled(false);
		telaVendedor.getTextFieldCep().setEnabled(false);
		telaVendedor.getTextFieldCidade().setEnabled(false);
		telaVendedor.getTextFieldComissao().setEnabled(false);
		telaVendedor.getTextFieldComplemento().setEnabled(false);
		telaVendedor.getTextFieldCpf().setEnabled(false);
		telaVendedor.getTextFieldDatadeNascimento().setEnabled(false);
		telaVendedor.getTextFieldID().setEnabled(false);
		telaVendedor.getTextFieldLogradouro().setEnabled(false);
		telaVendedor.getTextFieldNome().setEnabled(false);
		telaVendedor.getTextFieldNumero().setEnabled(false);
		telaVendedor.getTextFieldRg().setEnabled(false);
		telaVendedor.getTextFieldSalario().setEnabled(false);
		telaVendedor.getComboBoxEstado().setEnabled(false);
		telaVendedor.getComboBoxSexo().setEnabled(false);

		// Botões desabilitados no início
		telaVendedor.getBtnCancelar().setEnabled(false);
		telaVendedor.getBtnEditar().setEnabled(false);
		telaVendedor.getBtnConfirmar().setEnabled(false);
		telaVendedor.getBtnInserir().setEnabled(true);
		telaVendedor.getBtnBuscar().setEnabled(true);

	}

	private void limparPesquisa() {

		telaVendedor.getTextFieldPesquisa().setText("");
		telaVendedor.getComboBoxPesquisa().setSelectedItem("Selecione");
		telaVendedor.getTable().setModel(telaVendedor.getVendedorTableModel(false));

	}

	private void camposEditar() {

		// campos desabilitados
		telaVendedor.getTextFieldBairro().setEnabled(true);
		telaVendedor.getTextFieldCep().setEnabled(true);
		telaVendedor.getTextFieldCidade().setEnabled(true);
		telaVendedor.getTextFieldComissao().setEnabled(true);
		telaVendedor.getTextFieldComplemento().setEnabled(true);
		telaVendedor.getTextFieldCpf().setEnabled(true);
		telaVendedor.getTextFieldDatadeNascimento().setEnabled(true);
		telaVendedor.getTextFieldID().setEnabled(true);
		telaVendedor.getTextFieldLogradouro().setEnabled(true);
		telaVendedor.getTextFieldNome().setEnabled(true);
		telaVendedor.getTextFieldNumero().setEnabled(true);
		telaVendedor.getTextFieldRg().setEnabled(true);
		telaVendedor.getTextFieldSalario().setEnabled(true);
		telaVendedor.getComboBoxEstado().setEnabled(true);
		telaVendedor.getComboBoxSexo().setEnabled(true);

		// Botões desabilitados
		telaVendedor.getBtnCancelar().setEnabled(true);
		telaVendedor.getBtnBuscar().setEnabled(false);
		telaVendedor.getBtnInserir().setEnabled(false);
		telaVendedor.getBtnEditar().setEnabled(false);
		telaVendedor.getBtnConfirmar().setEnabled(true);

	}

	private void excluiRegistro(Vendedor vendedorExibicao) {

		String linhaV = new String ( String.valueOf(vendedorExibicao.getCodigo()) + ";"
				+ String.valueOf(vendedorExibicao.getNome()) + ";"
				+ String.valueOf(vendedorExibicao.getSexo()) + ";"
				+ String.valueOf(vendedorExibicao.getCpf()) + ";"
				+ String.valueOf(vendedorExibicao.getDataNasc()) + ";"
				+ String.valueOf(vendedorExibicao.getRg()) + ";" 
				+ String.valueOf(vendedorExibicao.getCep()) + ";" 
				+ String.valueOf(vendedorExibicao.getLogradouro()) + ";"
				+ String.valueOf(vendedorExibicao.getNumero()) + ";"
				+ String.valueOf(vendedorExibicao.getBairro()) + ";"
				+ String.valueOf(vendedorExibicao.getComplemento()) + ";"
				+ String.valueOf(vendedorExibicao.getCidade()) + ";"
				+ String.valueOf(vendedorExibicao.getEstado()) + ";"
				+ String.valueOf(vendedorExibicao.getComissao()) + ";"
				+ String.valueOf(vendedorExibicao.getSalario()) );

		try {

			BufferedReader s = new BufferedReader(new FileReader("arquivoVendedor.txt"));
			String linhaRead;

			while ((linhaRead = s.readLine()) != null) {

				try (BufferedWriter bw = new BufferedWriter(new FileWriter("temporario.txt", true))) {
					if (!linhaRead.equals(linhaV)) {
						bw.write(linhaRead);
						bw.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		new File("arquivoVendedor.txt").delete();
		new File("temporario.txt").renameTo(new File("arquivoVendedor.txt"));

	}

	private void camposPosBusca() {

		// campos desabilitados
		telaVendedor.getTextFieldBairro().setEnabled(false);
		telaVendedor.getTextFieldCep().setEnabled(false);
		telaVendedor.getTextFieldCidade().setEnabled(false);
		telaVendedor.getTextFieldComissao().setEnabled(false);
		telaVendedor.getTextFieldComplemento().setEnabled(false);
		telaVendedor.getTextFieldCpf().setEnabled(false);
		telaVendedor.getTextFieldDatadeNascimento().setEnabled(false);
		telaVendedor.getTextFieldID().setEnabled(false);
		telaVendedor.getTextFieldLogradouro().setEnabled(false);
		telaVendedor.getTextFieldNome().setEnabled(false);
		telaVendedor.getTextFieldNumero().setEnabled(false);
		telaVendedor.getTextFieldRg().setEnabled(false);
		telaVendedor.getTextFieldSalario().setEnabled(false);
		telaVendedor.getComboBoxEstado().setEnabled(false);
		telaVendedor.getComboBoxSexo().setEnabled(false);

		// Botões desabilitados
		telaVendedor.getBtnCancelar().setEnabled(true);
		telaVendedor.getBtnBuscar().setEnabled(false);
		telaVendedor.getBtnInserir().setEnabled(false);
		telaVendedor.getBtnConfirmar().setEnabled(false);
		telaVendedor.getBtnEditar().setEnabled(true);

	}

	private void inicializaTela() {
		telaVendedor = new VisaoCadastroVendedor();
		ControladorFrame.framePrincipal.setContentPane(telaVendedor);
		new ControladorCadastroVendedor(telaVendedor);
		ControladorFrame.framePrincipal.repaint();
		ControladorFrame.framePrincipal.validate();
	}

	private boolean buscaBanco() {
		String tipoBusca, valorBusca;

		tipoBusca = telaVendedor.getComboBoxPesquisa().getSelectedItem().toString();
		valorBusca = telaVendedor.getTextFieldPesquisa().getText();

		if (!tipoBusca.equals("Selecione")) {
			if (!valorBusca.trim().equals("")) {

				if (tipoBusca.equals("Código")) {
					telaVendedor.getTable().setModel(telaVendedor.getVendedorTableModel(false));// seta uma nova tabela,
																								// só pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayVendedores.size(); i++) {

						if (String.valueOf(arrayVendedores.get(i).getCodigo()).contains(valorBusca)) {

							Vendedor newVendedor = new Vendedor();

							newVendedor.setCodigo(arrayVendedores.get(i).getCodigo());
							newVendedor.setComissao(arrayVendedores.get(i).getComissao());
							newVendedor.setSalario(arrayVendedores.get(i).getSalario());
							newVendedor.setCpf(arrayVendedores.get(i).getCpf());
							newVendedor.setDataNasc(arrayVendedores.get(i).getDataNasc());
							newVendedor.setNome(arrayVendedores.get(i).getNome());
							newVendedor.setRg(arrayVendedores.get(i).getRg());
							newVendedor.setSexo(arrayVendedores.get(i).getSexo());
							newVendedor.setBairro(arrayVendedores.get(i).getBairro());
							newVendedor.setCep(arrayVendedores.get(i).getCep());
							newVendedor.setCidade(arrayVendedores.get(i).getCidade());
							newVendedor.setComplemento(arrayVendedores.get(i).getComplemento());
							newVendedor.setLogradouro(arrayVendedores.get(i).getLogradouro());
							newVendedor.setNumero(arrayVendedores.get(i).getNumero());
							newVendedor.setEstado(arrayVendedores.get(i).getEstado());

							telaVendedor.getVendedorTableModel(true).add_tabela(newVendedor);

						}
					}

					if (telaVendedor.getVendedorTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhum vendedor encontrado.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Nome")) {
					telaVendedor.getTable().setModel(telaVendedor.getVendedorTableModel(false));// seta uma nova tabela,
																								// só pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayVendedores.size(); i++) {

						if (arrayVendedores.get(i).getNome().contains(valorBusca)) {

							Vendedor newVendedor = new Vendedor();

							newVendedor.setCodigo(arrayVendedores.get(i).getCodigo());
							newVendedor.setComissao(arrayVendedores.get(i).getComissao());
							newVendedor.setSalario(arrayVendedores.get(i).getSalario());
							newVendedor.setCpf(arrayVendedores.get(i).getCpf());
							newVendedor.setDataNasc(arrayVendedores.get(i).getDataNasc());
							newVendedor.setNome(arrayVendedores.get(i).getNome());
							newVendedor.setRg(arrayVendedores.get(i).getRg());
							newVendedor.setSexo(arrayVendedores.get(i).getSexo());
							newVendedor.setBairro(arrayVendedores.get(i).getBairro());
							newVendedor.setCep(arrayVendedores.get(i).getCep());
							newVendedor.setCidade(arrayVendedores.get(i).getCidade());
							newVendedor.setComplemento(arrayVendedores.get(i).getComplemento());
							newVendedor.setLogradouro(arrayVendedores.get(i).getLogradouro());
							newVendedor.setNumero(arrayVendedores.get(i).getNumero());
							newVendedor.setEstado(arrayVendedores.get(i).getEstado());

							telaVendedor.getVendedorTableModel(true).add_tabela(newVendedor);

						}
					}

					if (telaVendedor.getVendedorTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhum vendedor encontrado.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Cpf")) {
					telaVendedor.getTable().setModel(telaVendedor.getVendedorTableModel(false));// seta uma nova tabela,
																								// só pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayVendedores.size(); i++) {

						if (arrayVendedores.get(i).getCpf().contains(valorBusca)) {

							Vendedor newVendedor = new Vendedor();

							newVendedor.setCodigo(arrayVendedores.get(i).getCodigo());
							newVendedor.setComissao(arrayVendedores.get(i).getComissao());
							newVendedor.setSalario(arrayVendedores.get(i).getSalario());
							newVendedor.setCpf(arrayVendedores.get(i).getCpf());
							newVendedor.setDataNasc(arrayVendedores.get(i).getDataNasc());
							newVendedor.setNome(arrayVendedores.get(i).getNome());
							newVendedor.setRg(arrayVendedores.get(i).getRg());
							newVendedor.setSexo(arrayVendedores.get(i).getSexo());
							newVendedor.setBairro(arrayVendedores.get(i).getBairro());
							newVendedor.setCep(arrayVendedores.get(i).getCep());
							newVendedor.setCidade(arrayVendedores.get(i).getCidade());
							newVendedor.setComplemento(arrayVendedores.get(i).getComplemento());
							newVendedor.setLogradouro(arrayVendedores.get(i).getLogradouro());
							newVendedor.setNumero(arrayVendedores.get(i).getNumero());
							newVendedor.setEstado(arrayVendedores.get(i).getEstado());

							telaVendedor.getVendedorTableModel(true).add_tabela(newVendedor);

						}
					}

					if (telaVendedor.getVendedorTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhum vendedor encontrado.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Preencha o campo de busca!", "Selecione",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {

			if (tipoBusca.equals("Selecione") && (valorBusca.trim().equals(""))) {

				telaVendedor.getTable().setModel(telaVendedor.getVendedorTableModel(false));// seta uma nova tabela,
				// exibição dos resultados da busca com todos os registros
				for (int i = 0; i < arrayVendedores.size(); i++) {

					Vendedor newVendedor = new Vendedor();

					newVendedor.setCodigo(arrayVendedores.get(i).getCodigo());
					newVendedor.setComissao(arrayVendedores.get(i).getComissao());
					newVendedor.setSalario(arrayVendedores.get(i).getSalario());
					newVendedor.setCpf(arrayVendedores.get(i).getCpf());
					newVendedor.setDataNasc(arrayVendedores.get(i).getDataNasc());
					newVendedor.setNome(arrayVendedores.get(i).getNome());
					newVendedor.setRg(arrayVendedores.get(i).getRg());
					newVendedor.setSexo(arrayVendedores.get(i).getSexo());
					newVendedor.setBairro(arrayVendedores.get(i).getBairro());
					newVendedor.setCep(arrayVendedores.get(i).getCep());
					newVendedor.setCidade(arrayVendedores.get(i).getCidade());
					newVendedor.setComplemento(arrayVendedores.get(i).getComplemento());
					newVendedor.setLogradouro(arrayVendedores.get(i).getLogradouro());
					newVendedor.setNumero(arrayVendedores.get(i).getNumero());
					newVendedor.setEstado(arrayVendedores.get(i).getEstado());

					telaVendedor.getVendedorTableModel(true).add_tabela(newVendedor);

				}

			} else if (telaVendedor.getVendedorTableModel(true).getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Busca finalizada, nenhum vendedor encontrado.", "Busca",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}

		}

		return true;
	}
}