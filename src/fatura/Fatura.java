package fatura;

import java.util.Date;

public class Fatura {
    public String status;
    public Date data;
    public Integer valorTotal;
    public String nome;

    public Fatura(Date data, Integer valorTotal, String nome) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nome = nome;
        this.status = "EM_ABERTO";
    }

    public void atualizaStatus(String status) {
        this.status = status;
    }
}