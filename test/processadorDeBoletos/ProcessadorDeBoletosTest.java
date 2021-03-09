import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fatura.Fatura;
import boleto.Boleto;
import pagamento.Pagamento;
import processadordeboletos.ProcessadorDeBoletos;


@DisplayName("Classe para teste do processador de boletos")
public class ProcessadorDeBoletosTest {
  private ProcessadorDeBoletos ProcessadorDeBoletos;
	
	@BeforeEach
	public void init() {
		ProcessadorDeBoletos = new ProcessadorDeBoletos();
	}
	
	@DisplayName("Testa que um pagamento é criado para cada boleto da lista")
	@Test
	public void testCriaçãoDePagamentosPorBoleto() {
		Fatura faturaA = new Fatura(new Date(), 1450, "Nicholas Ferrer");

		Boleto boletoA1 = new Boleto(faturaA, new Date(), "A", 450);
		Boleto boletoA2 = new Boleto(faturaA, new Date(), "B", 200);

		ArrayList<Boleto> listaDeBoletos = new ArrayList();

		listaDeBoletos.add(boletoA1);
		listaDeBoletos.add(boletoA2);

		Integer numBoletosProcessados = ProcessadorDeBoletos.init(listaDeBoletos);

		Pagamento pagamento = new Pagamento(boletoA1, new Date(), 0, "BOLETO");

		Assertions.assertEquals(numBoletosProcessados, pagamento.QtdDePagamentos());
	}

  @DisplayName("Testa se boletos cuja soma de valores pagos é igual ou maior ao valor da fatura e resulta em fatura PAGA")
  @Test
  public void testFaturaPaga() {
		Fatura faturaB = new Fatura(new Date(), 1100, "Mariana Freire");

		Boleto boletoB1 = new Boleto(faturaB, new Date(), "E",100);
		Boleto boletoB2 = new Boleto(faturaB, new Date(), "F", 1000);

		ArrayList<Boleto> listaDeBoletos = new ArrayList();

		listaDeBoletos.add(boletoB1);
		listaDeBoletos.add(boletoB2);

		ProcessadorDeBoletos.init(listaDeBoletos);

		Assertions.assertEquals(faturaB.status, "PAGA");
  }

	@DisplayName("Testa se a soma dos boletos processados é menor que o valor da fatura e resulta em fatura NAO_PAGA")
	@Test
	public void testFaturaNãoPaga() {
		Fatura faturaC = new Fatura(new Date(), 1700, "Cleide Santos");

		Boleto boletoC1 = new Boleto(faturaC, new Date(), "E", 300);
		Boleto boletoC2 = new Boleto(faturaC, new Date(), "F", 1000);

		ArrayList<Boleto> listaDeBoletos = new ArrayList();

		listaDeBoletos.add(boletoC1);
		listaDeBoletos.add(boletoC2);

		ProcessadorDeBoletos.init(listaDeBoletos);

		Assertions.assertEquals(faturaC.status, "NAO_PAGA");
	}
}