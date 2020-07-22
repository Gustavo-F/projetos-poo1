package steam.ihc;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import steam.bd.DesenvolvedoraDAO;
import steam.bd.GeneroDAO;
import steam.bd.JogoDAO;
import steam.entidades.Desenvolvedora;
import steam.entidades.Genero;
import steam.entidades.Jogo;

public class AlterarJogoFX extends Application {

	private String usuarioLogado;
	private Stage stage;
	private Pane pane;
	private TextField txtNome;
	private TextField txtPreco;
	private Label lblGenero;
	private ChoiceBox<String> chbGenero;
	private Label lblDesenvolvedora;
	private ComboBox<String> cmbDesenvolvedora;
	private Button btnVoltar;
	private Button btnAlterar;
	private Jogo jogo;

	public AlterarJogoFX(String usuarioLogado, Jogo jogo) {
		this.usuarioLogado = usuarioLogado;
		this.jogo = jogo;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		initComponentes();

		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Alteração de um jogo");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		txtNome = new TextField();
		txtNome.setPromptText("Nome do jogo");
		txtNome.setText(jogo.getNome());
		txtNome.setDisable(true);

		txtPreco = new TextField();
		txtPreco.setPromptText("Preço do jogo");
		txtPreco.setText(String.valueOf(jogo.getPreco()));

		lblGenero = new Label("Gênero:");
		chbGenero = new ChoiceBox<>();
		chbGenero.setItems(FXCollections.observableArrayList(geraListaGeneros()));
		chbGenero.getSelectionModel().select(jogo.getGeneros().get(0).getNome());
		chbGenero.setDisable(true);

		lblDesenvolvedora = new Label("Desenvolvedora:");
		cmbDesenvolvedora = new ComboBox<>();
		cmbDesenvolvedora.setItems(FXCollections.observableArrayList(geraListaDesenvolvedoras()));
		cmbDesenvolvedora.getSelectionModel().select(jogo.getDesenvolvedora().getNome());
		cmbDesenvolvedora.setDisable(true);

		btnAlterar = new Button("Alterar");
		btnAlterar.setOnAction(alterar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(txtNome, txtPreco, lblGenero, chbGenero, lblDesenvolvedora, cmbDesenvolvedora,
				btnAlterar, btnVoltar);

	}

	private void configLayout() {
		pane.setPrefSize(320, 245);

		txtNome.setLayoutX(10);
		txtNome.setLayoutY(10);
		txtNome.setPrefHeight(30);
		txtNome.setPrefWidth(pane.getPrefWidth() - 20);

		txtPreco.setLayoutX(10);
		txtPreco.setLayoutY(50);
		txtPreco.setPrefHeight(30);
		txtPreco.setPrefWidth(pane.getPrefWidth() - 20);

		lblGenero.setLayoutX(10);
		lblGenero.setLayoutY(90);

		chbGenero.setLayoutX(10);
		chbGenero.setLayoutY(110);
		chbGenero.setPrefHeight(30);
		chbGenero.setPrefWidth(pane.getPrefWidth() - 20);

		lblDesenvolvedora.setLayoutX(10);
		lblDesenvolvedora.setLayoutY(150);

		cmbDesenvolvedora.setLayoutX(10);
		cmbDesenvolvedora.setLayoutY(170);
		cmbDesenvolvedora.setPrefHeight(30);
		cmbDesenvolvedora.setPrefWidth(pane.getPrefWidth() - 20);

		btnAlterar.setLayoutX(10);
		btnAlterar.setLayoutY(210);
		btnAlterar.setPrefHeight(20);
		btnAlterar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnAlterar.getPrefWidth() + 20);
		btnVoltar.setLayoutY(210);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth() - 30) / 2);
	}

	private List<String> geraListaGeneros() {
		List<String> retorno = new ArrayList<String>();
		List<Genero> generos = new GeneroDAO().todos();
		for (Genero genero : generos)
			retorno.add(genero.getNome());
		return retorno;
	}

	private List<String> geraListaDesenvolvedoras() {
		List<String> retorno = new ArrayList<String>();
		List<Desenvolvedora> desenvolvedoras = new DesenvolvedoraDAO().todos();
		for (Desenvolvedora desenvolvedora : desenvolvedoras)
			retorno.add(desenvolvedora.getNome());
		return retorno;
	}

	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				abrirJanelaPrincipal();
			}
		};
	}

	private EventHandler<ActionEvent> alterar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtPreco.getText().isBlank()) {
					AlertaFX.alerta("Preço em branco!");
					return;
				}

				jogo.setPreco(Double.valueOf(txtPreco.getText()));
				new JogoDAO().atualizar(jogo);

				AlertaFX.info("Jogo atualizado com sucesso :)");

				abrirJanelaPrincipal();
			}
		};
	}

	private void abrirJanelaPrincipal() {
		try {
			new MainFX(usuarioLogado).start(stage);
		} catch (Exception e) {
			AlertaFX.erro("Não foi possível iniciar a tela principal!");
		}
	}

}
