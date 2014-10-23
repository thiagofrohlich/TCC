package br.ufpr.util;

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

	public static void assertParameterIsSupplied(Object id) throws NullParameterException {
		if(id == null) {
			throw new NullParameterException();
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

}
