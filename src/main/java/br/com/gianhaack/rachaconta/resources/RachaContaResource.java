package br.com.gianhaack.rachaconta.resources;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.http.ResponseEntity.ok;

import br.com.gianhaack.rachaconta.dtos.ContaDTO;
import br.com.gianhaack.rachaconta.dtos.ResponseContaDTO;
import br.com.gianhaack.rachaconta.services.RachaContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class RachaContaResource {

    @Autowired
    RachaContaService rachaContaService;

    @ApiOperation("Retorna o resultado do calcudo de quanto cada pessoa deve pagar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o resultado do calculo"),
            @ApiResponse(code = 400, message = "Problema algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/rachar", method = POST)
    public ResponseEntity racharConta(@RequestBody @Valid ContaDTO contaDTO) {
        ResponseContaDTO responseContaDTO = rachaContaService.calcular(contaDTO);
        return ok().body(responseContaDTO);
    }
}
