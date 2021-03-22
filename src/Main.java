import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    private static int k;

    static Map<Integer, List<Iris>> testColumnMap = new HashMap<>();
    static Map<Integer, TestMinMax> testMinMaxMap = new HashMap<>();
    private static List<String> description = new ArrayList<>();
//    List<Double> userInputList = new ArrayList<>();
    static List<UserDefinedIris>userDefinedIrisList= new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboardScanner = new Scanner(System.in);

        boolean ryg = true;
        while (ryg) {
            System.out.println("Podaj K albo zakończ [e]");
            String input= keyboardScanner.nextLine();
            if (input.equalsIgnoreCase("e")) {
                ryg = false;
            } else {
                k = Integer.parseInt(input);
                System.out.println("Podaj nowe parametry :");
                boolean ryg2 = true;
                while(ryg2){
                    System.out.println("Podaj param albo zakończ [END]");

                    String param = keyboardScanner.nextLine();
                    if(param.equalsIgnoreCase("END")){
                        ryg2 = false;
                    }else{
                        userDefinedIrisList.add(new UserDefinedIris(param));
                    }

                }


            }
        }
        keyboardScanner.close();



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
                if (i == 0) {
                    description.add(split[split.length - 1]);
                }
                testColumnMap.get(i + 1).add(new Iris(split[i].replace(",", ".")));
            }

            pilot++;
        }

        AtomicInteger pilot2= new AtomicInteger();

        testColumnMap.forEach((key, value) -> {
            List<Double> parsedTvalues = value.stream().map(iris -> Double.parseDouble(iris.getT())).collect(Collectors.toList());
            List<Double> emptyList = new ArrayList<>();
            emptyList.addAll(parsedTvalues);
//            Collections.copy(emptyList,parsedTvalues);
            emptyList.add(userDefinedIrisList.get(pilot2.get()).gettValue());
            Double min = Collections.min(emptyList);
            Double max = Collections.max(emptyList);
            testMinMaxMap.put(key, new TestMinMax(min, max, String.format("T%d", key)));
            pilot2.incrementAndGet();
        });

        normalizeT();
        calculateSqrt();
        scanner.close();



    }
//
//    private static Double normalizeUserT(List<UserDefinedIris> userDefinedIrisList, M)

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
        Map<Integer, CalculatedRowsAndIrisType> calculatedRowsMap = new LinkedHashMap<>();
        AtomicInteger rowNumber = new AtomicInteger();

        testColumnMap.get(1).forEach(t -> {
            double sumOfT = 0;
            for (int i = 0; i < testColumnMap.size(); i++) {
                sumOfT += Math.pow(testColumnMap.get(i + 1).get(rowNumber.get()).getNormalizedT(), 2);
            }
            calculatedRowsMap.put(rowNumber.get() + 1, new CalculatedRowsAndIrisType(description.get(rowNumber.get()), Math.sqrt(sumOfT)));
            rowNumber.getAndIncrement();
        });


        LinkedHashMap<Integer, CalculatedRowsAndIrisType> collectedMap = calculatedRowsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        System.out.println("finish");


    }


//    public int closest(double in, ) {
//        int min = Integer.MAX_VALUE;
//        int closest = of;
//
//        for (int v : in) {
//            final int diff = Math.abs(v - of);
//
//            if (diff < min) {
//                min = diff;
//                closest = v;
//            }
//        }
//
//        return closest;
//    }


}
