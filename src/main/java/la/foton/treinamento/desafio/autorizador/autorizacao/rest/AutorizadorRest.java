package la.foton.treinamento.desafio.autorizador.autorizacao.rest;

import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoTransferencia;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("autorizar")
@Consumes(MediaType.APPLICATION_JSON)
public class AutorizadorRest extends AbstractAutorizadorRest {

    @POST
    @Path("deposito")
    public Response validarDeposito(Transacao transacao) {
        return executa(transacao);
    }

    @POST
    @Path("saque")
    public Response validarSaque(Transacao transacao) {
        return executa(transacao);
    }

    @POST
    @Path("transferencia")
    public Response validarTransferencia(TransacaoTransferencia transacaoTransferencia) {
        return executa(transacaoTransferencia);
    }

    @POST
    @Path("saldo")
    public Response validarSaldo(Transacao transacao) {
        return executa(transacao);
    }

    @POST
    @Path("extrato")
    public Response validarExtrato(Transacao transacao) {
        return executa(transacao);
    }

}
