package Conta;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter

public class ContaBancaria {
    private String id;
    private String banco;
    private String agencia;
    private String conta;
    private ArrayList<OperacaoBancaria> operacaoBancaria = new ArrayList<>();
    private float saldo;
    private OperacaoBancaria operacao;

    public ContaBancaria(String id, String banco, String agencia, String conta) {
        this.id = id;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
    }

    public void operacoes(String operador, float valorOperacao, String tipoOperacao, String dataOperacao){
        float valorOperacaoAux = valorOperacao;
        if(tipoOperacao == "saque") {
            valorOperacaoAux = -valorOperacao;
        }
        operacaoBancaria.add(new OperacaoBancaria(operador, tipoOperacao, valorOperacao, dataOperacao));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaBancaria that = (ContaBancaria) o;
        return Objects.equals(conta, that.conta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conta);
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "id='" + id + '\'' +
                ", banco='" + banco + '\'' +
                ", agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", saldo='" + saldo + '\'' +
                '}';
    }


}
