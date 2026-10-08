package padroescomportamentais.integracao.state_observer;

public class PedidoEstadoCancelado extends PedidoEstado {

    private PedidoEstadoCancelado() {}

    private static PedidoEstadoCancelado instance = new PedidoEstadoCancelado();

    public static PedidoEstadoCancelado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Cancelado";
    }
}
