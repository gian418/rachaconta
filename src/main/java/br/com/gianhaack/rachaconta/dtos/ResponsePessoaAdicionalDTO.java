package br.com.gianhaack.rachaconta.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResponsePessoaAdicionalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private BigDecimal valorAPagar;
    private String linkPagamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public String getLinkPagamento() {
        return linkPagamento;
    }

    public void setLinkPagamento(String linkPagamento) {
        this.linkPagamento = linkPagamento;
    }
}
