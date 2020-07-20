package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import steam.entidades.Jogador;

public class JogadorDAO implements InterfaceDAO<Jogador> {

	public void adicionar(Jogador referencia) {
		try {
			String sql = "INSERT INTO Jogador VALUES ('" + referencia.getNome() + "','" + referencia.getEmail() + "','"
					+ referencia.getSenha() + "','" + referencia.getApelido() + "')";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível inserir o jogador no banco!");
		}
	}

	public void remover(Jogador referencia) {
		try {
			String sql = "DELETE FROM Jogador WHERE nome = '" + referencia.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível remover o jogador do banco!");
		}
	}

	public List<Jogador> todos() {
		List<Jogador> retorno = new ArrayList<Jogador>();
		try {
			String sql = "SELECT Nome, Email, Senha, Apelido FROM Jogador";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				String email = resultSet.getString("Email");
				String senha = resultSet.getString("Senha");
				String apelido = resultSet.getString("Apelido");
				retorno.add(new Jogador(nome, email, senha, apelido));
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar todos os jogadores do banco!");
		}
		return retorno;
	}

	public Jogador get(String nome) {
		Jogador retorno = null;
		try {
			String sql = "SELECT Email, Senha, Apelido FROM Jogador WHERE Nome = '" + nome + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String email = resultSet.getString("Email");
				String senha = resultSet.getString("Senha");
				String apelido = resultSet.getString("Apelido");
				retorno = new Jogador(nome, email, senha, apelido);
			}
			// PRECISO FECHAR O STATEMENT SÓ DEPOIS!
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar um jogador do banco!");
		}
		return retorno;
	}

}
