// Nodo doblemente enlazado para un jugador
class NodoJugador {
    String nombre;
    NodoJugador next;
    NodoJugador prev;

    public NodoJugador(String nombre) {
        this.nombre = nombre;
        this.next = null;
        this.prev = null;
    }
}

// Lista doblemente enlazada circular de jugadores
class ListaJugadoresCircular {
    private NodoJugador head;

    public ListaJugadoresCircular() {
        head = null;
    }

    public NodoJugador getHead() {
        return head;
    }

    // Insertar jugador al final del círculo
    public NodoJugador insertarJugador(String nombre) {
        NodoJugador nuevo = new NodoJugador(nombre);
        if (head == null) {
            head = nuevo;
            head.next = head;
            head.prev = head;
        } else {
            NodoJugador tail = head.prev;
            tail.next = nuevo;
            nuevo.prev = tail;
            nuevo.next = head;
            head.prev = nuevo;
        }
        return nuevo;
    }

    // Eliminar un jugador dado su nodo
    public void eliminarJugador(NodoJugador jugador) {
        if (jugador == null || head == null) return;

        // Si solo hay un jugador
        if (jugador == head && head.next == head) {
            head = null;
            return;
        }

        NodoJugador anterior = jugador.prev;
        NodoJugador siguiente = jugador.next;

        anterior.next = siguiente;
        siguiente.prev = anterior;

        // Si el eliminado era la cabeza, mover la cabeza
        if (jugador == head) {
            head = siguiente;
        }
    }

    // Mostrar todos los jugadores (para depuración)
    public void mostrarJugadores(NodoJugador actual) {
        if (head == null) {
            System.out.println("No hay jugadores.");
            return;
        }
        NodoJugador temp = head;
        do {
            if (temp == actual) {
                System.out.print("[" + temp.nombre + "] ");
            } else {
                System.out.print(temp.nombre + " ");
            }
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
}

// Lógica del juego por turnos
class JuegoTurnos {
    private ListaJugadoresCircular lista;
    private NodoJugador actual;

    public JuegoTurnos() {
        lista = new ListaJugadoresCircular();
        actual = null;
    }

    // Agregar un nuevo jugador al juego
    public void agregarJugador(String nombre) {
        NodoJugador nuevo = lista.insertarJugador(nombre);
        if (actual == null) {
            actual = nuevo;
        }
    }

    // Pasar turno al siguiente jugador
    public void siguienteTurno() {
        if (actual != null) {
            actual = actual.next;
        }
    }

    // Pasar turno al jugador anterior
    public void turnoAnterior() {
        if (actual != null) {
            actual = actual.prev;
        }
    }

    // Expulsar al jugador actual
    public void expulsarActual() {
        if (actual == null) return;

        NodoJugador siguiente = actual.next;

        // Si solo queda un jugador, al expulsarlo el juego queda sin jugadores
        if (actual == siguiente) {
            lista.eliminarJugador(actual);
            actual = null;
        } else {
            lista.eliminarJugador(actual);
            actual = siguiente;
        }
    }

    public String jugadorActual() {
        return (actual != null) ? actual.nombre : null;
    }

    public void mostrarMesa() {
        lista.mostrarJugadores(actual);
    }
}