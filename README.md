Proyecto Final - Study Jam II 2016
===
<div align="center">
    <center>
        <img src="http://developerstudyjams.com/images/masthead.png" width="400px"/>
    </center>
</div>

Repositorio Proyecto Final del Study Jam Android Development for Beginners II 2016, llevado a cabo en la ciudad de La Paz, Bolivia a cargo del <a target="_blank" href="http://www.gdg.androidbolivia.com">GDG Android Bolivia</a> .

Nombre Aplicación.
---
El nombre de la aplicación es: Christmas

Objetivo
---
El objetivo de la aplicacion es  realizar tarjetita de felicitaciones Navideniaspoder a partir de imagenes que se  agregar texto y se las guarda.

Caracteristicas
---
* Selecciona Imagenes que ya tiene cargada.
* las visualiza en la aplicacion.
* Tiene tipo de fuentes  especiales para navidad.
* Se coloca el texto combinando a la imagen seleccionada.
* Guarda la imagen en la memoria del celular en la galeria para poder compartirla a las diferentes redes sociales o aplicaciones que se pueda enviar.

Wireframes
---
Puedes visualizar los Wireframes de este proyecto.

https://ninjamock.com/s/1DJ7G

Compatibilidad
---
Esta aplicación es compatible con versiones de Android 4.4 o superior.

Uso
---------
Para probar este ejemplo clona este repositorio de la siguiente forma:
>
>     $ git clone https://github.com/01agduran/ProyectoFinal_StudyJam.git

Luego de ello dentro de Android Studio:

* File --> New --> Import Project 
* Seleccionas la ruta donde hiciste el `clone` del proyecto.
* Build --> Rebuild Project
* Run 

Corrida previa
---
Aca te muestro cual es el funcionamiento de mi aplicación a grandes rasgos.
<div align="center">
    <center>
        <table border="0">
            <tr>
                <td><img src="/img/gif01.gif" width="250"></td>
                <td><img src="/img/gif02.gif" width="250"></td>
                <td><img src="/img/gif03.gif" width="250"></td>
            </tr>
        </table>
    </center>
</div>
<br>

Descripción técnica
---
En este proyecto de utilizáron los siguientes componentes tanto en el `diseño` como en la `funcionalidad`:

**`Vista:`**
* LinearLayout (Horizontal) para la alineación de las vistas principales.
* RelativeLayout, para el acomodamiento de la segunda pantalla: SegundaActivity.
* Button, para seleccionar , bsucar, cambiar opciones,cambiar de pantallas
* EditTexts, para los titulos  , algunas indicaciones.
* AlertDialog para hacer una lista pequenia de las cosas que necesitamos bsucar.
* Canvas para realizar los graficos.
* Almacenamiento interno en la memoria del celular para poder visualizarlas en la galeria de fotos.
.
.
.

**`Funcionalidad:`**
* Cuenta con la galeria de imagenes para elegir una foto  que se ha guardado.
* Ciclo de un spalsh par que tenga tiempo de cargar los componentes.
.
.
.

Autor(a)
---
Alvaro Duran

Contactos
---
01agduran@gmail.com


