package processadordeboletos;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Array; 


import boleto.Boleto;
import pagamento.Pagamento;
import fatura.Fatura;

public class ProcessadorDeBoletos {
    public ProcessadorDeBoletos() {

    }

    public Integer init(ArrayList<Boleto> listaDeBoletos) {
        Integer totalValorBoletos = 0;
        Integer qtdBoletos = listaDeBoletos.size();
        Fatura fatura = listaDeBoletos.get(0).fatura;


        for(Boleto boleto_atual : listaDeBoletos) {

            new Pagamento(boleto_atual, new Date(), boleto_atual.valor , "BOLETO");

            totalValorBoletos += boleto_atual.valor;
        }

        if(totalValorBoletos >= fatura.valorTotal) {
            fatura.atualizaStatus("PAGA");
        } else {
            fatura.atualizaStatus("NAO_PAGA");
        }

        return qtdBoletos;
    }
}