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
import modelo.Lente;
import visao.VisaoCadastroLente;

public class ControladorCadastroLente implements ActionListener {

	private VisaoCadastroLente telaLente;
	private Lente lente;
	static ArrayList<Lente> arrayLentes = new ArrayList<Lente>();

	public ControladorCadastroLente(VisaoCadastroLente telaLente) {

		this.telaLente = telaLente;
		carregaArrayList();
		inicialCampos();
		addEventos();

	}

	public void addEventos() {

		telaLente.getBtnSair().addActionListener(this);
		telaLente.getBtnConfirmar().addActionListener(this);
		telaLente.getBtnEditar().addActionListener(this);
		telaLente.getBtnInserir().addActionListener(this);
		telaLente.getBtnBuscar().addActionListener(this);
		telaLente.getBtnCancelar().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == telaLente.getBtnBuscar()) {

			String busca;
			busca = telaLente.getComboBoxPesquisa().getSelectedItem().toString();

			if (busca == "Código" || busca == "Material" || busca == "Tipo" || busca == "Grau" || busca == "Selecione") {
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

		if (e.getSource() == telaLente.getBtnSair()) {
			Object[] options = { "NÃO", "SIM" };
			int opcao = JOptionPane.showOptionDialog(null, "Deseja retornar a tela inicial?", "SAIR",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if (opcao == 1) {
				new ControladorFrame();
			}
		}

		if (e.getSource() == telaLente.getBtnInserir()) {
			limparPesquisa();
			camposEditar();
		}

		if (e.getSource() == telaLente.getBtnCancelar()) {
			telaLente.getLabelCadastroDeLente().setText("Cadastro de Lente");
			inicialCampos();
			limparPesquisa();
			limpaTela();

		}

		if (e.getSource() == telaLente.getBtnEditar()) {
			telaLente.getLabelCadastroDeLente().setText("Cadastro de Lente - Editar");
			camposEditar();
			telaLente.getTextFieldCodigo().setEnabled(false);
			// limparPesquisa();
		}

		if (e.getSource() == telaLente.getBtnConfirmar()) {

			if (telaLente.getLabelCadastroDeLente().getText().equals("Cadastro de Lente - Editar")) {
				Object[] options = { "NÃO", "SIM" };
				int opcao = JOptionPane.showOptionDialog(null, "Deseja confirmar a operação?", "CONFIRMAR",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if (opcao == 1) {

					Lente lenteExibicao = new Lente();
					int linhaSelecionada = telaLente.getTable().getSelectedRow();
					lenteExibicao = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
					if(validacao() == true) {
						excluiRegistro(lenteExibicao);
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

				String busca = telaLente.getTextFieldCodigo().getText();

				if (opcao == 1) {

					int existe = 0;

					for (int i = 0; i < arrayLentes.size(); i++) {
						if (String.valueOf(arrayLentes.get(i).getCodigo()).contains(busca)) {
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
		telaLente.getTable().addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Lente lenteExibicao = new Lente();
					int linhaSelecionada = telaLente.getTable().getSelectedRow();
					lenteExibicao = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
					atribuiCampos(lenteExibicao);
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Lente lenteExibicao = new Lente();
					int linhaSelecionada = telaLente.getTable().getSelectedRow();
					lenteExibicao = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
					atribuiCampos(lenteExibicao);
				}
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Lente lenteExibicao = new Lente();
					int linhaSelecionada = telaLente.getTable().getSelectedRow();
					lenteExibicao = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
					atribuiCampos(lenteExibicao);
				}
			}

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Lente lenteExibicao = new Lente();
					int linhaSelecionada = telaLente.getTable().getSelectedRow();
					lenteExibicao = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
					atribuiCampos(lenteExibicao);
				}

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					Object[] options = { "NÃO", "SIM" };
					int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir a lente do banco de dados?",
							"Exclusão", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);

					if (opcao == 1) {
						Lente lenteExibida = new Lente();
						int linhaSelecionada = telaLente.getTable().getSelectedRow();
						lenteExibida = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
						excluiRegistro(lenteExibida);
						carregaArrayList();
						JOptionPane.showMessageDialog(null, "Produto Excluído");
						inicializaTela();
					}
				}

			}
		});
	}

	public void mouse() {
		telaLente.getTable().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 1) {
					Lente lenteExibicao = new Lente();
					int linhaSelecionada = telaLente.getTable().getSelectedRow();
					lenteExibicao = telaLente.getLenteTableModel(true).getLente(linhaSelecionada);
					atribuiCampos(lenteExibicao);
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

		lente = new Lente();
		this.lente.setCodigo(Integer.parseInt(telaLente.getTextFieldCodigo().getText()));
		this.lente.setEstoqueLente(Integer.parseInt(telaLente.getTextFieldEstoque().getText()));
		this.lente.setGrau(telaLente.getTextFieldGrau().getText());
		this.lente.setMaterial(telaLente.getTextFieldMaterial().getText());
		this.lente.setTipo(telaLente.getTextFieldTipo().getText());
		this.lente.setValorLente(Float.parseFloat(telaLente.getTextFieldPreco().getText()));

		arrayLentes.add(lente);

	}

	private void atribuiCampos(Lente lenteExibicao) {

		telaLente.getTextFieldCodigo().setText(String.valueOf(lenteExibicao.getCodigo()));
		telaLente.getTextFieldEstoque().setText(String.valueOf(lenteExibicao.getEstoqueLente()));
		telaLente.getTextFieldGrau().setText(String.valueOf(lenteExibicao.getGrau()));
		telaLente.getTextFieldMaterial().setText(lenteExibicao.getMaterial());
		telaLente.getTextFieldPreco().setText(String.valueOf(lenteExibicao.getValorLente()));
		telaLente.getTextFieldTipo().setText(lenteExibicao.getTipo());
	}

	private void gravaRegistro() {
		// grava o novo registro no fim do arquivo txt
		int i = arrayLentes.size() - 1;
		String[] linha = new String[] { String.valueOf(arrayLentes.get(i).getCodigo()) + ";"
				+ String.valueOf(arrayLentes.get(i).getEstoqueLente()) + ";"
				+ String.valueOf(arrayLentes.get(i).getGrau()) + ";" + arrayLentes.get(i).getMaterial() + ";"
				+ arrayLentes.get(i).getTipo() + ";" + String.valueOf(arrayLentes.get(i).getValorLente()) };

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("arquivoLente.txt", true))) {

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
		// lente
		arrayLentes.clear();

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader("arquivoLente.txt");
			br = new BufferedReader(fr);
			String line = br.readLine();

			while (line != null) {
				String array[] = line.split(";");
				line = br.readLine();
				lente = new Lente();
				this.lente.setCodigo(Integer.parseInt(array[0]));
				this.lente.setEstoqueLente(Integer.parseInt(array[1]));
				this.lente.setGrau(array[2]);
				this.lente.setMaterial(array[3]);
				this.lente.setTipo(array[4]);
				this.lente.setValorLente(Float.parseFloat(array[5]));
				arrayLentes.add(lente);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

		} finally {

			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private boolean validacao() {
		// verifica se todos os campos foram preenchidos pelo usuário
		if (telaLente.getTextFieldCodigo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Erro: Campo Código está vazio!");
			return false;
		}

		if (telaLente.getTextFieldEstoque().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Estoque");
			return false;
		}

		if (telaLente.getTextFieldGrau().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Grau");
			return false;
		}

		if (telaLente.getTextFieldMaterial().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Material");
			return false;
		}

		if (telaLente.getTextFieldPreco().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Preço");
			return false;
		}

		if (telaLente.getTextFieldTipo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Tipo");
			return false;
		}
		else {
			return true;
		}

	}

	private void limpaTela() {

		telaLente.getTextFieldCodigo().setText("");
		telaLente.getTextFieldEstoque().setText("");
		telaLente.getTextFieldGrau().setText("");
		telaLente.getTextFieldMaterial().setText("");
		telaLente.getTextFieldPreco().setText("");
		telaLente.getTextFieldTipo().setText("");

	}

	private void inicialCampos() {
		// campos desabilitados no início
		telaLente.getTextFieldCodigo().setEnabled(false);
		telaLente.getTextFieldEstoque().setEnabled(false);
		telaLente.getTextFieldGrau().setEnabled(false);
		telaLente.getTextFieldMaterial().setEnabled(false);
		telaLente.getTextFieldTipo().setEnabled(false);
		telaLente.getTextFieldPreco().setEnabled(false);
		// Botões desabilitados no início
		telaLente.getBtnCancelar().setEnabled(false);
		telaLente.getBtnEditar().setEnabled(false);
		telaLente.getBtnConfirmar().setEnabled(false);
		telaLente.getBtnInserir().setEnabled(true);
		telaLente.getBtnBuscar().setEnabled(true);

	}

	private void limparPesquisa() {

		telaLente.getTextFieldPesquisa().setText("");
		telaLente.getComboBoxPesquisa().setSelectedItem("Selecione");
		telaLente.getTable().setModel(telaLente.getLenteTableModel(false));

	}

	private void camposEditar() {

		// campos desabilitados
		telaLente.getTextFieldCodigo().setEnabled(true);
		telaLente.getTextFieldEstoque().setEnabled(true);
		telaLente.getTextFieldGrau().setEnabled(true);
		telaLente.getTextFieldMaterial().setEnabled(true);
		telaLente.getTextFieldTipo().setEnabled(true);
		telaLente.getTextFieldPreco().setEnabled(true);
		// Botões desabilitados
		telaLente.getBtnCancelar().setEnabled(true);
		telaLente.getBtnBuscar().setEnabled(false);
		telaLente.getBtnInserir().setEnabled(false);
		telaLente.getBtnEditar().setEnabled(false);
		telaLente.getBtnConfirmar().setEnabled(true);

	}

	private void excluiRegistro(Lente lenteExibicao) {
		
		String linha = new String (String.valueOf(lenteExibicao.getCodigo()) + ";"
				+ String.valueOf(lenteExibicao.getEstoqueLente()) + ";"
				+ String.valueOf(lenteExibicao.getGrau()) + ";" 
				+ lenteExibicao.getMaterial() + ";"
				+ lenteExibicao.getTipo() + ";"
				+ String.valueOf(lenteExibicao.getValorLente()));
		
		try {

			BufferedReader s = new BufferedReader(new FileReader("arquivoLente.txt"));
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

		new File("arquivoLente.txt").delete();
		new File("temporario.txt").renameTo(new File("arquivoLente.txt"));

	}

	private void camposPosBusca() {

		// campos desabilitados
		telaLente.getTextFieldCodigo().setEnabled(false);
		telaLente.getTextFieldEstoque().setEnabled(false);
		telaLente.getTextFieldGrau().setEnabled(false);
		telaLente.getTextFieldMaterial().setEnabled(false);
		telaLente.getTextFieldTipo().setEnabled(false);
		telaLente.getTextFieldPreco().setEnabled(false);
		// Botões desabilitados
		telaLente.getBtnCancelar().setEnabled(true);
		telaLente.getBtnBuscar().setEnabled(false);
		telaLente.getBtnInserir().setEnabled(false);
		telaLente.getBtnConfirmar().setEnabled(false);
		telaLente.getBtnEditar().setEnabled(true);

	}

	private void inicializaTela() {
		telaLente = new VisaoCadastroLente();
		ControladorFrame.framePrincipal.setContentPane(telaLente);
		new ControladorCadastroLente(telaLente);
		ControladorFrame.framePrincipal.repaint();
		ControladorFrame.framePrincipal.validate();
	}

	private boolean buscaBanco() {

		String tipoBusca, valorBusca;

		tipoBusca = telaLente.getComboBoxPesquisa().getSelectedItem().toString();
		valorBusca = telaLente.getTextFieldPesquisa().getText();

		if (!tipoBusca.equals("Selecione")) {
			if (!valorBusca.trim().equals("")) {

				if (tipoBusca.equals("Código")) {
					telaLente.getTable().setModel(telaLente.getLenteTableModel(false));// seta uma nova tabela, só pra
																						// exibição dos resultados da
																						// busca
					for (int i = 0; i < arrayLentes.size(); i++) {

						if (String.valueOf(arrayLentes.get(i).getCodigo()).contains(valorBusca)) {

							Lente newlente = new Lente();
							newlente.setCodigo(arrayLentes.get(i).getCodigo());
							newlente.setEstoqueLente(arrayLentes.get(i).getEstoqueLente());
							newlente.setGrau(arrayLentes.get(i).getGrau());
							newlente.setMaterial(arrayLentes.get(i).getMaterial());
							newlente.setTipo(arrayLentes.get(i).getTipo());
							newlente.setValorLente(arrayLentes.get(i).getValorLente());
							telaLente.getLenteTableModel(true).add_tabela(newlente);

						}
					}

					if (telaLente.getLenteTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma lente encontrada.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Material")) {
					telaLente.getTable().setModel(telaLente.getLenteTableModel(false));// seta uma nova tabela, só pra
																						// exibição dos resultados da
																						// busca
					for (int i = 0; i < arrayLentes.size(); i++) {

						if (arrayLentes.get(i).getMaterial().contains(valorBusca)) {

							Lente newlente = new Lente();
							newlente.setCodigo(arrayLentes.get(i).getCodigo());
							newlente.setEstoqueLente(arrayLentes.get(i).getEstoqueLente());
							newlente.setGrau(arrayLentes.get(i).getGrau());
							newlente.setMaterial(arrayLentes.get(i).getMaterial());
							newlente.setTipo(arrayLentes.get(i).getTipo());
							newlente.setValorLente(arrayLentes.get(i).getValorLente());
							telaLente.getLenteTableModel(true).add_tabela(newlente);

						}
					}

					if (telaLente.getLenteTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma lente encontrada.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Tipo")) {
					telaLente.getTable().setModel(telaLente.getLenteTableModel(false));// seta uma nova tabela, só pra
																						// exibição dos resultados da
																						// busca
					for (int i = 0; i < arrayLentes.size(); i++) {

						if (arrayLentes.get(i).getTipo().contains(valorBusca)) {

							Lente newlente = new Lente();
							newlente.setCodigo(arrayLentes.get(i).getCodigo());
							newlente.setEstoqueLente(arrayLentes.get(i).getEstoqueLente());
							newlente.setGrau(arrayLentes.get(i).getGrau());
							newlente.setMaterial(arrayLentes.get(i).getMaterial());
							newlente.setTipo(arrayLentes.get(i).getTipo());
							newlente.setValorLente(arrayLentes.get(i).getValorLente());
							telaLente.getLenteTableModel(true).add_tabela(newlente);

						}
					}

					if (telaLente.getLenteTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma lente encontrada.", "Busca",
								JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				}

				if (tipoBusca.equals("Grau")) {
					telaLente.getTable().setModel(telaLente.getLenteTableModel(false));// seta uma nova tabela, só pra
																						// exibição dos resultados da
																						// busca
					for (int i = 0; i < arrayLentes.size(); i++) {

						if (String.valueOf(arrayLentes.get(i).getGrau()).contains(valorBusca)) {

							Lente newlente = new Lente();
							newlente.setCodigo(arrayLentes.get(i).getCodigo());
							newlente.setEstoqueLente(arrayLentes.get(i).getEstoqueLente());
							newlente.setGrau(arrayLentes.get(i).getGrau());
							newlente.setMaterial(arrayLentes.get(i).getMaterial());
							newlente.setTipo(arrayLentes.get(i).getTipo());
							newlente.setValorLente(arrayLentes.get(i).getValorLente());
							telaLente.getLenteTableModel(true).add_tabela(newlente);

						}
					}

					if (telaLente.getLenteTableModel(true).getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma lente encontrada.", "Busca",
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
			if (tipoBusca.equals("Selecione") && valorBusca.trim().equals("")) {
				telaLente.getTable().setModel(telaLente.getLenteTableModel(false));// seta uma nova tabela, só pra
				// exibição dos resultados da busca com todos os registro
				for (int i = 0; i < arrayLentes.size(); i++) {

					Lente newlente = new Lente();
					newlente.setCodigo(arrayLentes.get(i).getCodigo());
					newlente.setEstoqueLente(arrayLentes.get(i).getEstoqueLente());
					newlente.setGrau(arrayLentes.get(i).getGrau());
					newlente.setMaterial(arrayLentes.get(i).getMaterial());
					newlente.setTipo(arrayLentes.get(i).getTipo());
					newlente.setValorLente(arrayLentes.get(i).getValorLente());
					telaLente.getLenteTableModel(true).add_tabela(newlente);

				}
				
			} else if (telaLente.getLenteTableModel(true).getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Busca finalizada, nenhuma lente encontrada.", "Busca",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}

		return true;
	}
}