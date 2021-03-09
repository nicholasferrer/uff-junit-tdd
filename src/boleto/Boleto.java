package boleto;

import java.util.Date;

import fatura.Fatura;

public class Boleto {
    public Fatura fatura;
    public Date data;
    public Integer codigo;
    public Integer valor;

	public Boleto(Fatura fatura, Date data, String ccdigo, Integer valor) {
		this.fatura = fatura;
		this.data = data;
		this.codigo = codigo;
		this.valor = valor;
	}
}