package steam;

import steam.bd.UtilBD;

public class Main {

	public static void main(String[] args) {
		UtilBD utilBD = new UtilBD();
		utilBD.initBD();
		utilBD.fecharConexao();
	}

}
