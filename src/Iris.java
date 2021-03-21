
public class Iris {

    private String t;

    public Iris(String t) {
        this.t = t;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "t='" + t + '\'' +
                '}';
    }
}
