import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<Iris> list = new ArrayList<>();

        Scanner scanner = new Scanner(new File("C:\\Users\\Cuba\\Desktop\\studia\\IV\\NAI\\cwiczenia\\2\\algor\\iris_training.txt"));

        while(scanner.hasNext()){
            String[] split = scanner.nextLine().split(" ");
            Iris iris = new Iris(split[0].replace(',','.'),split[1].replace(',','.'),split[2].replace(',','.'),split[3].replace(',','.'),split[4]);
            list.add(iris);
//            list.add(new Iris(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3])));
        }

        list.stream().forEach(System.out::println);



        Double min = Collections.min(list.stream().map(iris -> Double.parseDouble(iris.getT1())).collect(Collectors.toList()));
        Double max = Collections.max(list.stream().map(iris -> Double.parseDouble(iris.getT1())).collect(Collectors.toList()));

        System.out.println(min+" " + max);

        list.stream().forEach(iris -> System.out.println((Double.parseDouble(iris.getT1())-min)/(max-min))

        );

        scanner.close();
    }
}
