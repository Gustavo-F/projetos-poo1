package steam.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilBD {
	private static Connection conexao;

	public static Connection getConexao() {
		try {

			if (conexao == null)
				abrirConexao();

			if (conexao.isClosed())
				abrirConexao();

		} catch (SQLException e) {
			System.err.println("Não consegui abrir a conexão com o banco!");
		}

		return conexao;
	}

	private static void abrirConexao() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
		} catch (SQLException e) {
			System.err.println("Não consegui abrir a conexão com o banco!");
		} catch (ClassNotFoundException e2) {
			System.err.println("A biblioteca do SQLite não está funcionando corretamente!");
		}
	}

	public static void fecharConexao() {
		if (conexao == null)
			return;

		try {
			if (!conexao.isClosed())
				conexao.close();
		} catch (SQLException e) {
			System.err.println("Não consegui fechar a conexão com o banco!");
		}
	}

	public static void initBD() {
		try {
			conexao = getConexao();
			Statement stm = conexao.createStatement();
			criarGenero(stm);
			criarDesenvolvedora(stm);
			criarJogo(stm);
			criarGeneroJogo(stm);
			criarJogador(stm);
			stm.close();
		} catch (SQLException e) {
			System.err.println("Não consegui criar o banco!");
		}
	}

	private static void criarJogador(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Jogador");
		stm.executeUpdate(
				"CREATE TABLE Jogador (Nome varchar(10) NOT NULL PRIMARY KEY, Email varchar(50) NOT NULL, Senha varchar(50) NOT NULL, Apelido varchar(30) NOT NULL);");
		stm.executeUpdate("INSERT INTO Jogador VALUES ('admin','admin@gmail.com','admin','admin')");
	}

	private static void criarGeneroJogo(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS GeneroJogo");
		stm.executeUpdate("CREATE TABLE GeneroJogo (NomeGenero varchar(10) NOT NULL," + "NomeJogo varchar(10) NOT NULL,"
				+ "FOREIGN KEY (NomeGenero) REFERENCES Genero(Nome) ON DELETE CASCADE,"
				+ "FOREIGN KEY (NomeJogo) REFERENCES Jogo(Nome) ON DELETE CASCADE,"
				+ "CONSTRAINT PK_GENERO_JOGO PRIMARY KEY (NomeGenero,NomeJogo));");
		stm.executeUpdate("INSERT INTO GeneroJogo VALUES ('FPS','Counter Strike')");
		stm.executeUpdate("INSERT INTO GeneroJogo VALUES ('Ação','Counter Strike')");
		stm.executeUpdate("INSERT INTO GeneroJogo VALUES ('Ação','GTA')");
		stm.executeUpdate("INSERT INTO GeneroJogo VALUES ('Estratégia','Age of Empires')");
		stm.executeUpdate("INSERT INTO GeneroJogo VALUES ('Ação','Age of Empires')");
	}

	private static void criarJogo(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Jogo");
		stm.executeUpdate("CREATE TABLE Jogo (Nome varchar(10) NOT NULL PRIMARY KEY,"
				+ "Preco double NOT NULL, Desenvolvedora varchar(10) NOT NULL,"
				+ "FOREIGN KEY (Desenvolvedora) REFERENCES Desenvolvedora(Nome) ON DELETE CASCADE);");
		stm.executeUpdate("INSERT INTO Jogo VALUES ('Counter Strike', 10, 'Valve')");
		stm.executeUpdate("INSERT INTO Jogo VALUES ('GTA', 15, 'Rockstar')");
		stm.executeUpdate("INSERT INTO Jogo VALUES ('Age of Empires', 20, 'Microsoft')");
	}

	private static void criarDesenvolvedora(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Desenvolvedora");
		stm.executeUpdate("CREATE TABLE Desenvolvedora (Nome varchar(10) NOT NULL PRIMARY KEY,"
				+ "Email varchar(10) NOT NULL, Senha varchar(50) NOT NULL);");
		stm.executeUpdate("INSERT INTO Desenvolvedora VALUES ('Valve','contato@valve.com', 'valve')");
		stm.executeUpdate("INSERT INTO Desenvolvedora VALUES ('Rockstar','contato@rockstar.com', 'rockstar')");
		stm.executeUpdate("INSERT INTO Desenvolvedora VALUES ('Microsoft','contato@microsoft.com', 'microsoft')");
	}

	private static void criarGenero(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Genero");
		stm.executeUpdate("CREATE TABLE Genero (Nome varchar(10) NOT NULL PRIMARY KEY);");
		stm.executeUpdate("INSERT INTO Genero VALUES ('Ação')");
		stm.executeUpdate("INSERT INTO Genero VALUES ('Estratégia')");
		stm.executeUpdate("INSERT INTO Genero VALUES ('FPS')");
	}

	public static void alterarBD(String sql) throws SQLException {
		Connection bd = UtilBD.getConexao();
		Statement stm = bd.createStatement();
		stm.executeUpdate(sql);
		System.out.println("Executei: " + sql);
		stm.close();
	}

	public static ResultSet consultarBD(String sql) throws SQLException {
		Connection bd = UtilBD.getConexao();
		Statement stm = bd.createStatement();
		ResultSet retorno = stm.executeQuery(sql);
		System.out.println("Executei: " + sql);
//		stm.close();
		return retorno;
	}
}
