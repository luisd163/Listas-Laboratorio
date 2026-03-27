public class PanaderiaE1 {

    private ListaSimple<Cliente> clientes; // Lista simplemente enlazada
    private int indiceActual;

    public PanaderiaE1(ListaSimple<Cliente> clientes) {
        this.clientes = new ListaSimple<>();
        this.indiceActual = 0;
    }

    public void registrar_cliente(String nombre){
        Cliente cliente = new Cliente(nombre);
        clientes.agregarfinal(cliente);

        if (clientes.getTamanio() == 1) {
            indiceActual = 0;
        }
    }

    public Cliente cliente_espera(){
        if (clientes.estaVacia()) {
            return null;
        }

        return clientes.obtenerValorNodo(indiceActual);
    }

    public void atender_cliente(){
        Cliente clienteActual = cliente_espera();
        if (clienteActual == null) {
            return;
        }

        clientes.eliminar(clienteActual);

        if (clientes.estaVacia()) {
            indiceActual = 0;
        } else if (indiceActual >= clientes.getTamanio()) {
            indiceActual = clientes.getTamanio() - 1;
        }
    }

    public void cliente_anterior() {
        if (!clientes.estaVacia() && indiceActual > 0) {
            indiceActual--;
        }
    }

    public void siguiente_cliente() {
        if (!clientes.estaVacia() && indiceActual < clientes.getTamanio() - 1) {
            indiceActual++;
        }
    }

    public String obtenerClienteActual() {
        Cliente cliente = cliente_espera();
        return (cliente != null) ? cliente.nombre : null;
    }

    public void mostrarFila() {
        for (int i = 0; i < clientes.getTamanio(); i++) {
            Cliente cliente = clientes.obtenerValorNodo(i);
            if (cliente == null) {
                continue;
            }

            if (i == indiceActual) {
                System.out.print("[" + cliente.nombre + "] ");
            } else {
                System.out.print(cliente.nombre + " ");
            }
        }
        System.out.println();
    }

    // La estructura elegida permite recorrer la lista en orden de inserción, además no es necesario recorrerla de adelante
    // hacia atrás 
}