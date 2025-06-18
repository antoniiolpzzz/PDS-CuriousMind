# 📘 Manual de Usuario — CuriousMind

## Índice

1. [¿Qué es CuriousMind?](#qué-es-curiousmind)
2. [Requisitos para el funcionamiento](#requisitos-para-el-funcionamiento)
3. [Instalación y ejecución](#instalación-y-ejecución)
4. [Pantallas principales](#pantallas-principales)
   - [1. Pantalla de Inicio de sesión (Login)](#1-pantalla-de-inicio-de-sesión-login)
   - [2. Registro de usuario (Signup)](#2-registro-de-usuario-signup)
   - [3. Ventana Menu Principal (Home)](#3--ventana-menu-principal-home)
   - [4. Ventana Información de Usuario (UserWindow)](#4-ventana-información-de-usuario-userwindow)
   - [5. Ventana Principal del Curso (CourseDashboard)](#5-ventana-principal-del-curso-coursedashboard)
   - [6. Ventana Pregunta de Traducción (Translate)](#6--ventana-pregunta-de-traducción-translate)
   - [7. Ventana Pregunta de Rellenar Huecos (FillTheGap)](#7--ventana-pregunta-de-rellenar-huecos-fillthegap)
   - [8. Ventana Pregunta de Selección (FlashCard)](#8--ventana-pregunta-de-selección-flashcard)
   - [9. Ventana Pregunta tipo Test (Test)](#9--ventana-pregunta-tipo-test-test)
5. [Funcionalidad de la Aplicación](#funcionalidad-de-la-aplicación)
   - [1. Cómo crear una cuenta](#1-cómo-crear-una-cuenta)
   - [2. Cómo Iniciar Sesión](#2-cómo-iniciar-sesión)
   - [3. Cómo crear un curso](#3-cómo-crear-un-curso)
   - [4. Cómo compartir un curso](#4-cómo-compartir-un-curso)
   - [5. Cómo inscribirse en un curso](#5-cómo-inscribirse-en-un-curso)
7. [Funcionamiento de los Cursos](#funcionamiento-de-los-cursos)
8. [Pruebas y Testing ](#pruebas-y-testing)
9. [Preguntas frecuentes](#preguntas-frecuentes)
10. [Contacto y soporte](#contacto-y-soporte)

---

## ¿Qué es CuriousMind?

CuriousMind es una aplicación de escritorio dirigida a todo tipo de público cuyo objetivo es dar servicio a los usuarios para aprender o reforzar contenidos sobre cualquier tipo de ámbito. La aplicaión se estructura en cursos diferentes, que podrán ser cursos por defecto proporcionados por los desarrolladores de CuriousMind, o cursos propios creados por el usuario. 

Esta aplicación es un programa interactivo que hace que aprender idiomas, música, historia o incluso lenguajes de programación sea mucho más visual y entretenido que simplemente leer libros o buscar información online. Cuenta con distintos tipos de preguntas como tipo test, preguntas de rellenar huecos, juegos visuales con flashcards o incluso preguntas de traducción para los idiomas, que harán que el aprendizaje se convierta en algo más dinámico y divertido. 

---

## Requisitos para el funcionamiento

- Sistema operativo: (ej. Windows / macOS / Linux)
- Versión Java: 21
- IntelliJ IDEA (si se ejecuta desde código fuente)
- Versión Maven: 3.x y 4.x

---

## Instalación y ejecución 

//TODO

---

## Pantallas principales

### 1. Pantalla de Inicio de sesión (Login)

La pantalla **Login** muestra el formulario de inicio de sesión, el cual consta de nombre de usuario (*username*) y contraseña (*password*). Hemos añadido la opción de "mostrar contraseña" para evitar fallos o errores al escribir (*show password*).
Incorpora dos botones diferentes:

   - Log in: Una vez relleno el formulario, se pulsa este botón para acceder al menú principal de la aplicación
   - Sign up: Si es la primera vez que accedemos a CuriousMind, se pulsa este botón para crear nuestra cuenta desde cero. 

📸 Ventana LogIn ![Login screen](Images/UserManual/login.png)

Como se observa en la imagen, esta ventana incluye además botones "extra" a modo de barra superior:

   - About us: Muestra una ventana de información acerca del equipo de desarrollo de CuriousMind.
   - Contact: Indica las diferentes formas de contactarnos para cualquier tema, ya sea sobre mejoras, sobre dudas...
   - Log in: Este botón indica que nos encontramos en la ventana Log in.

📸 Ventanas About Us y Contact
<p align="center">
  <img src="Images/UserManual/aboutus.png" width="400"/>
  <img src="Images/UserManual/contact.png" width="400"/>
</p>



---

### 2. Registro de usuario (Signup)

La ventana **Signup** muestra el formulario de creación de cuenta, el cual consta de nombre completo (*fullname*), nombre de usuario (*username*), dirección de correo electrónico (*email address*) y contraseña (*password* y *confirm password*). Manteniendo la opcion de "mostrar contraseña", en esta ventana la incorporamos por duplicado, al tener dos campos de contraseña: elegir y confirmar. 
Incorpora dos botones diferentes:

   - Create Account: Una vez relleno el formulario, se pulsa este botón para crear la cuenta almacenándola en la base de datos. Al hacer click sobre este botón, se te redirigirá a la ventana LogIn para iniciar sesión con tus nuevos datos.
   - Log in: Si ya tenías una cuenta, puedes pulsar sobre este botón para volver a la ventana LogIn e iniciar sesión con tus datos. 

📸 Ventana SignUp ![SignUp screen](Images/UserManual/signup.png)

Mantiene los botones comentados anteriormente de la barra superior, con la ligera diferencia del tercero: 

   - Sign Up: Este botón indica que nos encontramos en la ventana Sign Up.


---

### 3.  Ventana Menu Principal (Home)

La pantalla **Home** actúa como el menú principal de la aplicación, estando esta dividida en tres secciones principales:

- *My Courses*: Donde aparece el listado de los cursos en los que estamos inscritos teniendo la posibilidad de entrar en ellos clicando o compartirlos. 
- *New Course*: Donde se encuentran todos los cursos de la aplicación y tenemos la posibilidad de compartirlos e inscribirnos en ellos.
- *Create New Course*: Este apartado te permite crear un curso a partir de la importación del mismo.

👉 Para más detalles, consulta [Cómo crear un curso](#3-cómo-crear-un-curso).

👉 Para más detalles, consulta [Cómo compartir un curso](#4-cómo-compartir-un-curso).

  📸 Ventana Home ![Home screen](Images/UserManual/home.png)

Por otro lado, pulsando en la foto de perfil o en el nombre del usuario seremos redirigidos a la pantalla de *Información de Usuario*.
El botón *Logout* <img src="Images/UserManual/logout.png" width="15"/>, como es habitual, permite cerrar la sesión y te redirige a la ventana de *Log In*.

  📸 Ventana Home Botón de User ![Home screen](Images/UserManual/homeUserButton.png)

---

### 4. Ventana Información de Usuario (UserWindow)

La pantalla de **UserWindow** muestra información de usuario una vez iniciada sesión. Se presenta una bienvenida personalizada con el nombre del usuario junto con el nivel actual del usuario.

En esta ventana se divide la información en dos secciones principales:

**1. Your courses:**  
Aquí se muestran los cursos en los que el usuario está inscrito. Incluye también una barra de progreso visual que indica el avance en dicho curso.

**2. Your stats:**  
Estadísticas del uso de la aplicación, distribuidas en cuatro métricas clave:
- 🏆 Best streak: racha de uso más larga (en días).
- 📅 Days of use: número total de días en los que se ha utilizado la app.
- ✅ Completed courses: cantidad de cursos terminados por el usuario.
- ⏱ Time of use: tiempo total de uso acumulado en minutos.

Además, incorpora en la parte inferior izquierda un botón de navegación para volver al menú principal con el texto **Home** <img src="Images/UserManual/homeButton.png" width="15" height="15"/>.

  📸 Ventana Información de Usuario ![UserWindow screen](Images/UserManual/userWindow.png)

---

### 5. Ventana Principal del Curso (CourseDashboard)

La pantalla principal de cada curso tiene una cabecera con el título y la imagen del mismo así como la lista de sus bloques de contenidos.
Cabe destacar que encontraremos de color verde los bloques de contenidos del curso que ya hayan sido completados por el usuario.
Esta diferenciación de color mencionada con anterioridad se debe a que el usuario no puede repetir un bloque de contenidos que ya ha sido completado.

📸 Ventana Home del Curso ![CourseDashboard screen](Images/UserManual/CourseDashboard.png) 

También incluye el botón **Home** <img src="Images/UserManual/homeButton.png" width="15" height="15"/>.

---

### 6.  Ventana Pregunta

Esta pantalla corresponde a un ejercicio interactivo dentro del curso seleccionado. Puede ser de varios tipos, como **Traducción**, **Fill the Gaps**, **Test** o **Flash Cards**.

---

**1. Ventana Pregunta de Traducción**

El objetivo principal es que el usuario traduzca una frase con palabras dadas como opciones.

📸 Ventana Pregunta Traducción ![Translate screen](Images/UserManual/translate.png)

Elementos principales:

- En la parte superior aparece el idioma actual (German), acompañado de la bandera correspondiente.
- Sección temática: muestra el tema que se está trabajando, es decir, el nombre del bloque de contenidos seleccionado.
- Enunciado e indicación de lo que hay que hacer.
- Zona de respuesta: área donde el usuario debe construir la frase correcta en seleccionando palabras entre múltiples opciones.

   📸 Ventana Pregunta Traducción ![Translate screen](Images/UserManual/translateAnswer.png)
  
- Botón **Submit** al final para comprobar si la respuesta es correcta o no.

📸 Ventanas Fallo y Acierto
<p align="center">
  <img src="Images/UserManual/failTranslate.png" height="200" style="vertical-align: middle;"/>
  <img src="Images/UserManual/correct.png" height="200" style="vertical-align: middle;"/>
</p>


En la parte inferior izquierda también se encuentra el botón **Exit** <img src="Images/UserManual/logout.png" width="10"/>, que permite salir del ejercicio y volver al menú principal de un curso.

---

**2. Ventana Pregunta de Rellenar Huecos (FillTheGap)**

El objetivo del ejercicio es completar correctamente una oración en inglés introduciendo la palabra que falta.

📸 Ventana Pregunta Rellenar Huecos ![FillTheGap screen](Images/UserManual/fill.png)

Elementos principales de la interfaz:

- En la parte superior aparece el idioma actual (German), acompañado de la bandera correspondiente.
- Sección temática: muestra el tema que se está trabajando, es decir, el nombre del bloque de contenidos seleccionado.
- Enunciado e indicación de lo que hay que hacer.
- Debajo se encuentra una caja de texto vacía donde el usuario debe escribir la palabra que falta.

📸 Ventana Pregunta Rellenar Huecos ![FillTheGap screen](Images/UserManual/fillAnswer.png)

- Botón **Submit** al final para comprobar si la respuesta es correcta o no.


📸 Ventanas Fallo y Acierto
<p align="center">
  <img src="Images/UserManual/failFill.png" height="200" style="vertical-align: middle;"/>
  <img src="Images/UserManual/correct.png" height="200" style="vertical-align: middle;"/>
</p>


En la parte inferior izquierda también se encuentra el botón **Exit** <img src="Images/UserManual/logout.png" width="10"/>, que permite salir del ejercicio y volver al menú principal de un curso.

---

**3. Ventana Pregunta de Selección (FlashCard)**

El objetivo principal es identificar correctamente una palabra del vocabulario presentada en forma de imagen.

📸 Ventana Pregunta Flashcard ![Flashcard screen](Images/UserManual/flashCard.png)

Elementos destacados:

- En la parte superior aparece el idioma actual (German), acompañado de la bandera correspondiente.
- Sección temática: muestra el tema que se está trabajando, es decir, el nombre del bloque de contenidos seleccionado.
- Enunciado e indicación de lo que hay que hacer.
- Debajo aparecen tres imágenes correspondientes con sus correspondientes nombres.
- El usuario debe seleccionar la imagen que representa la palabra correcta.

📸 Ventana Pregunta Flashcard ![Flashcard screen](Images/UserManual/flashCardAnswer.png)
  
- Botón **Submit** al final para comprobar si la respuesta es correcta o no.

📸 Ventanas Fallo y Acierto
<p align="center">
  <img src="Images/UserManual/failFlashCard.png" height="200" style="vertical-align: middle;"/>
  <img src="Images/UserManual/correct.png" height="200" style="vertical-align: middle;"/>
</p>

En la parte inferior izquierda también se encuentra el botón **Exit** <img src="Images/UserManual/logout.png" width="10"/>, que permite salir del ejercicio y volver al menú principal de un curso.

---

**4. Ventana Pregunta tipo Test (Test)**

El objetivo principal es que el usuario responda una pregunta tipo test seleccionando la opción correcta.

📸 Ventana Pregunta Test ![Test screen](Images/UserManual/test.png)

Elementos destacados:

- En la parte superior aparece el idioma actual (German), acompañado de la bandera correspondiente.
- Sección temática: muestra el tema que se está trabajando, es decir, el nombre del bloque de contenidos seleccionado.
- Enunciado e indicación de lo que hay que hacer.
- Debajo aparecen las opciones de respuesta de entre las cuales el usuario debe seleccionar la respuesta correcta.

📸 Ventana Pregunta Test ![Test screen](Images/UserManual/testAnswer.png)
  
- Botón **Submit** al final para comprobar si la respuesta es correcta o no.

📸 Ventanas Fallo y Acierto
<p align="center">
  <img src="Images/UserManual/failTest.png" height="200" style="vertical-align: middle;"/>
  <img src="Images/UserManual/correct.png" height="200" style="vertical-align: middle;"/>
</p>

En la parte inferior izquierda también se encuentra el botón **Exit** <img src="Images/UserManual/logout.png" width="10"/>, que permite salir del ejercicio y volver al menú principal de un curso.

---

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

📸 Ventana SignUp ![SignUp screen](Images/UserManual/signupFilled.png)

Una vez completado el registro, serás redirigido automáticamente a la pantalla de **Login** para que introduzcas tus credenciales y accedas a la aplicación.

---

### 2. Cómo Iniciar Sesión

Para acceder a tu cuenta en CuriousMind, sigue estos pasos:

1. Abre la aplicación.  
2. En la pantalla de **Login**, introduce tu **nombre de usuario** y tu **contraseña** en los campos correspondientes.  
3. Pulsa el botón **"Login"** para entrar en la aplicación.  

📸 Ventanas Log in (con y sin contraseña oculta)
<p align="center">
  <img src="Images/UserManual/loginFilled.png" width="500"/>
  <img src="Images/UserManual/loginFilledPassw.png" width="500"/>
</p>

Una vez iniciado sesión correctamente, accederás a la ventana **Home**, donde podrás ver tus cursos, crear nuevos o inscribirte en los existentes.

---


### 3. Cómo crear un curso

Para crear un curso, CuriousMind te da la posibilidad de importar un archivo `.json` (con el curso en cuestión)  
desde tu explorador de archivos. Para ello:

1. Dirígete a la ventana **Home**.  
2. Pulsa el botón **"+"** que se encuentra en la parte inferior, en la sección *"Create your Course"*.  

📸 Botón Importar Curso ![Import screen](Images/UserManual/importCourse.png)

3. Se abrirá una ventana como la siguiente:  

📸 Importar Curso ![Json screen](Images/UserManual/jsonchooser.png)

4. Añade el archivo `.json` y pulsa **Accept**.  

Una vez completados estos pasos, la aplicación creará el nuevo curso y lo mostrará en la sección *"New Course"*.  

---

### 4. Cómo compartir un curso

Para compartir un curso, CuriousMind te da la posibilidad de guardar un archivo `.json` (con el curso en cuestión)  
en tu explorador de archivos. Para ello:

1. Dirígete a la ventana **Home** o la ventana de Información de Usuario.  
2. Pulsa el botón <img src="Images/UserManual/share.png" width="10"/> que se encuentra a la derecha de cada botón de curso.  

📸 Botón Compartir ![Share screen](Images/UserManual/ShareButton.png) 

3. Se abrirá una ventana con tu explorador de archivos y podrás guardar el fichero con el curso.  
4. Elige la carpeta destino y pulsa de **Guardar**.  

Una vez completados estos pasos, el curso estará guardado en tu sistema local y podrás compartirlo con otros usuarios.

---

### 5. Cómo inscribirse en un curso

Para incribirte en un curso al que no te has inscrito ya, CuriousMind te da la posibilidad de elegir entre los cursos  
ya creados en la aplicación y participar en ellos. Para ello:

1. Dirígete a la ventana **Home**.
2. Pulsa el botón en cualquiera de los botones de cursos que se encuentran en la sección *"New Course"*.  

📸 Botón de Curso ![CourseButton screen](Images/UserManual/CourseButton.png)

3. Se abrirá una ventana como la siguiente en la cual debemos escoger la estrategia que queremos seguir en el curso:  

📸 Ventana Estrategia ![Login screen](Images/UserManual/Strategy.png)  
 

   Las estrategias sirven para personalizar la forma de aparición de las preguntas, entre ellas tenemos:
   - **Secuencial**: donde las preguntas aparecen en orden normal una detrás de otra.  
   - **Aleatorio**: las preguntas aparecen desordenadas cada vez que entras a los bloques de contenidos del curso.  
   - **Repetición Espaciada**: cada 3 preguntas, la primera de esas tres se repite para afianzar los conocimientos.

4. Una vez elegida la estrategia, pulsa el botón **Accept** y serás redirigido a la ventana de Home.

📸 Estrategia Seleccionada ![Login screen](Images/UserManual/selectedStrategy.png) 

Una vez completados estos pasos, el curso estará accesible en la sección *"My Courses"* tanto en la ventana Home como en la ventana de Usuario.

## Funcionamiento de los Cursos

### 6. Cómo completar un curso

Una vez te hayas inscrito en un curso, ¡es hora de completarlo! ✅ Para ello, necesitas superar **todos los bloques de contenido** que componen el curso y responder correctamente a **todas las preguntas** de cada bloque.

Para completar un curso, sigue estos pasos:

1. Dirígete a la ventana **Home** o a la ventana **UserWindow**.
2. En la sección *"My Courses"*, pulsa el botón del curso que quieras continuar.  

📸 Curso Inscrito  
![MyCourseButton screen](Images/UserManual/MyCourseButton.png)

3. Serás llevado a la ventana del curso (**CourseDashboard**), donde podrás ver todos los bloques de contenido disponibles.  
Pulsa en uno de ellos para comenzar.  

📸 Bloques de Contenido  
![ContentBlock screen](Images/UserManual/CourseDashboard.png) 

---

### ⛔ Sistema de vidas

Al iniciar un bloque, comenzarás con **5 vidas ❤️❤️❤️❤️❤️**. Esto significa que puedes fallar hasta **4 preguntas**.  
- Si fallas una quinta ❌, serás **expulsado** del bloque y tendrás que **empezar desde el principio** de ese bloque.
- Además, las preguntas que falles mientras completas un bloque serán **repetidas al final** del mismo para afianzar contenidos.

📌 *¡Ten cuidado! Cada fallo cuenta.*

---

### 🔄 Salirte a mitad de un bloque

Si decides salir del bloque antes de terminarlo (cerrando la app o saliendo al menú del curso):  
- **Perderás el progreso actual**, y **cuando vuelvas a entrar a ese bloque empezarás de cero**.  

🔁 *Es mejor terminar lo que empiezas antes de salir del bloque.*

---

### 🧠 Tipos de preguntas y orden

- El **tipo de pregunta** dentro de cada bloque es **aleatorio**: puede tocarte traducir, rellenar huecos, elegir la tarjeta Flashcard, o resolver un test.  
- El **orden de las preguntas** depende de la **estrategia que elegiste** al inscribirte en el curso:
  - **Secuencial** ➡️ preguntas en orden.
  - **Aleatorio** 🔀 ➡️ cada intento tiene orden distinto.
  - **Repetición Espaciada** ♻️ ➡️ se refuerzan conocimientos repitiendo preguntas.

---

### 🎯 Puntos, dificultad y niveles

Cada bloque tiene una **dificultad** asignada (fácil, medio, difícil).  
- Al completar un bloque, recibirás **puntos** en función de su dificultad:
  - Fácil ⭐ → 100 puntos
  - Medio ⭐⭐ → 300 puntos
  - Difícil ⭐⭐⭐ → 500 puntos

🏆 Los puntos te ayudan a **aumentar tu nivel como estudiante** en CuriousMind. Cuantos más bloques completes, más nivel tendrás **(1 nivel = 100 puntos)**.

![Completed Content Block screen](Images/UserManual/contentFinal.png)

---

Por cada bloque de contenidos que completes **sin agotar tus vidas**, el bloque se marcará como **completado** 🎉, ya no lo podrás volver a hacer, y aparecerá de color verde en el menú del curso.

📸 Curso Completado  
![CompletedCourse screen](Images/UserManual/CompletedCourse.png) 

---
## Pruebas y testing

### 🧪 Base de datos para pruebas y testing

CuriousMind incluye una base de datos pensada para pruebas, que sirve para comprobar el correcto funcionamiento de la aplicación.  
Contiene ejemplos realistas del flujo de la aplicación, ideal para testing funcional, encontrados en la carpeta: [Cursos de Prueba](src/...DDBB...TODO). 
Además, la aplicación está preparada para que si al arrancar todavía no hay cursos creados en la base de datos, se generan automáticamente 2 cursos por defecto que se encuentran en la carpeta ...TODO samplescourses....

---

### 📚 Cursos disponibles en la base de datos

En la base de datos hay **5 cursos predefinidos**, cada uno diseñado con distintos niveles de dificultad y todos los tipos de preguntas. A continuación, se describen brevemente:

1. **Curso de Alemán 🇩🇪**  
   Incluye bloques de contenido con que te van a permitir obtener las bases del idioma.  
   La estrategia seleccionada para este curso es aleatoria.

2. **Curso de C++ 💻**  
   Con bloques centrados en fundamentos de código.
   La estrategia seleccionada para este curso es repetición espaciada.

3. **Música 🎵**  
   Con bloques variados con historia y fundamentos de la música.
   La estrategia seleccionada para este curso es repetición secuencial.

4. **Curso de Ciencia y Naturaleza 🌱**  
   Dividido en bloques por temas (biología, física, ecología…).  
   La estrategia seleccionada para este curso es repetición secuencial.

5. **Curso de Matemáticas 📐**  
   Contiene bloques orientados a lógica, cálculo y resolución de problemas.  
   La estrategia seleccionada para este curso es repetición secuencial.


---

### 👤 Usuario de prueba de la base de datos

Se incluye un **usuario de ejemplo** con el siguiente estado:

- Nombre de Usuario: Curious10
- Contraseña: CuriousPassword10 
  
- Inscrito en **3 cursos** (Alemán, Música y C++).
- Ha **completado 1 curso ✅**.

---


## Preguntas frecuentes

**¿La app guarda mi progreso automáticamente?**  
Sí, todos tus cambios se guardan y además la aplicación es capaz de guardar el momento en el que te conectas cada día para poder registrar tus estadíticas.

**En caso de salir de un bloque de contenidos iniciado ¿Al volver a entrar recupero la pregunta por la que iba?**  
No, si sales de un bloque de contenidos iniciado, como en la mayoría de aplicaciones relacionadas con el aprendizaje interactivo, debes iniciar de nuevo ese bloque.

**¿Puedo volver a inscribirme en un curso del que ya estoy inscrito?**  
No, una vez que te has inscrito en un curso, este aparece en la sección *"Mis Cursos"* y no es posible volver a inscribirse hasta que haya sido completado.  

**¿Puedo volver a realizar un bloque de contenidos que ya he completado?**  
No, una vez que completas con éxito un bloque de contenidos de un curso no se puede volver a hacer y quedará marcado en verde en la ventana principal del curso.


---

## Contacto y soporte

Para cualquier duda, sugerencia o reporte de errores, puedes ponerte en contacto con el equipo de desarrollo escribiendo a:  
📧 CuriousMind@gmail.com

---

