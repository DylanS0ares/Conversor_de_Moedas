import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String moeda1 = "";
        String moeda2 = "";
        int num = 0;
        while (num != 7) {
            System.out.println("Olá seja bem vindo ao conversor de Moedas!\n");
            System.out.println("Qual tipo de conversão o senhor(a) gostaria de realizar?\n" +
                    "1- Dólar ==> Peso argentino\n" +
                    "2- Peso argentino ==> Dólar\n" +
                    "3- Dólar ==> Real brasileiro\n" +
                    "4- Real brasileiro ==> Dólar\n" +
                    "5- Dólar ==> Peso colombiano\n" +
                    "6- Peso colombiano ==> Dólar\n" +
                    "7- Sair\n");

            num = sc.nextInt();
            switch (num) {
                case 1:
                    moeda1 = "USD";
                    moeda2 = "ARS";
                    break;
                case 2:
                    moeda2 = "USD";
                    moeda1 = "ARS";
                    break;
                case 3:
                    moeda2 = "BRL";
                    moeda1 = "USA";
                    break;
                case 4:
                    moeda1 = "BRL";
                    moeda2 = "USA";
                    break;
                case 5:
                    moeda2 = "COP";
                    moeda1 = "USA";
                    break;
                case 6:
                    moeda1 = "COP";
                    moeda2 = "USA";
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Digite uma opção válida!\n");
                    break;
            }


            try {
                HttpClient client = HttpClient.newHttpClient();
                String endereco = "https://v6.exchangerate-api.com/v6/b0dc209f281716e451160118/latest" + "/" + moeda1;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
                Gson gson = new Gson();
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
                JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
                double valorUSD = rates.get(moeda2).getAsDouble();
                System.out.println("Quanto você quer converter ?");
                double valorconv = sc.nextInt();
                System.out.println("Converteu " + valorconv + "[" + moeda1 + "] para " + valorconv * valorUSD + "[" + moeda2 + "]\n");

            } catch (Exception e) {
                e.printStackTrace();

            }


        }

    }


}