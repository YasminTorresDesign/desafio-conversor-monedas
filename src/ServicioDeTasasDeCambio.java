import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioDeTasasDeCambio {
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/c2911db6dea604ea5090b378/latest/USD";

    // Método para obtener las tasas de cambio desde la API
    public String obtenerTasasDeCambio() throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        return respuesta.body();  // Devolvemos la respuesta como un String
    }

    // Método para convertir el JSON en un objeto Java
    public RespuestaTasasDeCambio parsearTasasDeCambio(String respuestaJson) {
        Gson gson = new Gson();
        return gson.fromJson(respuestaJson, RespuestaTasasDeCambio.class);
    }
}
