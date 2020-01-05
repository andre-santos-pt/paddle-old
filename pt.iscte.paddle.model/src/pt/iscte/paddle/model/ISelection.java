package pt.iscte.paddle.model;

public interface ISelection extends IControlStructure {
	IBlock getAlternativeBlock(); // may be null

	IBlock createAlternativeBlock();
	
	default boolean hasAlternativeBlock() {
		return getAlternativeBlock() != null;
	}
}
