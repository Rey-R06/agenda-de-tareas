# Agenda de Tareas

## Descripción
Este es un proyecto de una agenda de tareas desarrollada en Java, que permite agregar, ver, buscar y eliminar tareas, almacenándolas en un archivo JSON.

## Requisitos
- Java 8 o superior
- [Gson](https://github.com/google/gson) para la serialización y deserialización de JSON

## Instalación y Configuración
1. **Clonar el repositorio:**
   ```sh
   git clone https://github.com/Rey-R06/agenda-de-tareas.git
   ```
2. **Configurar la ruta del archivo JSON:**
   - Edita la variable `ARCHIVO_TAREAS` en la clase `gestorArchivos.java` y coloca la ruta donde deseas guardar las tareas.
   - Ejemplo:
     ```java
     private static final String ARCHIVO_TAREAS = "C:/ruta/deseada/tareas.json";
     ```
3. **Agregar Gson al proyecto:**
   - Si usas Maven, agrégalo en `pom.xml`:
     ```xml
     <dependencies>
         <dependency>
             <groupId>com.google.code.gson</groupId>
             <artifactId>gson</artifactId>
             <version>2.8.9</version>
         </dependency>
     </dependencies>
     ```
   - Si no usas Maven, descarga `gson.jar` desde [aquí](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.9/) y agrégalo al classpath del proyecto.

## Uso
Ejecuta la clase `Main.java` para acceder al menú interactivo, donde podrás:
- Ver tareas
- Agregar nuevas tareas
- Eliminar tareas
- Buscar tareas por título

## Contribución
1. Realiza un fork del repositorio.
2. Crea una nueva rama (`git checkout -b nueva-funcionalidad`).
3. Realiza cambios y confirma (`git commit -m 'Agregar nueva funcionalidad'`).
4. Envía un pull request.

## Licencia
Este proyecto se distribuye bajo la licencia MIT.

---
**Nota:** Asegúrate de adaptar correctamente la ruta del archivo JSON antes de ejecutar la aplicación.

