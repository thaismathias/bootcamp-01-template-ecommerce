package com.mercadolivre.mercadolivre.email;

import com.mercadolivre.mercadolivre.model.Pergunta;
import com.mercadolivre.mercadolivre.model.Produto;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Override
    public void enviarEmailPergunta(Pergunta pergunta, Produto produto) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getDona().getEmail());
        sm.setFrom("mercadolivre@zup.com");
        //sm.setSubject("Você tem uma nova pergunta:");

        sm.setSubject("Nova pergunta sobre o produto: "
                + produto.getId() + " - "
                + produto.getNome());

        sm.setText(pergunta.getTitulo());

        enviarEmail(sm);
    }
}
