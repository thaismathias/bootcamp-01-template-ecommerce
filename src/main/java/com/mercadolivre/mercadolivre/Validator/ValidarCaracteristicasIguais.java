package com.mercadolivre.mercadolivre.Validator;

import com.mercadolivre.mercadolivre.api.model.ProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidarCaracteristicasIguais implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        ProdutoRequest request = (ProdutoRequest) target;

        if(request.caracteristicasIguais()) {
            errors.rejectValue("caracteristicas", null, "Característica duplicada.");
        }
    }
}
