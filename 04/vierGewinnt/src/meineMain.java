/**
 * Created by Kids on 2/22/2017.
 */
public class meineMain {

    public static void main(String[] args) {

        VierGewinnt vg = new VierGewinnt();

        vg.printTable();

        //vg.put(0);
        vg.put(1);
        vg.put(2);
        vg.put(3);

        vg.put(1);
        vg.put(1);
        vg.put(1);

        vg.put(6);
        vg.put(6);
        vg.put(6);
        vg.put(6);

        vg.put(2);
        vg.put(2);

        vg.put(3);
        vg.put(3);
        vg.put(3);

        vg.put(4);
        vg.put(4);
        vg.put(4);
        vg.put(4);
        vg.put(4);

        //vg.put(5);
        vg.put(5);
        vg.put(5);

        System.out.println("----------------------------------");

        vg.printTable();

        //System.out.println("Horizontal: " + vg.checkHorizontal());
        //System.out.println("Vertikal: " + vg.checkVertical());
        //System.out.println("Diagonal nach rechts:" + vg.checkDiagonalRight());
        //System.out.println("Diagonal nach links: " + vg.checkDiagonalLeft());
    }

}
