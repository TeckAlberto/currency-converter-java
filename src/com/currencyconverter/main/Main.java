package com.currencyconverter.main;

import com.currencyconverter.connections.ExchangeConnection;
import com.currencyconverter.models.ExchangeCurrency;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException,InterruptedException {

        Scanner scanner = new Scanner(System.in);

        String[] options = {"USD", "ARS", "USD", "MXM", "USD", "COP"};

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();


        while(true) {

            Main.showMenu();
            var input = scanner.nextInt();

            if(input == 7) {
                break;
            }

            try {
                ExchangeConnection ec = new ExchangeConnection(options[input - 1]);
                var json = ec.getResponse();

                ExchangeCurrency currency = gson.fromJson(json, ExchangeCurrency.class);

                System.out.println("Introduzca el valor que desea convertir: ");

                double value = scanner.nextDouble();

                switch (input) {
                    case 1:
                        showMessage(currency, "ARS", value);
                        break;

                    case 2:
                        showMessage(currency, "USD", value);
                        break;

                    case 3:
                        showMessage(currency, "MXM", value);
                        break;

                    case 4:
                        showMessage(currency, "USD", value);
                        break;

                    case 5:
                        showMessage(currency, "COP", value);
                        break;

                    case 6:
                        showMessage(currency, "USD", value);
                        break;
                    default:
                        System.out.println("Escoja una opcion valida!");
                        break;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }



        }

    }

    public static void showMenu() {
        var menu = """
                1 ) Dolar =>> Peso Argentino
                2 ) Peso Argentino =>> Dolar
                3 ) Dolar =>> Peso Mexicano
                4 ) Peso Mexicano =>> Dolar
                5 ) Dolar =>> Peso Colombiano
                6 ) Peso Colombiano =>> Dolar
                7 ) Salir
                
                Escoje una opcion:
                """;

        System.out.println(menu);
    }

    public static void showMessage(ExchangeCurrency obj, String code, Double value) {
        System.out.println("**********************************************");

        double total = obj.conversion_rates().get(code) * value;
        String message = String.format("""
                El valor %.2f [%s]
                corresponde al valor final de
                %.2f [%s]
                """, value, obj.base_code(), total, code);

        System.out.println(message);
    }
}
