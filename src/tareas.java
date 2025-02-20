import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class tareas {

    static Scanner sc = new Scanner(System.in);
    static Map<String, String> tareas = new HashMap<>();


    public static void verTareas(){
        int eleccion;
        Set<Map.Entry<String, String>> entradas = tareas.entrySet();
        if (tareas.isEmpty()){
            System.out.println("No hay tareas");
        }else {
            for (Map.Entry<String, String> entrada : entradas) {
                System.out.println("Titulo: " + entrada.getKey() + ", descripcion: " + entrada.getValue());
            }
        }
        do {
            System.out.println("0-regresar");
            eleccion = sc.nextInt();
        }while (eleccion != 0);
        Menu.showMenu();
    }



    public static void agregarTarea(String titulo, String descripcion){
        int response;
        String tituloNuevo;
        if (tareas.containsKey(titulo)){
            System.out.println("Titulo de tarea ya exites:");
            System.out.println(tareas.get(titulo));
            System.out.println("\n1-cambiar titulo \n2-sobreecribir tarea\n0-salir");
            response = sc.nextInt();
            sc.nextLine();
            switch (response){
                case 1:
                    System.out.println("Titulo nuevo:");
                    tituloNuevo = sc.nextLine();
                    tareas.put(tituloNuevo, descripcion);
                    tareas.remove(titulo);
                    System.out.println("tarea agregada");
                    Menu.showMenu();
                    break;
                case 2:
                    //falta esto y ya
                    tareas.put(titulo, descripcion);
                    System.out.println("tarea sobreescrita con exito");
                    Menu.showMenu();
                    break;
                case 0:
                    Menu.showMenu();
                    break;
            }
        }else{
            tareas.put(titulo, descripcion);
            System.out.println("tarea agregada");
            Menu.showMenu();
        }

    }

    public static void eliminarTarea(String titulo){
        if (tareas.containsKey(titulo)){
            System.out.println("titulo: "+titulo+"\ndescripcion: "+tareas.get(titulo));
            int eleccion;
            do {
                System.out.println("Eliminar tarea? \n1-si \n0-regresar");
                eleccion = sc.nextInt();
                switch (eleccion){
                    case 1:
                        tareas.remove(titulo);
                        System.out.println("tarea eliminada");
                        eleccion = 0;
                        break;
                    case 0:
                        Menu.showMenu();
                    default:
                        System.out.println();
                        break;
                }
            }while (eleccion != 0);
            Menu.showMenu();
        }else{
            System.out.println("Tarea no encontrada");
            Menu.showMenu();
        }

    }

    public static void eliminarAllTareas(){
        tareas.clear();
        System.out.println("Todas las tareas han sido eliminadas.");
        Menu.showMenu();
    }

    public static void buscarTarea(String titulo){
        int response;
        System.out.println("Descripcion");
        if (tareas.get(titulo) != null){
            System.out.println(tareas.get(titulo));
        }else {
            System.out.println("No existe tarea con ese titulo");
        }
        do {
            System.out.println("0-regresar");
            response = sc.nextInt();
        }while (response != 0);
        Menu.showMenu();
    }
}
