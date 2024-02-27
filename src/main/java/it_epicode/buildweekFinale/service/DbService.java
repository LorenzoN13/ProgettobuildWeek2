package it_epicode.buildweekFinale.service;

import it_epicode.buildweekFinale.exception.BadRequestException;
import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.Comune;
import it_epicode.buildweekFinale.model.Provincia;
import it_epicode.buildweekFinale.repository.ComuneRepository;
import it_epicode.buildweekFinale.repository.ProvinciaRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Service
public class DbService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Transactional
    public void salvaProvince() {
        File file = new File("./province-italiane.csv");
        String s;
        try {
            s = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            throw new BadRequestException("File non trovato");
        }
        String[] righe = s.split("\r\n");
        for (int i = 1; i < righe.length; i++) {
            String[] columns = righe[i].split(";");
            Provincia provincia = new Provincia(columns[0], columns[1], columns[2]);
            provinciaRepository.save(provincia);
        }
    }

    @Transactional
    public void salvaComuni() {
        File file = new File("./comuni-italiani.csv");
        String s;
        try {
            s = FileUtils.readFileToString(file, Charset.defaultCharset())
                    .replace("Verbano-Cusio-Ossola", "Verbania")
                    .replace("Valle d'Aosta/Vallée d'Aoste", "Aosta")
                    .replace("Monza e della Brianza", "Monza-Brianza")
                    .replace("Bolzano/Bozen", "Bolzano")
                    .replace("La Spezia", "La-Spezia")
                    .replace("Reggio nell'Emilia", "Reggio-Emilia")
                    .replace("Forlì-Cesena", "Forli-Cesena")
                    .replace("Pesaro e Urbino", "Pesaro-Urbino")
                    .replace("Ascoli Piceno", "Ascoli-Piceno")
                    .replace("Reggio Calabria", "Reggio-Calabria")
                    .replace("Vibo Valentia", "Vibo-Valentia")
                    .replace("Sud Sardegna", "Sud-Sardegna");
        } catch (IOException e) {
            throw new BadRequestException("File non trovato");
        }
        String[] righe = s.split("\r\n");
        for (int i = 1; i < righe.length; i++) {
            String[] columns = righe[i].split(";");
            Provincia provincia = provinciaRepository.getProvinciaByNome(columns[3]).orElseThrow(() -> new NotFoundException(columns[3] + " non è una provincia"));
            Comune comune = new Comune(columns[2], provincia);
            comuneRepository.save(comune);
        }
    }

    @Transactional
    public void aggiornaDb(boolean cleanBefore) {
        if (cleanBefore) {
            comuneRepository.deleteAll();
            provinciaRepository.deleteAll();
        }
        salvaProvince();
        salvaComuni();
    }

    public List<Provincia> getProvince(){
        return provinciaRepository.findAll();
    }

    public Provincia getProvinciaById(int id){
        return provinciaRepository.findById(id).orElseThrow(() -> new NotFoundException("Provincia con id = " + id + " non trovata"));
    }

    public Provincia getProvinciaByNome(String nome){
        return provinciaRepository.getProvinciaByNome(nome).orElseThrow(() -> new NotFoundException(nome + " non è una provincia"));
    }

    public Provincia getProvinciaBySigla(String sigla){
        return provinciaRepository.getProvinciaBySigla(sigla).orElseThrow(() -> new NotFoundException("Non esiste una regione con sigla " + sigla));
    }

    public List<Provincia> getProvinceByRegione(String regione){
        List<Provincia> province = provinciaRepository.getProvinciaByRegione(regione);
        if (province.isEmpty()) throw new BadRequestException(regione + " non è una regione");
        return province;
    }

    public List<Comune> getComuni(){
        return comuneRepository.findAll();
    }

    public Comune getComuneById(int id){
        return comuneRepository.findById(id).orElseThrow(() -> new NotFoundException("Comune con id = " + id + " non trovato"));
    }

    public Comune getComuneByNome(String nome){
        return comuneRepository.getComuneByNome(nome).orElseThrow(() -> new NotFoundException(nome + " non è un comune"));
    }

    public List<Comune> getComuniByProvincia(String nomeProvincia){
        Provincia provincia = getProvinciaByNome(nomeProvincia);
        List<Comune> comuni = comuneRepository.getComuneByProvincia(provincia);
        if (comuni.isEmpty()) throw new BadRequestException(provincia + " non è una provincia");
        return comuni;
    }
}