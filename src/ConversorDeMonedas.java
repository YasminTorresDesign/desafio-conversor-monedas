import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConversorDeMonedas {
    public static void main(String[] args) {
        ServicioDeTasasDeCambio servicioDeTasas = new ServicioDeTasasDeCambio();
        FiltroDeMonedas filtroDeMonedas = new FiltroDeMonedas();

        try {
            // Obtener las tasas de cambio desde la API
            String respuestaJson = servicioDeTasas.obtenerTasasDeCambio();
            System.out.println("Respuesta JSON: " + respuestaJson);  // Para verificar el contenido

            // Parsear la respuesta JSON
            RespuestaTasasDeCambio respuestaTasas = servicioDeTasas.parsearTasasDeCambio(respuestaJson);

            // Verificar si las tasas de conversión son nulas
            Map<String, Double> tasas = respuestaTasas.obtenerTasasDeConversion();
            if (tasas == null) {
                System.out.println("Las tasas de conversión son nulas.");
            } else {
                // Filtrar y mostrar las monedas de interés
                filtroDeMonedas.filtrarYMostrarTasas(tasas);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
    }
}

