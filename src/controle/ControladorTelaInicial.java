package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Vendedor;
import visao.VisaoCadastroArmacao;
import visao.VisaoCadastroLente;
import visao.VisaoCadastroVendedor;
import visao.VisaoPDV;
import visao.VisaoTelaInicial;

	
public class ControladorTelaInicial implements ActionListener{
		private VisaoTelaInicial telaInicial;
		private VisaoCadastroArmacao telaArmacao;
		private VisaoCadastroLente telaLente;
		private VisaoCadastroVendedor telaVendedor;
		private VisaoPDV telaVenda;
		ControladorCadastroLente ControladorCadastroLente;
		ControladorCadastroArmacao ControladorCadastroArmacao;
		ControladorCadastroVendedor ControladorCadastroVendedor;
		ControladorVenda ControladorVenda;
		int quantidadeVendida;
		float valorTotalVendido;
		String vendedor1, vendedor2, vendedor3, vendedor4;
		float salario1, salario2, salario3, salario4;
		private ArrayList<Float> valorOculos = new ArrayList<Float>();
		
		public ControladorTelaInicial(VisaoTelaInicial telaInicial) {
			this.telaInicial = telaInicial;
			
			addEventos();
		}
		
		public void addEventos() {
			telaInicial.getMenuItemCadastroLente().addActionListener(this);
			telaInicial.getMenuItemCadastroArmacao().addActionListener(this);
			telaInicial.getMenuItemCadastroVendedor().addActionListener(this);
			telaInicial.getMenuItemPDVTerminal().addActionListener(this);
			telaInicial.getMenuItemResumo().addActionListener(this);
		}


		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == telaInicial.getMenuItemCadastroLente()) {
				telaLente = new VisaoCadastroLente();
				ControladorFrame.framePrincipal.setContentPane(telaLente);
				ControladorCadastroLente = new ControladorCadastroLente(telaLente);
				ControladorFrame.framePrincipal.repaint();
				ControladorFrame.framePrincipal.validate();
			}
			
			if (e.getSource() == telaInicial.getMenuItemCadastroArmacao()) {
				telaArmacao = new VisaoCadastroArmacao();
				ControladorFrame.framePrincipal.setContentPane(telaArmacao);
				ControladorCadastroArmacao = new ControladorCadastroArmacao(telaArmacao);
				ControladorFrame.framePrincipal.repaint();
				ControladorFrame.framePrincipal.validate();
			}
			
			if (e.getSource() == telaInicial.getMenuItemCadastroVendedor()) {
				telaVendedor = new VisaoCadastroVendedor();
				ControladorFrame.framePrincipal.setContentPane(telaVendedor);
				ControladorCadastroVendedor = new ControladorCadastroVendedor(telaVendedor);
				ControladorFrame.framePrincipal.repaint();
				ControladorFrame.framePrincipal.validate();
			}
			
			if (e.getSource() == telaInicial.getMenuItemPDVTerminal()) {
				telaVenda = new VisaoPDV();
				ControladorFrame.framePrincipal.setContentPane(telaVenda);
				ControladorVenda = new ControladorVenda(telaVenda);
				ControladorFrame.framePrincipal.repaint();
				ControladorFrame.framePrincipal.validate();
			}
			
			if (e.getSource() == telaInicial.getMenuItemResumo()) {
				
				carregaArrayList(); //Carrega array dos óculos vendidos
				calculaResumo();

				JOptionPane.showMessageDialog(null, "Quantidade Vendida = " + quantidadeVendida + " óculos\n"
						+ "Valor Total Vendido = R$ " + valorTotalVendido + "\n\n" 
						+ "Vendedor   /  R$  Salário  +  Comissão\n"
						+ vendedor1 + "   / R$ " 
						+ salario1 + "    \n" 
						+ vendedor2 + "    / R$ " 
						+ salario2 + "    \n" 
						+ vendedor3 + "    / R$ "
						+ salario3 + "    \n"
						+ vendedor4 + "    / R$"
						+ salario4, "Balanço Financeiro", JOptionPane.INFORMATION_MESSAGE);

				new ControladorFrame();
			}
			
		}
		
		private void carregaArrayList() {
			// Óculos
			controle.ControladorVenda.arrayListOculos.clear();
			 
			BufferedReader br = null;
			FileReader fr = null;

			try {

				fr = new FileReader("arquivoOculos.txt");
				br = new BufferedReader(fr);
				String line = br.readLine();

				while (line != null) {
					String array[] = line.split(";");
					line = br.readLine();
					
					valorOculos.add( Float.parseFloat(array[14]) );
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
			
			controle.ControladorCadastroVendedor.arrayVendedores.clear();
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
		
		private void calculaResumo() {
			
			quantidadeVendida = valorOculos.size();
			
			for (int i = 0; i < valorOculos.size(); i++) {

					valorTotalVendido = valorOculos.get(i);

			}
			
			for (int i = 0; i < controle.ControladorCadastroVendedor.arrayVendedores.size(); i++) {
				
				if(i == 0) {
					vendedor1 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getNome();
					salario1 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getComissao() + 
							controle.ControladorCadastroVendedor.arrayVendedores.get(i).getSalario();

				}
				
				if(i == 1) {
					vendedor2 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getNome();
					salario2 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getComissao() + 
							controle.ControladorCadastroVendedor.arrayVendedores.get(i).getSalario();
				}
				
				if(i == 2) {
					vendedor3 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getNome();
					salario3 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getComissao() + 
							controle.ControladorCadastroVendedor.arrayVendedores.get(i).getSalario();
				}
				
				if(i == 3) {
					vendedor4 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getNome();
					salario4 = controle.ControladorCadastroVendedor.arrayVendedores.get(i).getComissao() + 
							controle.ControladorCadastroVendedor.arrayVendedores.get(i).getSalario();
				}
			}
			
		}
	
}