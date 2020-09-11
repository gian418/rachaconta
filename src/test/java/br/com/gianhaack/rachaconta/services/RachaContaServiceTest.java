package br.com.gianhaack.rachaconta.services;

import br.com.gianhaack.rachaconta.dtos.ContaDTO;
import br.com.gianhaack.rachaconta.dtos.PessoaAdicionalDTO;
import br.com.gianhaack.rachaconta.dtos.ResponseContaDTO;
import br.com.gianhaack.rachaconta.enums.TipoCobrancaEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@SpringBootTest
public class RachaContaServiceTest {

    @Autowired
    private RachaContaService service;

    /**
     * Testa se o valor final da conta vai estar correto em uma situações onde
     * a conta tem desconto real (nao percentual), nao tem taxa de garcom,
     * possui entrega e nao possui pessoas adicionais
     */
    @Test
    public void valorFinalComEntregaComDescontoRealSemTaxaGarcom() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDescontoPercentual(false);
        contaDTO.setValorDesconto(new BigDecimal(20));
        contaDTO.setTaxaGarcom(false);
        contaDTO.setValorEntrega(new BigDecimal(8));
        contaDTO.setValorTotal(new BigDecimal(50));
        contaDTO.setTipoCobranca(TipoCobrancaEnum.PICPAY);

        ResponseContaDTO response = service.calcular(contaDTO);
        BigDecimal valorFinal = response.getValorFinalConta();

        Assert.isTrue(
                valorFinal.compareTo(new BigDecimal(38)) == 0,
                "Problema ao calcular o valor final da conta que não possui pessoas adicionais e sem taxa garcom"
        );
    }

    /**
     * Testa se o valor a pagar pelo principal vai estar correto em uma situação onde
     * a conta tem desconto real (nao percentual), nao tem taxa de garcom,
     * possui entrega e nao possui pessoas adicionais
     */
    @Test
    public void valorAPagarPeloPrincipalComEntregaComDescontoRealSemTaxaGarcom() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDescontoPercentual(false);
        contaDTO.setValorDesconto(new BigDecimal(20));
        contaDTO.setTaxaGarcom(false);
        contaDTO.setValorEntrega(new BigDecimal(8));
        contaDTO.setValorTotal(new BigDecimal(50));
        contaDTO.setTipoCobranca(TipoCobrancaEnum.PICPAY);

        ResponseContaDTO response = service.calcular(contaDTO);
        BigDecimal valorAPagar = response.getValorAPagarPessoaPrincipal();

        Assert.isTrue(
                valorAPagar.compareTo(new BigDecimal(38)) == 0,
                "Problema ao calcular o valor a pagar pelo principal numa conta sem pessoas adicionais e sem taxa garcom"
        );
    }

    /**
     * Testa se o valor final da conta vai estar correto em uma situações onde
     * a conta tem desconto real (nao percentual), com taxa de garcom,
     * possui entrega e nao possui pessoas adicionais
     */
    @Test
    public void valorFinalComEntregaComDescontoRealComTaxaGarcom() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDescontoPercentual(false);
        contaDTO.setValorDesconto(new BigDecimal(20));
        contaDTO.setTaxaGarcom(true);
        contaDTO.setValorEntrega(new BigDecimal(8));
        contaDTO.setValorTotal(new BigDecimal(50));
        contaDTO.setTipoCobranca(TipoCobrancaEnum.PICPAY);

        ResponseContaDTO response = service.calcular(contaDTO);
        BigDecimal valorFinal = response.getValorFinalConta().setScale(2, BigDecimal.ROUND_HALF_EVEN);

        Assert.isTrue(
                valorFinal.compareTo(new BigDecimal(43).setScale(2, BigDecimal.ROUND_HALF_EVEN)) == 0,
                "Problema ao calcular o valor final da conta que não possui pessoas adicionais e com taxa garcom"
        );
    }

    /**
     * Testa se o valor a pagar pelo principal vai estar correto em uma situação onde
     * a conta tem desconto real (nao percentual), possui taxa de garcom,
     * possui entrega e nao possui pessoas adicionais
     */
    @Test
    public void valorAPagarPeloPrincipalComEntregaComDescontoRealComTaxaGarcom() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDescontoPercentual(false);
        contaDTO.setValorDesconto(new BigDecimal(20));
        contaDTO.setTaxaGarcom(true);
        contaDTO.setValorEntrega(new BigDecimal(8));
        contaDTO.setValorTotal(new BigDecimal(50));
        contaDTO.setTipoCobranca(TipoCobrancaEnum.PICPAY);

        ResponseContaDTO response = service.calcular(contaDTO);
        BigDecimal valorAPagar = response.getValorAPagarPessoaPrincipal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

        Assert.isTrue(
                valorAPagar.compareTo(new BigDecimal(43).setScale(2, BigDecimal.ROUND_HALF_EVEN)) == 0,
                "Problema ao calcular o valor a pagar pelo principal numa conta sem pessoas adicionais e com taxa garcom"
        );
    }

    /**
     * Testa se o valor a pagar pelo principal vai estar correto em uma situação onde
     * a conta tem desconto real (nao percentual), sem taxa de garcom,
     * possui entrega e com uma pessoa adicional
     */
    @Test
    public void valorAPagarPeloPrincipalComEntregaComDescontoRealSemTaxaGarcomUmaPessoaAdicional() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDescontoPercentual(false);
        contaDTO.setValorDesconto(new BigDecimal(20));
        contaDTO.setTaxaGarcom(false);
        contaDTO.setValorEntrega(new BigDecimal(8));
        contaDTO.setValorTotal(new BigDecimal(50));
        contaDTO.setTipoCobranca(TipoCobrancaEnum.PICPAY);

        PessoaAdicionalDTO pessoaAdicional = new PessoaAdicionalDTO();
        pessoaAdicional.setNome("Maria");
        pessoaAdicional.setValorTotal(new BigDecimal(8));
        contaDTO.getPessoasAdicionais().add(pessoaAdicional);

        ResponseContaDTO response = service.calcular(contaDTO);
        BigDecimal valorAPagar = response.getValorAPagarPessoaPrincipal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

        Assert.isTrue(
                valorAPagar.compareTo(new BigDecimal(31.92).setScale(2, BigDecimal.ROUND_HALF_EVEN)) == 0,
                "Problema ao calcular o valor a pagar pelo principal numa conta sem pessoas adicionais e com taxa garcom"
        );
    }
}
