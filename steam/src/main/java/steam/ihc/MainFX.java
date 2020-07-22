package steam.ihc;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import steam.bd.JogoDAO;
import steam.entidades.Jogo;

public class MainFX extends Application {

	private Stage stage;
	private Pane pane;
	private String usuarioLogado;
	private Button btnSair;
	private Button btnCadastrarJogo;
	private Button btnAlterarJogo;
	private Button btnExcluirJogo;
	private ListView<String> listaJogos;

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
		btnSair.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Steam de " + usuarioLogado);
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		listaJogos = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList(geraListaJogos());
		listaJogos.setItems(items);

		btnCadastrarJogo = new Button("Cadastrar jogo");
		btnCadastrarJogo.setOnAction(abrirCadastroJogo());

		btnAlterarJogo = new Button("Alterar jogo");
		btnAlterarJogo.setOnAction(abrirAlteracaoJogo());

		btnExcluirJogo = new Button("Excluir jogo");
		btnExcluirJogo.setOnAction(excluirJogo());

		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());

		pane = new AnchorPane();
		pane.getChildren().addAll(listaJogos, btnCadastrarJogo, btnAlterarJogo, btnExcluirJogo, btnSair);

	}

	private void configLayout() {
		pane.setPrefSize(640, 480);

		listaJogos.setLayoutX(10);
		listaJogos.setLayoutY(10);
		listaJogos.setPrefHeight(pane.getPrefHeight() - 55);
		listaJogos.setPrefWidth(pane.getPrefWidth() - 20);

		btnCadastrarJogo.setLayoutX(pane.getPrefWidth() - 590);
		btnCadastrarJogo.setLayoutY(pane.getPrefHeight() - 35);
		btnCadastrarJogo.setPrefHeight(20);
		btnCadastrarJogo.setPrefWidth(150);

		btnAlterarJogo.setLayoutX(pane.getPrefWidth() - 430);
		btnAlterarJogo.setLayoutY(pane.getPrefHeight() - 35);
		btnAlterarJogo.setPrefHeight(20);
		btnAlterarJogo.setPrefWidth(150);

		btnExcluirJogo.setLayoutX(pane.getPrefWidth() - 270);
		btnExcluirJogo.setLayoutY(pane.getPrefHeight() - 35);
		btnExcluirJogo.setPrefHeight(20);
		btnExcluirJogo.setPrefWidth(150);

		btnSair.setLayoutX(pane.getPrefWidth() - 110);
		btnSair.setLayoutY(pane.getPrefHeight() - 35);
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth(100);
	}

	private List<String> geraListaJogos() {
		List<String> retorno = new ArrayList<String>();
		List<Jogo> jogos = new JogoDAO().todos();
		for (Jogo jogo : jogos)
			retorno.add(jogo.getNome());
		return retorno;
	}

	private EventHandler<ActionEvent> excluirJogo() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (listaJogos.getSelectionModel().isEmpty()) {
					AlertaFX.alerta("Selecione um jogo para ser excluído.");
					return;
				}

				JogoDAO dao = new JogoDAO();
				Jogo jogo = dao.get(listaJogos.getSelectionModel().getSelectedItem());
				dao.remover(jogo);
				atualizarLista();
			}
		};
	}

	private void atualizarLista() {
		ObservableList<String> items = FXCollections.observableArrayList(geraListaJogos());
		listaJogos.setItems(items);
	}

	private EventHandler<ActionEvent> abrirAlteracaoJogo() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (listaJogos.getSelectionModel().isEmpty()) {
					AlertaFX.alerta("Selecione um jogo para ser alterado.");
					return;
				}
				
				String nomeJogo = listaJogos.getSelectionModel().getSelectedItem();
				Jogo jogo = new JogoDAO().get(nomeJogo);
				
				try {
					new AlterarJogoFX(usuarioLogado, jogo).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de cadastro de um jogo!");
				}
			}
		};
	}

	private EventHandler<ActionEvent> abrirCadastroJogo() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarJogoFX(usuarioLogado).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de cadastro de um jogo!");
				}
			}
		};
	}

	private EventHandler<ActionEvent> sair() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new LoginFX().start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de login");
				}
			}
		};
	}

}
