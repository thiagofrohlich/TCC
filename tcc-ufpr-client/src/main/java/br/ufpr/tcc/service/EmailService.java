package br.ufpr.tcc.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {

	
	@SuppressWarnings("deprecation")
	public void enviaEmailSimples(String destinatario, String nome, String titulo, String link) throws EmailException {  
        
        SimpleEmail email = new SimpleEmail();  
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
        email.addTo(destinatario, nome); //destinatário  
        email.setFrom("rrsilva1991@gmail.com", "Eu"); // remetente  
        email.setSubject(titulo); // assunto do e-mail  
        email.setMsg("Para concluir a ação, entre nesse link\n\n" + link); //conteudo do e-mail  
        email.setAuthentication("rrsilva1991@gmail.com", "45454762");  
        email.setSmtpPort(587);  
        email.setSSL(false);  
        email.setTLS(false);  
        email.send();     
    }  
}
