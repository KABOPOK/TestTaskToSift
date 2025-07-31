package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FileWriter {
    private static final String STRING_POSTFIX = "strings.txt";
    private static final String INTEGER_POSTFIX = "integers.txt";
    private static final String FLOAT_POSTFIX = "floats.txt";

    private final String prefix;
    private final Path outputDir;
    private boolean appendS;
    private boolean appendI;
    private boolean appendF;

    public FileWriter(String path, String prefix, boolean append) {
        this.outputDir = Paths.get(path != null ? path : ".");
        this.prefix = prefix != null ? prefix : "";
        this.appendS = append;
        this.appendI = append;
        this.appendF = append;
    }

    public void writeToStringFile(StringBuilder stringBuffer) throws IOException {
        writeToFile(STRING_POSTFIX, stringBuffer.toString());
    }

    public void writeToFloatFile(StringBuilder floatBuffer) throws IOException {
        writeToFile(FLOAT_POSTFIX, floatBuffer.toString());
    }

    public void writeToIntegerFile(StringBuilder integerBuffer) throws IOException {
        writeToFile(INTEGER_POSTFIX, integerBuffer.toString());
    }

    private void writeToFile(String fileName, String content) throws IOException {
        if (Files.notExists(outputDir)) {
            Files.createDirectories(outputDir);
        }
        boolean append = false;
        if (fileName.equals(STRING_POSTFIX)) append = appendS; appendS = true;
        if (fileName.equals(FLOAT_POSTFIX)) append = appendF; appendF = true;
        if (fileName.equals(INTEGER_POSTFIX)) append = appendI; appendI = true;
        OpenOption[] options = append
                ? new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND}
                : new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
        Path filePath = outputDir.resolve(prefix + fileName);
        Files.writeString(filePath, content, StandardCharsets.UTF_8, options);
    }
}
