package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import steam.entidades.Genero;
import steam.entidades.Jogo;
import steam.ihc.AlertaFX;

public class JogoDAO implements InterfaceDAO<Jogo> {

	public void adicionar(Jogo jogo) {
		try {
			String sql = "INSERT INTO Jogo VALUES ('" + jogo.getNome() + "'," + jogo.getPreco() + ",'"
					+ jogo.getDesenvolvedora().getNome() + "')";
			UtilBD.alterarBD(sql);

			for (Genero genero : jogo.getGeneros()) {
				sql = "INSERT INTO GeneroJogo VALUES ('" + genero.getNome() + "','" + jogo.getNome() + "')";
				UtilBD.alterarBD(sql);
			}
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível inserir o jogo no banco!");
		}
	}
	
	public void atualizar(Jogo jogo) {
		try {
			String sql = "UPDATE Jogo SET Preco = " + jogo.getPreco() + " WHERE Nome = '"
					+ jogo.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível atualizar o jogo no banco!");
		}
	}
	
	public void remover(Jogo jogo) {
		try {
			String sql = "DELETE FROM Jogo WHERE nome = '" + jogo.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível remover o jogo do banco!");
		}
	}

	public List<Jogo> todos() {
		List<Jogo> retorno = new ArrayList<Jogo>();
		try {
			String sql = "SELECT Nome, Preco, Desenvolvedora FROM Jogo";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				Double preco = resultSet.getDouble("Preco");
				String nomeDesenvolvedora = resultSet.getString("Desenvolvedora");
				retorno.add(
						new Jogo(nome, new DesenvolvedoraDAO().get(nomeDesenvolvedora), preco, getGenerosJogo(nome)));
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível consultar todos os jogos do banco!");
		}
		return retorno;
	}

	public Jogo get(String nome) {
		Jogo retorno = null;
		try {
			String sql = "SELECT Nome, Preco, Desenvolvedora FROM Jogo WHERE nome = '" + nome + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				Double preco = resultSet.getDouble("Preco");
				String nomeDesenvolvedora = resultSet.getString("Desenvolvedora");
				retorno = new Jogo(nome, new DesenvolvedoraDAO().get(nomeDesenvolvedora), preco, getGenerosJogo(nome));
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível consultar um jogo do banco!");
		}
		return retorno;
	}

	private List<Genero> getGenerosJogo(String nome) throws SQLException {
		List<Genero> generosJogo = new ArrayList<Genero>();
		String sqlGenerosJogo = "SELECT NomeGenero FROM GeneroJogo WHERE NomeJogo = '" + nome + "'";
		ResultSet resultSetGenerosJogo = UtilBD.consultarBD(sqlGenerosJogo);
		while (resultSetGenerosJogo.next())
			generosJogo.add(new Genero(resultSetGenerosJogo.getString("NomeGenero")));
		resultSetGenerosJogo.getStatement().close();
		return generosJogo;
	}
}
