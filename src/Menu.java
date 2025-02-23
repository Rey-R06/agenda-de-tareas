import java.time.LocalDate;
import java.util.Scanner;
//class menu
public class Menu {
    public static void showMenu(){
        // Cargar las tareas al iniciar el programa
        tareas.cargarTareasIniciales();

        System.out.println("\n--- Menú de Tareas ---");
        int response = 0;
        do {
            System.out.println("1.Ver tareas");
            System.out.println("2.Agregar tareas");
            System.out.println("3.Eliminar 1 tarea");
            System.out.println("4.Eliminar todas las tareas");
            System.out.println("5.Buscar tarea");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());
            String titulo;
            switch (response){
                case 1:
                    System.out.println("\n--- Ver tareas ---");
                    tareas.verTareas();
                    break;
                case 2:
                    System.out.println("\n--- Agregar tarea ---");
                    System.out.print("Título: ");
                    titulo = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();
                    System.out.println("Ingresa la fecha:");
                    LocalDate fecha = tareas.leerFecha();
                    tareas.agregarTarea(titulo, descripcion, fecha);
                    break;
                case 3:
                    System.out.println("\n--- Eliminar tarea ---");
                    System.out.print("Título de la tarea que desea eliminar: ");
                    titulo = sc.nextLine();
                    tareas.eliminarTarea(titulo);
                    break;
                case 4:
                    System.out.println("\n--- Eliminar todas las tareas ---");
                    tareas.eliminarAllTareas();
                    break;
                case 5:
                    System.out.println("\n--- Buscar tarea ---");
                    System.out.print("Título de la tarea que desea buscar: ");
                    titulo = sc.nextLine();
                    tareas.buscarTarea(titulo);
                    break;
                case 0:
                    System.out.println("\nGracias por usar la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intenta de nuevo.");
            }
        }while (response != 0);
    }
}