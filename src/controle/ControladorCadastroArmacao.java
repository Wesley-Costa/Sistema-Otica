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
import modelo.Armacao;
import visao.VisaoCadastroArmacao;

public class ControladorCadastroArmacao implements ActionListener {

	private VisaoCadastroArmacao telaArmacao;
	private Armacao armacao;
	static ArrayList<Armacao> arrayArmacoes = new ArrayList<Armacao>();

	public ControladorCadastroArmacao(VisaoCadastroArmacao telaArmacao) {

		this.telaArmacao = telaArmacao;
		carregaArrayList();
		inicialCampos();
		addEventos();

	}

	public void addEventos() {

		telaArmacao.getBtnSair().addActionListener(this);
		telaArmacao.getBtnConfirmar().addActionListener(this);
		telaArmacao.getBtnEditar().addActionListener(this);
		telaArmacao.getBtnInserir().addActionListener(this);
		telaArmacao.getBtnBuscar().addActionListener(this);
		telaArmacao.getBtnCancelar().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == telaArmacao.getBtnBuscar()) {

			String busca;
			busca = telaArmacao.getComboBoxPesquisa().getSelectedItem().toString();

			if (busca == "Código" || busca == "Material" || busca == "Formato" || busca == "Marca"
					|| busca == "Selecione") {
				if (buscaBanco() == true) {
					camposPosBusca();
					selecao();
					mouse();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Preencha o campo de busca!", "Selecione",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (e.getSource() == telaArmacao.getBtnSair()) {
			Object[] options = { "NÃO", "SIM" };
			int opcao = JOptionPane.showOptionDialog(null, "Deseja retornar a tela inicial?", "SAIR",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if (opcao == 1) {
				new ControladorFrame();
			}
		}

		if (e.getSource() == telaArmacao.getBtnInserir()) {
			limparPesquisa();
			camposEditar();
		}

		if (e.getSource() == telaArmacao.getBtnCancelar()) {
			telaArmacao.getLabelTitulo().setText("Cadastro de Armação");
			inicialCampos();
			limparPesquisa();
			limpaTela();

		}

		if (e.getSource() == telaArmacao.getBtnEditar()) {
			telaArmacao.getLabelTitulo().setText("Cadastro de Armação - Editar");
			camposEditar();
			telaArmacao.getTextFieldCodigo().setEnabled(false);
			// limparPesquisa();
		}

		if (e.getSource() == telaArmacao.getBtnConfirmar()) {

			if (telaArmacao.getLabelTitulo().getText().equals("Cadastro de Armação - Editar")) {
				Object[] options = { "NÃO", "SIM" };
				int opcao = JOptionPane.showOptionDialog(null, "Deseja confirmar a operação?", "CONFIRMAR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if (opcao == 1) {

					Armacao armacaoExibicao = new Armacao();
					int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
					armacaoExibicao = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
					if(validacao()==true) {
						excluiRegistro(armacaoExibicao);
						capturaDados();
						gravaRegistro();
						JOptionPane.showMessageDialog(null, "Produto Alterado");
						inicializaTela();
					}
				}

			} else {

				Object[] options = { "NÃO", "SIM" };
				int opcao = JOptionPane.showOptionDialog(null, "Deseja confirmar a operação?", "CONFIRMAR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				String busca = telaArmacao.getTextFieldCodigo().getText();

				if (opcao == 1) {

					int existe = 0;

					for (int i = 0; i < arrayArmacoes.size(); i++) {
						if (String.valueOf(arrayArmacoes.get(i).getCodigo()).contains(busca)) {
							JOptionPane.showMessageDialog(null, "Código já Cadastrado");
							existe = 1;
						}
					}

					if (existe == 0) {
						if(validacao() == true) {
							capturaDados();
							gravaRegistro();
							JOptionPane.showMessageDialog(null, "Produto Cadastrado");
							inicializaTela();
						}
					}
				}
			}

		}

	}

	public void selecao() {
		telaArmacao.getTable().addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Armacao armacaoExibicao = new Armacao();
					int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
					armacaoExibicao = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
					atribuiCampos(armacaoExibicao);
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Armacao armacaoExibicao = new Armacao();
					int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
					armacaoExibicao = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
					atribuiCampos(armacaoExibicao);
				}
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Armacao armacaoExibicao = new Armacao();
					int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
					armacaoExibicao = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
					atribuiCampos(armacaoExibicao);
				}
			}

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Armacao armacaoExibicao = new Armacao();
					int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
					armacaoExibicao = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
					atribuiCampos(armacaoExibicao);
				}

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					Object[] options = { "NÃO", "SIM" };
					int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir a armação do banco de dados?",
							"Exclusão", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);

					if (opcao == 1) {
						Armacao armacaoExibida = new Armacao();
						int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
						armacaoExibida = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
						excluiRegistro(armacaoExibida);
						carregaArrayList();
						JOptionPane.showMessageDialog(null, "Produto Excluído");
						inicializaTela();

					}
				}

			}
		});
	}

	public void mouse() {
		telaArmacao.getTable().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent a) {
				if (a.getClickCount() == 1) {
					Armacao armacaoExibicao = new Armacao();
					int linhaSelecionada = telaArmacao.getTable().getSelectedRow();
					armacaoExibicao = telaArmacao.getArmacaoTableModel(true).getArmacao(linhaSelecionada);
					atribuiCampos(armacaoExibicao);
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

		armacao = new Armacao();
		this.armacao.setCodigo(Integer.parseInt(telaArmacao.getTextFieldCodigo().getText()));
		this.armacao.setCor(telaArmacao.getTextFieldCor().getText());
		this.armacao.setEstoqueArm(Integer.parseInt(telaArmacao.getTextFieldEstoque().getText()));
		this.armacao.setFormato(telaArmacao.getTextFieldFormato().getText());
		this.armacao.setMarca(telaArmacao.getTextFieldMarca().getText());
		this.armacao.setMaterial(telaArmacao.getTextFieldMaterial().getText());
		this.armacao.setTamanho(telaArmacao.getTextFieldTamanho().getText());
		this.armacao.setValor(Float.parseFloat(telaArmacao.getTextFieldValorUnitario().getText()));

		arrayArmacoes.add(armacao);

	}

	private void atribuiCampos(Armacao armacaoExibicao) {

		telaArmacao.getTextFieldCodigo().setText(String.valueOf(armacaoExibicao.getCodigo()));
		telaArmacao.getTextFieldCor().setText(armacaoExibicao.getCor());
		telaArmacao.getTextFieldEstoque().setText(String.valueOf(armacaoExibicao.getEstoqueArm()));
		telaArmacao.getTextFieldFormato().setText(armacaoExibicao.getFormato());
		telaArmacao.getTextFieldMarca().setText(armacaoExibicao.getMarca());
		telaArmacao.getTextFieldMaterial().setText(armacaoExibicao.getMaterial());
		telaArmacao.getTextFieldTamanho().setText(armacaoExibicao.getTamanho());
		telaArmacao.getTextFieldValorUnitario().setText(String.valueOf(armacaoExibicao.getValor()));

	}

	private void gravaRegistro() {
		// grava o novo registro no fim do arquivo txt
		int i = arrayArmacoes.size() - 1;
		String[] linha = new String[] {
				String.valueOf(arrayArmacoes.get(i).getCodigo()) + ";" + arrayArmacoes.get(i).getCor() + ";"
						+ String.valueOf(arrayArmacoes.get(i).getEstoqueArm()) + ";" + arrayArmacoes.get(i).getFormato()
						+ ";" + arrayArmacoes.get(i).getMarca() + ";" + arrayArmacoes.get(i).getMaterial() + ";"
						+ arrayArmacoes.get(i).getTamanho() + ";" + String.valueOf(arrayArmacoes.get(i).getValor()) };

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("arquivoArmacao.txt", true))) {

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
		// armacao
		arrayArmacoes.clear();

		BufferedReader brA = null;
		FileReader frA = null;

		try {

			frA = new FileReader("arquivoArmacao.txt");
			brA = new BufferedReader(frA);
			String line = brA.readLine();

			while (line != null) {
				String array[] = line.split(";");
				line = brA.readLine();
				armacao = new Armacao();
				this.armacao.setCodigo(Integer.parseInt(array[0]));
				this.armacao.setCor(array[1]);
				this.armacao.setEstoqueArm(Integer.parseInt(array[2]));
				this.armacao.setFormato(array[3]);
				this.armacao.setMarca(array[4]);
				this.armacao.setMaterial(array[5]);
				this.armacao.setTamanho(array[6]);
				this.armacao.setValor(Float.parseFloat(array[7]));
				arrayArmacoes.add(armacao);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

		} finally {

			try {
				if (brA != null)
					brA.close();
				if (frA != null)
					frA.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private boolean validacao() {
		// verifica se todos os campos foram preenchidos pelo usuário
		if (telaArmacao.getTextFieldCodigo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Erro: Campo Código está vazio!");
			return false;
		}

		if (telaArmacao.getTextFieldCor().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Cor");
			return false;
		}

		if (telaArmacao.getTextFieldEstoque().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Estoque");
			return false;
		}

		if (telaArmacao.getTextFieldFormato().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Formato");
			return false;
		}

		if (telaArmacao.getTextFieldMarca().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Marca");
			return false;
		}

		if (telaArmacao.getTextFieldMaterial().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Material");
			return false;
		}

		if (telaArmacao.getTextFieldTamanho().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Tamanho");
			return false;
		}

		if (telaArmacao.getTextFieldValorUnitario().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Valor");
			return false;
		} else {
			return true;
		}

	}

	private void limpaTela() {

		telaArmacao.getTextFieldCodigo().setText("");
		telaArmacao.getTextFieldCor().setText("");
		telaArmacao.getTextFieldEstoque().setText("");
		telaArmacao.getTextFieldFormato().setText("");
		telaArmacao.getTextFieldMarca().setText("");
		telaArmacao.getTextFieldMaterial().setText("");
		telaArmacao.getTextFieldTamanho().setText("");
		telaArmacao.getTextFieldValorUnitario().setText("");

	}

	private void inicialCampos() {

		// campos desabilitados no início
		telaArmacao.getTextFieldCodigo().setEnabled(false);
		telaArmacao.getTextFieldCor().setEnabled(false);
		telaArmacao.getTextFieldEstoque().setEnabled(false);
		telaArmacao.getTextFieldFormato().setEnabled(false);
		telaArmacao.getTextFieldMarca().setEnabled(false);
		telaArmacao.getTextFieldMaterial().setEnabled(false);
		telaArmacao.getTextFieldTamanho().setEnabled(false);
		telaArmacao.getTextFieldValorUnitario().setEnabled(false);
		// Botões desabilitados no início
		telaArmacao.getBtnCancelar().setEnabled(false);
		telaArmacao.getBtnEditar().setEnabled(false);
		telaArmacao.getBtnConfirmar().setEnabled(false);
		telaArmacao.getBtnInserir().setEnabled(true);
		telaArmacao.getBtnBuscar().setEnabled(true);

	}

	private void limparPesquisa() {

		telaArmacao.getTextFieldPesquisa().setText("");
		telaArmacao.getComboBoxPesquisa().setSelectedItem("Selecione");
		telaArmacao.getTable().setModel(telaArmacao.getArmacaoTableModel(false));

	}

	private void camposEditar() {

		// campos desabilitados
		telaArmacao.getTextFieldCodigo().setEnabled(true);
		telaArmacao.getTextFieldCor().setEnabled(true);
		telaArmacao.getTextFieldEstoque().setEnabled(true);
		telaArmacao.getTextFieldFormato().setEnabled(true);
		telaArmacao.getTextFieldMarca().setEnabled(true);
		telaArmacao.getTextFieldMaterial().setEnabled(true);
		telaArmacao.getTextFieldTamanho().setEnabled(true);
		telaArmacao.getTextFieldValorUnitario().setEnabled(true);
		// Botões desabilitados
		telaArmacao.getBtnCancelar().setEnabled(true);
		telaArmacao.getBtnBuscar().setEnabled(false);
		telaArmacao.getBtnInserir().setEnabled(false);
		telaArmacao.getBtnEditar().setEnabled(false);
		telaArmacao.getBtnConfirmar().setEnabled(true);

	}

	private void excluiRegistro(Armacao armacaoExibicao) {

		String linha = new String(String.valueOf(armacaoExibicao.getCodigo()) + ";" + armacaoExibicao.getCor() + ";"
				+ String.valueOf(armacaoExibicao.getEstoqueArm()) + ";" + armacaoExibicao.getFormato() + ";"
				+ armacaoExibicao.getMarca() + ";" + armacaoExibicao.getMaterial() + ";" + armacaoExibicao.getTamanho()
				+ ";" + String.valueOf(armacaoExibicao.getValor()));

		try {

			BufferedReader s = new BufferedReader(new FileReader("arquivoArmacao.txt"));
			String linhaRead;

			while ((linhaRead = s.readLine()) != null) {

				try (BufferedWriter bw = new BufferedWriter(new FileWriter("temporario.txt", true))) {
					if (!linhaRead.equals(linha)) {
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

		new File("arquivoArmacao.txt").delete();
		new File("temporario.txt").renameTo(new File("arquivoArmacao.txt"));

	}

	private void camposPosBusca() {

		// campos desabilitados
		telaArmacao.getTextFieldCodigo().setEnabled(false);
		telaArmacao.getTextFieldCor().setEnabled(false);
		telaArmacao.getTextFieldEstoque().setEnabled(false);
		telaArmacao.getTextFieldFormato().setEnabled(false);
		telaArmacao.getTextFieldMarca().setEnabled(false);
		telaArmacao.getTextFieldMaterial().setEnabled(false);
		telaArmacao.getTextFieldTamanho().setEnabled(false);
		telaArmacao.getTextFieldValorUnitario().setEnabled(false);
		// Botões desabilitados
		telaArmacao.getBtnCancelar().setEnabled(true);
		telaArmacao.getBtnBuscar().setEnabled(false);
		telaArmacao.getBtnInserir().setEnabled(false);
		telaArmacao.getBtnConfirmar().setEnabled(false);
		telaArmacao.getBtnEditar().setEnabled(true);

	}

	private void inicializaTela() {
		telaArmacao = new VisaoCadastroArmacao();
		ControladorFrame.framePrincipal.setContentPane(telaArmacao);
		new ControladorCadastroArmacao(telaArmacao);
		ControladorFrame.framePrincipal.repaint();
		ControladorFrame.framePrincipal.validate();
	}

	private boolean buscaBanco() {
		String tipoBusca, valorBusca;

		tipoBusca = telaArmacao.getComboBoxPesquisa().getSelectedItem().toString();
		valorBusca = telaArmacao.getTextFieldPesquisa().getText();

		if (!tipoBusca.equals("Selecione")) {
			if (!valorBusca.trim().equals("")) {

				if (tipoBusca.equals("Código")) {
					telaArmacao.getTable().setModel(telaArmacao.getArmacaoTableModel(false));// seta uma nova tabela, só
																								// pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayArmacoes.size(); i++) {

						if (String.valueOf(arrayArmacoes.get(i).getCodigo()).contains(valorBusca)) {

							Armacao newArmacao = new Armacao();
							newArmacao.setCodigo(arrayArmacoes.get(i).getCodigo());
							newArmacao.setCor(arrayArmacoes.get(i).getCor());
							newArmacao.setEstoqueArm(arrayArmacoes.get(i).getEstoqueArm());
							newArmacao.setFormato(arrayArmacoes.get(i).getFormato());
							newArmacao.setMarca(arrayArmacoes.get(i).getMarca());
							newArmacao.setMaterial(arrayArmacoes.get(i).getMaterial());
							newArmacao.setTamanho(arrayArmacoes.get(i).getTamanho());
							newArmacao.setValor(arrayArmacoes.get(i).getValor());
							telaArmacao.getArmacaoTableModel(true).add_tabela(newArmacao);

						}
					}

					if (telaArmacao.getArmacaoTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma armação encontrada.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Material")) {
					telaArmacao.getTable().setModel(telaArmacao.getArmacaoTableModel(false));// seta uma nova tabela, só
																								// pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayArmacoes.size(); i++) {

						if (arrayArmacoes.get(i).getMaterial().contains(valorBusca)) {

							Armacao newArmacao = new Armacao();
							newArmacao.setCodigo(arrayArmacoes.get(i).getCodigo());
							newArmacao.setCor(arrayArmacoes.get(i).getCor());
							newArmacao.setEstoqueArm(arrayArmacoes.get(i).getEstoqueArm());
							newArmacao.setFormato(arrayArmacoes.get(i).getFormato());
							newArmacao.setMarca(arrayArmacoes.get(i).getMarca());
							newArmacao.setMaterial(arrayArmacoes.get(i).getMaterial());
							newArmacao.setTamanho(arrayArmacoes.get(i).getTamanho());
							newArmacao.setValor(arrayArmacoes.get(i).getValor());
							telaArmacao.getArmacaoTableModel(true).add_tabela(newArmacao);

						}
					}

					if (telaArmacao.getArmacaoTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma armação encontrada.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Formato")) {
					telaArmacao.getTable().setModel(telaArmacao.getArmacaoTableModel(false));// seta uma nova tabela, só
																								// pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayArmacoes.size(); i++) {

						if (arrayArmacoes.get(i).getFormato().contains(valorBusca)) {

							Armacao newArmacao = new Armacao();
							newArmacao.setCodigo(arrayArmacoes.get(i).getCodigo());
							newArmacao.setCor(arrayArmacoes.get(i).getCor());
							newArmacao.setEstoqueArm(arrayArmacoes.get(i).getEstoqueArm());
							newArmacao.setFormato(arrayArmacoes.get(i).getFormato());
							newArmacao.setMarca(arrayArmacoes.get(i).getMarca());
							newArmacao.setMaterial(arrayArmacoes.get(i).getMaterial());
							newArmacao.setTamanho(arrayArmacoes.get(i).getTamanho());
							newArmacao.setValor(arrayArmacoes.get(i).getValor());
							telaArmacao.getArmacaoTableModel(true).add_tabela(newArmacao);
						}
					}

					if (telaArmacao.getArmacaoTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma armação encontrada.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Marca")) {
					telaArmacao.getTable().setModel(telaArmacao.getArmacaoTableModel(false));// seta uma nova tabela, só
																								// pra
					// exibição dos resultados da
					// busca
					for (int i = 0; i < arrayArmacoes.size(); i++) {

						if (arrayArmacoes.get(i).getMarca().contains(valorBusca)) {

							Armacao newArmacao = new Armacao();
							newArmacao.setCodigo(arrayArmacoes.get(i).getCodigo());
							newArmacao.setCor(arrayArmacoes.get(i).getCor());
							newArmacao.setEstoqueArm(arrayArmacoes.get(i).getEstoqueArm());
							newArmacao.setFormato(arrayArmacoes.get(i).getFormato());
							newArmacao.setMarca(arrayArmacoes.get(i).getMarca());
							newArmacao.setMaterial(arrayArmacoes.get(i).getMaterial());
							newArmacao.setTamanho(arrayArmacoes.get(i).getTamanho());
							newArmacao.setValor(arrayArmacoes.get(i).getValor());
							telaArmacao.getArmacaoTableModel(true).add_tabela(newArmacao);

						}
					}

					if (telaArmacao.getArmacaoTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma armação encontrada.", "Busca",
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
				telaArmacao.getTable().setModel(telaArmacao.getArmacaoTableModel(false));// seta uma nova tabela, só pra
				// exibição dos resultados da busca com todos os registro
				for (int i = 0; i < arrayArmacoes.size(); i++) {

					Armacao newArmacao = new Armacao();
					newArmacao.setCodigo(arrayArmacoes.get(i).getCodigo());
					newArmacao.setCor(arrayArmacoes.get(i).getCor());
					newArmacao.setEstoqueArm(arrayArmacoes.get(i).getEstoqueArm());
					newArmacao.setFormato(arrayArmacoes.get(i).getFormato());
					newArmacao.setMarca(arrayArmacoes.get(i).getMarca());
					newArmacao.setMaterial(arrayArmacoes.get(i).getMaterial());
					newArmacao.setTamanho(arrayArmacoes.get(i).getTamanho());
					newArmacao.setValor(arrayArmacoes.get(i).getValor());
					telaArmacao.getArmacaoTableModel(true).add_tabela(newArmacao);

				}

			}

			else if (telaArmacao.getArmacaoTableModel(true).getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma armação encontrada.", "Busca",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}

		return true;
	}
}