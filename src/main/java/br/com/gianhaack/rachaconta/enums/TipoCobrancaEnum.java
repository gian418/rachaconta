package br.com.gianhaack.rachaconta.enums;

public enum TipoCobrancaEnum {

    PICPAY("https://www.picpay.com.br/cobrar/%s"),
    NUCONTA("https://www.nubank.com.br/cobrar/%s");

    private String linkCobranca;

    private TipoCobrancaEnum(String linkCobranca) {
        this.linkCobranca = linkCobranca;
    }

    public String getLinkCobranca() {
        return linkCobranca;
    }
}
