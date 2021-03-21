
public class Iris {

    private String t;
    private double normalizedT;

    public Iris(String t) {
        this.t = t;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public void setNormalizedT(double normalizedT) {
        this.normalizedT = normalizedT;
    }

    public double getNormalizedT() {
        return normalizedT;
    }


    @Override
    public String toString() {
        return "Iris{" +
                "t='" + t + '\'' +
                '}';
    }
}
