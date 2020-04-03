package steam.entidades;

import java.util.ArrayList;

public class Pessoa {
	private String nome;
	private String email;
	protected ArrayList<Jogo> jogos; // protected significa que tanto a classe, quanto as classes filhas podem
										// acessar o atributo diretamente

	public Pessoa(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
		this.jogos = new ArrayList<Jogo>();
	}

	public void adicionaJogo(Jogo jogo) {
		for (int indice = 0; indice < jogos.size(); indice++)
			if (jogos.get(indice).getNome().contentEquals(jogo.getNome()))
				return;

		jogos.add(jogo);
	}

	public void removeJogo(Jogo jogo) {
		for (int indice = 0; indice < jogos.size(); indice++) {
			if (jogos.get(indice).getNome().contentEquals(jogo.getNome())) {
				jogos.remove(indice);
				return;
			}
		}
	}

	public ArrayList<Jogo> getJogos() {
		return jogos;
	}

	// Como o nome e o email não podem ser alterados, vou criar apenas os getters.

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public void imprime() {
		System.out.println("Impressão da classe Pessoa");
	}
}
