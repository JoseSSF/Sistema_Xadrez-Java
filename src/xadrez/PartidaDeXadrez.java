package xadrez;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configInicial();
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
	private void configInicial() {
		tabuleiro.lugarPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 1));
		tabuleiro.lugarPeca(new King(tabuleiro, Cor.PRETO), new Posicao(0, 4));
		tabuleiro.lugarPeca(new King(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
	}
}
