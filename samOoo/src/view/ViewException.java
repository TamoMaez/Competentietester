package view;

public class ViewException extends Exception {
	private static final long serialVersionUID = 1L;

	public ViewException(String message){
		super(message);
	}
	
	public ViewException(Throwable cause){
		super(cause);
	}
	
	public ViewException(String message, Throwable cause){
		super(message, cause);
	}
}
