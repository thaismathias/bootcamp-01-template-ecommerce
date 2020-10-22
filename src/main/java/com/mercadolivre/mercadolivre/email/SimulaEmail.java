package com.mercadolivre.mercadolivre.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class SimulaEmail extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SimulaEmail.class);

    @Override
    public void enviarEmail(SimpleMailMessage message) {
        LOG.info("Simulando email...");
        LOG.info(message.toString());
        LOG.info("Email enviado com sucesso.");
    }
}
