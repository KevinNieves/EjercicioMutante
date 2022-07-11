package com.magneto.mutante;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.magneto.mutante.dominio.ValidaADN;
import com.magneto.mutante.models.adnModel;
import com.magneto.mutante.models.mutanteModel;
import com.magneto.mutante.services.MutanteService;

@SpringBootTest
class MutanteApplicationTests {
	
	@Autowired
	MutanteService Repo = new MutanteService();
    
	@Test
	public void ReportStats() {
		assertNotNull(Repo.estadisticasMutante().getRatio());
	}

	@Test
	public void ReportAllMutant() {
		assertNotNull(Repo.obtenerMutantes().get(0).getId());
	}
	
	@Test
	public void ValidarinsertarMutante() {
		String []dna={"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		adnModel dnaArr=new adnModel();
		dnaArr.setDna(dna);
		assertEquals(Repo.postAdn(dnaArr).getStatusCode(),HttpStatus.OK);
	}
    
	@Test
	public void ValidarADN() {
		String []dna={"TTGCTA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		ValidaADN validaADN = new ValidaADN();
        boolean esMutante=validaADN.isMutant(dna);
		assertFalse(esMutante);
	}
	@Test
	public void ValidarMutanteModel() {
		mutanteModel mutante=new mutanteModel();
		mutante.setId(1);
		mutante.setAdn("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
		mutante.setMutante("true");
		assertNotNull(mutante);
	}

}
