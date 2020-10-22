package com.mercadolivre.mercadolivre.email;

import com.mercadolivre.mercadolivre.model.Pergunta;
import com.mercadolivre.mercadolivre.model.Produto;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviarEmail(SimpleMailMessage message);

    void enviarEmailPergunta(Pergunta pergunta, Produto produto);

}
