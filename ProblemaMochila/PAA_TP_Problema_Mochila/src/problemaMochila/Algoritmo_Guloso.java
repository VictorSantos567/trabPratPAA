package problemaMochila;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Algoritmo_Guloso {
	static class Item {
		public int peso;
		public int valor;
		public Double balanco;

		public Item(int peso, int valor) {
			this.peso = peso;
			this.valor = valor;
			balanco = (double) valor / peso;
		}

		@Override
		public String toString() {
			return "Item{" + "peso=" + peso + ", valor=" + valor + ", balanco=" + balanco + '}';
		}
	}

	static List<Item> items = new ArrayList<>();

	public static void gerarItems(int quantidade) {
		Random random = new Random();
		for (int i = 0; i < quantidade; i++) {
			items.add(new Item(random.nextInt(10), random.nextInt(12)));
		}
	}

	private static List<Item> algoritmoGuloso(int capacidade) {
		List<Item> mochila = new ArrayList<>();
		items.sort(new Comparator<Item>() {
			@Override
			public int compare(Item item, Item t1) {
				return t1.balanco.compareTo(item.balanco);
			}
		});
		for (Item i : items) {
			if (capacidade - i.peso >= 0) {
				capacidade = capacidade - i.peso;
				mochila.add(i);
			}
		}
		return mochila;
	}

	public static void imprimirItensmochila(List<Item> mochila) {
		for (Item i : mochila) {
			System.out.println(i.toString());
		}
	}

	public static void calcularTempoMochilaGulosa(List<Item> mochila) {
		long tempoInicial = System.nanoTime();
		imprimirItensmochila(mochila);
		long tempoFinal = System.nanoTime();
		System.out.println("Tempo do algoritmo tal: " + (tempoFinal - tempoInicial));
	}

	public static void main(String[] args) {
		gerarItems(60);
		List<Item> mochila = algoritmoGuloso(60);
		calcularTempoMochilaGulosa(mochila);
	}

}
