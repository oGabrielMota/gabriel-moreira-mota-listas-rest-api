package br.com.aceleragep.GabrielListaRestAPI.exeptions;

public class NotFoundBussinessException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public NotFoundBussinessException(String message) {
		super(message);
	}
}
