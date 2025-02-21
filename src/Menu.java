import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    public static void showMenu(){

        System.out.println("Welcome to My Appointments");
        System.out.println("Selecciona la opción deseada");

        int response = 0;
        do {
            System.out.println("1.Ver tareas");
            System.out.println("2.Agregar tareas");
            System.out.println("3.Eliminar tareas");
            System.out.println("4.Eliminar todas las tareas");
            System.out.println("5.Buscar tarea");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());
            String titulo;
            switch (response){
                case 1:
                    System.out.println("ver");
                    tareas.verTareas();
                    break;
                case 2:
                    System.out.println("agregar");
                    System.out.println("Titulo: ");
                    titulo = sc.nextLine();
                    System.out.println("Descripcion: ");
                    String descripcion = sc.nextLine();
                    // Leer la fecha usando el método separado
                    System.out.println("Ingresa la fecha:");
                    LocalDate fecha = tareas.leerFecha();
                    tareas.agregarTarea(titulo, descripcion, fecha);
                    break;
                case 3:
                    System.out.println("eliminar\n");
                    System.out.println("Titulo de la tarea que desea eliminar: ");
                    titulo = sc.nextLine();
                    tareas.eliminarTarea(titulo);
                    break;
                case 4:
                    tareas.eliminarAllTareas();
                    break;
                case 5:
                    System.out.println("Titulo de la tarea que desea buscar: ");
                    titulo = sc.nextLine();
                    tareas.buscarTarea(titulo);
                    break;
                case 0:
                    System.out.println("Thank you for you visit");
                    break;
                default:
                    System.out.println("Please select a correct answer");
            }
        }while (response != 0);
    }
}