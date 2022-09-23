package br.com.aceleragep.GabrielListaRestAPI.exeptions;

public class BadRequestBussinessException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public BadRequestBussinessException(String message) {
		super(message);
	}
}
