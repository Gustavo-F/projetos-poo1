package steam.ihc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarJogadorFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new AnchorPane();
		pane.setPrefSize(320, 240);
		
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setTitle("Registro de um novo jogador");
		stage.setResizable(false);
		stage.show();
	}

}
