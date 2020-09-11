package br.com.gianhaack.rachaconta.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class PessoaAdicionalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Preenchimento obrigatório")
    private String nome;

    @NotNull(message = "Preenchimento obrigatório")
    private BigDecimal valorTotal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
