package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import steam.entidades.Genero;
import steam.ihc.AlertaFX;

public class GeneroDAO implements InterfaceDAO<Genero> {

	public void adicionar(Genero genero) {
		try {
			String sql = "INSERT INTO Genero VALUES ('" + genero.getNome() + "')";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível inserir o genero no banco!");
		}
	}

	public void remover(Genero genero) {
		try {
			String sql = "DELETE FROM Genero WHERE nome = '" + genero.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível remover o genero do banco!");
		}
	}

	public List<Genero> todos() {
		List<Genero> retorno = new ArrayList<Genero>();
		try {
			String sql = "SELECT Nome FROM Genero";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				retorno.add(new Genero(nome));
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível consultar todos os generos do banco!");
		}
		return retorno;
	}

	public Genero get(String nome) {
		Genero retorno = null;
		try {
			String sql = "SELECT Nome FROM Genero WHERE Nome = '" + nome + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				retorno = new Genero(nome);
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível consultar um genero do banco!");
		}
		return retorno;
	}

}
