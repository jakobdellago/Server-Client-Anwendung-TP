import java.util.ArrayList;

/**
 * Created by Kids on 2/22/2017.
 */
public class VierGewinnt {


    private int spaltenAnzahl = 7;
    private int zeilenAnzahl = 6;
    private int[][] table = new int[spaltenAnzahl][zeilenAnzahl];

    //Fügt einer angegebenen Spalte ein steinchen hinzu (von unten nach oben..)
    //Falls der Spalte kein Steinchen hinzugefügt werden kann(Spalte existiert nicht/Spalte ist voll) wird false zurückgegeben
    //Wird das Steinchen hinzugefügt wird true zurückgegeben
    public boolean put(int spalte){
        //Kontrolle
        if(spalte>=spaltenAnzahl||spalte<0){
            return false;
        }

        for(int y=zeilenAnzahl-1; y>=0; y--){
            if(table[spalte][y]==0){
                table[spalte][y] = 1;
                return true;
            };
        }
        return false;
    }

    public void printTable(){
        for(int y=0; y<zeilenAnzahl; y++){
            for(int x=0; x<spaltenAnzahl; x++){
                    System.out.print(table[x][y]+" ");
            }
            System.out.println();
        }
    }

    public boolean checkHorizontal(){

        int counter=0;

        for(int zeile=0; zeile<zeilenAnzahl; zeile++) {
            for (int i = 0; i < spaltenAnzahl; i++) {
                if (table[i][zeile] == 1) {
                    counter++;
                } else counter = 0;

                if (counter >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVertical(){

        int counter=0;

        for(int spalte=0; spalte<spaltenAnzahl; spalte++){
            for(int i=0; i<zeilenAnzahl; i++){
                if(table[spalte][i]==1){
                    counter++;
                }else counter=0;

                if(counter>=4){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkDiagonalRight(){
        int counter = 0;
        int winCounter = 0;

        //Testet alle kombinationen, welche entlang x=0 diagonal möglich sind
        for(int zeile=0; zeile<(spaltenAnzahl+zeilenAnzahl-1); zeile++){

            //Zeile gibt an, von welcher Zeile aus gestartet wurde
            //Diese Schleife schaut, dass bei übertreten der tabelle nach oben(ab y<0) die nächste zeile überprüft wird
            while(zeile-counter>=0){
                //Diese Bedingung schaut, dass nicht unterhalb der tabelle(y>zeilenanzahl) kontrolliert wird AND dass nicht bei x>spaltenanzahl-1 kontrolliert wird
                if(zeile-counter<zeilenAnzahl && counter<spaltenAnzahl) {
                    if (table[counter][zeile - counter] == 1) {
                        winCounter++;
                        if (winCounter >= 4) {
                            return true;
                        }
                    } else {
                        winCounter = 0;
                    }
                }
                counter++;
            }
            counter=0;
            winCounter=0;
        }


        return false;

    }

    //TODO
    /*
    public boolean checkDiagonalLeft(){

        int counter = 0;
        int winCounter = 0;

        for(int zeile=0; zeile<(spaltenAnzahl+zeilenAnzahl-1); zeile++){

            while(counter<=zeile){
                if(zeile<zeilenAnzahl-1) {
                    if (table[][zeile-(counter-spaltenAnzahl-1)] == 1) {
                        winCounter++;
                        if (winCounter >= 4) {
                            return true;
                        }
                    } else {
                        winCounter = 0;
                    }
                }
                counter++;
            }
            counter=0;
            winCounter=0;
        }


        return false;

    }*/
}
