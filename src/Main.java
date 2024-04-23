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
        Map<String, Set<String>> vinOwners = new HashMap<>();
        Map<String, Car> insRegNo = new HashMap<>();
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
                String owner = OwnerFirstname + tOwnerLastName;
                Car c = new Car(vin, RegNumber, year, Date, OwnerFirstname, tOwnerLastName);
                list1.add(c);
                if (vinOwners.get(vin) == null)
                    vinOwners.put(vin, new HashSet<>());
                vinOwners.get(vin).add(owner);

                if (insu.containsKey(vin)) {
                    insu.put(vin, insu.get(vin) + 1);
                } else {
                    insu.put(vin, 1);
                }

                if (!insRegNo.containsKey(vin) || (insRegNo.containsKey(vin) && compareDates(c.getDateOfInsurance(), insRegNo.get(vin).getDateOfInsurance()) > 0)) {
                    insRegNo.put(vin, c);
                }
            }

            for (int i = 0; i < 5; i++) {
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
                System.out.println(insRegNo.get(entryWithMaxValue.getKey()).getRegNumber()+ " " + insu.get(entryWithMaxValue.getKey()) + " " + " " + vinOwners.get(entryWithMaxValue.getKey()).size());
                insu.remove(entryWithMaxValue.getKey());
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } finally {
            sc.close();
        }

    }

    private static int compareDates(String date1, String date2) {
        String[] d1 = date1.split("\\.");
        String[] d2 = date2.split("\\.");
        return (d1[2]).compareTo(d2[2]);
    }
}

