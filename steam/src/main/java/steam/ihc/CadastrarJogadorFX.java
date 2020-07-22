package steam.ihc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import steam.bd.JogadorDAO;
import steam.entidades.Jogador;

public class CadastrarJogadorFX extends Application {

	private Stage stage;
	private Pane pane;
	private TextField txtUsuario;
	private TextField txtEmail;
	private PasswordField txtSenha1;
	private PasswordField txtSenha2;
	private TextField txtApelido;
	private Button btnVoltar;
	private Button btnCadastrar;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		initComponentes();

		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Registro de um novo jogador");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		txtUsuario = new TextField();
		txtUsuario.setPromptText("Digite aqui seu usuário");

		txtEmail = new TextField();
		txtEmail.setPromptText("Digite aqui seu e-mail");

		txtSenha1 = new PasswordField();
		txtSenha1.setPromptText("Digite aqui sua senha");

		txtSenha2 = new PasswordField();
		txtSenha2.setPromptText("Confirme sua senha");

		txtApelido = new TextField();
		txtApelido.setPromptText("Digite aqui seu apelido");

		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(cadastrar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(txtUsuario, txtEmail, txtSenha1, txtSenha2, txtApelido, btnCadastrar, btnVoltar);

	}

	private void configLayout() {
		pane.setPrefSize(320, 245);
		
		txtUsuario.setLayoutX(10);
		txtUsuario.setLayoutY(10);
		txtUsuario.setPrefHeight(30);
		txtUsuario.setPrefWidth(pane.getPrefWidth() - 20);

		txtEmail.setLayoutX(10);
		txtEmail.setLayoutY(50);
		txtEmail.setPrefHeight(30);
		txtEmail.setPrefWidth(pane.getPrefWidth() - 20);

		txtSenha1.setLayoutX(10);
		txtSenha1.setLayoutY(90);
		txtSenha1.setPrefHeight(30);
		txtSenha1.setPrefWidth(pane.getPrefWidth() - 20);

		txtSenha2.setLayoutX(10);
		txtSenha2.setLayoutY(130);
		txtSenha2.setPrefHeight(30);
		txtSenha2.setPrefWidth(pane.getPrefWidth() - 20);

		txtApelido.setLayoutX(10);
		txtApelido.setLayoutY(170);
		txtApelido.setPrefHeight(30);
		txtApelido.setPrefWidth(pane.getPrefWidth() - 20);

		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(210);
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnCadastrar.getPrefWidth() + 20);
		btnVoltar.setLayoutY(210);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth() - 30) / 2);
	}

	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				abrirJanelaLogin();
			}
		};
	}

	private EventHandler<ActionEvent> cadastrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtUsuario.getText().isBlank()) {
					AlertaFX.alerta("Usuário em branco!");
					return;
				}
				if (txtEmail.getText().isBlank()) {
					AlertaFX.alerta("E-mail em branco!");
					return;
				}
				if (txtSenha1.getText().isBlank()) {
					AlertaFX.alerta("Senha em branco!");
					return;
				}
				if (txtSenha2.getText().isBlank()) {
					AlertaFX.alerta("Confirmação da senha em branco!");
					return;
				}
				if (!txtSenha1.getText().contentEquals(txtSenha2.getText())) {
					AlertaFX.alerta("Confirmação da senha difere da senha!");
					return;
				}
				if (txtApelido.getText().isBlank()) {
					AlertaFX.alerta("Apelido em branco!");
					return;
				}

				new JogadorDAO().adicionar(new Jogador(txtUsuario.getText(), txtEmail.getText(), txtSenha1.getText(),
						txtApelido.getText()));

				AlertaFX.info("Usuário cadastrado com sucesso :)");

				abrirJanelaLogin();
			}
		};
	}

	private void abrirJanelaLogin() {
		try {
			new LoginFX().start(stage);
		} catch (Exception e) {
			AlertaFX.erro("Não foi possível iniciar a tela de login!");
		}
	}
}
