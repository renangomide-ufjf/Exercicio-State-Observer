package padroescomportamentais.integracao.state_observer;

public abstract class PedidoEstado {

    public abstract String getEstado();

    public boolean pagar(Pedido pedido) {
        return false;
    }

    public boolean enviar(Pedido pedido) {
        return false;
    }

    public boolean entregar(Pedido pedido) {
        return false;
    }

    public boolean cancelar(Pedido pedido) {
        return false;
    }
}
