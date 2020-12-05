package problemaMochila;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Forca_Bruta {

	static class Item {
		public int peso;
		public int valor;

		public Item(int peso, int valor) {
			this.peso = peso;
			this.valor = valor;

		}
	}

	static public double tempo = 0;
	static ArrayList<Double> listTemp = new ArrayList<>();
	static List<Item> items = new ArrayList<>();

	public static void gerarItems(int quantidade) {
		Random random = new Random();
		for (int i = 0; i < quantidade; i++) {
			items.add(new Item(random.nextInt(10), random.nextInt(12)));
		}
	}

	// Se a > b retorna a, senão retorna b. Retorna o maior
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// Retorna o peso maximo que cabe na mochila
	// Coloca na mochila com capacidade c
	static int mochila(int c, int n) {
		// Caso base
		if (n == 0 || c == 0)
			return 0;

		if (items.get(n - 1).peso > c)
			return mochila(c, n - 1);

		// Retorna o maximo em dois casos:
		// (1) Quando o item é incluso
		// (2) Quando não é.
		else
			return max(items.get(n - 1).valor + mochila(c - items.get(n - 1).peso, n - 1), mochila(c, n - 1));
	}

	public static void calcularTempoMochilaforcabruta(int c, int n) {
		System.out.println("");
		long tempoInicial = System.nanoTime();
		System.out.println("Valor máximo conseguido na mochila = " + mochila(c, n));
		long tempoFinal = System.nanoTime();
		tempo = (tempoFinal - tempoInicial) / 1_000_000_000d;
		System.out.println("Tempo do algoritmo: " + tempo);
		listTemp.add(tempo);
	}

	public static void main(String args[]) {
		Locale.setDefault(Locale.US);
		Random random = new Random();
		gerarItems(25);
		int c = random.nextInt(50);
		int n = items.size();
		// System.out.println("Valor máximo conseguido na mochila = " + mochila(c, n));
		for (int i = 0; i < 5; i++) {
			calcularTempoMochilaforcabruta(c, n);
		}

		double total = 0;

		for (double temp : listTemp) {
			total += temp;
		}

		System.out.println("");
		System.out.println("Media do tempo dos algoritmos: " + total / 5);
	}
}
