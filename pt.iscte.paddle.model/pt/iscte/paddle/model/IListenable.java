package pt.iscte.paddle.model;

public interface IListenable<L> {
	void addListener(L listener);
	void removeListener(L listener);
}
