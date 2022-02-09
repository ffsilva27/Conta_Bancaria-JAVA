package Conta;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter

public class OperacaoBancaria {
    private String operador;
    private String tipoOperacao;
    private float valorOperacao;
    private String dataHoraOperacao;

    public OperacaoBancaria(String operador, String tipoOperacao, float valorOperacao, String dataHoraOperacao) {
        this.operador = operador;
        this.tipoOperacao = tipoOperacao;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacaoBancaria that = (OperacaoBancaria) o;
        return Float.compare(that.valorOperacao, valorOperacao) == 0 && Objects.equals(operador, that.operador) && Objects.equals(tipoOperacao, that.tipoOperacao) && Objects.equals(dataHoraOperacao, that.dataHoraOperacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operador, tipoOperacao, valorOperacao, dataHoraOperacao);
    }

    @Override
    public String toString() {
        return "OperacaoBancaria{" +
                "operador='" + operador + '\'' +
                ", tipoOperacao='" + tipoOperacao + '\'' +
                ", valorOperacao=" + valorOperacao +
                ", dataHoraOperacao='" + dataHoraOperacao + '\'' +
                '}';
    }
}
