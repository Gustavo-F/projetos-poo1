package steam.ihc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFX extends Application {

	private String usuarioLogado;

	public MainFX(String usuarioLogado) {
		super();
		if (usuarioLogado.isBlank())
			usuarioLogado = "Erro - Nome de usuário em branco!";
		this.usuarioLogado = usuarioLogado;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new AnchorPane();
		pane.setPrefSize(640, 480);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Steam de " + usuarioLogado);
		stage.setResizable(false);
		stage.show();
	}

}
