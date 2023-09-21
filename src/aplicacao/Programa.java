package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoDeXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();
		
		while (!partidaDeXadrez.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaDeXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicao(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaDeXadrez.getPecas(),movimentosPossiveis);
	
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicao(sc);
	
				PecaDeXadrez pecaCapiturada = partidaDeXadrez.realizarJogadaXadrez(origem, destino);
				
				if(pecaCapiturada != null) {
					capturada.add(pecaCapiturada);
				}
			}
			catch(ExcecaoDeXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printPartida(partidaDeXadrez, capturada);
		 
	}

}
