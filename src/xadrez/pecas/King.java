package xadrez.pecas;

import tabuleiroDejogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

//O nome dessa classe esta em inglês para não confundir a peça no tabuleiro com a da rainha pois ficaria "R" e "R" em português
public class King extends PecaDeXadrez{

	public King(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "K"; 
	}
}
