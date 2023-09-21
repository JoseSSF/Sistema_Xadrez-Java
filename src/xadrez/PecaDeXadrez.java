package xadrez;

import tabuleiroDejogo.Peca;
import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
	
	private Cor cor;
	private int contagemDeMovimentos;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContagemDeMovimentos() {
		return contagemDeMovimentos;
	}
	
	public void aumentarContagemDeMovimentos() {
		contagemDeMovimentos++;
	}
	
	public void diminuirContagemDeMovimentos() {
		contagemDeMovimentos--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
