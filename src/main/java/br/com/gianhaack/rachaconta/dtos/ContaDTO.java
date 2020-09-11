package br.com.gianhaack.rachaconta.dtos;

import br.com.gianhaack.rachaconta.enums.TipoCobrancaEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContaDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Preenchimento obrigatório")
    private BigDecimal valorTotal;

    private BigDecimal valorEntrega;

    private BigDecimal valorDesconto;

    private boolean descontoPercentual;

    private boolean taxaGarcom;

    private TipoCobrancaEnum tipoCobranca;

    private List<@Valid PessoaAdicionalDTO> pessoasAdicionais = new ArrayList<>();

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(BigDecimal valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public boolean isTaxaGarcom() {
        return taxaGarcom;
    }

    public void setTaxaGarcom(boolean taxaGarcom) {
        this.taxaGarcom = taxaGarcom;
    }

    public List<PessoaAdicionalDTO> getPessoasAdicionais() {
        return pessoasAdicionais;
    }

    public void setPessoasAdicionais(List<PessoaAdicionalDTO> pessoasAdicionais) {
        this.pessoasAdicionais = pessoasAdicionais;
    }

    public TipoCobrancaEnum getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(TipoCobrancaEnum tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public boolean isDescontoPercentual() {
        return descontoPercentual;
    }

    public void setDescontoPercentual(boolean descontoPercentual) {
        this.descontoPercentual = descontoPercentual;
    }

}
