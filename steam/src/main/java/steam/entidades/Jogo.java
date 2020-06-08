package steam.entidades;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
	private String nome;
	private double preco;
	/**
	 * A estrutura de dados ArrayList é como um vetor, mas sem limite de tamanho.
	 * Para adicionar um elementos utilizamos o método add() e para remover o método
	 * remove(). O tipo do objeto que será armazenado é indicado dentro do < >.
	 */
	private List<Genero> generos;
	private Desenvolvedora desenvolvedora;

	/**
	 * Teremos dois métodos construtores, isto é, faremos a sobrecarga do método
	 * construtor. A sobrecarga pode ser feita para qualquer método. Basta utilizar
	 * o mesmo tipo de retorno e o mesmo nome, alterando apenas os parâmetros que
	 * são passados para o método.
	 * 
	 * Neste primeiro método, o parâmetros nome e desenvolvedora são necessários
	 * para incializar criar um Jogo.
	 * 
	 */
	public Jogo(String nome, Desenvolvedora desenvolvedora) {
		super();
		this.nome = nome;
		this.preco = 0.0; // como não passei por parâmetro, o inicio zerado
		this.desenvolvedora = desenvolvedora;
		this.generos = new ArrayList<Genero>(); // crio um objeto do tipo ArrayList, que armazenará objetos do tipo
												// Genero.
	}

	/**
	 * Neste segundo método, temos os parâmetros nome, desenvolvedora e preco. Com
	 * isso, de certa forma criamos uma limitação para como nossos objetos podem ser
	 * criados. Para um objeto da classe Jogo, por exemplo, poderemos criar
	 * instâncias ou com a inicialização de apenas o nome e a desenvolvedora, ou com
	 * a inicialização do nome, da desenvolvedora e do preço.
	 */
	public Jogo(String nome, Desenvolvedora desenvolvedora, double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.desenvolvedora = desenvolvedora;
		this.generos = new ArrayList<Genero>(); // crio um objeto do tipo ArrayList, que armazenará objetos do tipo
		// Genero.
	}
	
	public Jogo(String nome, Desenvolvedora desenvolvedora, double preco, List<Genero> generos) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.desenvolvedora = desenvolvedora;
		this.generos = generos;
	}

	public void adicionaGenero(Genero genero) {
		// Primeiro, busco na lista de generos se este genero já foi adicionado.
		// Se sim, simplesmente retorno do método e não adiciono o gênero novamente.
		// Futuramente aprenderemos uma maneira mais eficaz de realizar esta busca
		// (método equals()). Mas por enquanto, assim está ótimo.
		for (int indice = 0; indice < generos.size(); indice++)
			// É com o método contentEquals() que devemos verificar se duas Strings são
			// iguais
			if (generos.get(indice).getNome().contentEquals(genero.getNome()))
				return;

		// Se chegar aqui, este gênero ainda não foi adicionado. Então adiciono o gênero
		// na lista de gêneros.
		generos.add(genero);
	}

	public void removeGenero(Genero genero) {
		// Busco o gênero através do nome
		for (int indice = 0; indice < generos.size(); indice++) {
			// podemos utilizar o método get() da classe ArrayList para recuperar o objeto
			// em um determinado índice da lista
			if (generos.get(indice).getNome().contentEquals(genero.getNome())) {
				// Se cheguei aqui, encontrei o gênero. Então o removo e retorno do método
				generos.remove(indice);
				return;
			}
		}
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Desenvolvedora getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(Desenvolvedora desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

}
