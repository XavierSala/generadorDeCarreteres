# generadorDeCarreteres
El programa es fa servir per generar diferents resultats sobre les carreteres 
de l'exercici del mòdul 6 de programació de DAW. Que va sobre l'accés a base de dades 

## Exercici

[Exercici](https://uf.ctrl-alt-d.net/material/mostra/453/rectilandia-el-29e-estat-de-la-unio-europea)

Una cosa que molta gent no sap és que la Unió Europea en comptes de tenir 28 estats en té 29. El que passa és que un dels Estats, Rectilàndia,  no el té en compte ningú perquè no envien cap representant al Parlament Europeu des de que van saber que els estels de la bandera formen una “rodona”

![Europa](https://raw.githubusercontent.com/utrescu/PaginaPersonal/master/static/images/rectxit1.png)

A Rectilàndia odien profundament les corbes i per això es gasten totes les subvencions de la Unió Europea en destruir les antigues carreteres entre els seus pobles i ciutats i crear-ne de noves que siguin completament rectes.

![Europa](https://raw.githubusercontent.com/utrescu/PaginaPersonal/master/static/images/rectxit2.png)

El problema és que ara la Unió Europea es nega a donar-los subvencions per fer més carreteres i camins. Ara, la Unió Europera només els dóna subvencions per informatitzar coses … 

Com que els dirigents de Rectilàndia saben com funciona la Unió Europea,  han pensat que amb la subvenció d’informatització poden fer un petit programa d’ordinador i amb la resta de diners en faran més carreteres rectes. El programa ha d’agafar una ciutat d’origen i una de destí i dir per quins pobles es passarà per anar-hi

![Rectilàndia](https://raw.githubusercontent.com/utrescu/PaginaPersonal/master/static/images/rectxit3.png)

Exemple: 

    Anar de Bellcargolada a Castellbrú de Dalt

    Resultat: 

      Bellcargolada -> Santa Teresa de Dalt -> Vilallisa del Rosco -> Castellbrú de Dalt

    (tot i que sovint hi haurà més d’un camí, amb un dels camins 
     en tenen prou, no sigui que gastin massa)

Us proporcionen una base de dades Postresql amb els enllaços actuals de les carreteres (i els km que separen cada una de les ciutats) [Descarregar](https://drive.google.com/file/d/1TRTG9enU6YX3Dpr1ekviCz1WZ7oYxB_W/view?usp=sharing)

### Tasca

1. Desenvolupeu un programa que faci el que demanen els dirigents de Rectilàndia

    * **BONUS OPCIONAL**: Feu que el programa doni el camí més curt


### Estructura de la base de dades

![BDD](https://raw.githubusercontent.com/utrescu/PaginaPersonal/master/static/images/rectxit4.png)

## El programa

El programa fa servir la llibreria de generació aleatòria de Pobles per generar dinàmicament diferents pobles
i rutes entre els pobles.

Per ara cal canviar les constants del programa `App.java` per poder canviar-ne les opcions (però algun dia
ho canviaré, o no)

~~~~~~~~~~~~~~~~java
    // Número de pobles que s'han de generar
    private static final int NUMERODEPOBLES = 12;

    // Número mínim de veins que tindrà cada poble
    private static final int MINVEINS = 2;
    // Número màxim de veins
    private static final int MAXVEINS = 4;

    // Per quan sembla que no té més possibles opcions de veins
    private static final int MAXINTENTS = 10;

    // Número màxim de Km entre ciutats
    private static final int MAXKM = 6;

    // S'han de desar les dades a la base de dades
    private static final boolean SAVEDATABASE = false;    
	  private static final String DATABASE_JDBC = "jdbc:postgresql://localhost/ciutats";
	  private static final String DATABASE_USER = "postgres";
	  private static final String DATABASE_PASSWORD = "ies2010";
~~~~~~~~~~~~~~~~

### Què fa el programa?

#### Mostra el graf generat

El programa treu una pantalla gràfica amb els pobles i camins aleatòris generats en forma de graf

![Exemple1](imatges/exemple1.png)

![Exemple2](imatges/exemple2.png)

Notes: 
* L'aspecte es pot personalitzar canviant el CSS
* En les carreteres s'hi ha afegit el pes d'anar d'un poble a un altre per poder fer el programa més complexe.

#### Desa les dades en una base de dades Postgresql

En cas de canviar la constant `SAVEDATABASE` a `true` el generat es guardarà en una base de dades segons les característiques definides en les constants `DATABASE`

~~~ java
	  private static final String DATABASE_JDBC = "jdbc:postgresql://localhost/ciutats";
	  private static final String DATABASE_USER = "postgres";
	  private static final String DATABASE_PASSWORD = "ies2010";
~~~

En el programa només hi he definit el driver JDBC de Postgres o sigui que si es canvia s'haurà d'afegir el
nou driver.

![BDD](https://raw.githubusercontent.com/utrescu/PaginaPersonal/master/static/images/rectxit4.png)


## TODO 

* Entrada de dades des de la 
* Permetre personalitzar la base de dades generada
* ...
