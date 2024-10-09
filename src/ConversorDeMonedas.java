import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class ConversorDeMonedas {
    public static void main(String[] args) {
        ServicioDeTasasDeCambio servicioDeTasas = new ServicioDeTasasDeCambio();
        FiltroDeMonedas filtroDeMonedas = new FiltroDeMonedas();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        try {
            // Obtener las tasas de cambio desde la API
            String respuestaJson = servicioDeTasas.obtenerTasasDeCambio();
            RespuestaTasasDeCambio respuestaTasas = servicioDeTasas.parsearTasasDeCambio(respuestaJson);
            Map<String, Double> tasas = respuestaTasas.obtenerTasasDeConversion();

            if (tasas == null) {
                System.out.println("No se pudo obtener las tasas de conversión. El programa finalizará.");
                return; // Termina el programa si no hay tasas
            }

            while (continuar) {
                System.out.println("----- Conversor de Monedas -----");
                System.out.println("1. Mostrar monedas disponibles");
                System.out.println("2. Convertir monedas");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        // Mostrar las monedas disponibles
                        filtroDeMonedas.filtrarYMostrarTasas(tasas);
                        break;

                    case 2:
                        // Conversión de monedas
                        System.out.println("Ingrese el código de la moneda de origen (por ejemplo, USD): ");
                        String monedaOrigen = scanner.next().toUpperCase();

                        System.out.println("Ingrese el código de la moneda de destino (por ejemplo, EUR): ");
                        String monedaDestino = scanner.next().toUpperCase();

                        if (!tasas.containsKey(monedaOrigen) || !tasas.containsKey(monedaDestino)) {
                            System.out.println("Moneda no válida. Intente nuevamente.");
                        } else {
                            System.out.println("Ingrese el monto a convertir: ");
                            double monto = scanner.nextDouble();

                            // Realizar la conversión
                            double tasaOrigen = tasas.get(monedaOrigen);
                            double tasaDestino = tasas.get(monedaDestino);

                            double montoConvertido = (monto / tasaOrigen) * tasaDestino;
                            System.out.printf("%.2f %s equivalen a %.2f %s%n", monto, monedaOrigen, montoConvertido, monedaDestino);
                        }
                        break;

                    case 3:
                        // Salir del programa
                        continuar = false;
                        System.out.println("Gracias por utilizar el conversor de monedas.");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }

        scanner.close();
    }
}

