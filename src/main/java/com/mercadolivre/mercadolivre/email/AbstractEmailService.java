package com.mercadolivre.mercadolivre.email;

import com.mercadolivre.mercadolivre.model.Pergunta;
import com.mercadolivre.mercadolivre.model.Produto;
import org.springframework.mail.SimpleMailMessage;

import static com.mercadolivre.mercadolivre.helper.Constants.URL;

public abstract class AbstractEmailService implements EmailService {

    @Override
    public void enviarEmailPergunta(Pergunta pergunta, Produto produto) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getDona().getEmail());
        sm.setFrom("mercadolivre@zup.com");

        String conteudoEmail = "\nOlá, " + produto.getDona().getEmail() +
                "\nVocê tem uma nova pergunta em um de seus produtos" +
                "\nProduto [" + produto.getId() + "]: " + produto.getNome() +
                "\nLink: " + URL + "/produtos/" + produto.getId();

        sm.setSubject("Nova pergunta sobre o produto: "
                + produto.getId() + " - "
                + produto.getNome());

        sm.setText(conteudoEmail);

        enviarEmail(sm);
    }
}
