package la.foton.treinamento.desafio.autorizador.conta.rest;

import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.dto.ContaDTO;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;
import la.foton.treinamento.desafio.autorizador.conta.service.ContaService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContaRest {

    @EJB
    private ContaService contaService;

    @POST
    @Path("cadastra")
    public Response cadastraConta(ContaDTO contaDTO) throws NegocioException {
        Conta conta = contaService.cadastraConta(contaDTO.retornaConta(), contaDTO.getCpfTitular());
        return Response.created(
                UriBuilder.fromPath("conta/consulta?agencia={agencia}&numero={numero}")
                        .build(conta.getAgencia(), conta.getNumero())
        ).build();
    }

    @GET
    @Path("consulta/{agencia}/{numero}")
    public Response consultaConta(@PathParam("agencia") Integer agencia, @PathParam("numero") Integer numero) throws NegocioException {
        return Response.ok(contaService.consultaConta(agencia, numero)).build();
    }

    @DELETE
    @Path("remove/{agencia}/{numero}")
    public Response removeConta(@PathParam("agencia") Integer agencia, @PathParam("numero") Integer numero) throws NegocioException {
        contaService.remove(agencia, numero);
        return Response.noContent().build();
    }
}
