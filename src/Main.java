import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.lang.Double.MAX_VALUE;

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



//        Scanner scanner = new Scanner(new File("C:\\Users\\Cuba\\Desktop\\studia\\IV\\NAI\\cwiczenia\\2\\Iris\\iris_training.txt"));
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
            List<Double> dummyList = new ArrayList<>();
            dummyList.addAll(parsedTvalues);
//            Collections.copy(dummyList,parsedTvalues);
            dummyList.add(userDefinedIrisList.get(pilot2.get()).gettValue());
            Double min = Collections.min(dummyList);
            Double max = Collections.max(dummyList);
            testMinMaxMap.put(key, new TestMinMax(min, max, String.format("T%d", key)));



            pilot2.incrementAndGet();
        });



        normalizeT();
        calculateSqrt();

        double closest = CollectionsExplorer.findClosest(normalizeUserT(), sortedMap);
        CollectionsExplorer.findAllIrises(k, sortedMap,closest);
        scanner.close();



    }
//
    private static Double normalizeUserT(){
        double normalizedValue[]= new double[userDefinedIrisList.size()];
        for(int i = 0;i<userDefinedIrisList.size();i++){
             normalizedValue[i] = (userDefinedIrisList.get(i).gettValue() - testMinMaxMap.get(i+1).getMin()) / (testMinMaxMap.get(i+1).getMax() - testMinMaxMap.get(i+1).getMin());
        }

        double v =0;
        for(double d :normalizedValue){
            v += Math.pow(d, 2);
        }



        return Math.sqrt(v);

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
    static LinkedHashMap<Integer, CalculatedRowsAndIrisType> sortedMap;

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


        sortedMap = calculatedRowsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));



    }


    public static Double closest(double userNormalizedValue) {

        AtomicReference<Double> min = new AtomicReference<>(MAX_VALUE);
        AtomicReference<Double> closest = new AtomicReference<>(userNormalizedValue);

        Double finalClosest = closest.get();
        double finalMin = min.get();
        sortedMap.forEach((key, value) -> {
            final double diff = Math.abs(value.getSumOfT()- finalClosest);

            if (diff < finalMin){
                min.set(diff);
                closest.set(value.getSumOfT());
            }
        });


        return closest.get();
    }


}
