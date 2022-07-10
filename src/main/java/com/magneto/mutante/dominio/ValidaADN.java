package com.magneto.mutante.dominio;

public class ValidaADN {
    //Variables
    private String [][] mtADN;
    
    //Constructor
    public ValidaADN() {
    }

    //Valda que la estructura sea de NxN
    boolean estructuraADN(String[] adn, int filas) {
        //Variables
        boolean estructuraOk = true;
        int columnas = 0;

        //Valida que todos los String sean igual al tamaño del array
        for (int i = 0; i < filas; i++) {
            columnas = adn[i].length();
            if (columnas != filas) {
                estructuraOk = false;
                break;
            }
        }
        return estructuraOk;
    }

    //Valda que los String contengan las letras (A,T,C,G)
    boolean cadenaADN(String[] adn, int filas) {
        //Variables
        this.mtADN= new String[filas][filas];
        boolean cadenaOk = true;
        int columnas=filas;
        char subADN;
        
        // Valida que la cadena contenga (A,T,G,C)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                subADN = adn[i].charAt(j);
                if (subADN != 'A' && subADN != 'T' && subADN != 'G' && subADN != 'C') {
                    cadenaOk = false;
                    break;
                }else{
                    mtADN[i][j]=Character.toString(subADN);
                }
            }
            if (cadenaOk == false){
                break;
            }
        }
        //isMutant=matrizMutant(adn,col,fil); 

        return cadenaOk;
    }
    
    boolean esMutante(int filas) {
        //Variables
        boolean esMutanteOK = false;
        int cont=0;
        String cadenaBase="";
        String cadenaValidacion="";
        
        //Inicio validacion
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < filas; j++) {
                cadenaBase = mtADN[i][j]+mtADN[i][j]+mtADN[i][j]+mtADN[i][j];
                //Vertical
                if ((i+3)<filas){
                    cadenaValidacion=mtADN[i][j]+mtADN[i+1][j]+mtADN[i+2][j]+mtADN[i+3][j];
                    if (cadenaValidacion.equals(cadenaBase)){
                        cont++;
                    }
                }
                //Horizontal
                if ((j+3)<filas){
                    cadenaValidacion=mtADN[i][j]+mtADN[i][j+1]+mtADN[i][j+2]+mtADN[i][j+3];
                    if (cadenaValidacion.equals(cadenaBase)){
                        cont++;
                    }
                }
                //Oblicua
                if (((i+3)<filas)&&((j+3)<filas)){
                    cadenaValidacion=mtADN[i][j]+mtADN[i][j+1]+mtADN[i][j+2]+mtADN[i][j+3];
                    if (cadenaValidacion.equals(cadenaBase)){
                        cont++;
                    }
                }
            }
            if (cont>1){
                esMutanteOK=true;
                break;
            }
        }
        return esMutanteOK;
    }

    public boolean isMutant(String[] adn) {
        //variables
        int filas = 0;
        boolean ADNCorrecto = true;
        boolean StringCorrecto = true;
        boolean isMutantVar = false;

        //Tamaño del array
        filas = adn.length;

        //Verifica que se pueda obtener una matriz de NxN
        if (filas>=4){
          ADNCorrecto = estructuraADN(adn, filas);  
        }else{
            ADNCorrecto = false;
        }
        //Verifica que cada String contenga los tipos de letras permitidos
        if (ADNCorrecto == true) {
            StringCorrecto =cadenaADN(adn, filas);
        }else{
            System.out.println("La matriz no es NxN o no permite construir secuencias de 4 caracteres");
        }
        //Valida si es mutante
        if (StringCorrecto == true) {
            isMutantVar = esMutante(filas);
        }else{
            System.out.println("Los string contiene letras distintas a (A,T,C,G)");
        }
        

        return isMutantVar;
    }
}
