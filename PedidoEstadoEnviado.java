package padroescomportamentais.integracao.state_observer;

public class PedidoEstadoEnviado extends PedidoEstado {

    private PedidoEstadoEnviado() {}

    private static PedidoEstadoEnviado instance = new PedidoEstadoEnviado();

    public static PedidoEstadoEnviado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Enviado";
    }

    @Override
    public boolean entregar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        return true;
    }
}
