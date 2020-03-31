package steam;

import java.util.ArrayList;

import steam.entidades.Desenvolvedora;
import steam.entidades.Genero;
import steam.entidades.Jogo;

public class Main {

	public static void main(String[] args) {
		Desenvolvedora rockstar = new Desenvolvedora("Rockstar", "contato@rockstar.com");
		Genero acao = new Genero("Ação");
		Genero fps = new Genero("FPS");
		Genero estrategia = new Genero("Estratégia");
		
		Jogo jogo1 = new Jogo("Primeiro jogo", rockstar);
		jogo1.adicionaGenero(acao);
		jogo1.adicionaGenero(fps);
		jogo1.adicionaGenero(estrategia);
		System.out.println("Tamanho da lista antes da remoção: " + jogo1.getGeneros().size());
		jogo1.removeGenero(estrategia);
		System.out.println("Tamanho da lista depois da remoção: " + jogo1.getGeneros().size());
		
		Jogo jogo2 = new Jogo("Segundo jogo", rockstar, 15.5);
		
//		System.out.println("Preço do jogo 1: " + jogo1.getPreco());
//		System.out.println("Preço do jogo 2: " + jogo2.getPreco());
		
		ArrayList<Jogo> jogos = new ArrayList<Jogo>();
		jogos.add(jogo1);
		jogos.add(jogo2);
				
		jogos.remove(0);
				
	}

}
