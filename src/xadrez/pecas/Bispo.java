package xadrez.pecas;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Bispo extends PecaDeXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao auxP = new Posicao(0, 0);
		// Noroeste
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha()-1, auxP.getColuna()-1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Nordeste
		auxP.setValores(posicao.getLinha()-1, posicao.getColuna()+ 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha()-1, auxP.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Sudeste
		auxP.setValores(posicao.getLinha()+1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha()+1, auxP.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}

		// Sudoeste
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha()+1, auxP.getColuna()-1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		return mat;
	}
}
