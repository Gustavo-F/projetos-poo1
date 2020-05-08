package steam;

import steam.bd.DesenvolvedoraDAO;
import steam.bd.InterfaceDAO;
import steam.bd.JogadorDAO;
import steam.bd.JogoDAO;

public class Main {

	public static void main(String[] args) {
		InterfaceDAO bd = new DesenvolvedoraDAO();
		bd.adicionar(null);

		bd = new JogadorDAO();
		bd.adicionar(null);

		bd = new JogoDAO();
		bd.adicionar(null);
	}

}
