import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<Iris> list = new ArrayList<>();

        Scanner scanner = new Scanner(new File("C:\\Users\\Cuba\\Desktop\\studia\\IV\\NAI\\cwiczenia\\2\\algor\\iris_training.txt"));

        while(scanner.hasNext()){
            String[] split = scanner.nextLine().split(" ");
            Iris iris = new Iris(split[0],split[1],split[2],split[3],split[4]);
            list.add(iris);
//            list.add(new Iris(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3])));
        }

        list.stream().forEach(System.out::println);
        scanner.close();
    }
}
