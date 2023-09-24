package xadrez.pecas;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

//O nome dessa classe esta em inglês para não confundir a peça no tabuleiro com a da rainha pois ficaria "R" e "R" em português
public class King extends PecaDeXadrez {

	private PartidaDeXadrez partidaDeXadrez;

	public King(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez auxP = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return auxP == null || auxP.getCor() != getCor();
	}

	private boolean testeTorreRoque(Posicao posicao) {
		PecaDeXadrez auxP = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return auxP != null && auxP instanceof Torre && auxP.getCor() == getCor()
				&& auxP.getContagemDeMovimentos() == 0;
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
		// Movimento Roque
		if (getContagemDeMovimentos() == 0 && !partidaDeXadrez.getCheck()) {
			// Roque pequeno
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testeTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			// Roque grande
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if (testeTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}

		return mat;
	}
}
