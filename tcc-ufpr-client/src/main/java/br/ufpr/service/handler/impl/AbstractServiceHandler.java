package br.ufpr.service.handler.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.ufpr.service.handler.ServiceHandler;

@SuppressWarnings("unchecked")
public abstract class AbstractServiceHandler<M, ID extends Serializable> implements ServiceHandler<M, ID> {

	private RestTemplate restTemplate;
	private final String absolutePath = "http://localhost:8080/tcc-ufpr-web";
	
	protected RestTemplate getRestTemplate() {
		if(restTemplate == null) {
			restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		}
		return restTemplate;
	}

	@Override
	public M getOne(ID id) {
		return (M) getRestTemplate().getForObject(getPath()+"/{id}", getReturnClass(), id);
	}

	@Override
	public M create(M model) {
		return (M) getRestTemplate().postForEntity(getPath(), model, getReturnClass());
	}

	@Override
	public void update(M model) {
		getRestTemplate().put(getPath(), model);
	}

	@Override
	public void delete(ID id) {
		getRestTemplate().delete(getPath()+"/{id}", id);
	}
	
	protected final String getPath() {
		return absolutePath + getRelativePath();
	}
	
	protected final Class<? extends M> getReturnClass() {
		  ParameterizedType parameterizedType =
		    (ParameterizedType) getClass().getGenericSuperclass();
		 return (Class<? extends M>) parameterizedType.getActualTypeArguments()[0];
	}
	
}
