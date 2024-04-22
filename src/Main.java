import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try{
            File file=new File("testdata.txt");
            Scanner sc=new Scanner(file);
            List<Car> list1=new ArrayList<>();
            while(sc.hasNext()){
 Car c=new Car();
 c.setVIN(sc.next());
 c.setRegNumber(sc.next());
 c.setYear(sc.nextInt());
 c.setDateOfInsurance(sc.next());
 c.setOwnerFirstName(sc.next());
 c.setOwnerLastName(sc.next());
 list1.add(c);
            }
            PriorityQueue<Map.Entry<String, Integer>> list2=new PriorityQueue<>(Collections.reverseOrder());
            for(Car c1:list1){
                    Map.Entry<String, Integer> m1 = new  AbstractMap.SimpleEntry<>(c1.getVIN(), 1);
                    for(Map.Entry<String, Integer>l2:list2){
                        if(l2.getKey().equals(m1.getKey())){
                            int n=l2.getValue();
                            l2.setValue(++n);
                        }
                    }
                    list2.add(m1);

            }
            for(int i=0;i<5;i++) {
                Map.Entry<String, Integer> m1= list2.poll();
                String vin=m1.getKey();
               String ownerLastName=null;
               int n=0;
               for(Car c2:list1){
                   if(c2.getVIN().equals(vin)&& !c2.getOwnerLastName().equals(ownerLastName)){
                       n++;
                   }
                   ownerLastName=c2.getOwnerLastName();
               }
                System.out.println(vin+" "+m1.getValue()+ n);


            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        }
    }
