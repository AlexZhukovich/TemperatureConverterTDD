package com.alexzh.temperatureconverter.model;

public class ConvertedResult {

    private double result;
    private Temperature resultUnit;
    private InputData inputData;

    public ConvertedResult(double result, Temperature resultUnit, InputData inputData) {
        this.result = result;
        this.resultUnit = resultUnit;
        this.inputData = inputData;
    }

    public double getResult() {
        return result;
    }

    public Temperature getResultUnit() {
        return resultUnit;
    }

    public InputData getInputData() {
        return inputData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof ConvertedResult))) return false;

        ConvertedResult that = (ConvertedResult) o;

        if (Double.compare(that.result, result) != 0) return false;
        if (resultUnit != that.resultUnit) return false;
        return inputData != null ? inputData.equals(that.inputData) : that.inputData == null;

    }

    @Override
    public int hashCode() {
        int result1;
        long temp;
        temp = Double.doubleToLongBits(result);
        result1 = (int) (temp ^ (temp >>> 32));
        result1 = 31 * result1 + (resultUnit != null ? resultUnit.hashCode() : 0);
        result1 = 31 * result1 + (inputData != null ? inputData.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "ConvertedResult{" +
                "result=" + result +
                ", resultUnit=" + resultUnit +
                ", inputData=" + inputData +
                '}';
    }
}
