package br.com.gianhaack.rachaconta.resources;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import br.com.gianhaack.rachaconta.dtos.ContaDTO;
import br.com.gianhaack.rachaconta.dtos.PessoaAdicionalDTO;
import br.com.gianhaack.rachaconta.services.RachaContaService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@WebMvcTest
public class RachaContaResourceTest {

    @MockBean
    private RachaContaService rachaContaService;

    @Autowired
    private RachaContaResource resource;

    Gson gson = new Gson();

    private static final String endpoint = "/rest/rachar";

    @BeforeEach
    public void setup() {
        standaloneSetup(this.resource);
    }

    @Test
    public void deveRetornarOK_QuandoPopularValoTotalContaSemPessoaAdicional() {
        ContaDTO conta = new ContaDTO();
        conta.setValorTotal(new BigDecimal(50));

        given()
                .contentType(JSON)
                .body(gson.toJson(conta))
                .when().post(endpoint)
                .then().statusCode(OK.value());
    }

    @Test
    public void deveRetornarOK_QuandoPopularValoTotalContaComPessoaAdicional() {
        ContaDTO conta = new ContaDTO();
        conta.setValorTotal(new BigDecimal(50));

        PessoaAdicionalDTO pessoaAdicional = new PessoaAdicionalDTO();
        pessoaAdicional.setNome("Pedrinho");
        pessoaAdicional.setValorTotal(new BigDecimal(8));
        conta.getPessoasAdicionais().add(pessoaAdicional);

        given()
                .contentType(JSON)
                .body(gson.toJson(conta))
                .when().post(endpoint)
                .then().statusCode(OK.value());
    }

    @Test
    public void deveRetornarBadRequest_QuandoNaoPopularValorTotalContaSemPessoaAdicional() {
        given()
                .contentType(JSON)
                .body("{}")
                .when().post(endpoint)
                .then().statusCode(BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarBadRequest_QuandoNaoPouplarValorTotalContaComPessoaAdicional() {
        ContaDTO conta = new ContaDTO();
        PessoaAdicionalDTO pessoaAdicional = new PessoaAdicionalDTO();
        pessoaAdicional.setNome("Yoda");
        pessoaAdicional.setValorTotal(new BigDecimal(8));
        conta.getPessoasAdicionais().add(pessoaAdicional);

        given()
                .contentType(JSON)
                .body(conta)
                .when().post(endpoint)
                .then().statusCode(BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarBadRequest_QuandoNaoPopularNomeDaPessoaAdicional() {
        ContaDTO conta = new ContaDTO();
        conta.setValorTotal(new BigDecimal(50));

        PessoaAdicionalDTO pessoaAdicional = new PessoaAdicionalDTO();
        pessoaAdicional.setValorTotal(new BigDecimal(8));
        conta.getPessoasAdicionais().add(pessoaAdicional);

        given()
                .contentType(JSON)
                .body(conta)
                .when().post(endpoint)
                .then().statusCode(BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarBadRequest_QuandoNaoPopularValoTotalDaPessoaAdicional() {
        ContaDTO conta = new ContaDTO();
        conta.setValorTotal(new BigDecimal(50));

        PessoaAdicionalDTO pessoaAdicional = new PessoaAdicionalDTO();
        pessoaAdicional.setNome("Luke");
        conta.getPessoasAdicionais().add(pessoaAdicional);

        given()
                .contentType(JSON)
                .body(conta)
                .when().post(endpoint)
                .then().statusCode(BAD_REQUEST.value());
    }

}
