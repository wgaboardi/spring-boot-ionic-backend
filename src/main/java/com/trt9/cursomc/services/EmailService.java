package com.trt9.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.trt9.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
