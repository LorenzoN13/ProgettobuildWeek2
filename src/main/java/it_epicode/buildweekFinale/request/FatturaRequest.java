package it_epicode.buildweekFinale.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FatturaRequest {

    @NotBlank(message = "nunmero obbligatorio")
    private String numero;

    @NotNull(message = "data obbligatoria")
    private LocalDate data;

    @NotNull(message = "importo obblifgatorio")
    private int importo;

    @NotNull(message = "numeroFattura obbligatoria")
    private String numeroFattura;
}
