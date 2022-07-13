
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
    
    int totalRegistros = 0;
    int totalADNMutante = 0;
    double promedioMutante = 0;
    boolean esMutante;
    
    @Autowired
    MutanteRepository mutanteRepository;
    mutanteModel mutante= new mutanteModel();
    ValidaADN validaADN = new ValidaADN();
    estadisticasModel estadistica = new estadisticasModel();
    
    //Consulta todas las secuencias de ADN registradas
    public ArrayList<mutanteModel> obtenerMutantes() {
        return (ArrayList<mutanteModel>) mutanteRepository.findAll();
    }

    //Valida y guarda las secuencias de ADN 
    public ResponseEntity<String> postAdn(adnModel dnaArr){
        //Inicializa valores 
        esMutante=false;
        
        //Convierte a String
        String stringArray = Arrays.toString(dnaArr.getDna());
        //Consulta en BD si existe el ADN
        ArrayList<mutanteModel> ArrMutanteExistente = mutanteRepository.findByAdn(stringArray);
        int contArrMutanteExistente = ArrMutanteExistente.size();
        
        
        //Si no existe guarda en BD
        if (contArrMutanteExistente==0){
            //llama al metodo que valida la secuencia
            esMutante=validaADN.isMutant(dnaArr.getDna());
            //Guarda
            mutante.setAdn(stringArray);
            mutante.setMutante(esMutante+"");
            mutanteRepository.save(mutante);
        }else{
            //Al ser evaluado con anterioridad se toma el valor registrado en la BD
            esMutante=Boolean.valueOf(ArrMutanteExistente.get(0).getMutante());
        }
        
        if (esMutante==true){
            return new ResponseEntity<>("mutant: true",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("mutant: false",HttpStatus.FORBIDDEN);
        }
        
    }

    //Calcula las estadisticas 
    public estadisticasModel estadisticasMutante() {
        //Inicializa los valores
        totalRegistros = (int)mutanteRepository.count();
        totalADNMutante = mutanteRepository.findByMutante("true").size();
        promedioMutante = (double) totalADNMutante / (double) totalRegistros;

        //Carga el objeto
        estadistica.setCount_human_dna(totalRegistros);
        estadistica.setCount_mutant_dna(totalADNMutante);
        estadistica.setRatio(promedioMutante);

        return estadistica;
    }
}
