package steam.entidades;

public abstract class Pessoa {
	private String nome;
	private String email;

	public Pessoa(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
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
		imprimeAbstrato();
	}

	public abstract void imprimeAbstrato();
}
