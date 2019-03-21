package main;

public class UException extends Exception{
	public UException(String info) {
		super("Uchain_Exception-INFO::" + info);

	}
}
