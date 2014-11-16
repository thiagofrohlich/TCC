package br.ufpr.util;

import java.util.List;

import br.ufpr.domain.DomainObject;
import br.ufpr.exception.MissingIdException;
import br.ufpr.exception.NoResultFoundException;
import br.ufpr.exception.NotDeletedObjectException;
import br.ufpr.exception.NullParameterException;

public class AssertUtils {

	public static void assertHasId(DomainObject domainObject) throws MissingIdException {
		if(domainObject.getId() == null) {
			throw new MissingIdException();
		}
	}

	public static void assertParameterIsSupplied(Object parameter) throws NullParameterException {
		if(parameter == null) {
			throw new NullParameterException();
		} else if(parameter instanceof String) {
			assertStringIsNotEmpty((String) parameter);
		}
	}

	public static void assertParameterIsSupplied(Object id, String message) throws NullParameterException {
		if(id == null) {
			throw new NullParameterException(message);
		}
	}
	
	public static void assertIsDeleted(DomainObject domainObject) throws NotDeletedObjectException {
		if(!domainObject.isDeleted()) {
			throw new NotDeletedObjectException();
		}
	}

	public static void assertIsFound(DomainObject domainObject) throws NoResultFoundException {
		if(domainObject == null) {
			throw new NoResultFoundException();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void assertIsFound(List domainList) throws NoResultFoundException {
		if(domainList == null || domainList.isEmpty()) {
			throw new NoResultFoundException();
		}
	}

	private static void assertStringIsNotEmpty(String parameter)
			throws NullParameterException {
		if(parameter != null && parameter.trim().equals("")) {
			throw new NullParameterException();				
		}
	}

}
