import java.util.Scanner;

public class ReproductorMusicaE3 {
    private static final Scanner SCANNER = new Scanner(System.in);
    private ListaSimpleCircular<Cancion> canciones;
    private int indiceActual;

    public ReproductorMusicaE3(ListaSimpleCircular<Cancion> canciones) {
        this.canciones = new ListaSimpleCircular<>();
        this.indiceActual = 0;
    }

    public void agregar_cancion(String nombre){
        canciones.agregarfinal(new Cancion(nombre));

        if (canciones.getTamanio() == 1) {
            indiceActual = 0;
        }
    }

    public void eliminar_cancion(String nombre){
        Cancion cancion = getCancion(nombre);
        if (cancion == null) {
            return;
        }

        int indiceEliminado = canciones.obtenerPosicionNodo(cancion);
        canciones.eliminar(cancion);

        if (canciones.estaVacia()) {
            indiceActual = 0;
        } else if (indiceEliminado < indiceActual) {
            indiceActual--;
        } else if (indiceActual >= canciones.getTamanio()) {
            indiceActual = canciones.getTamanio() - 1;
        }
    }

    public Cancion getCancion(String nombre){
        for (Cancion cancion : canciones) {
            if (cancion.getNombre().equals(nombre)) {
                return cancion;
            }
        }
        return null;
    }

    public void atras() {
        if (!canciones.estaVacia() && indiceActual > 0) {
            indiceActual--;
        }
    }

    public void adelante() {
        if (!canciones.estaVacia() && indiceActual < canciones.getTamanio() - 1) {
            indiceActual++;
        }
    }

    public String obtenerCancionActual() {
        if (canciones.estaVacia()) {
            return null;
        }

        Cancion cancion = canciones.obtenerValorNodo(indiceActual);
        return (cancion != null) ? cancion.getNombre() : null;
    }

    public void mostrarListaReproduccion() {
        for (int i = 0; i < canciones.getTamanio(); i++) {
            Cancion cancion = canciones.obtenerValorNodo(i);
            if (cancion == null) {
                continue;
            }

            if (i == indiceActual) {
                System.out.print("[" + cancion.getNombre() + "] ");
            } else {
                System.out.print(cancion.getNombre() + " ");
            }
        }
        System.out.println();
    }

    public void reproducir(boolean continuar){
        if (!continuar || canciones.estaVacia()) {
            return;
        }

        while (continuar && indiceActual < canciones.getTamanio()) {
            System.out.println("Cancion: " + canciones.obtenerValorNodo(indiceActual).getNombre());

            if (indiceActual < canciones.getTamanio() - 1) {
                System.out.print("¿Deseas seguir reproduciendo? (s/n): ");
                String respuesta = SCANNER.nextLine().trim().toLowerCase();
                continuar = respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");

                if (continuar) {
                    adelante();
                }
            } else {
                continuar = false;
            }
        }
    }

    //Se usa ListaSimpleCircular porque las canciones deben reproducirse infinitamente en ciclo continuo.
}
