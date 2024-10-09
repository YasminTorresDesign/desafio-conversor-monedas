import java.util.Map;

public class RespuestaTasasDeCambio {
    private Map<String, Double> conversion_rates; // Debe coincidir con la clave exacta en el JSON

    // Método para obtener las tasas de conversión
    public Map<String, Double> obtenerTasasDeConversion() {
        return conversion_rates;
    }
}
