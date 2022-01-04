package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.Armacao;
import modelo.Lente;
import modelo.Oculos;
import modelo.Vendedor;
import visao.VisaoPDV;
import java.util.ArrayList;


public class ControladorVenda implements ActionListener {

	private VisaoPDV telaVenda;
	private Oculos oculosAtual;
	private Lente lente;
	private Armacao armacao;
	private Vendedor vendedor = new Vendedor();
	static ArrayList<Oculos> arrayListOculos = new ArrayList<Oculos>();

	public ControladorVenda(VisaoPDV telaVenda) {
		this.telaVenda = telaVenda;
		carregaArrayList();
		campos(true);
		iniciaVenda();
	}

	public void iniciaVenda() {

		oculosAtual = new Oculos();
		addEventos();
	}

	public void campos(boolean b) {
		telaVenda.getTextFieldValorLente().setEnabled(false);
		telaVenda.getTextFieldValorArmacao().setEnabled(false);
		telaVenda.getTextFieldValorTotal().setEnabled(false);
		telaVenda.getTextFieldCodigoLente().setEnabled(true);
		telaVenda.getTextFieldDescricaoLente().setEnabled(false);
		telaVenda.getTextFieldCodigoVendedor().setEnabled(true);
		telaVenda.getTextFieldDescricaoVendedor().setEnabled(false);
		telaVenda.getTextFieldCodigoArmacao().setEnabled(true);
		telaVenda.getTextFieldDescricaoArmacao().setEnabled(false);
		telaVenda.getComboBoxMontagem().setEnabled(true);
	}

