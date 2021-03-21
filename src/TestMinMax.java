public class TestMinMax {

   private double min;
   private double max;
   private String name;

    public TestMinMax(double min, double max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestMinMax{" +
                "min=" + min +
                ", max=" + max +
                ", name='" + name + '\'' +
                '}';
    }
}
