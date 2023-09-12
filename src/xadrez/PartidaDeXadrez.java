package xadrez;

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
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int x=0; x<tabuleiro.getLinhas(); x++){
			for(int y=0; y<tabuleiro.getColunas(); y++) {
				mat [x][y] = (PecaDeXadrez)tabuleiro.peca(x, y);
			}
		}
		return mat;
	}
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	
	private void configInicial() {
		colocarNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 8,new King(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 1,new King(tabuleiro, Cor.BRANCO));
	}
}