	public void addEventos() {

		telaVenda.getBtnSair().addActionListener(this);
		telaVenda.getBtnConfirmar().addActionListener(this);
		getSelecionaLenteAction();
		getSelecionaArmacaoAction();
		getSelecionaVendedorAction();
		getDefineMontagemAction();

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == telaVenda.getBtnSair()) {

			Object[] options = { "NÃO", "SIM" };
			int opcao = JOptionPane.showOptionDialog(null, "Deseja retornar a tela inicial?", "SAIR",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if (opcao == 1) {
				new ControladorFrame();
			}
		}

		if (e.getSource() == telaVenda.getBtnConfirmar()) {

			Object[] options = { "NÃO", "SIM" };
			int opcao = JOptionPane.showOptionDialog(null, "Deseja confirmar a operação?", "CONFIRMAR",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if (opcao == 1) {
				
				if(validacao()==true) {

					excluiRegistro();
	
					float comissao = vendedor.getComissao() + 10;
					vendedor.setComissao(comissao);
	
					int estoqueA = armacao.getEstoqueArm() - 1;
					armacao.setEstoqueArm(estoqueA);
	
					int estoqueL = lente.getEstoqueLente() - 1;
					lente.setEstoqueLente(estoqueL);
	
					arrayListOculos.add(oculosAtual);
	
					atualizaArquivo();
	
					JOptionPane.showMessageDialog(null, "Venda Efetuada!");
	
					reinicializaTela();
				}

			}

		}
	}
	
	private boolean validacao() {
		
		if(telaVenda.getTextFieldDescricaoVendedor().getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Insira o vendedor");
			return false;
		}
		
		if(telaVenda.getTextFieldDescricaoLente().getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Insira Lente");
			return false;
		}
		
		if(telaVenda.getTextFieldDescricaoArmacao().getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Insira a Armação");
			return false;
		}
		
		if(telaVenda.getTextFieldValorTotal().getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Selecione a Montagem");
			return false;
		}
		else {
			return true;
		}
	}

	private void getSelecionaLenteAction() {

		telaVenda.getTextFieldCodigoLente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				lente = new Lente();

				if (buscaLente() == true) {

					if (lente.getEstoqueLente() != 0) {
						oculosAtual.setLente(lente);
						telaVenda.getTextFieldDescricaoLente().setText("Material: "
								+ oculosAtual.getLente().getMaterial() + ", Tipo: " + oculosAtual.getLente().getTipo());

						JOptionPane.showMessageDialog(null,
								"Você escolheu:\nMaterial: " + oculosAtual.getLente().getMaterial() + "\nTipo: "
										+ oculosAtual.getLente().getTipo() + "\nGrau: "
										+ oculosAtual.getLente().getGrau() + "\nQuantidade no Estoque: "
										+ oculosAtual.getLente().getEstoqueLente());

						telaVenda.getTextFieldValorLente()
								.setText(String.valueOf(oculosAtual.getLente().getValorLente()));
					}
					else {
						JOptionPane.showMessageDialog(null, "Estoque esgotado! \nEscolha outro modelo!");
					}
				}
			}
		});

	}

	private void getSelecionaArmacaoAction() {

		telaVenda.getTextFieldCodigoArmacao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				armacao = new Armacao();

				if (buscaArmacao() == true) {
					if (armacao.getEstoqueArm() != 0) {

						oculosAtual.setArmacao(armacao);
						telaVenda.getTextFieldDescricaoArmacao()
								.setText("Material: " + oculosAtual.getArmacao().getMaterial() + ", Marca: "
										+ oculosAtual.getArmacao().getMarca());

						JOptionPane.showMessageDialog(null,
								"Você escolheu:\nMaterial: " + oculosAtual.getArmacao().getMaterial() + "\nCor: "
										+ oculosAtual.getArmacao().getCor() + "\nFormato: "
										+ oculosAtual.getArmacao().getFormato() + "\nTamanho: "
										+ oculosAtual.getArmacao().getTamanho() + "\nMarca "
										+ oculosAtual.getArmacao().getMarca() + "\nQuantidade no Estoque: "
										+ oculosAtual.getArmacao().getEstoqueArm());

						telaVenda.getTextFieldValorArmacao()
								.setText(String.valueOf(oculosAtual.getArmacao().getValor()));
					}
					else {
						JOptionPane.showMessageDialog(null, "Estoque esgotado! \nEscolha outro modelo!");
					}
				}
			}
		});

	}

	private void getSelecionaVendedorAction() {

		telaVenda.getTextFieldCodigoVendedor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (buscaVendedor() == true) {

					telaVenda.getTextFieldDescricaoVendedor().setText("Nome: " + vendedor.getNome());
				}

			}
		});

	}

	private void getDefineMontagemAction() {
		telaVenda.getComboBoxMontagem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcao = telaVenda.getComboBoxMontagem().getSelectedItem().toString();
				if (opcao.equals("Sim")) {

					float parcialL = (oculosAtual.getLente().getValorLente());
					float parcialA = (oculosAtual.getArmacao().getValor());
					float total = parcialA + parcialL + 10;
					telaVenda.getTextFieldValorTotal().setText(String.valueOf(total));
					oculosAtual.setValorTotal(total);

				} else if (opcao.equals("Não")) {

					float parcialL = (oculosAtual.getLente().getValorLente());
					float parcialA = (oculosAtual.getArmacao().getValor());
					float total = parcialA + parcialL;
					telaVenda.getTextFieldValorTotal().setText(String.valueOf(total));
					oculosAtual.setValorTotal(total);
				}
			}
		});
	}

	private boolean buscaLente() {

		String valorBusca;
		int existe = 0;
		valorBusca = telaVenda.getTextFieldCodigoLente().getText();

		if (!valorBusca.trim().equals("")) {

			for (int i = 0; i < ControladorCadastroLente.arrayLentes.size(); i++) {

				if (String.valueOf(ControladorCadastroLente.arrayLentes.get(i).getCodigo()).contains(valorBusca)) {

					lente.setCodigo(ControladorCadastroLente.arrayLentes.get(i).getCodigo());
					lente.setEstoqueLente(ControladorCadastroLente.arrayLentes.get(i).getEstoqueLente());
					lente.setGrau(ControladorCadastroLente.arrayLentes.get(i).getGrau());
					lente.setMaterial(ControladorCadastroLente.arrayLentes.get(i).getMaterial());
					lente.setTipo(ControladorCadastroLente.arrayLentes.get(i).getTipo());
					lente.setValorLente(ControladorCadastroLente.arrayLentes.get(i).getValorLente());
					existe += 1;
				}
			}

		}
		if (existe != 0) {

			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Lente não encontrada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	private boolean buscaArmacao() {

		String valorBusca;
		int existe = 0;
		valorBusca = telaVenda.getTextFieldCodigoArmacao().getText();

		if (!valorBusca.trim().equals("")) {

			for (int i = 0; i < ControladorCadastroArmacao.arrayArmacoes.size(); i++) {

				if (String.valueOf(ControladorCadastroArmacao.arrayArmacoes.get(i).getCodigo()).contains(valorBusca)) {

					armacao.setCodigo(ControladorCadastroArmacao.arrayArmacoes.get(i).getCodigo());
					armacao.setCor(ControladorCadastroArmacao.arrayArmacoes.get(i).getCor());
					armacao.setEstoqueArm(ControladorCadastroArmacao.arrayArmacoes.get(i).getEstoqueArm());
					armacao.setFormato(ControladorCadastroArmacao.arrayArmacoes.get(i).getFormato());
					armacao.setMarca(ControladorCadastroArmacao.arrayArmacoes.get(i).getMarca());
					armacao.setMaterial(ControladorCadastroArmacao.arrayArmacoes.get(i).getMaterial());
					armacao.setTamanho(ControladorCadastroArmacao.arrayArmacoes.get(i).getTamanho());
					armacao.setValor(ControladorCadastroArmacao.arrayArmacoes.get(i).getValor());
					existe += 1;
				}
			}

		}
		if (existe != 0) {

			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Armação não encontrada!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	private boolean buscaVendedor() {

		String valorBusca;
		int existe = 0;
		valorBusca = telaVenda.getTextFieldCodigoVendedor().getText();

		if (!valorBusca.trim().equals("")) {

			for (int i = 0; i < ControladorCadastroVendedor.arrayVendedores.size(); i++) {

				if (String.valueOf(ControladorCadastroVendedor.arrayVendedores.get(i).getCodigo())
						.contains(valorBusca)) {

					vendedor.setCodigo(ControladorCadastroVendedor.arrayVendedores.get(i).getCodigo());
					vendedor.setComissao(ControladorCadastroVendedor.arrayVendedores.get(i).getComissao());
					vendedor.setSalario(ControladorCadastroVendedor.arrayVendedores.get(i).getSalario());
					vendedor.setCpf(ControladorCadastroVendedor.arrayVendedores.get(i).getCpf());
					vendedor.setDataNasc(ControladorCadastroVendedor.arrayVendedores.get(i).getDataNasc());
					vendedor.setNome(ControladorCadastroVendedor.arrayVendedores.get(i).getNome());
					vendedor.setRg(ControladorCadastroVendedor.arrayVendedores.get(i).getRg());
					vendedor.setSexo(ControladorCadastroVendedor.arrayVendedores.get(i).getSexo());
					vendedor.setBairro(ControladorCadastroVendedor.arrayVendedores.get(i).getBairro());
					vendedor.setCep(ControladorCadastroVendedor.arrayVendedores.get(i).getCep());
					vendedor.setCidade(ControladorCadastroVendedor.arrayVendedores.get(i).getCidade());
					vendedor.setComplemento(ControladorCadastroVendedor.arrayVendedores.get(i).getComplemento());
					vendedor.setLogradouro(ControladorCadastroVendedor.arrayVendedores.get(i).getLogradouro());
					vendedor.setNumero(ControladorCadastroVendedor.arrayVendedores.get(i).getNumero());
					vendedor.setEstado(ControladorCadastroVendedor.arrayVendedores.get(i).getEstado());
					existe += 1;
				}
			}
		}
		if (existe != 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Vendedor não encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	private void reinicializaTela() {

		telaVenda = new VisaoPDV();
		ControladorFrame.framePrincipal.setContentPane(telaVenda);
		new ControladorVenda(telaVenda);
		ControladorFrame.framePrincipal.repaint();
		ControladorFrame.framePrincipal.validate();

	}

	private void excluiRegistro() {
		// Lente
		String linhaL = new String (String.valueOf(lente.getCodigo()) + ";"
				+ String.valueOf(lente.getEstoqueLente()) + ";"
				+ String.valueOf(lente.getGrau()) + ";" 
				+ lente.getMaterial() + ";"
				+ lente.getTipo() + ";"
				+ String.valueOf(lente.getValorLente()));

		try {

			BufferedReader sL = new BufferedReader(new FileReader("arquivoLente.txt"));
			String linhaReadL;

			while ((linhaReadL = sL.readLine()) != null) {

				try (BufferedWriter bwL = new BufferedWriter(new FileWriter("temporarioL.txt", true))) {
					if (!linhaReadL.equals(linhaL)) {
						bwL.write(linhaReadL);
						bwL.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			sL.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		new File("arquivoLente.txt").delete();
		new File("temporarioL.txt").renameTo(new File("arquivoLente.txt"));

		// Armação
		String linhaA = new String (String.valueOf(armacao.getCodigo()) + ";" 
				+ armacao.getCor() + ";"
				+ String.valueOf(armacao.getEstoqueArm()) + ";" + armacao.getFormato()
				+ ";" + armacao.getMarca() + ";" + armacao.getMaterial() + ";"
				+ armacao.getTamanho() + ";" + String.valueOf(armacao.getValor()) );

		try {

			BufferedReader sA = new BufferedReader(new FileReader("arquivoArmacao.txt"));
			String linhaReadA;

			while ((linhaReadA = sA.readLine()) != null) {

				try (BufferedWriter bwA = new BufferedWriter(new FileWriter("temporarioA.txt", true))) {
					if (!linhaReadA.contains(linhaA)) {
						bwA.write(linhaReadA);
						bwA.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			sA.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		new File("arquivoArmacao.txt").delete();
		new File("temporarioA.txt").renameTo(new File("arquivoArmacao.txt"));

		// Vendedor
		String linhaV = new String ( String.valueOf(vendedor.getCodigo()) + ";"
				+ String.valueOf(vendedor.getNome()) + ";"
				+ String.valueOf(vendedor.getSexo()) + ";"
				+ String.valueOf(vendedor.getCpf()) + ";"
				+ String.valueOf(vendedor.getDataNasc()) + ";"
				+ String.valueOf(vendedor.getRg()) + ";" 
				+ String.valueOf(vendedor.getCep()) + ";" 
				+ String.valueOf(vendedor.getLogradouro()) + ";"
				+ String.valueOf(vendedor.getNumero()) + ";"
				+ String.valueOf(vendedor.getBairro()) + ";"
				+ String.valueOf(vendedor.getComplemento()) + ";"
				+ String.valueOf(vendedor.getCidade()) + ";"
				+ String.valueOf(vendedor.getEstado()) + ";"
				+ String.valueOf(vendedor.getComissao()) + ";"
				+ String.valueOf(vendedor.getSalario()) );

		try {

			BufferedReader sV = new BufferedReader(new FileReader("arquivoVendedor.txt"));
			String linhaReadV;

			while ((linhaReadV = sV.readLine()) != null) {

				try (BufferedWriter bwV = new BufferedWriter(new FileWriter("temporarioV.txt", true))) {
					if (!linhaReadV.equals(linhaV)) {
						bwV.write(linhaReadV);
						bwV.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			sV.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		new File("arquivoVendedor.txt").delete();
		new File("temporarioV.txt").renameTo(new File("arquivoVendedor.txt"));

	}

	private void atualizaArquivo() {

		// grava o novo registro de óculos no fim do arquivo txt
		int i = arrayListOculos.size() - 1;
		String[] linhaO = new String[] { arrayListOculos.get(i).getArmacao().getCodigo() + ";"
				+ arrayListOculos.get(i).getArmacao().getCor() + ";"
				+ arrayListOculos.get(i).getArmacao().getEstoqueArm() + ";"
				+ arrayListOculos.get(i).getArmacao().getFormato() + ";"
				+ arrayListOculos.get(i).getArmacao().getMarca() + ";"
				+ arrayListOculos.get(i).getArmacao().getMaterial() + ";"
				+ arrayListOculos.get(i).getArmacao().getTamanho() + ";"
				+ arrayListOculos.get(i).getArmacao().getValor() + ";" + arrayListOculos.get(i).getLente().getCodigo()
				+ ";" + arrayListOculos.get(i).getLente().getEstoqueLente() + ";"
				+ arrayListOculos.get(i).getLente().getGrau() + ";" + arrayListOculos.get(i).getLente().getMaterial()
				+ ";" + arrayListOculos.get(i).getLente().getTipo() + ";"
				+ arrayListOculos.get(i).getLente().getValorLente() + ";"
				+ String.valueOf(arrayListOculos.get(i).getValorTotal()) };

		try (BufferedWriter bwO = new BufferedWriter(new FileWriter("arquivoOculos.txt", true))) {

			for (String line : linhaO) {
				bwO.write(line);
				bwO.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		String[] linhaA = new String[] { String.valueOf(armacao.getCodigo()) + ";" + armacao.getCor() + ";"
				+ String.valueOf(armacao.getEstoqueArm()) + ";" + armacao.getFormato() + ";" + armacao.getMarca() + ";"
				+ armacao.getMaterial() + ";" + armacao.getTamanho() + ";" + String.valueOf(armacao.getValor()) };

		try (BufferedWriter bwA = new BufferedWriter(new FileWriter("arquivoArmacao.txt", true))) {

			for (String line : linhaA) {
				bwA.write(line);
				bwA.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		// grava o novo registro no fim do arquivo txt

		String[] linhaL = new String[] { String.valueOf(lente.getCodigo()) + ";"
				+ String.valueOf(lente.getEstoqueLente()) + ";" + String.valueOf(lente.getGrau()) + ";"
				+ lente.getMaterial() + ";" + lente.getTipo() + ";" + String.valueOf(lente.getValorLente()) };

		try (BufferedWriter bwL = new BufferedWriter(new FileWriter("arquivoLente.txt", true))) {

			for (String line : linhaL) {
				bwL.write(line);
				bwL.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		// Vendedor

		String[] linhaV = new String[] { String.valueOf(vendedor.getCodigo()) + ";" + String.valueOf(vendedor.getNome())
				+ ";" + String.valueOf(vendedor.getSexo()) + ";" + String.valueOf(vendedor.getCpf()) + ";"
				+ String.valueOf(vendedor.getDataNasc()) + ";" + String.valueOf(vendedor.getRg()) + ";"
				+ String.valueOf(vendedor.getCep()) + ";" + String.valueOf(vendedor.getLogradouro()) + ";"
				+ String.valueOf(vendedor.getNumero()) + ";" + String.valueOf(vendedor.getBairro()) + ";"
				+ String.valueOf(vendedor.getComplemento()) + ";" + String.valueOf(vendedor.getCidade()) + ";"
				+ String.valueOf(vendedor.getEstado()) + ";" + String.valueOf(vendedor.getComissao()) + ";"
				+ String.valueOf(vendedor.getSalario()) };

		try (BufferedWriter bwV = new BufferedWriter(new FileWriter("arquivoVendedor.txt", true))) {

			for (String line : linhaV) {
				bwV.write(line);
				bwV.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void carregaArrayList() {

		controle.ControladorCadastroLente.arrayLentes.clear();
		controle.ControladorCadastroArmacao.arrayArmacoes.clear();
		controle.ControladorCadastroVendedor.arrayVendedores.clear();

		// Carrega Array de Lentes
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader("arquivoLente.txt");
			br = new BufferedReader(fr);
			String line = br.readLine();

			while (line != null) {
				String array[] = line.split(";");
				line = br.readLine();
				Lente lente = new Lente();
				lente.setCodigo(Integer.parseInt(array[0]));
				lente.setEstoqueLente(Integer.parseInt(array[1]));
				lente.setGrau(array[2]);
				lente.setMaterial(array[3]);
				lente.setTipo(array[4]);
				lente.setValorLente(Float.parseFloat(array[5]));
				controle.ControladorCadastroLente.arrayLentes.add(lente);
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

		// Carrega Array de Armações
		BufferedReader brA = null;
		FileReader frA = null;

		try {

			frA = new FileReader("arquivoArmacao.txt");
			brA = new BufferedReader(frA);
			String line = brA.readLine();

			while (line != null) {
				String array[] = line.split(";");
				line = brA.readLine();
				Armacao armacao = new Armacao();
				armacao.setCodigo(Integer.parseInt(array[0]));
				armacao.setCor(array[1]);
				armacao.setEstoqueArm(Integer.parseInt(array[2]));
				armacao.setFormato(array[3]);
				armacao.setMarca(array[4]);
				armacao.setMaterial(array[5]);
				armacao.setTamanho(array[6]);
				armacao.setValor(Float.parseFloat(array[7]));
				controle.ControladorCadastroArmacao.arrayArmacoes.add(armacao);
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

		// Carrega Array de Vendedores
		BufferedReader brV = null;
		FileReader frV = null;

		try {

			frV = new FileReader("arquivoVendedor.txt");
			brV = new BufferedReader(frV);
			String line = brV.readLine();

			while (line != null) {

				String array[] = line.split(";");
				line = brV.readLine();

				Vendedor vendedor = new Vendedor();

				vendedor.setCodigo(Integer.parseInt(array[0]));
				vendedor.setComissao(Float.parseFloat(array[13]));
				vendedor.setSalario(Float.parseFloat(array[14]));
				vendedor.setCpf(array[3]);
				vendedor.setDataNasc(array[4]);
				vendedor.setNome(array[1]);
				vendedor.setRg(array[5]);
				vendedor.setSexo(array[2]);
				vendedor.setBairro(array[9]);
				vendedor.setCep(array[6]);
				vendedor.setCidade(array[11]);
				vendedor.setComplemento(array[10]);
				vendedor.setLogradouro(array[7]);
				vendedor.setNumero(array[8]);
				vendedor.setEstado(array[12]);

				controle.ControladorCadastroVendedor.arrayVendedores.add(vendedor);
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

}