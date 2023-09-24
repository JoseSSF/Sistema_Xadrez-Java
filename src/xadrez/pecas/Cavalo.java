package xadrez.pecas;

import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Cavalo extends PecaDeXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez auxP = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return auxP == null || auxP.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao auxP = new Posicao(0, 0);
		
		auxP.setValores(posicao.getLinha() - 1, posicao.getColuna()-2);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha() -2, posicao.getColuna()-1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha()-2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha()-1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() +2);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha() +2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		
		auxP.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExistente(auxP) && podeMover(auxP)) {
			mat[auxP.getLinha()][auxP.getColuna()] = true;
		}
		

		return mat;
	}
}
