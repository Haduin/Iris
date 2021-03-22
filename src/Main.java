import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    static Map<Integer, List<Iris>> testColumnMap = new HashMap<>();
    static Map<Integer, TestMinMax> testMinMaxMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\48505\\Desktop\\Iris\\iris_training.txt"));

        int pilot = 0;
        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(" ");
            if (pilot == 0) {
                for (int i = 0; i < split.length - 1; i++) {
                    testColumnMap.put(i + 1, new ArrayList<>());
                }
            }

            for (int i = 0; i < split.length - 1; i++) {
                testColumnMap.get(i + 1).add(new Iris(split[i].replace(",", ".")));
            }

            pilot++;
        }


        testColumnMap.forEach((key, value) -> {
            Double min = Collections.min(value.stream().map(iris -> Double.parseDouble(iris.getT())).collect(Collectors.toList()));
            Double max = Collections.max(value.stream().map(iris -> Double.parseDouble(iris.getT())).collect(Collectors.toList()));
            testMinMaxMap.put(key, new TestMinMax(min, max, String.format("T%d", key)));
        });

        normalizeT();
        calculateSqrt();
        scanner.close();
    }

    private static void normalizeT() {

        testColumnMap.forEach((key, value) -> {
            TestMinMax testMinMax = testMinMaxMap.get(key);
            value.forEach(t -> {
                double normalizedValue = (Double.parseDouble(t.getT()) - testMinMax.getMin()) / (testMinMax.getMax() - testMinMax.getMin());
                t.setNormalizedT(normalizedValue);

            });
        });
    }

    private static void calculateSqrt() {
        Map<Integer, Double> calculatedRowsMap = new HashMap<>();
        AtomicInteger rowNumber = new AtomicInteger();

        testColumnMap.get(1).forEach(t -> {
            double sumOfT = 0;
            for (int i = 0; i<testColumnMap.size(); i++){
                sumOfT +=Math.pow(testColumnMap.get(i+1).get(rowNumber.get()).getNormalizedT(),2);
            }
            calculatedRowsMap.put(rowNumber.get()+1,Math.sqrt(sumOfT));

            rowNumber.getAndIncrement();
        });

        System.out.println("finish");
    }


}
