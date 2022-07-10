
package com.magneto.mutante.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.magneto.mutante.models.mutanteModel;

@Repository
public interface MutanteRepository extends CrudRepository<mutanteModel, Long> {
    public abstract ArrayList<mutanteModel> findByMutante(String mutante);
    public abstract ArrayList<mutanteModel> findByAdn(String adn);
}


