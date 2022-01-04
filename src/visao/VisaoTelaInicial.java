package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VisaoTelaInicial extends JPanel {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBarMenu = null;
	private JMenu menuCadastros = null;
	private JMenu menuPDV = null;
	private JMenuItem menuItemCadastroLente = null;
	private JMenuItem menuItemCadastroArmacao = null;
	private JMenuItem menuItemCadastroVendedor = null;
	private JMenuItem menuItemPDVTerminal = null;
	private JMenuItem menuItemResumo;
	private JSeparator separatorTitulo = null;
	private JLabel labelTelaInicial = null;
	private JLabel labelLogo = null;
	private Icon imagemSistema = null;


	public VisaoTelaInicial() {
		setLayout(null);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.menu);
		setSize(800, 600);
		add(getLabelLogo());
		add(getMenuBarMenu());
		add(getSeparatorTitulo());
		add(getLabelTelaInicial());
	}

	public JMenuBar getMenuBarMenu() {
		if (menuBarMenu == null) {
			menuBarMenu = new JMenuBar();
			menuBarMenu.setFont(new Font("Arial", Font.PLAIN, 12));
			menuBarMenu.setForeground(new Color(192, 192, 192));
			menuBarMenu.setBackground(new Color(252, 246, 229));
			menuBarMenu.setLayout(null);
			menuBarMenu.setBounds(5, 45, 780, 30);
			menuBarMenu.add(getMenuCadastros());
			menuBarMenu.add(getMenuPDV());

		}
		return menuBarMenu;
	}

	public JMenu getMenuCadastros() {
		if (menuCadastros == null) {
			menuCadastros = new JMenu(" Cadastro");
			menuCadastros.setBorder(null);
			menuCadastros.setIcon(null);
			menuCadastros.setLayout(null);
			menuCadastros.setHorizontalAlignment(SwingConstants.CENTER);
			menuCadastros.setFont(new Font("Arial", Font.PLAIN, 22));
			menuCadastros.setBackground(Color.WHITE);
			menuCadastros.setForeground(Color.BLACK);
			menuCadastros.setBounds(0, 0, 160, 30);
			menuCadastros.add(getMenuItemCadastroLente());
			menuCadastros.add(getMenuItemCadastroArmacao());
			menuCadastros.add(getMenuItemCadastroVendedor());
		}
		return menuCadastros;
	}

	public JMenu getMenuPDV() {
		if (menuPDV == null) {
			menuPDV = new JMenu(" Vendas ");
			menuPDV.setBorder(null);
			menuPDV.setIcon(null);
			menuPDV.setLayout(null);
			menuPDV.setHorizontalAlignment(SwingConstants.TRAILING);
			menuPDV.setFont(new Font("Arial", Font.PLAIN, 22));
			menuPDV.setBackground(Color.WHITE);
			menuPDV.setForeground(Color.BLACK);
			menuPDV.setBounds(300, 0, 110, 30);
			menuPDV.add(getMenuItemPDVTerminal());
			menuPDV.add(getMenuItemResumo());
		}
		return menuPDV;
	}

	public JMenuItem getMenuItemCadastroLente() {
		if (menuItemCadastroLente == null) {
			menuItemCadastroLente = new JMenuItem("Lente");
			menuItemCadastroLente.setForeground(Color.BLACK);
			menuItemCadastroLente.setFont(new Font("Arial", Font.PLAIN, 16));
			menuItemCadastroLente.setBackground(Color.WHITE);
		}
		return menuItemCadastroLente;
	}

	public JMenuItem getMenuItemCadastroArmacao() {
		if (menuItemCadastroArmacao == null) {
			menuItemCadastroArmacao = new JMenuItem("Armação");
			menuItemCadastroArmacao.setForeground(Color.BLACK);
			menuItemCadastroArmacao.setFont(new Font("Arial", Font.PLAIN, 16));
			menuItemCadastroArmacao.setBackground(Color.WHITE);
		}
		return menuItemCadastroArmacao;
	}

	public JMenuItem getMenuItemCadastroVendedor() {
		if (menuItemCadastroVendedor == null) {
			menuItemCadastroVendedor = new JMenuItem("Vendedor");
			menuItemCadastroVendedor.setForeground(Color.BLACK);
			menuItemCadastroVendedor.setFont(new Font("Arial", Font.PLAIN, 16));
			menuItemCadastroVendedor.setBackground(Color.WHITE);
		}
		return menuItemCadastroVendedor;
	}

	public JMenuItem getMenuItemPDVTerminal() {
		if (menuItemPDVTerminal == null) {
			menuItemPDVTerminal = new JMenuItem("Venda");
			menuItemPDVTerminal.setForeground(Color.BLACK);
			menuItemPDVTerminal.setFont(new Font("Arial", Font.PLAIN, 16));
			menuItemPDVTerminal.setBackground(Color.WHITE);
		}
		return menuItemPDVTerminal;
	}

	public JSeparator getSeparatorTitulo() {
		if (separatorTitulo == null) {
			separatorTitulo = new JSeparator();
			separatorTitulo.setBackground(SystemColor.menu);
			separatorTitulo.setForeground(Color.BLACK);
			separatorTitulo.setBounds(2, 40, 790, 37);
		}
		return separatorTitulo;
	}

	public JLabel getLabelTelaInicial() {
		if (labelTelaInicial == null) {
			labelTelaInicial = new JLabel("NOSSA ÓTICA");
			labelTelaInicial.setFont(new Font("Arial", Font.BOLD, 20));
			labelTelaInicial.setBounds(10, 0, 243, 43);
		}
		return labelTelaInicial;
	}
	
	public JLabel getLabelLogo() {
		if(labelLogo == null) {
			imagemSistema = new ImageIcon("otica.png");
			labelLogo = new JLabel(imagemSistema);
			labelLogo.setHorizontalAlignment(SwingConstants.LEADING);
			labelLogo.setBounds(5, 88, 780, 501);
		}
		return labelLogo;
	}
	
	public JMenuItem getMenuItemResumo() {
		if (menuItemResumo == null) {
			menuItemResumo = new JMenuItem("Resumo");
			menuItemResumo.setFont(new Font("Arial", Font.PLAIN, 16));
		}
		return menuItemResumo;
	}
}
