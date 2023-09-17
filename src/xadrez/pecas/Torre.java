package xadrez.pecas;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Torre extends PecaDeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
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
		
		return mat;
	}
}
