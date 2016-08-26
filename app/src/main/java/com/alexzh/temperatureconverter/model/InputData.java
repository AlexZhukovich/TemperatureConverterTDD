package com.alexzh.temperatureconverter.model;

public class InputData {

    private double value;
    private Temperature fromUnit;
    private Temperature toUnit;

    public InputData(double value, Temperature fromUnit, Temperature toUnit) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    public double getInputValue() {
        return value;
    }

    public Temperature getFromUnit() {
        return fromUnit;
    }

    public Temperature getToUnit() {
        return toUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof InputData))) return false;

        InputData inputData = (InputData) o;

        if (Double.compare(inputData.value, value) != 0) return false;
        if (fromUnit != inputData.fromUnit) return false;
        return toUnit == inputData.toUnit;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fromUnit != null ? fromUnit.hashCode() : 0);
        result = 31 * result + (toUnit != null ? toUnit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "value=" + value +
                ", fromUnit=" + fromUnit +
                ", toUnit=" + toUnit +
                '}';
    }
}