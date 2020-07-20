package steam.entidades;

public abstract class Pessoa {
	private String nome;
	private String email;
	private String senha;

	public Pessoa(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public abstract void imprimeAbstrato();
}
