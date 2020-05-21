package steam.bd;

import java.sql.SQLException;
import java.util.List;

import steam.entidades.Genero;

public class GeneroDAO implements InterfaceDAO<Genero> {

	public void adicionar(Genero genero) {
		try {
			String sql = "INSERT INTO Genero VALUES ('" + genero.getNome() + "')";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível inserir o genero no banco!");
		}
	}

	public void remover(Genero genero) {
		try {
			String sql = "DELETE FROM Genero WHERE nome = '" + genero.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível remover o genero no banco!");
		}
	}

	public List<Genero> todos() {
		// TODO Auto-generated method stub
		return null;
	}

}
