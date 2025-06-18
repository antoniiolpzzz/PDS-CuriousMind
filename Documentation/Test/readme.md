# 📋 Pruebas del Proyecto

Este apartado describe la estrategia y el estado actual de las pruebas implementadas en el proyecto.

## 🧩 Estructura de pruebas

### ✅ Pruebas unitarias (Modelo - MVC)

- Se han implementado pruebas unitarias para cada clase del modelo.
- Por cada clase de modelo existe una clase de test correspondiente.
- Cada método público ha sido probado para verificar su correcto funcionamiento.
- **Cobertura**: se busca una cobertura completa de los métodos del modelo.

### 📊 Estadísticas de cobertura (Maven + plugin de cobertura)

- Se utilizará el plugin de Maven para generar informes de cobertura de código.
- Tipos de cobertura:
  - **Cobertura por línea**
  - **Cobertura por rama**
- El informe generado se adjunta a continuación 

### 🔄 Pruebas de integración

- Se valorará la implementación de clases de test que validen el comportamiento conjunto de varios componentes.
- Objetivo: garantizar que los módulos del sistema interactúan correctamente.

### 🎯 Pruebas de aceptación

- Validación de funcionalidades completas desde la perspectiva del usuario.
- Ejemplo: probar el flujo completo de creación y consumo de cursos en la aplicación.

### 🛠️ Tests con Mockito

- Se utilizará Mockito para:
  - Simular componentes dependientes.
  - Aislar el código bajo prueba.
  - Facilitar la escritura de pruebas unitarias e integración.

---

