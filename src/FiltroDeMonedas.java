import java.util.Map;

public class FiltroDeMonedas {
    // Método para filtrar y mostrar solo las monedas de interés
    public void filtrarYMostrarTasas(Map<String, Double> tasas) {
        String[] monedasDeInteres = {"EUR", "GBP", "JPY"};  // Monedas que queremos filtrar

        // Iteramos sobre las monedas de interés y mostramos sus tasas
        for (String moneda : monedasDeInteres) {
            if (tasas.containsKey(moneda)) {
                System.out.println(moneda + ": " + tasas.get(moneda));
            }
        }
    }
}
