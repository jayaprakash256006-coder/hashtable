
public class ParkingLotSystem{

    static String[] parking=new String[10];

    public static int hash(String plate){
        return Math.abs(plate.hashCode())%parking.length;
    }

    public static void park(String plate){

        int index=hash(plate);

        while(parking[index]!=null){
            index=(index+1)%parking.length;
        }

        parking[index]=plate;

        System.out.println("Parked at "+index);
    }

    public static void main(String[] args){

        park("ABC123");
        park("XYZ999");
    }
}