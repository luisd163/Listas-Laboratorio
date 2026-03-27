// HISTORIAL
class HistorialNavegador {
    private ListaDoble<PaginaWeb> historial;
    private int indiceActual;

    // Inicia el historial con una página inicial
    public HistorialNavegador(String pagina) {
        PaginaWeb paginaInicial = new PaginaWeb(pagina);
        this.historial = new ListaDoble<>();
        this.historial.agregarfinal(paginaInicial);
        this.indiceActual = 0;
    }

    // Visitar nueva página
    public void visitar(String url) {
        while (historial.getTamanio() - 1 > indiceActual) {
            historial.eliminarUltimo();
        }

        historial.agregarfinal(new PaginaWeb(url));
        indiceActual = historial.getTamanio() - 1;
    }

    // Retroceder una página
    public void atras() {
        if (indiceActual > 0) {
            indiceActual--;
        }
    }

    // Avanzar una página
    public void adelante() {
        if (indiceActual < historial.getTamanio() - 1) {
            indiceActual++;
        }
    }

    // Obtener la página actual
    public String obtenerPaginaActual() {
        if (historial.estaVacia()) {
            return null;
        }

        PaginaWeb pagina = historial.obtener(indiceActual);
        return (pagina != null) ? pagina.getDominio() : null;
    }

    // Mostrar todo el historial, marcando la página actual entre corchetes
    public void mostrarHistorial() {
        for (int i = 0; i < historial.getTamanio(); i++) {
            PaginaWeb pagina = historial.obtener(i);
            if (pagina == null) {
                continue;
            }

            if (i == indiceActual) {
                System.out.print("[" + pagina.getDominio() + "] ");
            } else {
                System.out.print(pagina.getDominio() + " ");
            }
        }
        System.out.println();
    }

    // Se usa ListaDoble porque requiere navegar adelante y atrás entre páginas visitadas de forma eficiente.
}