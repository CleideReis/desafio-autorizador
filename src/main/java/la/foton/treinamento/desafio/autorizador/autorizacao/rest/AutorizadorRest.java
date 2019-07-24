package la.foton.treinamento.desafio.autorizador.autorizacao.rest;

import la.foton.treinamento.desafio.autorizador.autorizacao.service.AutorizadorService;
import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoTransferencia;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("autorizar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorizadorRest {

    @EJB
    private AutorizadorService autorizadorService;

    @POST
    @Path("deposito")
    public Response validarDeposito(Transacao transacao) {
        return Response.ok(autorizadorService.executa(transacao)).build();
    }

    @POST
    @Path("saque")
    public Response validarSaque(Transacao transacao) {
        return Response.ok(autorizadorService.executa(transacao)).build();
    }

    @POST
    @Path("transferencia")
    public Response validarTransferencia(TransacaoTransferencia transacaoTransferencia) {
        return Response.ok(autorizadorService.executa(transacaoTransferencia)).build();
    }

}
