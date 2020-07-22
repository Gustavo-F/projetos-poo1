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

public class CadastrarJogoFX extends Application {

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
	private Button btnCadastrar;

	public CadastrarJogoFX(String usuarioLogado) {
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
		stage.setTitle("Registro de um novo jogo");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		txtNome = new TextField();
		txtNome.setPromptText("Nome do jogo");

		txtPreco = new TextField();
		txtPreco.setPromptText("Preço do jogo");

		lblGenero = new Label("Gênero:");
		chbGenero = new ChoiceBox<>();
		chbGenero.setItems(FXCollections.observableArrayList(geraListaGeneros()));
		chbGenero.getSelectionModel().select(0);

		lblDesenvolvedora = new Label("Desenvolvedora:");
		cmbDesenvolvedora = new ComboBox<>();
		cmbDesenvolvedora.setItems(FXCollections.observableArrayList(geraListaDesenvolvedoras()));
		cmbDesenvolvedora.getSelectionModel().select(0);

		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(cadastrar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(txtNome, txtPreco, lblGenero, chbGenero, lblDesenvolvedora, cmbDesenvolvedora,
				btnCadastrar, btnVoltar);

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

		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(210);
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnCadastrar.getPrefWidth() + 20);
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

	private EventHandler<ActionEvent> cadastrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNome.getText().isBlank()) {
					AlertaFX.alerta("Nome em branco!");
					return;
				}
				if (txtPreco.getText().isBlank()) {
					AlertaFX.alerta("Preço em branco!");
					return;
				}

				Genero genero = new GeneroDAO().get(chbGenero.getSelectionModel().getSelectedItem());
				Desenvolvedora desenvolvedora = new DesenvolvedoraDAO()
						.get(cmbDesenvolvedora.getSelectionModel().getSelectedItem());

				Jogo jogo = new Jogo(txtNome.getText(), desenvolvedora, Double.valueOf(txtPreco.getText()));
				jogo.adicionaGenero(genero);
				
				new JogoDAO().adicionar(jogo);

				AlertaFX.info("Jogo cadastrado com sucesso :)");

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
