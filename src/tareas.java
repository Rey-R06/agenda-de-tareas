import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class tareas {

    static Scanner sc = new Scanner(System.in);
    static Map<String, Map<String, Object>> tareasSession = new HashMap<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static void verTareas(){
        int eleccion;
        if (tareasSession.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            for (Map.Entry<String, Map<String, Object>> entrada : tareasSession.entrySet()) {
                System.out.println("Título: " + entrada.getKey());
                System.out.println("Descripción: " + entrada.getValue().get("descripcion"));
                System.out.println("Fecha: " + entrada.getValue().get("fecha"));
                System.out.println();
            }
        }
        do {
            System.out.println("0-regresar");
            eleccion = sc.nextInt();
        }while (eleccion != 0);
    }



    public static void agregarTarea(String titulo, String descripcion, LocalDate fecha) {
        Map<String, Object> detalles = new HashMap<>();
        int response;
        if (tareasSession.containsKey(titulo)){
            System.out.println("Titulo de tarea ya exites:");
            System.out.println(tareasSession.get(titulo));
            System.out.println("\n1-cambiar titulo \n2-sobreecribir tarea\n0-salir");
            response = sc.nextInt();
            sc.nextLine();
            switch (response){
                case 1:
                    System.out.println("Titulo nuevo:");
                    titulo = sc.nextLine();
                    detalles.put("descripcion", descripcion);
                    detalles.put("fecha", fecha);
                    tareasSession.put(titulo, detalles);

                    // Guardar las tareas en el archivo
                    gestorArchivos.guardarTareas(tareasSession);
                    System.out.println("tarea agregada");
                    break;
                case 2:
                    tareasSession.put(titulo, Map.of("descripcion", descripcion, "fecha", fecha));
                    System.out.println("tarea sobreescrita con exito");

                    // Guardar las tareas en el archivo
                    gestorArchivos.guardarTareas(tareasSession);
                    break;
                case 0:
                    break;
            }
        }else{
            detalles.put("descripcion", descripcion);
            detalles.put("fecha", fecha);
            tareasSession.put(titulo, detalles);
            System.out.println("Tarea agregada: " + titulo);

            // Guardar las tareas en el archivo
            gestorArchivos.guardarTareas(tareasSession);
        }

    }

    public static void eliminarTarea(String titulo){
        if (tareasSession.containsKey(titulo)){
            System.out.println("titulo: "+titulo+"\ndescripcion: "+tareasSession.get(titulo));
            int eleccion;
            do {
                System.out.println("Eliminar tarea? \n1-si \n0-regresar");
                eleccion = sc.nextInt();
                switch (eleccion){
                    case 1:
                        tareasSession.remove(titulo);
                        System.out.println("tarea eliminada");
                        eleccion = 0;
                        gestorArchivos.guardarTareas(tareasSession);
                        break;
                    case 0:
                    default:
                        System.out.println();
                        break;
                }
            }while (eleccion != 0);
        }else{
            System.out.println("Tarea no encontrada");
        }

    }

    public static void eliminarAllTareas() {
        tareasSession.clear();
        // Guardar las tareas en el archivo
        gestorArchivos.guardarTareas(tareasSession);
        System.out.println("Todas las tareas han sido eliminadas.");
    }

    public static void buscarTarea(String titulo){
        int response;
        System.out.println("Descripcion");
        if (tareasSession.get(titulo) != null){
            Map<String, Object> detalles = tareasSession.get(titulo);
            // Acceder a los elementos dentro del Map de detalles
            String descripcion = (String) detalles.get("descripcion");
            LocalDate fecha = (LocalDate) detalles.get("fecha");

            System.out.println("Descripcion: "+descripcion);
            System.out.println("Fecha: "+fecha.format(formatter));
        }else {
            System.out.println("No existe tarea con ese titulo");
        }
        do {
            System.out.println("0-regresar");
            response = sc.nextInt();
        }while (response != 0);
    }

    // Método para validar el día según el mes y el año
    public static boolean esDiaValido(int año, int mes, int dia) {
        // Obtener el número de días en el mes
        YearMonth yearMonth = YearMonth.of(año, mes);
        int diasEnMes = yearMonth.lengthOfMonth();

        // Verificar si el día es válido
        return dia >= 1 && dia <= diasEnMes;
    }

    public static LocalDate leerFecha() {
        int año;
        int mes;
        int dia;
        while (true) {
            try {
                // Solicitar año
                do {
                    System.out.print("Año (debe ser 2025 o posterior): ");
                    año = Integer.parseInt(sc.nextLine());
                    if (año < 2025) {
                        System.out.println("El año debe ser 2025 o posterior.");
                    }
                } while (año < 2025);

                // Solicitar mes
                do {
                    System.out.print("Mes (1-12): ");
                    mes = Integer.parseInt(sc.nextLine());
                    if (mes < 1 || mes > 12) {
                        System.out.println("El mes debe estar entre 1 y 12.");
                    }
                } while (mes < 1 || mes > 12);

                // Solicitar día
                do {
                    System.out.print("Día: ");
                    dia = Integer.parseInt(sc.nextLine());
                    if (!esDiaValido(año, mes, dia)) {
                        System.out.println("Día inválido para el mes y año ingresados.");
                    }
                } while (!esDiaValido(año, mes, dia));
                // Crear y retornar la fecha
                return LocalDate.of(año, mes, dia);
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println("Fecha inválida. Intenta de nuevo.");
            }
        }
    }

    // Método para cargar las tareas al iniciar el programa
    public static void cargarTareasIniciales() {
        tareasSession = gestorArchivos.cargarTareas();
    }

}


