package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiroDejogo.Peca;
import tabuleiroDejogo.Posicao;
import tabuleiroDejogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.King;
import xadrez.pecas.Peao;
import xadrez.pecas.Queen;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaDeXadrez enPassantVulneravel;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configInicial();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public PecaDeXadrez getEnPassantVulneravel() {
		return enPassantVulneravel;
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int x = 0; x < tabuleiro.getLinhas(); x++) {
			for (int y = 0; y < tabuleiro.getColunas(); y++) {
				mat[x][y] = (PecaDeXadrez) tabuleiro.peca(x, y);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaDeXadrez realizarJogadaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerJogada(origem, destino);

		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturarPeca);
			throw new ExcecaoDeXadrez("Você não pode se colocar em check");
		}

		PecaDeXadrez pecaMovida = (PecaDeXadrez) tabuleiro.peca(destino);

		check = (testeCheck(oponente(jogadorAtual))) ? true : false;

		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} 
		else {
			proximoTurno();
		}

		// Movimento en passant
		if (pecaMovida instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVulneravel = pecaMovida;
		} 
		else {
			enPassantVulneravel = null;
		}

		return (PecaDeXadrez) capturarPeca;
	}

	private Peca fazerJogada(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		p.aumentarContagemDeMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// Movimento roque pequeno
		if (p instanceof King && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.lugarPeca(torre, destinoT);
			torre.aumentarContagemDeMovimentos();
		}

		// Movimento roque grande
		if (p instanceof King && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.lugarPeca(torre, destinoT);
			torre.aumentarContagemDeMovimentos();
		}

		// Movimento en passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} 
				else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}

		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		p.diminuirContagemDeMovimentos();
		tabuleiro.lugarPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.lugarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// Movimento roque pequeno
		if (p instanceof King && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoT);
			tabuleiro.lugarPeca(torre, origemT);
			torre.diminuirContagemDeMovimentos();
			;
		}

		// Movimento roque grande
		if (p instanceof King && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoT);
			tabuleiro.lugarPeca(torre, origemT);
			torre.diminuirContagemDeMovimentos();
		}

		// Movimento en passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulneravel) {
				PecaDeXadrez peao = (PecaDeXadrez)tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				} 
				else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.lugarPeca(peao, posicaoPeao);
			}
		}
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existeUmaPeca(posicao)) {
			throw new ExcecaoDeXadrez("Não há nenhuma peça na posição de origem");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoDeXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExcecaoDeXadrez("Não existem movimentos possíveis para esta peça");
		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentosPossiveis(destino)) {
			throw new ExcecaoDeXadrez("A peça escolhida não pode mover-se para a posição de destino");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaDeXadrez king(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof King) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe rei " + cor + " no tabuleiro");
	}

	private boolean testeCheck(Cor cor) {
		Posicao kingPosicao = king(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[kingPosicao.getLinha()][kingPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int x = 0; x < tabuleiro.getLinhas(); x++) {
				for (int y = 0; y < tabuleiro.getColunas(); y++) {
					if (mat[x][y]) {
						Posicao origem = ((PecaDeXadrez) p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(x, y);
						Peca pecaCapturada = fazerJogada(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void configInicial() {
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Queen(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new King(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Queen(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new King(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
	}
}
