package br.com.gianhaack.rachaconta.services;

import static java.lang.String.format;
import static br.com.gianhaack.rachaconta.enums.TipoCobrancaEnum.PICPAY;
import static br.com.gianhaack.rachaconta.enums.TipoCobrancaEnum.NUCONTA;

import br.com.gianhaack.rachaconta.dtos.ContaDTO;
import br.com.gianhaack.rachaconta.dtos.PessoaAdicionalDTO;
import br.com.gianhaack.rachaconta.dtos.ResponseContaDTO;
import br.com.gianhaack.rachaconta.dtos.ResponsePessoaAdicionalDTO;
import br.com.gianhaack.rachaconta.enums.TipoCobrancaEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class RachaContaService {

    public ResponseContaDTO calcular(ContaDTO contaDTO) {
        BigDecimal valorFinalConta = calcularValorFinalConta(contaDTO);
        BigDecimal valorPessoaPrincipal = contaDTO.getValorTotal();

        ResponseContaDTO responseContaDTO = new ResponseContaDTO();
        responseContaDTO.setValorFinalConta(valorFinalConta);

        for (PessoaAdicionalDTO pessoaAdicional : contaDTO.getPessoasAdicionais()) {
            valorPessoaPrincipal = valorPessoaPrincipal.subtract(pessoaAdicional.getValorTotal());
            BigDecimal valorFinalPessoa = taxaDaPessoa(pessoaAdicional.getValorTotal(), contaDTO.getValorTotal()).multiply(valorFinalConta);

            ResponsePessoaAdicionalDTO responsePessoaAdicionalDTO = new ResponsePessoaAdicionalDTO();
            responsePessoaAdicionalDTO.setNome(pessoaAdicional.getNome());
            responsePessoaAdicionalDTO.setValorAPagar(valorFinalPessoa);
            responsePessoaAdicionalDTO.setLinkPagamento(gerarLinkCobranca(contaDTO.getTipoCobranca(), UUID.randomUUID().toString()));

            responseContaDTO.getPessoasAdicionais().add(responsePessoaAdicionalDTO);
        }

        BigDecimal valorFinalPessoaPrincipal = taxaDaPessoa(valorPessoaPrincipal, contaDTO.getValorTotal()).multiply(valorFinalConta);
        responseContaDTO.setValorAPagarPessoaPrincipal(valorFinalPessoaPrincipal);

        return responseContaDTO;
    }

    private BigDecimal calcularValorFinalConta(ContaDTO contaDTO) {
        BigDecimal valorFinal = aplicarTaxaGarcom(contaDTO.isTaxaGarcom(), contaDTO.getValorTotal());
        valorFinal = valorFinal.subtract(calcularDesconto(valorFinal, contaDTO.getValorDesconto(), contaDTO.isDescontoPercentual()))
                .add(contaDTO.getValorEntrega());;

        return valorFinal;
    }

    private BigDecimal calcularDesconto(BigDecimal valor, BigDecimal valorDesconto, boolean isPercentual) {
        if(isPercentual) {
            BigDecimal taxaCalculo = valorDesconto.divide(new BigDecimal(100));
            return valor.multiply(taxaCalculo);
        }

        return  valorDesconto;
    }

    private BigDecimal aplicarTaxaGarcom(boolean isTaxaGarcom, BigDecimal valorConta) {
        return isTaxaGarcom ? valorConta.multiply(new BigDecimal(1.1)) : valorConta;
    }

    private BigDecimal taxaDaPessoa(BigDecimal valorItemDaPessoa, BigDecimal valorTotalConta) {
        return valorItemDaPessoa.divide(valorTotalConta);
    }

    private String gerarLinkCobranca(TipoCobrancaEnum tipoCobranca, String infoPagamento) {
        switch (tipoCobranca) {
            case PICPAY:
                return format(PICPAY.getLinkCobranca(), infoPagamento);
            case NUCONTA:
                return format(NUCONTA.getLinkCobranca(), infoPagamento);
            default:
                return "Tipo de cobrança não suportado";
        }
    }

}
