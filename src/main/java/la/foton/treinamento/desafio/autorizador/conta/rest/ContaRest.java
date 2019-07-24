package la.foton.treinamento.desafio.autorizador.conta.rest;

import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.dto.ContaDTO;
import la.foton.treinamento.desafio.autorizador.conta.dto.ContaResponseDTO;
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
    @Path("consulta")
    public Response consultaConta(@QueryParam("agencia") Integer agencia, @QueryParam("numero") Integer numero) throws NegocioException {
        return Response.ok(ContaResponseDTO.paraDTO(contaService.consultaConta(agencia, numero))).build();
    }
}
