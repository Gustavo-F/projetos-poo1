package steam.entidades;

import java.util.ArrayList;

public class Desenvolvedora extends Pessoa {

	// sempre que um Jogador comprar um Jogo, será adicionado na lista de clientes
	// da desenvolvedora
	private ArrayList<Jogador> clientes;

	public Desenvolvedora(String nome, String email, String senha) {
		super(nome, email, senha);
		clientes = new ArrayList<Jogador>();
	}

	public void adicionaCliente(Jogador cliente) {
		for (int indice = 0; indice < clientes.size(); indice++)
			if (clientes.get(indice).getNome().contentEquals(cliente.getNome()))
				return;

		clientes.add(cliente);
	}

	public void removeCliente(Jogador cliente) {
		for (int indice = 0; indice < clientes.size(); indice++) {
			if (clientes.get(indice).getNome().contentEquals(cliente.getNome())) {
				clientes.remove(indice);
				return;
			}
		}
	}

	public ArrayList<Jogador> getClientes() {
		return clientes;
	}

//	@Override
//	public void imprime() {
//		System.out.println("Impressão da classe Desenvolvedora");
//	}

	@Override
	public void imprimeAbstrato() {
		System.out.println("Impressão concreta da classe desenvolvedora.");
	}
}
