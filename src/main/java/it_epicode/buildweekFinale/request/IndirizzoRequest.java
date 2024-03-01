package it_epicode.buildweekFinale.request;

import it_epicode.buildweekFinale.model.SedeIndirizzo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndirizzoRequest {

    @NotBlank(message = "via obbligatoria")
    private String via;

    @NotBlank(message = "civico obbligatorio")
    private String civico;

    @NotNull(message = "id Comune obbligatorio")
    @Min(value = 1, message = "id minimo 1")
    private Integer idComune;

    @NotBlank(message = "cap obbligatoria")
    private String cap;

    private SedeIndirizzo sedeIndirizzo;

}
