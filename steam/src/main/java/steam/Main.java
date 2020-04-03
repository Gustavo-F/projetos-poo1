package steam;

import steam.entidades.Desenvolvedora;
import steam.entidades.Jogador;
import steam.entidades.Pessoa;

public class Main {

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa("Lucas", "lucas.bueno@ifsc.edu.br");
		Pessoa desenvolvedora = new Desenvolvedora("Rockstar", "contato@rockstar.com");
		Pessoa jogador = new Jogador("Lucas", "lucas.jogador@jogador.com", "bueno");
		
		pessoa.imprime();
		desenvolvedora.imprime();
		jogador.imprime();
	}

}
