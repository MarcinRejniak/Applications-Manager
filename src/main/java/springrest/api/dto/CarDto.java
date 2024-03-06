package springrest.api.dto;

public class CarDto {
    private final String brand;
    private final String model;
    private final int year;

    public CarDto(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}
