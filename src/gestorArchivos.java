import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class gestorArchivos {
    private static final String ARCHIVO_TAREAS = "C:/Users/User/Documents/tareas.json";

    // Configura Gson con el LocalDateAdapter
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdadter()) // Registra el adaptador
            .create();

    // Método para guardar tareas
    public static void guardarTareas(Map<String, Map<String, Object>> tareas){
        try (FileWriter writer = new FileWriter(ARCHIVO_TAREAS)) {
            gson.toJson(tareas, writer);
            System.out.println("Tareas guardadas correctamente en el archivo JSON.");
        }catch (IOException e){
            System.out.println("error: "+e);
        }
    }

    // Método para cargar tareas
    public static Map<String, Map<String, Object>> cargarTareas() {
        Map<String, Map<String, Object>> tareas = new HashMap<>();

        File archivo = new File(ARCHIVO_TAREAS);
        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El archivo de tareas está vacío o no existe. Se creará uno nuevo.");
            return tareas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_TAREAS))) {
            Type tipo = new TypeToken<Map<String, Map<String, Object>>>() {}.getType();
            tareas = gson.fromJson(reader, tipo);
            System.out.println("Tareas cargadas correctamente desde el archivo JSON.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Error: El archivo JSON tiene un formato inválido. Se creará uno nuevo.");
        }

        return tareas;
    }
}