import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    static Map<Integer,List<Iris>> testColumnMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        List<Iris> list = new ArrayList<>();

        Scanner scanner = new Scanner(new File("C:\\Users\\48505\\Desktop\\Iris\\iris_training.txt"));

        int pilot = 0;
        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split(" ");


            if (pilot==0){
            for (int i=0;i<split.length;i++){
                testColumnMap.put(i+1, new ArrayList<>());
            }}

            for (int i=0;i<split.length;i++){
                testColumnMap.get(i+1).add(new Iris(split[i]));
            }

            pilot++;
        }

        list.stream().forEach(System.out::println);

//
//        Double min = Collections.min(list.stream().map(iris -> Double.parseDouble(iris.getT1())).collect(Collectors.toList()));
//        Double max = Collections.max(list.stream().map(iris -> Double.parseDouble(iris.getT1())).collect(Collectors.toList()));
//
//        System.out.println(min + " " + max);
//
//        list.stream().forEach(iris -> System.out.println((Double.parseDouble(iris.getT1()) - min) / (max - min))
//
//        );

//        scanner.close();
    }
}
