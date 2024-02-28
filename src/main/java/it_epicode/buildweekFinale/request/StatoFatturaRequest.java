package it_epicode.buildweekFinale.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatoFatturaRequest {

    @NotNull(message = "nome obbligatorio")
    private String nome;

}
