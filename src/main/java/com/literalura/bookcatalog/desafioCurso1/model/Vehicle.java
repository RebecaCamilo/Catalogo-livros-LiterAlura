package com.literalura.bookcatalog.desafioCurso1.model;

public class Vehicle {
    private String value;
    private String brand;
    private String model;
    private Integer year;
    private String fuel;

    public Vehicle(DetailsDto valueDto, String brand, String model, YearsModelDto yearsModelDto) {
        this.brand = brand;
        this.model = model;
        try {
            this.value = valueDto.value();
            String[] parts = yearsModelDto.yearsModelName().split(" ");
            this.year = Integer.parseInt(parts[0]);
            this.fuel = valueDto.fuel();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            this.value = null;
            this.year = null;
            this.fuel = null;
        }
    }

    @Override
    public String toString() {
        return "[value=" + value +
                ", brand=" + brand +
                ", model=" + model +
                ", year=" + year +
                ", fuel=" + fuel + "]";
    }
}
