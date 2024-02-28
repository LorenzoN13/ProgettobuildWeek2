package it_epicode.buildweekFinale.request;

import it_epicode.buildweekFinale.model.Stato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.lang.ref.PhantomReference;
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

    @NotNull(message = "statofattura obbligatorio")
    private Stato stato;

    @NotNull(message = "dataFine obbligatoria")
    private LocalDate dataFine;
}
