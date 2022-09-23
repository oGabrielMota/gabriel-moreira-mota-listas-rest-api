package br.com.aceleragep.GabrielListaRestAPI.handlers;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldsExceptionOutput {
	private String name;
	private String message;

}
