import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;
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
	public void inicializa() {
		ProcessadorDeBoletos = new ProcessadorDeBoletos();
	}
	
	@DisplayName("Testa que um pagamento é criado para cada boleto da lista")
	@Test
	public void testaCriaçãoDePagamentos() {
		Fatura faturaA = new Fatura(new Date(), 1450, "Nicholas Ferrer");

		Boleto boletoA1 = new Boleto(faturaA, new Date(), "A", 450);
		Boleto boletoA2 = new Boleto(faturaA, new Date(), "B", 200);

		List<Boleto> lista_de_boletos = Arrays.asList(boletoA1, boletoA2);

		Integer numero_de_boletos_processados = ProcessadorDeBoletos.init(lista_de_boletos);

		Pagamento pagamento = new Pagamento();

		Assertions.assertEquals(numero_de_boletos_processados, pagamento.retornarQuantidadeDePagamentos());
	}

  @DisplayName("Testa se boletos cuja soma de valores pagos é igual ou maior ao valor da fatura e resulta em fatura PAGA")
  @Test
  public void testaStatusDeFaturaPaga() {
		Fatura faturaB = new Fatura(new Date(), 1450, "Mariana Freire");

		Boleto boletoB1 = new Boleto(faturaB, new Date(), "E",450);
		Boleto boletoB2 = new Boleto(faturaB, new Date(), "F", 1000);

		List<Boleto> lista_de_boletos = Arrays.asList(boletoB1, boletoB2);

		ProcessadorDeBoletos.init(lista_de_boletos);

		Assertions.assertEquals(faturaB.status, "PAGA");
  }

	@DisplayName("Testa se a soma dos boletos processados é menor que o valor da fatura e resulta em fatura NAO_PAGA")
	@Test
	public void testaStatusDeFaturaNãoPaga() {
		Fatura faturaC = new Fatura(new Date(), 1450, "Cleide Santos");

		Boleto boletoC1 = new Boleto(faturaC, new Date(), "E", 450);
		Boleto boletoC2 = new Boleto(faturaC, new Date(), "F", 1000);

		List<Boleto> lista_de_boletos = Arrays.asList(boletoC1, boletoC2);

		ProcessadorDeBoletos.init(lista_de_boletos);

		Assertions.assertEquals(faturaC.status, "NAO_PAGA");
	}
}