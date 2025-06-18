# 📋 Pruebas del Proyecto

Este apartado describe la estrategia y el estado actual de las pruebas implementadas en el proyecto.

## 🧩 Estructura de pruebas

### ✅ Pruebas unitarias (Modelo - MVC)

- Se han implementado pruebas unitarias para cada clase del modelo.
- Por cada clase de modelo existe una clase de test correspondiente.
- Cada método público ha sido probado para verificar su correcto funcionamiento.
- Se adjuntan estas clases de prueba en el directorio [src/test](enlace al directorio)
### 📊 Estadísticas de cobertura (Maven + plugin de cobertura)

- Se ha usado el plugin de Maven con JaCoCo para generar informes de cobertura de código.
- Tipos de cobertura:
  - **Cobertura por línea**
  - **Cobertura por rama**
- El informe generado se adjunta a continuación:
📸![image](https://github.com/antoniiolpzzz/PDS-CuriousMind/raw/b89e72fa54b8db1e46a37aecefd982192e917665/Documentation/Test/informeCobertura.png)

### 🔄 Pruebas de integración

- Se lleva a cabo la implementación de clases de test que validen el comportamiento conjunto de varios componentes.
- Objetivo: garantizar que los módulos del sistema interactúan correctamente.
- Este test tambien se incluye en el directorio [src/test](enlace al directorio)

### 🎯 Pruebas de aceptación

- Validación de funcionalidades completas desde la perspectiva del usuario.
- Se ha decidido el uso de vídeos explicativos donde se muestra cómo interactuará el usuario con la aplicacion.
- [Video Demostrativo](https://dai.ly/k3ALFKPBzbRbZODhqhq)
[![Video Demostrativo](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/19fbc344a0d8a998837dee42300f22f802e38d75/Documentation/Test/Captura%20-%20Video%20demostrativo.png)](https://geo.dailymotion.com/player.html?video=k3ALFKPBzbRbZODhqhq)

### 🛠️ Tests con Mockito

- Se ha usado Mockito para:
  - Simular componentes dependientes.
  - Aislar el código bajo prueba.
  - Facilitar la escritura de pruebas unitarias e integración.

---
