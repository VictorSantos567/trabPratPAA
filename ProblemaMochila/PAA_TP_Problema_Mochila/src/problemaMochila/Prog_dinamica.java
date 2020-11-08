package problemaMochila;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Random;

public class Prog_dinamica {

	static class Item {
		public int peso;
		public int valor;

		public Item(int peso, int valor) {
			this.peso = peso;
			this.valor = valor;

		}
	}

	static List<Item> items = new ArrayList<>();

	public static void gerarItems(int quantidade) {
		Random random = new Random();
		for (int i = 0; i < quantidade; i++) {
			items.add(new Item(random.nextInt(10), random.nextInt(12)));
		}

	}

	/**
	 * Começamos pelo caso base: zero itens com zero valor e Então iniciamos a
	 * encher a mochila
	 */
	public static int calcula_0_1(int c, List<Item> list) {
		int n = list.size();
		int[][] k = new int[n + 1][c + 1];
		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= c; w++) {
				if ((i == 0) || (w == 0)) // condição inicial
					k[i][w] = 0; // nada na mochila
				else
				// ainda dá para tentar inserir o item na mochila
				if (list.get(i - 1).peso <= w)
					// 2 condições: ainda tem espaço ou tentamos retirar um item
					k[i][w] = Math.max(list.get(i - 1).valor + k[i - 1][w - list.get(i - 1).peso], k[i - 1][w]);
				else
					// mochila já está cheia
					k[i][w] = k[i - 1][w];
			}
		}
		// imprime a matriz de cálculo
		for (int w = 0; w <= c; w++)
			System.out.printf("%3d ", w);
		System.out.printf(" << c\n");
		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= c; w++)
				System.out.printf("%3d ", k[i][w]);
			System.out.printf(" << %3d\n", i);
		}

		return k[n][c];
	}

	/*
	 * Usamos um pequeno truque aqui. Primeiro recriamos a entrada de tal forma que
	 * ela fique compatível com o Knapsack 0_1 isto é, armazenamos todos os dados em
	 * novos arrays criando as repetições indicadas em qtd. Então simplismente
	 * chamamos procedimento já feito para Knapsack_conjunto.java que chamamos aqui
	 * de calcula_0_1()
	 */
	public static int calcula(int c, int[] qtd) {
		int n = IntStream.of(qtd).sum();
		List<Item> aux = new ArrayList<>();
		int j = 0;
		for (int i = 0; i < items.size(); i++) {
			for (int q = 0; q < qtd[i]; q++) {
				aux.add(items.get(i));
				j += 1;
			}
		}
		return calcula_0_1(c, aux);
	}

	public static void calcularTempoMochilaDinamica(int c, int[] qtd) {
		long tempoInicial = System.nanoTime();
		// System.out.printf("Valor máximo conseguido na mochila = %d\n", calcula(c,
		// qtd));
		long tempoFinal = System.nanoTime();
		System.out.println("Tempo do algoritmo tal: " + (tempoFinal - tempoInicial));
	}

	public static void main(String[] args) {
		Random random = new Random();
		gerarItems(60);
		int[] qtd = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		int c = random.nextInt(60);
		System.out.println();
		calcularTempoMochilaDinamica(c, qtd);
	}

}
