package pagamento;

import java.util.Date;

import fatura.Fatura;
import boleto.Boleto;

public class Pagamento {
    public Boleto boleto;
    public Date data;
    public Integer valor;
    public String tipoDePagamento;

    public static int count = 0;

    public Pagamento(Boleto boleto, Date data, Integer valor, String tipoDePagamento) {
        this.boleto = boleto;
        this.data = data;
        this.valor = valor;
        this.tipoDePagamento = tipoDePagamento;
        count++;
    }

    public Integer QtdDePagamentos() {
        return count;
    }
}