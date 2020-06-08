package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import steam.entidades.Desenvolvedora;

public class DesenvolvedoraDAO implements InterfaceDAO<Desenvolvedora> {

	public void adicionar(Desenvolvedora desenvolvedora) {
		try {
			String sql = "INSERT INTO Desenvolvedora VALUES ('" + desenvolvedora.getNome() + "','"
					+ desenvolvedora.getEmail() + "')";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível inserir a desenvolvedora no banco!");
		}
	}

	public void remover(Desenvolvedora desenvolvedora) {
		try {
			String sql = "DELETE FROM Desenvolvedora WHERE nome = '" + desenvolvedora.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível remover a desenvolvedora do banco!");
		}

	}

	public List<Desenvolvedora> todos() {
		List<Desenvolvedora> retorno = new ArrayList<Desenvolvedora>();
		try {
			String sql = "SELECT Nome, Email FROM Desenvolvedora";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				String email = resultSet.getString("Email");
				retorno.add(new Desenvolvedora(nome, email));
			}
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar todas as desenvolvedoras do banco!");
		}
		return retorno;
	}

	public Desenvolvedora get(String nome) {
		Desenvolvedora retorno = null;
		try {
			String sql = "SELECT Email FROM Desenvolvedora WHERE Nome = '" + nome + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String email = resultSet.getString("Email");
				retorno = new Desenvolvedora(nome, email);
			}
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar uma desenvolvedora do banco!");
		}
		return retorno;
	}

}
