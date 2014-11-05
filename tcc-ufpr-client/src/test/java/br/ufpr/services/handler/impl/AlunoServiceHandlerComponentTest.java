package br.ufpr.services.handler.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.ufpr.service.handler.AlunoServiceHandler;
import br.ufpr.support.SpringTestSupport;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class AlunoServiceHandlerComponentTest {

	@Autowired
	private AlunoServiceHandler alunoServiceHandler;
	
	@Test
	public void shouldCallGetOne() {
		alunoServiceHandler.getOne(1);
	}
	
}
