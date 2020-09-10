package br.com.gianhaack.rachaconta.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResponseContaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal valorFinalConta;
    private BigDecimal valorAPagarPessoaPrincipal;
    private List<ResponsePessoaAdicionalDTO> pessoasAdicionais = new ArrayList<>();

    public BigDecimal getValorFinalConta() {
        return valorFinalConta;
    }

    public void setValorFinalConta(BigDecimal valorFinalConta) {
        this.valorFinalConta = valorFinalConta;
    }

    public BigDecimal getValorAPagarPessoaPrincipal() {
        return valorAPagarPessoaPrincipal;
    }

    public void setValorAPagarPessoaPrincipal(BigDecimal valorAPagarPessoaPrincipal) {
        this.valorAPagarPessoaPrincipal = valorAPagarPessoaPrincipal;
    }

    public List<ResponsePessoaAdicionalDTO> getPessoasAdicionais() {
        return pessoasAdicionais;
    }

    public void setPessoasAdicionais(List<ResponsePessoaAdicionalDTO> pessoasAdicionais) {
        this.pessoasAdicionais = pessoasAdicionais;
    }
}
