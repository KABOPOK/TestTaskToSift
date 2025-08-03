package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Buffer implements AutoCloseable {

    private static final int UPPER_LIMIT_BYTES = 1_000_000_000;

    private final StringBuilder stringBuffer = new StringBuilder();
    private final StringBuilder floatBuffer = new StringBuilder();
    private final StringBuilder integerBuffer = new StringBuilder();

    private int sizeStringBuffer = 0;
    private int sizeFloatBuffer = 0;
    private int sizeIntegerBuffer = 0;

    Statistic statistic;
    FileWriter fileWriter;

    Boolean calculateStatistic = false;

    public Buffer(String calculateStatisticType, FileWriter fileWriter) {
        if (calculateStatisticType.equals("-f")) calculateStatistic = true;
        statistic = new Statistic(calculateStatistic);
        this.fileWriter = fileWriter;
    }

    private BigInteger isInteger(String line) {
        try {
            return new BigInteger(line);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal isDecimal(String line) {
        try {
            return new BigDecimal(line);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void processLine(String line) throws IOException {
        BigInteger bigInteger = isInteger(line);
        BigDecimal bigDecimal = isDecimal(line);
        if(bigInteger != null) putInteger(line);
        else if (bigDecimal != null) putFloat(line);
        else putString(line);
    }

    private void putString(String stringValue) throws IOException {
        int size = 2 * stringValue.length() + 2;
        statistic.addString(stringValue);
        if (size + sizeStringBuffer  >= UPPER_LIMIT_BYTES) {
            fileWriter.writeToStringFile(stringBuffer);
            stringBuffer.setLength(0);
            sizeStringBuffer = 0;
        }
        stringBuffer.append(stringValue).append('\n');
        sizeStringBuffer += size;
    }

    private void putFloat(String floatValue) throws IOException {
        int size = 2*floatValue.length() + 2;
        statistic.addFloat(floatValue);
        if (size + sizeFloatBuffer >= UPPER_LIMIT_BYTES){
            fileWriter.writeToFloatFile(floatBuffer);
            floatBuffer.setLength(0);
            sizeFloatBuffer = 0;
        }
        floatBuffer.append(floatValue).append('\n');
        sizeFloatBuffer += size;
    }

    private void putInteger(String integerValue) throws IOException {
        int size = 2*integerValue.length() + 2;
        statistic.addInteger(integerValue);
        if (size + sizeIntegerBuffer >= UPPER_LIMIT_BYTES){
            fileWriter.writeToIntegerFile(integerBuffer);
            integerBuffer.setLength(0);
            sizeIntegerBuffer = 0;
        }
        integerBuffer.append(integerValue).append('\n');
        sizeIntegerBuffer+=size;
    }

    private void clearBuffer() throws IOException {
        if (!stringBuffer.isEmpty()) fileWriter.writeToStringFile(stringBuffer);
        if (!floatBuffer.isEmpty()) fileWriter.writeToFloatFile(floatBuffer);
        if (!integerBuffer.isEmpty()) fileWriter.writeToIntegerFile(integerBuffer);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    @Override
    public void close() throws IOException {
        clearBuffer();
    }

}
