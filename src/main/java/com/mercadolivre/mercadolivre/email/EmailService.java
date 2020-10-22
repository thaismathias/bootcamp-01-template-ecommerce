package com.mercadolivre.mercadolivre.email;

import com.mercadolivre.mercadolivre.model.Pergunta;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviarEmail(SimpleMailMessage message);

    void enviarEmailPergunta(Pergunta pergunta);

}
