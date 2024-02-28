package it_epicode.buildweekFinale.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IndirizzoRequest {

    @NotBlank(message = "via obbligatoria")
    private String via;

    @NotBlank(message = "civico obbligatorio")
    private String civico;

    @NotBlank(message = "localita obbligatoria")
    private String localita;

    @NotBlank(message = "cap obbligatoria")
    private String cap;

}
