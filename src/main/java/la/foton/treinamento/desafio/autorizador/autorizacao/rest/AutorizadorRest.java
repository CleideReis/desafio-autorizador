package la.foton.treinamento.desafio.autorizador.autorizacao.rest;

import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoExtratoPorPeriodo;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoFinaceira;
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
    public Response depositoResponse(TransacaoFinaceira transacaoFinaceira) {
        return executa(transacaoFinaceira);
    }

    @POST
    @Path("saque")
    public Response SaqueResponse(TransacaoFinaceira transacaoFinaceira) {
        return executa(transacaoFinaceira);
    }

    @POST
    @Path("transferencia")
    public Response transferenciaResponse(TransacaoTransferencia transacaoTransferencia) {
        return executa(transacaoTransferencia);
    }

    @POST
    @Path("saldo")
    public Response saldoResponse(Transacao transacao) {
        return executa(transacao);
    }

    @POST
    @Path("extrato")
    public Response extratoResponse(Transacao transacao) {
        return executa(transacao);
    }

    @POST
    @Path("extrato-por-periodo")
    public Response extratoPorPeriodoResponse(TransacaoExtratoPorPeriodo transacao) {
        return executa(transacao);
    }

}
