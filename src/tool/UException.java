package tool;

public class UException extends Exception{
	private static final long serialVersionUID = 1L;

	public UException(String info) {
		super("Uchain_Exception-INFO::" + info);

	}
}
