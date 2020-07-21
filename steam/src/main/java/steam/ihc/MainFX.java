package steam.ihc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFX extends Application {

	private Stage stage;
	private Pane pane;
	private String usuarioLogado;
	private Button btnVoltar;

	public MainFX(String usuarioLogado) {
		if (usuarioLogado.isBlank())
			usuarioLogado = "Erro - Nome de usuário em branco!";
		this.usuarioLogado = usuarioLogado;
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		initComponentes();

		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Steam de " + usuarioLogado);
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.setPrefSize(640, 480);
		pane.getChildren().addAll(btnVoltar);

	}

	private void configLayout() {
		btnVoltar.setLayoutX(pane.getPrefWidth() - 105);
		btnVoltar.setLayoutY(pane.getPrefHeight() - 30);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth(100);
	}

	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new LoginFX().start(stage);
				} catch (Exception e) {
					System.err.println("Não foi possível iniciar a tela de login");
				}
			}
		};
	}

}
