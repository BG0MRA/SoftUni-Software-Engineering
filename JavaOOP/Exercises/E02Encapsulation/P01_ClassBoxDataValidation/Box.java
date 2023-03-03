package E02Encapsulation.P01_ClassBoxDataValidation;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    private void setLength(double length) {
        if (isValidArgument("Length", length)) {
            this.length = length;
        }
    }

    private void setWidth(double width) {
        if (isValidArgument("Width", width)) {
            this.width = width;
        }
    }

    private void setHeight(double height) {
        if (isValidArgument("Height", height)) {
            this.height = height;
        }
    }

    private boolean isValidArgument(String name, double argument) {
        if (argument > 0) {
            return true;
        } else {
            throw new IllegalStateException(name + " cannot be zero or negative.");
        }

    }

    public double calculateSurfaceArea() {
        return (2 * length * width + 2 * length * height + 2 * width * height);
    }

    public double calculateLateralSurfaceArea() {
        return (2 * length * height + 2 * width * height);
    }

    public double calculateVolume() {
        return length * width * height;
    }
}
