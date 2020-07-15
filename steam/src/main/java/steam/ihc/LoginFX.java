package steam.ihc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginFX extends Application {

	@Override
	public void start(Stage stage) {
		Label label = new Label("Hello IHC World!");
		label.setFont(new Font("Arial", 48));
		Scene scene = new Scene(new StackPane(label), 640, 480);
		stage.setScene(scene);
		stage.show();
	}
}