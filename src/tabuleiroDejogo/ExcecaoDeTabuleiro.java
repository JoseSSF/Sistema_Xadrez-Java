package tabuleiroDejogo;

public class ExcecaoDeTabuleiro extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ExcecaoDeTabuleiro(String mensagem) {
		super(mensagem);
	}
}
