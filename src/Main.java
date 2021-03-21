import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    static Map<Integer,List<Iris>> testColumnMap = new HashMap<>();
    static Map<Integer,TestMinMax> testMinMaxMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        List<Iris> list = new ArrayList<>();

        Scanner scanner = new Scanner(new File("C:\\Users\\48505\\Desktop\\Iris\\iris_training.txt"));

        int pilot = 0;
        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(" ");


            if (pilot==0){
            for (int i=0;i<split.length-1;i++){
                testColumnMap.put(i+1, new ArrayList<>());
            }}

            for (int i=0;i<split.length-1;i++){
                testColumnMap.get(i+1).add(new Iris(split[i].replace(",",".")));
            }

            pilot++;
        }



        testColumnMap.entrySet().forEach(entry -> {
            Double min = Collections.min(entry.getValue().stream().map(iris -> Double.parseDouble(iris.getT())).collect(Collectors.toList()));
            Double max = Collections.max(entry.getValue().stream().map(iris -> Double.parseDouble(iris.getT())).collect(Collectors.toList()));
            testMinMaxMap.put(entry.getKey(),new TestMinMax(min,max,String.format("T%d",entry.getKey())));
        });
        scanner.close();
    }
}
