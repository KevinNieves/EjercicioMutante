
package com.magneto.mutante.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magneto.mutante.models.adnModel;
import com.magneto.mutante.models.estadisticasModel;
import com.magneto.mutante.models.mutanteModel;
import com.magneto.mutante.services.MutanteService;

@RestController
public class MutanteController {
    String respuestaPost="";
    
    @Autowired
    MutanteService mutanteService;

    @GetMapping("/Listmutant")
    public ArrayList<mutanteModel>obtenerMutantes(){
        return mutanteService.obtenerMutantes();
    }
    @PostMapping("/mutant/")
    public  ResponseEntity<String> postAdn(@RequestBody adnModel dnaArr){
        return this.mutanteService.postAdn(dnaArr);
    }
    @RequestMapping("/stats")
    public estadisticasModel estadisticasMutante(){
        return  this.mutanteService.estadisticasMutante();
    }
    
}



