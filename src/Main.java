import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = null;
        List<Car> list1 = new ArrayList<>();
        Map<String, Integer> insu = new HashMap();
        Map<String, Stack<String>> regNumb = new HashMap<>();
        try {
            Path resource = Paths.get("src", "resources");
            String absolutepath = resource.toFile().getAbsolutePath();
            File file = new File(absolutepath + "/testdata.txt");
            sc = new Scanner(file);

            while (sc.hasNext()) {

                String vin = sc.next();
                String RegNumber = sc.next();
                int year = sc.nextInt();
                String Date = sc.next();
                String OwnerFirstname = sc.next();
                String tOwnerLastName = sc.next();
                Car c = new Car(vin, RegNumber, year, Date, OwnerFirstname, tOwnerLastName);
                list1.add(c);
            }
            for (Car c1 : list1) {
                String vin = c1.getVIN();
                String RegNumber = c1.getRegNumber();
                if (regNumb.get(vin) == null) {
                    regNumb.put(vin, new Stack<>());
                }
                regNumb.get(vin).add(RegNumber);

                if (insu.containsKey(vin)) {
                    insu.put(vin, insu.get(vin) + 1);
                } else {
                    insu.put(vin, 1);
                }
            }

for(int i=0;i<5;i++){
            Map.Entry<String, Integer> entryWithMaxValue = null;
            for (Map.Entry<String, Integer> currentEntry :
                    insu.entrySet()) {

                if (
                    // If this is the first entry, set result as
                    // this
                        entryWithMaxValue == null

                                // If this entry's value is more than the
                                // max value Set this entry as the max
                                || currentEntry.getValue().compareTo(
                                entryWithMaxValue.getValue())
                                > 0) {

                    entryWithMaxValue = currentEntry;
                }
            }
 // Return the entry with highest value
            System.out.println(regNumb.get(entryWithMaxValue.getKey()).peek() + " " + insu.get(entryWithMaxValue.getKey()) + " " + " " + regNumb.get(entryWithMaxValue.getKey()).size());
    insu.remove(entryWithMaxValue.getKey());
}}
catch (FileNotFoundException e) {
            e.getMessage();
        } finally {
            sc.close();
        }
    }
}


