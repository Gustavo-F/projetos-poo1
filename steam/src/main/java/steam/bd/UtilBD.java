package steam.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilBD {
	private Connection conexao;

	public Connection getConexao() {
		if (conexao == null)
			abrirConexao();
		return conexao;
	}

	private void abrirConexao() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
		} catch (SQLException e) {
			System.err.println("Não consegui abrir a conexão com o banco!");
		} catch (ClassNotFoundException e2) {
			System.err.println("A biblioteca do SQLite não está funcionando corretamente!");
		}
	}

	public void fecharConexao() {
		if (conexao == null)
			return;

		try {
			if (!conexao.isClosed())
				conexao.close();
		} catch (SQLException e) {
			System.err.println("Não consegui fechar a conexão com o banco!");
		}

	}

	public void initBD() {
		try {
			conexao = getConexao();
			Statement stm = conexao.createStatement();
			stm.executeUpdate("DROP TABLE IF EXISTS Genero");
			stm.executeUpdate("CREATE TABLE Genero (Nome varchar(10) NOT NULL PRIMARY KEY);");
			stm.executeUpdate("INSERT INTO Genero VALUES ('Ação')");
			stm.executeUpdate("INSERT INTO Genero VALUES ('Estratégia')");
			stm.executeUpdate("INSERT INTO Genero VALUES ('FPS')");
			stm.close();
		} catch (SQLException e) {
			System.err.println("Não consegui criar o banco!");
		}
	}

}
