package com.mercadolivre.mercadolivre.api.model;

import com.mercadolivre.mercadolivre.model.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class ImagemProduto {

    @URL
    @NotBlank
    private String link;

    public ImagemProduto() {

    }

    public ImagemProduto(@NotBlank @URL String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    public String getLink() {
        return link;
    }
}
