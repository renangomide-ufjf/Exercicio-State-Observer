package padroescomportamentais.integracao.state_observer;

public class PedidoEstadoCriado extends PedidoEstado {

    private PedidoEstadoCriado() {}

    private static PedidoEstadoCriado instance = new PedidoEstadoCriado();

    public static PedidoEstadoCriado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Criado";
    }

    @Override
    public boolean pagar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoPago.getInstance());
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }
}
