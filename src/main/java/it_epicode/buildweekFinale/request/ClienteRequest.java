package it_epicode.buildweekFinale.request;

import it_epicode.buildweekFinale.model.Indirizzo;
import it_epicode.buildweekFinale.model.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class ClienteRequest {
    @NotBlank(message = "ragioneSociale obbligatoria")
    private String ragioneSociale;

    @NotBlank(message = "partitaIva obbligatoria")
    private String partitaIva;

    @Email(message = "formato email non valido")
    private String email;

    @NotNull(message = "Data Inserimento obbligatorio")
    private LocalDate dataInserimento;

    @NotNull(message = "Data Ultimo Contatto obbligatorio")
    private LocalDate dataUltimoContatto;

    @NotNull(message = "FatturaAnnula obbligatoria")
    private int fatturatoAnnuale;

    @NotBlank(message = "pec obbligatoria")
    private String pec;

    @NotBlank(message = "telefono obbligatorio")
    private String telefono;

    @NotBlank(message = "emailDiContatto obbligatorio")
    private String emailContatto;

    @NotBlank(message = "nomeContatto obbligatorio")
    private String nomeContatto;

    @NotBlank(message = "cognomeContatto obbligatorio")
    private String cognomeContatto;

    @NotBlank(message = "telefonoContatto obbligatorio")
    private String telefonoContatto;

    @NotBlank(message = "logoAziendale obbligatorio")
    private String logoAziendale;

    @NotNull(message = "tipo obbligatorio")
    private Tipo tipo;

    @NotNull(message = "indirizzo obbligatorio ")
    private List<IndirizzoRequest> indirizzo;

}
