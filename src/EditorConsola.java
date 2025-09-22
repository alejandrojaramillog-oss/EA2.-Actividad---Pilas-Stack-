import java.util.Scanner;

/**
 * Simula un editor de texto simple con Deshacer (Undo) y Rehacer (Redo) 
 * utilizando dos instancias de la clase Pila.
 */
public class EditorConsola {

    private Pila pilaPrincipal = new Pila(); // Pila para acciones realizadas
    private Pila pilaRedo = new Pila();      // Pila para acciones deshechas
    private Scanner scanner = new Scanner(System.in);

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
                procesarOpcion(opcion);
            } else {
                System.out.println("\n❌ Entrada inválida. Ingrese un número.");
                scanner.nextLine(); 
                opcion = 0; 
            }
        } while (opcion != 5);
        
        System.out.println("\n👋 Programa finalizado.");
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n--- Editor Simple (Pila) ---");
        System.out.println("1. 📝 Escribir texto (PUSH a Pila Principal)");
        System.out.println("2. ⏪ Deshacer (Undo)");
        System.out.println("3. ⏩ Rehacer (Redo)");
        System.out.println("4. 📖 Mostrar texto actual y cima (PEEK)");
        System.out.println("5. 🚪 Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: escribirTexto(); break;
            case 2: deshacerAccion(); break;
            case 3: rehacerAccion(); break;
            case 4: mostrarEstado(); break;
            case 5: break; // Salir
            default: System.out.println("\n❌ Opción no reconocida.");
        }
    }

    // OPERACIÓN 1: Escribir texto (PUSH)
    private void escribirTexto() {
        System.out.print("Ingrese la línea de texto a añadir: ");
        String linea = scanner.nextLine();
        pilaPrincipal.push(linea);

        // ¡Regla de Undo/Redo! Una nueva acción invalida el historial de 'redo'.
        if (!pilaRedo.isEmpty()) {
            System.out.println("⚠️ Historial de Rehacer (Redo) limpiado.");
            while (!pilaRedo.isEmpty()) {
                pilaRedo.pop(); // Vacía la pila Redo
            }
        }
    }

    // OPERACIÓN 2: Deshacer (POP -> PUSH)
    private void deshacerAccion() {
        String accionDeshecha = pilaPrincipal.pop();
        
        if (accionDeshecha != null) {
            pilaRedo.push(accionDeshecha); // Mueve la acción a la pila Redo
            System.out.println("\n✅ UNDO realizado. Se deshizo: '" + accionDeshecha + "'.");
        } else {
            System.out.println("\n⚠️ No hay acciones para deshacer.");
        }
    }

    // OPERACIÓN 3: Rehacer (POP -> PUSH)
    private void rehacerAccion() {
        String accionRehecha = pilaRedo.pop();

        if (accionRehecha != null) {
            pilaPrincipal.push(accionRehecha); // Mueve la acción de vuelta a la principal
            System.out.println("\n✅ REDO realizado. Se rehizo: '" + accionRehecha + "'.");
        } else {
            System.out.println("\n⚠️ No hay acciones para rehacer.");
        }
    }

    // OPERACIÓN 4: Mostrar estado (Contenido y PEEK)
    private void mostrarEstado() {
        System.out.println("\n--- ESTADO ACTUAL DEL EDITOR ---");
        
        // Muestra el texto completo (contenido de la Pila Principal)
        String contenido = pilaPrincipal.mostrarContenido();
        if (contenido.isEmpty()) {
            System.out.println("Contenido: [El editor está vacío]");
        } else {
            System.out.println("Contenido:\n" + contenido);
        }

        // Muestra el elemento en la cima (PEEK)
        String cima = pilaPrincipal.peek();
        System.out.println("--------------------------------");
        if (cima != null) {
            System.out.println("Cima (Última acción): " + cima.trim());
        } else {
            System.out.println("Cima (Última acción): N/A");
        }
        System.out.println("Acciones para REDO: " + (!pilaRedo.isEmpty() ? "Sí, " + pilaRedo.peek() + " es la próxima." : "No."));
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        EditorConsola editor = new EditorConsola();
        editor.ejecutar();
    }
}