package xadrez;

import tabuleiroDejogo.Tabuleiro;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
	}
	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		for (int x=0; x<tabuleiro.getLinha(); x++){
			for(int y=0; y<tabuleiro.getColuna(); y++) {
				mat [x][y] = (PecaDeXadrez)tabuleiro.peca(x, y);
			}
		}
		return mat;
	}
}
