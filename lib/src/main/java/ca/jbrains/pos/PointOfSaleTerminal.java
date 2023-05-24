package ca.jbrains.pos;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        new CommandProcessor(
                new PointOfSaleCommandInterpreter(
                        new Sale(
                                new EnglishLanguageMessageFormat(),
                                new Catalog(Map.of()))))
                .process(
                        new InputStreamReader(System.in),
                        new OutputStreamWriter(System.out)
                );
    }
}
