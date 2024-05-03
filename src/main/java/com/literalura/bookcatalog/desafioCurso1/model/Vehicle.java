package com.literalura.bookcatalog.desafioCurso1.model;

public class Vehicle {
    private String vehicleType;
    private String value;
    private String brand;
    private String model;
    private Integer year;
    private String fuel;

    public Vehicle(VehicleDetailsDto valueDto) {
        this.value = valueDto.value();
        this.brand = valueDto.brand();
        this.model = valueDto.model();
        this.year = Integer.parseInt(valueDto.modelYear());
        this.fuel = valueDto.fuel();
        this.vehicleType = getVehicleTypeFromNumber(valueDto.vehicleType());

    }

    private String getVehicleTypeFromNumber(Integer vehicleType) {
        return switch (vehicleType) {
            case 1 -> "cars";
            case 2 -> "motorcycles";
            case 3 -> "trucks";
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Vehicle[vehicleType=" + vehicleType +
                ", value=" + value +
                ", brand=" + brand +
                ", model=" + model +
                ", year=" + year +
                ", fuel=" + fuel + "]";
    }
}
