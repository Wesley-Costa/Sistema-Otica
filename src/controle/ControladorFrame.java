package controle;

import visao.VisaoFramePrincipal;
import visao.VisaoTelaInicial;

public class ControladorFrame {

	static VisaoFramePrincipal framePrincipal = new VisaoFramePrincipal();
	private VisaoTelaInicial telaInicial;
	
	public ControladorFrame() {
		telaInicial = new VisaoTelaInicial();
		ControladorTelaInicial ControladorTelaInicial = new ControladorTelaInicial(telaInicial);
		framePrincipal.setContentPane(telaInicial);
		framePrincipal.repaint();
		framePrincipal.validate();
	}

	public static void main(String[] args) {
		new ControladorFrame();
	}

}
