package br.com.gianhaack.rachaconta.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RachaContaResource {

    @RequestMapping(value = "/rachar", method = GET)
    public ResponseEntity racharConta(){
        return ok().build();
    }
}
