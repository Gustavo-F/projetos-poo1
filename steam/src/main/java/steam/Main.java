package steam;

import steam.bd.GeneroDAO;
import steam.bd.UtilBD;
import steam.entidades.Genero;

public class Main {

	public static void main(String[] args) {
		UtilBD.initBD();

		GeneroDAO dao = new GeneroDAO();
		Genero teste = new Genero("Teste");
		dao.adicionar(teste);
		dao.remover(teste);
		
		UtilBD.fecharConexao();
	}

}
