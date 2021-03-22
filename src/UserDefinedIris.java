public class UserDefinedIris {

    private double tValue;

    public UserDefinedIris(String param) {
        tValue = Double.parseDouble(param);
    }

    public double gettValue() {
        return tValue;
    }

    public void settValue(double tValue) {
        this.tValue = tValue;
    }

    @Override
    public String toString() {
        return "UserDefinedIris{" +
                "tValue=" + tValue +
                '}';
    }
}
