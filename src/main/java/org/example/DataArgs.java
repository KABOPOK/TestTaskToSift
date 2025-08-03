package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataArgs {

    private final List<String> files;

    private String statisticType = "-";
    private String prefix = "";
    private String outPath = "";

    private Boolean append = false;

    public DataArgs(String[] args) {
        files = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-a":
                    append = true;
                    break;
                case "-s":
                case "-f":
                    statisticType = arg;
                    break;
                case "-o":
                    if (i + 1 < args.length) {
                        outPath = args[++i];
                        File outDir = new File(outPath);
                        if (!outDir.exists()) {
                            if (!outDir.mkdirs()) {
                                System.err.println("Ошибка: не удалось создать директорию по пути: " + outPath);
                                System.exit(1);
                            }
                        }
                        if (!outDir.isDirectory() || !outDir.canWrite()) {
                            System.err.println("Ошибка: указанный путь не является доступной для записи директорией: " + outPath);
                            System.exit(1);
                        }
                    }
                    else {
                        System.err.println("Ошибка: не указан путь после -o");
                        System.exit(1);
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        System.err.println("Ошибка: не указан префикс после -p");
                        System.exit(1);
                    }
                    break;
                default:
                    files.add(arg);
            }
        }
        if (files.isEmpty()) {
            System.err.println("Ошибка: не указаны входные файлы.");
            System.exit(1);
        }
    }

    public String getStatisticType() {
        return statisticType;
    }

    public Boolean getAppend() {
        return append;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getOutPath() {
        return outPath;
    }

}

