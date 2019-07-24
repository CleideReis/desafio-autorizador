package la.foton.treinamento.desafio.autorizador;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {

    @GET
    public String aplicacaoAr( ) {
        return new String("Inicia api...");
    }
}
