package org.example;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Statistic {
    public boolean calculateStatistic = false;

    public boolean integerUndefined = true;
    public BigInteger maxIntegerValue = BigInteger.ZERO;
    public BigInteger minIntegerValue = BigInteger.ZERO;
    public BigInteger sumOfIntegers = BigInteger.ZERO;
    public long integerCounter = 0;

    public boolean floatUndefined = true;
    public BigDecimal maxFloatValue = BigDecimal.ZERO;
    public BigDecimal minFloatValue = BigDecimal.ZERO;
    public BigDecimal sumOfFloats = BigDecimal.ZERO;
    public long floatCounter = 0;

    public boolean stringUndefined = true;
    public long stringCounter = 0;
    public long stringMaxLength = 0;
    public long stringMinLength = 0;

    public Statistic(Boolean calculateStatistic) {
        this.calculateStatistic = calculateStatistic;
    }

    public void addInteger(String value) {
        if (calculateStatistic) {
            BigInteger bigInteger = new BigInteger(value);
            if (integerUndefined) {
                maxIntegerValue = bigInteger;
                minIntegerValue = bigInteger;
                integerUndefined = false;
            } else {
                maxIntegerValue = bigInteger.max(maxIntegerValue);
                minIntegerValue = bigInteger.min(minIntegerValue);
            }
            sumOfIntegers = sumOfIntegers.add(bigInteger);
        }
        ++integerCounter;
    }

    public void addFloat(String value) {
        if (calculateStatistic) {
            BigDecimal bigDecimal = new BigDecimal(value);
            if (floatUndefined) {
                maxFloatValue = bigDecimal;
                minFloatValue = bigDecimal;
                floatUndefined = false;
            } else {
                maxFloatValue = bigDecimal.max(maxFloatValue);
                minFloatValue = bigDecimal.min(minFloatValue);
            }
            sumOfFloats = sumOfFloats.add(bigDecimal);
        }
        ++floatCounter;
    }

    public void addString(String value) {
        if (calculateStatistic) {
            int length = value.length();
            if (stringUndefined) {
                stringMaxLength = length;
                stringMinLength = length;
                stringUndefined = false;
            } else {
                stringMaxLength = Math.max(stringMaxLength, length);
                stringMinLength = Math.min(stringMinLength, length);
            }
        }
        ++stringCounter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Целые числа: ").append(integerCounter).append('\n');
        if (calculateStatistic && !integerUndefined) {
            sb.append("  Минимум: ").append(minIntegerValue).append('\n');
            sb.append("  Максимум: ").append(maxIntegerValue).append('\n');
            sb.append("  Сумма: ").append(sumOfIntegers).append('\n');
            sb.append("  Среднее: ").append(
                    integerCounter > 0 ? sumOfIntegers.divide(BigInteger.valueOf(integerCounter)) : "N/A"
            ).append('\n');
        }
        sb.append("Числа с плавающей точкой: ").append(floatCounter).append('\n');
        if (calculateStatistic && !floatUndefined) {
            sb.append("  Минимум: ").append(minFloatValue).append('\n');
            sb.append("  Максимум: ").append(maxFloatValue).append('\n');
            sb.append("  Сумма: ").append(sumOfFloats).append('\n');
            sb.append("  Среднее: ").append(
                    floatCounter > 0 ? sumOfFloats.divide(BigDecimal.valueOf(floatCounter), BigDecimal.ROUND_HALF_UP) : "N/A"
            ).append('\n');
        }
        sb.append("Строки: ").append(stringCounter).append('\n');
        if (calculateStatistic && !stringUndefined) {
            sb.append("  Мин. длина: ").append(stringMinLength).append('\n');
            sb.append("  Макс. длина: ").append(stringMaxLength).append('\n');
        }
        return sb.toString();
    }

    String getShortStatistic() {
        StringBuilder sb = new StringBuilder();
        sb.append("Краткая статистика:\n");
        sb.append("Целые числа: ").append(integerCounter).append('\n');
        sb.append("Числа с плавающей точкой: ").append(floatCounter).append('\n');
        sb.append("Строки: ").append(stringCounter).append('\n');
        return sb.toString();
    }
}
