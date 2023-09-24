package xadrez.pecas;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

//O nome dessa classe esta em inglês para não confundir a peça no tabuleiro com a do rei pois ficaria "R" e "R" em português
public class Queen extends PecaDeXadrez {

	public Queen(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao auxP = new Posicao(0, 0);
		// Acima
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setLinha(auxP.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Esquerda
		auxP.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setColuna(auxP.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Direita
		auxP.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setColuna(auxP.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}

		// Abaixo
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setLinha(auxP.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}

		// Noroeste
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha() - 1, auxP.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Nordeste
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha() - 1, auxP.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Sudeste
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha() + 1, auxP.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}

		// Sudoeste
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(auxP) && !getTabuleiro().existeUmaPeca(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
			auxP.setValores(auxP.getLinha() + 1, auxP.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(auxP) && existePecaOponente(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}

		return mat;
	}
}
