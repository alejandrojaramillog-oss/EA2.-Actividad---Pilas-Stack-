import java.util.ArrayList;
import java.util.List;

/**
 * Implementación manual de la estructura de datos Pila (Stack) con comportamiento LIFO.
 */
public class Pila {
    
    // Lista para almacenar elementos; el final de la lista es la cima de la pila.
    private List<String> elementos = new ArrayList<>();
    
    /**
     * Añade un elemento a la cima (PUSH).
     * @param elemento El String a añadir.
     */
    public void push(String elemento) {
        elementos.add(elemento);
    }
    
    /**
     * Remueve y retorna el elemento de la cima (POP).
     * @return El String removido, o null si está vacía.
     */
    public String pop() {
        if (isEmpty()) {
            return null; 
        }
        // LIFO: El último en entrar (último índice) es el primero en salir.
        return elementos.remove(elementos.size() - 1);
    }
    
    /**
     * Retorna (sin remover) el elemento de la cima (PEEK).
     * @return El String en la cima, o null si está vacía.
     */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return elementos.get(elementos.size() - 1);
    }
    
    /**
     * Verifica si la pila está vacía.
     * @return true si la pila no contiene elementos.
     */
    public boolean isEmpty() {
        return elementos.isEmpty();
    }
    
    /**
     * Muestra todos los elementos de la pila (el contenido del editor).
     * @return Un String con todas las líneas.
     */
    public String mostrarContenido() {
        StringBuilder sb = new StringBuilder();
        for (String linea : elementos) {
            sb.append(linea).append("\n");
        }
        return sb.toString();
    }
}