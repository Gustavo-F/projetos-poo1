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
import steam.bd.JogadorDAO;
import steam.entidades.Jogador;

public class LoginFX extends Application {

	private Stage stage;
	private Label lblSteam;
	private TextField txtUsuario;
	private PasswordField txtSenha;
	private Button btnEntrar;
	private Button btnSair;
	private Button btnCadastrar;
	private Pane pane;

	@Override
	public void start(Stage stage) { // o palco vem por parâmetro

		// ATENÇÃO: SEMPRE IMPORTAR OS COMPONENTES DO PACOTE JAVAFX!!!
		this.stage = stage;
		initComponentes();
		configLayout();

		Scene scene = new Scene(pane);
		btnEntrar.requestFocus(); // precisa ser depois de inicializar a cena

		stage.setScene(scene);
		stage.setTitle("Steam login");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		lblSteam = new Label("Bem-vindo à Steam");

		txtUsuario = new TextField();
		txtUsuario.setPromptText("Digite aqui seu usuário");

		txtSenha = new PasswordField();
		txtSenha.setPromptText("Digite aqui sua senha");

		btnEntrar = new Button("Entrar");
		btnEntrar.setOnAction(entrar());

		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());

		btnCadastrar = new Button("Registrar nova conta");
		btnCadastrar.setOnAction(abrirJanelaCadastro());

		pane = new AnchorPane();

		pane.getChildren().add(lblSteam);
		pane.getChildren().addAll(txtUsuario, txtSenha, btnEntrar, btnSair, btnCadastrar);
	}

	private void configLayout() {
		pane.setPrefSize(320, 180);
		
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
	}

	private EventHandler<ActionEvent> entrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (txtUsuario.getText().isBlank()) {
						AlertaFX.alerta("Usuário em branco!");
						return;
					}
					if (txtSenha.getText().isBlank()) {
						AlertaFX.alerta("Senha em branco!");
						return;
					}

					Jogador usuarioBD = new JogadorDAO().get(txtUsuario.getText());

					if (usuarioBD == null) {
						AlertaFX.alerta("Usuário ou senha inválidos!");
						return;
					}

					if (!usuarioBD.getSenha().contentEquals(txtSenha.getText())) {
						AlertaFX.alerta("Usuário ou senha inválidos!");
						return;
					}

					new MainFX(txtUsuario.getText()).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela principal!");
				}
			}
		};
	}

	private EventHandler<ActionEvent> sair() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		};
	}

	private EventHandler<ActionEvent> abrirJanelaCadastro() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarJogadorFX().start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de cadastro de jogador!");
				}
			}
		};
	}
}