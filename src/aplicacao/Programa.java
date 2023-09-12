package aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

		while (true) {
			UI.printTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.lerPosicao(sc);

			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.lerPosicao(sc);

			PecaDeXadrez pecaCapiturada = partidaDeXadrez.realizarJogadaXadrez(origem, destino);
		}
	}

}
