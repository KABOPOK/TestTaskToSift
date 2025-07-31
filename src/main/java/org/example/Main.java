package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataArgs dataArgs = new DataArgs(args);
        FileWriter fileWriter = new FileWriter(dataArgs.getOutPath(), dataArgs.getPrefix(), dataArgs.getAppend());
        try (Buffer buffer = new Buffer(dataArgs.getStatisticType(), fileWriter)) {
            for (String file : dataArgs.getFiles()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.processLine(line);
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении файла " + file + ": " + e.getMessage());
                }
            }
            if(dataArgs.getStatisticType().equals("-f")) {
                System.out.println(buffer.getStatistic().toString());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в выходные файлы: " + e.getMessage());
        }
    }
}