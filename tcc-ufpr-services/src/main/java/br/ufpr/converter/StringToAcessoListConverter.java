package br.ufpr.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.CustomConverter;

import br.ufpr.model.Acesso;
import br.ufpr.model.BusinessEnum;

public class StringToAcessoListConverter implements CustomConverter {

	private static final String SEPARATOR_TOKEN = ",";
	private static final Logger LOG = Logger.getLogger(StringToAcessoListConverter.class);
	
	@Override
	public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
		if(source == null || destinationClass == null) {
			LOG.error("Either Source or DestinationClass are null. Cannot convert - source = " + source + " / destinationClass = " + destinationClass);
			return null;
		}
		
		if(destinationClass.getSimpleName().equalsIgnoreCase("String")) {
			return getString(source);
		} else {
			return getEnumList(destinationClass, source);
		}
		
	}
	
	private Object getString(Object object) {
		StringBuilder sb = new StringBuilder();
		for(Object o : ((List) object)) {
			sb.append(((BusinessEnum) o).getKey() + SEPARATOR_TOKEN);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	private Object getEnumList(Class<?> destinationClass, Object source) {
		List list = new ArrayList<>();
		for(Object o : ((String) source).split(SEPARATOR_TOKEN)) {
			list.add(getEnum(o));
		}
		return list;
	}
	
	private Object getEnum(Object source) {
		return Acesso.valueOf((String) source);
	}
	
	private Class<?> getType(List list) {
		return null;
	}

}
