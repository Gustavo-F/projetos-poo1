package steam.entidades;

import java.util.ArrayList;

public class Jogador extends Pessoa {
	private String apelido;
	private ArrayList<Pessoa> amigos;

	// para criar um jogador, sou obrigado a passar o nome, o e-mail e o apelido
	public Jogador(String nome, String email, String apelido) {
		super(nome, email); // chamo o método construtor da classe mãe Pessoa
		this.apelido = apelido;
		this.amigos = new ArrayList<Pessoa>(); // apenas crio a instância da lista de amigos (a lista inicia vazia)
	}

	@Override
	public void adicionaJogo(Jogo jogo) {
		for (int indice = 0; indice < jogos.size(); indice++)
			if (jogos.get(indice).getNome().contentEquals(jogo.getNome()))
				return;

		jogos.add(jogo);

		// a diferença deste método para o da classe mãe, é que aqui adicionamos o
		// jogador como cliente da desenvolvedora do jogo
		jogo.getDesenvolvedora().adicionaCliente(this);
	}

	@Override
	public void removeJogo(Jogo jogo) {
		for (int indice = 0; indice < jogos.size(); indice++) {
			if (jogos.get(indice).getNome().contentEquals(jogo.getNome())) {
				jogos.remove(indice);
				// a diferença deste método para o da classe mãe, é que aqui removemos o
				// jogador como cliente da desenvolvedora do jogo
				jogo.getDesenvolvedora().removeCliente(this);
				return;
			}
		}
	}

	/**
	 * Como estou passando um amigo do tipo Pessoa como parâmetro para o método,
	 * este amigo pode ser ou um objeto da classe Jogador ou um objeto da classe
	 * Desenvolvedora, pois ambas as classes são filhas da classe Pessoa (!), isso
	 * significa que, com uma referência para o tipo Pessoa, temos certeza de que
	 * estes objetos possuem os métodos disponíveis na classe Pessoa. Isso é
	 * polimorfismo: uma referência de um tipo pode armazenar objetos de tipos
	 * diferentes.
	 */
	public void adicionaAmigo(Pessoa amigo) {
		for (int indice = 0; indice < amigos.size(); indice++)
			if (amigos.get(indice).getNome().contentEquals(amigo.getNome()))
				return;

		amigos.add(amigo);
	}

	public void removeAmigo(Pessoa amigo) {
		for (int indice = 0; indice < amigos.size(); indice++) {
			if (amigos.get(indice).getNome().contentEquals(amigo.getNome())) {
				amigos.remove(indice);
				return;
			}
		}
	}

	public ArrayList<Pessoa> getAmigos() {
		return amigos;
	}

	public String getApelido() {
		return apelido;
	}

	// o apelido de um jogador pode mudar, mas não o seu nome
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	@Override
	public void imprime() {
		System.out.println("Impressão da classe Jogador");
	}

}
