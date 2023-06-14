package ca.jbrains.pos;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        final PointOfSaleCommandInterpreter commandInterpreter = new PointOfSaleCommandInterpreter(
                new Sale(
                        new EnglishLanguageMessageFormat(),
                        new Catalog(
                                Map.of("12345", "CAD 7.95",
                                        "23456", "CAD 12.50"))));
        new CommandProcessor(
                new WarnOnEmptyCommandInterpreter(commandInterpreter))
                .process(
                        new InputStreamReader(System.in),
                        new OutputStreamWriter(System.out)
                );
    }
}
