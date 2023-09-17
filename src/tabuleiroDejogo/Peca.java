package tabuleiroDejogo;

public abstract class Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentosPossiveis(Posicao posicao){
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeAlgumMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for(int x=0; x<mat.length; x++) {
			for(int y=0; y<mat.length; y++) {
				if(mat[x][y]) {
					return true;
				}
			}
		}
		return false;
	}
}
