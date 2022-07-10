
package com.magneto.mutante.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.magneto.mutante.dominio.ValidaADN;
import com.magneto.mutante.models.adnModel;
import com.magneto.mutante.models.estadisticasModel;
import com.magneto.mutante.models.mutanteModel;
import com.magneto.mutante.repositories.MutanteRepository;

@Service
public class MutanteService {
    mutanteModel mutante;
    long idInsert=0;
    long totalRegistros=0;
    long totalADNMutante=0;
    double promedioMutante=0;
    boolean esMutante;

    ArrayList<mutanteModel> listaMutante;

    @Autowired
    MutanteRepository mutanteRepository;

    public ArrayList<mutanteModel> obtenerMutantes(){
        return (ArrayList<mutanteModel>) mutanteRepository.findAll();
    }
    
    public ResponseEntity<String> postAdn(adnModel dnaArr){

        //Valida la secuencia de ADN y retorna true o false
        String[] dna=dnaArr.getDna();
        String stringArray = Arrays.toString(dna);
        ValidaADN validaADN = new ValidaADN();
        esMutante=validaADN.isMutant(dna);

        //Guarda en BD la secuencia ****Falta guardar la cadena en BD
        if (mutanteRepository.findByAdn(stringArray).size()==0){
            mutanteModel newMutante = new mutanteModel();
            newMutante.setAdn(stringArray);
            newMutante.setMutante(esMutante+"");
            mutanteRepository.save(newMutante);
        }
        
        if (esMutante==true){
            return new ResponseEntity<>("mutant: true",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("mutant: false",HttpStatus.FORBIDDEN);
        }
        
    }

    public estadisticasModel estadisticasMutante(){

        totalRegistros=mutanteRepository.count();
        totalADNMutante=mutanteRepository.findByMutante("true").size();
        promedioMutante=Math.pow((double)totalADNMutante/(double)totalRegistros,3);

        estadisticasModel estadistica = new estadisticasModel();
        estadistica.setCount_human_dna(totalRegistros+"");
        estadistica.setCount_mutant_dna(totalADNMutante+"");
        estadistica.setRatio(promedioMutante+"");

        return estadistica;
    }
}
