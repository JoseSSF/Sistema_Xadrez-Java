package aplicacao;

import xadrez.PecaDeXadrez;

public class UI {
	
	public static void printTabuleiro(PecaDeXadrez[][] pecas) {
		for (int x=0; x<pecas.length; x++) {
			System.out.print((8-x) + " ");
			for (int y=0; y<pecas.length; y++) {
				printPeca(pecas[x][y]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	private static void printPeca(PecaDeXadrez peca) {
		if (peca == null) {
			System.out.print("-");
		}
		else {
			System.out.println(peca);
		}
		System.out.print(" ");
	}
}
