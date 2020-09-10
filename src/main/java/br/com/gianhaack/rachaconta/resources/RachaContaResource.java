package br.com.gianhaack.rachaconta.resources;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.http.ResponseEntity.ok;

import javax.validation.Valid;
import br.com.gianhaack.rachaconta.dtos.ContaDTO;
import br.com.gianhaack.rachaconta.dtos.ResponseContaDTO;
import br.com.gianhaack.rachaconta.services.RachaContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RachaContaResource {

    @Autowired
    RachaContaService rachaContaService;

    @RequestMapping(value = "/rachar", method = POST)
    public ResponseEntity racharConta(@Valid @RequestBody ContaDTO contaDTO) {
        ResponseContaDTO responseContaDTO = rachaContaService.calcular(contaDTO);
        return ok().body(responseContaDTO);
    }
}
