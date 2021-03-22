public class CalculatedRowsAndIrisType implements Comparable {

    private String IrisName;
    private double sumOfT;

    public CalculatedRowsAndIrisType() {
    }

    public CalculatedRowsAndIrisType(String irisName, double sumOfT) {
        this.sumOfT = sumOfT;
        IrisName = irisName;
    }

    public double getSumOfT() {
        return sumOfT;
    }

    public void setSumOfT(double sumOfT) {
        this.sumOfT = sumOfT;
    }

    public String getIrisName() {
        return IrisName;
    }

    public void setIrisName(String irisName) {
        IrisName = irisName;
    }

    @Override
    public String toString() {
        return "CalculatedRowsAndIrisType{" +
                "IrisName='" + IrisName + '\'' +
                ", sumOfT=" + sumOfT +
                '}';
    }

    @Override
    public int compareTo(Object calculatedRowsAndIrisType) {

        if (getSumOfT() > ((CalculatedRowsAndIrisType) calculatedRowsAndIrisType).getSumOfT()) {
            return 1;
        }
        if (getSumOfT() < ((CalculatedRowsAndIrisType) calculatedRowsAndIrisType).getSumOfT()) {
            return -1;
        }
        return 0;
    }
}
