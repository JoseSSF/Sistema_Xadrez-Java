package xadrez.pecas;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

//O nome dessa classe esta em inglês para não confundir a peça no tabuleiro com a da rainha pois ficaria "R" e "R" em português
public class King extends PecaDeXadrez {

	public King(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez auxP = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return auxP == null || auxP.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao auxP = new Posicao(0, 0);
		// Acima
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Abaixo
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Esquerda
		auxP.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Direita
		auxP.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Noroeste
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Nordeste
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Sudoeste
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		// Sudeste
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		

		return mat;
	}
}
