import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class gestorArchivos {
    private static final String ARCHIVO_TAREAS = "C:/Users/User/Documents/tareas.txt";

    public static void guardarTareas(Map<String, Map<String, Object>> tareas){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_TAREAS))){

            for (Map.Entry<String, Map<String, Object>> entrada : tareas.entrySet()) {
                String titulo = entrada.getKey(); // Obtener el título
                Map<String, Object> detalles = entrada.getValue(); // Obtener los detalles

                // Acceder a los elementos dentro del Map de detalles
                String descripcion = (String) detalles.get("descripcion");
                LocalDate fecha = (LocalDate) detalles.get("fecha");

                // Escribir la tarea en el archivo con un formato consistente
                writer.write(titulo + "|" + descripcion + "|" + fecha);
                writer.newLine();


            }

            System.out.println("Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    // Método para cargar las tareas desde un archivo


    public static Map<String, Map<String, Object>> cargarTareas() {
        Map<String, Map<String, Object>> tareas = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_TAREAS))) {
            String linea;
            int numeroLinea = 0; // Para rastrear el número de línea en caso de error

            while ((linea = reader.readLine()) != null) {
                numeroLinea++; // Incrementar el contador de líneas
                try {
                    // Eliminar espacios en blanco al inicio y final de la línea
                    linea = linea.trim();

                    // Ignorar líneas vacías
                    if (linea.isEmpty()) {
                        continue;
                    }

                    // Dividir la línea en partes usando "|" como separador
                    String[] partes = linea.split("\\|");

                    // Verificar que la línea tenga exactamente 3 partes
                    if (partes.length == 3) {
                        String titulo = partes[0].trim();
                        String descripcion = partes[1].trim();
                        LocalDate fecha = LocalDate.parse(partes[2].trim());

                        // Crear el mapa de detalles
                        Map<String, Object> detalles = new HashMap<>();
                        detalles.put("descripcion", descripcion);
                        detalles.put("fecha", fecha);

                        // Agregar la tarea al mapa principal
                        tareas.put(titulo, detalles);

                    } else {
                        System.out.println("Advertencia: Línea mal formateada en el archivo (línea " + numeroLinea + "): " + linea);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Fecha inválida en la línea " + numeroLinea + ": " + linea);
                }
            }
            System.out.println("Tareas cargadas correctamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar las tareas: " + e.getMessage());
        }

        return tareas;
    }

}
