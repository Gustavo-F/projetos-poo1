package steam;

import javafx.application.Application;
import steam.bd.UtilBD;
import steam.ihc.LoginFX;

public class Main {

	public static void main(String[] args) {
		UtilBD.initBD();

		Application.launch(LoginFX.class);
		
		UtilBD.fecharConexao();
	}

}
