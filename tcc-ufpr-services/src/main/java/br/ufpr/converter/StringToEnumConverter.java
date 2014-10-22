package br.ufpr.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.dozer.CustomConverter;

import br.ufpr.model.BusinessEnum;

public class StringToEnumConverter implements CustomConverter {

	private static final Logger LOG = Logger.getLogger(StringToEnumConverter.class);
	
	@Override
	public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
		if(source == null || destinationClass == null) {
			LOG.error("Either Source or DestinationClass are null. Cannot convert - source = " + source + " / destinationClass = " + destinationClass);
			return null;
		}
		
		if(destinationClass.getSimpleName().equalsIgnoreCase("String")) {
			return getString(source);
		} else {
			return getEnum(destinationClass, source);
		}
		
	}
	
	private Object getString(Object object) {
		return ((BusinessEnum) object).getKey();
	}
	
	private Object getEnum(Class<?> destinationClass, Object source) {
		Object enumeration = null;
		
		Method[] methods = destinationClass.getMethods();
		for(Method method : methods) {
			if(method.getName().equalsIgnoreCase("valueOf")) {
				try {
					enumeration = method.invoke(destinationClass.getClass(), source);
				} catch(IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
					LOG.error("Error to convert String to Enum - " + e.getMessage(), e);
				}
			}
		}
		
		return enumeration;
	}

}
