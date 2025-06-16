# 📘 Manual de Usuario — CuriousMind

## Índice

1. [¿Qué es CuriousMind?](#qué-es-curiousmind)
2. [Requisitos del sistema](#requisitos-del-sistema)
3. [Instalación y ejecución](#instalación-y-ejecución)
4. [Pantallas principales](#pantallas-principales)
   - [1. Pantalla de Inicio de sesión (Login)](#1-pantalla-de-inicio-de-sesión-login)
   - [2. Registro de usuario (Signup)](#2-registro-de-usuario-signup)
   - [3. Página principal (Home)](#3-página-principal-home)
   - [4. Vista de curso](#4-vista-de-curso)
   - [5. Bloques de contenido y preguntas](#5-bloques-de-contenido-y-preguntas)
   - [6. Perfil y estadísticas](#6-perfil-y-estadísticas)
5. [Cómo crear un curso](#cómo-crear-un-curso)
6. [Preguntas frecuentes](#preguntas-frecuentes)
7. [Contacto y soporte](#contacto-y-soporte)

---

## ¿Qué es CuriousMind?

CuriousMind es una aplicación de escritorio dirigida a todo tipo de público cuyo objetivo es dar servicio a los usuarios para aprender o reforzar contenidos sobre cualquier tipo de ámbito. La aplicaión se estructura en cursos diferentes, que podrán ser cursos por defecto proporcionados por los desarrolladores de CuriousMind, o cursos propios creados por el usuario. 

Esta aplicación es un programa interactivo que hace que aprender idiomas, música, historia o incluso lenguajes de programación sea mucho más visual y entretenido que simplemente leer libros o buscar información online. Cuenta con distintos tipos de preguntas como tipo test, preguntas de rellenar huecos, juegos visuales con flashcards o incluso preguntas de traducción para los idiomas, que harán que el aprendizaje se convierta en algo más dinámico y divertido. 

---

## Requisitos del sistema

- Sistema operativo: (ej. Windows / macOS / Linux)
- Java: Versión XX o superior
- IntelliJ IDEA (si se ejecuta desde código fuente)
- Maven: Versión XX

---

## Instalación y ejecución

1. Clona o descarga el repositorio.
2. Abre el proyecto en IntelliJ IDEA.
3. Ejecuta la clase `App.java`.
4. Se abrirá la interfaz principal de la aplicación.

---

## Pantallas principales

### 1. Pantalla de Inicio de sesión (Login)

La pantalla Login muestra el formulario de inicio de sesión, el cual consta de nombre de usuario (*username*) y contraseña (*password*). Hemos añadido la opción de "mostrar contraseña" para evitar fallos o errores al escribir (*show password*).
Incorpora dos botones diferentes:

   - Log in: Una vez relleno el formulario, se pulsa este botón para acceder al menú principal de la aplicación
   - Sign up: Si es la primera vez que accedemos a CuriousMind, se pulsa este botón para crear nuestra cuenta desde cero. 

📸 Ventana LogIn ![Login screen](Images/login.png)

Como se observa en la imagen, esta ventana incluye además botones "extra" a modo de barra superior:

   - About us: Muestra una ventana de información acerca del equipo de desarrollo de CuriousMind.
   - Contact: Indica las diferentes formas de contactarnos para cualquier tema, ya sea sobre mejoras, sobre dudas...
   - Log in: Este botón indica que nos encontramos en la ventana Log in.

📸 Ventanas About Us y Contact
<p align="center">
  <img src="Images/aboutus.png" width="400"/>
  <img src="Images/contact.png" width="400"/>
</p>



---

### 2. Registro de usuario (Signup)

La ventana Signup muestra el formulario de creación de cuenta, el cual consta de nombre completo (*fullname*), nombre de usuario (*username*), dirección de correo electrónico (*email address*) y contraseña (*password* y *confirm password*). Manteniendo la opcion de "mostrar contraseña", en esta ventana la incorporamos por duplicado, al tener dos campos de contraseña: elegir y confirmar. 
Incorpora dos botones diferentes:

   - Create Account: Una vez relleno el formulario, se pulsa este botón para crear la cuenta almacenándola en la base de datos. Al hacer click sobre este botón, se te redirigirá a la ventana LogIn para iniciar sesión con tus nuevos datos.
   - Log in: Si ya tenías una cuenta, puedes pulsar sobre este botón para volver a la ventana LogIn e iniciar sesión con tus datos. 

📸 Ventana SignUp ![SignUp screen](Images/signup.png)

Mantiene los botones comentados anteriormente de la barra superior, con la ligera diferencia del tercero: 

   - Sign Up: Este botón indica que nos encontramos en la ventana Sign Up.



---

### 3.  Ventana Menu Principal (Home)

La pantalla Home actúa como el menú principal de la aplicación, esta ventana esta dividida en tres secciones principales:

- *My Courses*: Donde aparece el listado de los cursos en los que estamos inscritos teniendo la posibilidad de entrar en ellos clicando o compartirlos.
- *New Course*: Donde se encuentran todos los cursos de la aplicación y tenemos la posibilidad de compartirlos e inscribirnos en ellos.
- *Create New Course*: Este apartado te permite crear un curso a partir de la importación del mismo.
  👉 Para más detalles, consulta [Cómo crear un curso](#3-cómo-crear-un-curso).

Estos botones están acompañados de iconos visuales que facilitan la navegación. El botón Logout <img src="Images/logout.png" width="10"/>, como es habitual, permite cerrar la sesión y te redirige a la ventana de *Log In*.

📸 Ventana Home

---

### 4. Ventana Información de Usuario (UserWindow)

La pantalla Profile muestra la vista de usuario una vez iniciada sesión. Se presenta una bienvenida personalizada con el nombre del usuario (por ejemplo, "Hello Javi44!") junto con el nivel actual del usuario, visible en la parte superior derecha (LEVEL: 0).

En esta ventana se divide la información en dos secciones principales:

**1. Your courses:**  
Aquí se muestran los cursos en los que el usuario está inscrito. En el ejemplo, se presenta el curso de **Alemán (German)** con su respectiva bandera. Incluye también una barra de progreso visual que indica el avance en dicho curso.

**2. Your stats:**  
Estadísticas del uso de la aplicación, distribuidas en cuatro métricas clave:
- 🏆 Best streak: racha de uso más larga (en días).
- 📅 Days of use: número total de días en los que se ha utilizado la app.
- ✅ Completed courses: cantidad de cursos terminados por el usuario.
- ⏱ Time of use: tiempo total de uso acumulado.

Además, incorpora en la parte inferior izquierda un botón de navegación para volver al menú principal con el texto **Home**.

📸 Ventana Profile



### 5.  Ventana Pregunta de Traducción (Translate)

Esta pantalla corresponde a un ejercicio interactivo dentro del curso seleccionado (en este caso, Alemán). Está diseñada para evaluar la comprensión del idioma mediante la traducción de frases.

Elementos principales:

- En la parte superior aparece el idioma actual (German), acompañado de la bandera correspondiente 🇩🇪.
- Sección temática: muestra el tema que se está trabajando, en este caso **Food Vocabulary**.
- Instrucción del ejercicio: "Translate to English".
- Frase en español a traducir: **"El perro es azul"**.
- Zona de respuesta: área donde el usuario debe construir la frase correcta en inglés seleccionando palabras entre múltiples opciones. Algunas son útiles (is, blue, dog), otras son distractores (iron, pencil, cat…).
- Botón **Submit** al final para comprobar si la respuesta es correcta.

En la parte inferior izquierda también se encuentra el botón **Exit**, que permite salir del ejercicio.

📸 Ventana Translate to English

### 6.  Ventana Pregunta de Rellenar Huecos (FillTheGap)

Esta pantalla pertenece a una de las actividades interactivas del curso de idiomas seleccionado (en este caso, Alemán 🇩🇪), dentro del tema **Food Vocabulary**. El objetivo del ejercicio es completar correctamente una oración en inglés introduciendo la palabra que falta.

Elementos principales de la interfaz:

- En la parte superior se muestra claramente el idioma del curso activo (**German**) junto a su bandera.
- Se indica el tipo de ejercicio con la instrucción: **"Complete the sentence"**.
- A continuación aparece la frase incompleta:  
  **"The ___ is the satellite of the Earth."**
- Debajo se encuentra una caja de texto vacía donde el usuario debe escribir la palabra que falta (en este caso, "moon").
- Al final del ejercicio hay un botón **Submit** que el usuario pulsa para enviar su respuesta y verificar si es correcta.

Como es habitual, en la parte inferior izquierda está disponible el botón **Exit**, que permite abandonar el ejercicio actual y volver al menú.

📸 Ventana Complete the sentence

### 7.  Ventana Pregunta de Selección (FlashCard)

Esta ventana forma parte de las actividades interactivas del módulo de vocabulario del idioma activo (en este caso, Alemán 🇩🇪), dentro del tema **Food Vocabulary**. El objetivo principal es identificar correctamente una palabra del vocabulario presentada en forma de imagen.

Elementos destacados:

- En la parte superior se muestra el idioma seleccionado (**German**) con su respectiva bandera, además del nombre del tema ("Food Vocabulary").
- Se indica la instrucción del ejercicio: **"Choose the correct answer:"**, seguida de la pregunta:  
  **"Where is the onion?"**
- Debajo aparecen tres imágenes correspondientes a distintas palabras del vocabulario:
  - 🍎 Apfel (manzana)
  - 🥕 Karotte (zanahoria)
  - 🧅 Zwiebel (cebolla)
- El usuario debe seleccionar la imagen que representa la palabra correcta (en este caso, "Zwiebel" para cebolla).
- Tras la selección, se pulsa el botón **Submit** para validar la respuesta y continuar.

En la parte inferior izquierda se mantiene visible el botón **Exit**, que permite salir del ejercicio en cualquier momento.

📸 Ventana Choose the correct answer

### 8.  Ventana Pregunta tipo Test (Test)


## Funcionalidad de la Aplicación

### 1. Cómo crear una cuenta

Para registrarte en CuriousMind y comenzar a utilizar la aplicación, sigue estos pasos:

1. Abre la aplicación y dirígete a la pantalla de **Sign Up**.  
2. Rellena los siguientes campos obligatorios:
   - Tu **nombre completo**  
   - Tu **nombre de usuario**  
   - Tu **correo electrónico**  
   - Tu **contraseña**  
   - Vuelve a introducir la **contraseña** para confirmarla (debe coincidir con la anterior).  
3. Pulsa el botón **"Sign Up"**.

📸 Ventana SignUp ![SignUp screen](Images/signupFilled.png)

Una vez completado el registro, serás redirigido automáticamente a la pantalla de **Login** para que introduzcas tus credenciales y accedas a la aplicación.

---

### 2. Cómo Iniciar Sesión

Para acceder a tu cuenta en CuriousMind, sigue estos pasos:

1. Abre la aplicación.  
2. En la pantalla de **Login**, introduce tu **nombre de usuario** y tu **contraseña** en los campos correspondientes.  
3. Pulsa el botón **"Login"** para entrar en la aplicación.  

📸 Ventanas Log in (con y sin contraseña oculta)
<p align="center">
  <img src="Images/loginFilled.png" width="500"/>
  <img src="Images/loginFilledPassw.png" width="500"/>
</p>

Una vez iniciado sesión correctamente, accederás a la ventana **Home**, donde podrás ver tus cursos, crear nuevos o inscribirte en los existentes.

---


### 3. Cómo crear un curso

Para crear un curso, CuriousMind te da la posibilidad de importar un archivo `.json` (con el curso en cuestión)  
desde tu explorador de archivos. Para ello:

1. Dirígete a la ventana **Home** (explicada en el apartado correspondiente).  
2. Pulsa el botón **"+"** que se encuentra en la parte inferior, en la sección *"Create your Course"*.  

📸 Botón Importar Curso ![Import screen](Images/importCourse.png)

3. Se abrirá una ventana como la siguiente:  

📸 Importar Curso ![Json screen](Images/jsonchooser.png)

4. Añade el archivo `.json` y pulsa **Accept**.  

Una vez completados estos pasos, la aplicación creará el nuevo curso y lo mostrará en la sección *"New Course"*.  

---

### 4. Cómo compartir un curso

Para compartir un curso, CuriousMind te da la posibilidad de guardar un archivo `.json` (con el curso en cuestión)  
en tu explorador de archivos. Para ello:

1. Dirígete a la ventana **Home** (explicada en el apartado correspondiente) o la ventana de Información de Usuario.  
2. Pulsa el botón <img src="Images/share.png" width="10"/> que se encuentra a la derecha de cada botón de curso.  

📸 Botón Compartir ![Share screen](Images/ShareButton.png) 

3. Se abrirá una ventana con tu explorador de archivos y podrás guardar el fichero con el curso.  
4. Elige la carpeta destino y pulsa de **Guardar**.  

Una vez completados estos pasos, el curso estará guardado en tu sistema local y podrás compartirlo con otros usuarios.

---

### 5. Cómo inscribirse en un curso

Para incribirte en un curso al que no te has inscrito ya, CuriousMind te da la posibilidad de elegir entre los cursos  
ya creados en la aplicación y participar en ellos. Para ello:

1. Dirígete a la ventana **Home** (explicada en el apartado correspondiente).  
2. Pulsa el botón en cualquiera de los botones de cursos que se encuentran en la sección *"New Course"*.  

📸 Botón de Curso ![CourseButton screen](Images/CourseButton.png)

3. Se abrirá una ventana como la siguiente en la cual debemos escoger la estrategia que queremos seguir en el curso:  

📸 Ventana Estrategia ![Login screen](Images/strategy.png)
 

   Las estrategias sirven para personalizar la forma de aparición de las preguntas, entre ellas tenemos:
   - **Secuencial**: donde las preguntas aparecen en orden normal una detrás de otra.  
   - **Aleatorio**: las preguntas aparecen desordenadas cada vez que entras a los bloques de contenidos del curso.  
   - **Repetición Espaciada**: cada 3 preguntas, la primera de esas tres se repite para afianzar los conocimientos.

4. Una vez elegida la estrategia, pulsa el botón **Accept** y serás redirigido a la ventana de Home.

📸 Estrategia Seleccionada ![Login screen](Images/strategySelected.png) 

Una vez completados estos pasos, el curso estará accesible en la sección *"My Courses"* tanto en la ventana Home como en la ventana de Usuario.

---

## Preguntas frecuentes

**¿Puedo editar un curso después de crearlo?**  
(Sí / No / En desarrollo...)

**¿Puedo eliminar mi cuenta?**  
(Sí, desde el perfil...)

**¿La app guarda mi progreso automáticamente?**  
(Sí / No...)

**¿Puedo volver a inscribirme en un curso del que ya estoy inscrito?**  
No, una vez que te has inscrito en un curso, este aparece en la sección *"Mis Cursos"* y no es posible volver a inscribirse nuevamente.  


---

## Contacto y soporte

Para cualquier duda, sugerencia o reporte de errores, puedes ponerte en contacto con el equipo de desarrollo escribiendo a:  
📧 CuriousMind@gmail.com

---

