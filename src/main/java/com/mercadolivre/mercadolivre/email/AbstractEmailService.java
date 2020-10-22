package com.mercadolivre.mercadolivre.email;

import com.mercadolivre.mercadolivre.model.Pergunta;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Override
    public void enviarEmailPergunta(Pergunta pergunta) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getDona().getEmail());
        sm.setFrom("mercadolivre@zup.com");
        sm.setSubject("Nova pergunta para o produto id: "
                + pergunta.getProduto().getId() + " - "
                + pergunta.getProduto().getNome());
        sm.setText(pergunta.getTitulo());
        enviarEmail(sm);
    }
}
