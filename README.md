# Magneto Recluta mutantes

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-men y para ello me ha pedido que le ayude a desarrollar un programa que analice las secuencias de ADN y determine si un humano es mutante o no. 

**Consideraciones generales:**
1)	La secuencia de ADN esta compuesta por un array de (NxN).
2)	Las letras de los Strings solo pueden ser: (A,T,C,G).
3)	Un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.

## Instrucciones de uso:

**1)Servicio /stats**
    Acceder a: https://mutantes-355503.uc.r.appspot.com/stats  
    
**2)Servicio /mutant/**
    Desde un cliente Api Rest, a través un HTTP POST acceder  a https://mutantes-355503.uc.r.appspot.com/mutant/

   2.1 Requiere de un Json con la siguiente estructura:
   {"dna":["TTGC","CAGT","TTAT","AGAA"]}
   2.2 Ejemplo desde Postman
   ![HTTP POST en Postman](https://github.com/KevinNieves/EjercicioMutante/blob/main/src/main/resources/img/Postman.png)

## Test-Automáticos
Code coverage - informe de Jacoco.<br />
![Informe Jacoco](https://github.com/KevinNieves/EjercicioMutante/blob/main/src/main/resources/img/informe_jacoco.png)

## Instrucciones de ejecución API
1)	Descargar los archivos del repositorio a un directorio local 
2)	Actualizar las variables de conexión a base de datos MySQL.
![Parametros BD MySQL](https://github.com/KevinNieves/EjercicioMutante/blob/main/src/main/resources/img/BD.png)

3)	Accedemos a la terminal, nos ubicamos en la ruta del proyecto y ejecutamos el comando mvn spring-boot:run
4)	Para ejecuciones de test automáticos ejecutar el comando mvn test
5)	Mientras la aplicación esté corriendo (Punto 3) se puede acceder a los servicios: <br />
    http://localhost:8080/stats <br />
    http://localhost:8080/mutant/


