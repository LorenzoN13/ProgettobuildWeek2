package it.epicode.bw5.controller;

import it.epicode.bw5.model.CustomResponse;
import it.epicode.bw5.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbController {
    @Autowired
    private DbService dbService;

    @GetMapping("/aggiorna-db")
    public CustomResponse aggiornaDb(){
        dbService.aggiornaDb(true);
        return new CustomResponse(HttpStatus.OK.toString(), true);
    }

    @GetMapping("/province")
    public CustomResponse getProvince(){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getProvince());
    }

    @GetMapping("/province/id/{id}")
    public CustomResponse getProvinciaById(@PathVariable int id){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getProvinciaById(id));
    }

    @GetMapping("/province/nome/{nome}")
    public CustomResponse getProvinciaByNome(@PathVariable String nome){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getProvinciaByNome(nome));
    }

    @GetMapping("/province/sigla/{sigla}")
    public CustomResponse getProvinciaBySigla(@PathVariable String sigla){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getProvinciaBySigla(sigla));
    }

    @GetMapping("/province/regione/{regione}")
    public CustomResponse getProvinceByRegione(@PathVariable String regione){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getProvinceByRegione(regione));
    }

    @GetMapping("/comuni")
    public CustomResponse getComuni(){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getComuni());
    }

    @GetMapping("/comuni/id/{id}")
    public CustomResponse getComuni(@PathVariable int id){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getComuneById(id));
    }

    @GetMapping("/comuni/nome/{nome}")
    public CustomResponse getComuneByNome(@PathVariable String nome){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getComuneByNome(nome));
    }

    @GetMapping("/comuni/provincia/{nomeProvincia}")
    public CustomResponse getComuniByProvincia(@PathVariable String nomeProvincia){
        return new CustomResponse(HttpStatus.OK.toString(), dbService.getComuniByProvincia(nomeProvincia));
    }
}
