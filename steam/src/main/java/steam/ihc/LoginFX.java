package steam.ihc;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFX extends Application {

	@Override
	public void start(Stage stage) { // o palco vem por parâmetro

		// ATENÇÃO: SEMPRE IMPORTAR OS COMPONENTES DO PACOTE JAVAFX!!!

		// rótulos
		Label lblSteam = new Label("Bem-vindo à Steam");

		// entrada de textos
		TextField txtUsuario = new TextField();
		txtUsuario.setPromptText("Digite aqui seu usuário");

		// entrada de senhas
		PasswordField txtSenha = new PasswordField();
		txtSenha.setPromptText("Digite aqui sua senha");

		// botões
		Button btnEntrar = new Button("Entrar");
		Button btnSair = new Button("Sair");
		Button btnCadastrar = new Button("Registrar nova conta");

		// painel
		Pane pane = new AnchorPane();
		pane.setPrefSize(320, 180);

		// adicionando os componentes no painel
		pane.getChildren().add(lblSteam); // um a um
		pane.getChildren().addAll(txtUsuario, txtSenha, btnEntrar, btnSair, btnCadastrar);

		// configurando os componentes no painel
		lblSteam.setLayoutX(10);
		lblSteam.setLayoutY(10);

		txtUsuario.setLayoutX(10);
		txtUsuario.setLayoutY(35);
		txtUsuario.setPrefHeight(30);
		txtUsuario.setPrefWidth(pane.getPrefWidth() - 20);

		txtSenha.setLayoutX(10);
		txtSenha.setLayoutY(75);
		txtSenha.setPrefHeight(30);
		txtSenha.setPrefWidth(pane.getPrefWidth() - 20);

		btnEntrar.setLayoutX(10);
		btnEntrar.setLayoutY(115);
		btnEntrar.setPrefHeight(20);
		btnEntrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnSair.setLayoutX(btnEntrar.getPrefWidth() + 20);
		btnSair.setLayoutY(115);
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(145);
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth(pane.getPrefWidth() - 20);

		// comportamento
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarJogadorFX().start(new Stage());
				} catch (Exception e) {
					System.err.println("Não foi possível iniciar a tela de cadastro de jogador!");
				}
			}
		});

		btnSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});

		btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new MainFX(txtUsuario.getText()).start(new Stage());
				} catch (Exception e) {
					System.err.println("Não foi possível iniciar a tela principal!");
				}
			}
		});

		// cena
		Scene scene = new Scene(pane);
		btnEntrar.requestFocus(); // precisa ser depois de inicializar a cena

		// palco
		stage.setScene(scene);
		stage.setTitle("Steam login");
		stage.setResizable(false);
		stage.show();
	}
}