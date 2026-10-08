package padroescomportamentais.integracao.state_observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoIntegrationTest {

    @Test
    void deveNotificarClienteTransicaoValida() {
        Pedido pedido = new Pedido(1);
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanhar(pedido);

        assertTrue(pedido.pagar());
        assertEquals(PedidoEstadoPago.getInstance(), pedido.getEstado());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Pago}", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarMultiplosClientesMesmoPedido() {
        Pedido pedido = new Pedido(1);
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        cliente1.acompanhar(pedido);
        cliente2.acompanhar(pedido);

        assertTrue(pedido.pagar());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Pago}", cliente1.getUltimaNotificacao());
        assertEquals("Cliente 2, status do pedido atualizado: Pedido{numero=1, estado=Pago}", cliente2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteQuandoTransicaoFalhar() {
        Pedido pedido = new Pedido(1);
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanhar(pedido);

        assertFalse(pedido.entregar());
        assertEquals(PedidoEstadoCriado.getInstance(), pedido.getEstado());
        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarApenasClienteDoPedidoCorreto() {
        Pedido pedido1 = new Pedido(1);
        Pedido pedido2 = new Pedido(2);
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        cliente1.acompanhar(pedido1);
        cliente2.acompanhar(pedido2);

        assertTrue(pedido1.pagar());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Pago}", cliente1.getUltimaNotificacao());
        assertNull(cliente2.getUltimaNotificacao());
    }

    @Test
    void deveNotificarSequenciaCompletaCicloDeVida() {
        Pedido pedido = new Pedido(1);
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanhar(pedido);

        assertTrue(pedido.pagar());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Pago}", cliente.getUltimaNotificacao());

        assertTrue(pedido.enviar());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Enviado}", cliente.getUltimaNotificacao());

        assertTrue(pedido.entregar());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Entregue}", cliente.getUltimaNotificacao());
        assertEquals(PedidoEstadoEntregue.getInstance(), pedido.getEstado());
    }

    @Test
    void deveNotificarCancelamentoPedido() {
        Pedido pedido = new Pedido(1);
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanhar(pedido);

        assertTrue(pedido.cancelar());
        assertEquals(PedidoEstadoCancelado.getInstance(), pedido.getEstado());
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Cancelado}", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveDispararNovaNotificacaoAposCancelamentoEmTransicaoInvalida() {
        Pedido pedido = new Pedido(1);
        Cliente cliente = new Cliente("Cliente 1");
        cliente.acompanhar(pedido);

        assertTrue(pedido.cancelar());
        String notificacaoCancelado = cliente.getUltimaNotificacao();
        assertEquals("Cliente 1, status do pedido atualizado: Pedido{numero=1, estado=Cancelado}", notificacaoCancelado);

        assertFalse(pedido.pagar());
        assertEquals(notificacaoCancelado, cliente.getUltimaNotificacao());
    }
}
