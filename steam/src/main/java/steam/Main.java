package steam;

import steam.bd.GeneroDAO;
import steam.bd.UtilBD;
import steam.entidades.Genero;

public class Main {

	public static void main(String[] args) {
		UtilBD.initBD();

		GeneroDAO dao = new GeneroDAO();
		for (Genero gen : dao.todos())
			System.out.println(gen.getNome());
		System.out.println(dao.get("Estratégia").getNome());
		UtilBD.fecharConexao();
	}

}
