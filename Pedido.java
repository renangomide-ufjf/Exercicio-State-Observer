package padroescomportamentais.integracao.state_observer;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Pedido extends Observable {

    private int numero;
    private PedidoEstado estado;

    public Pedido(int numero) {
        this.numero = numero;
        this.estado = PedidoEstadoCriado.getInstance();
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        setChanged();
        notifyObservers();
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public String getNomeEstado() {
        return estado.getEstado();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean pagar() {
        return estado.pagar(this);
    }

    public boolean enviar() {
        return estado.enviar(this);
    }

    public boolean entregar() {
        return estado.entregar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", estado=" + estado.getEstado() +
                '}';
    }
}
