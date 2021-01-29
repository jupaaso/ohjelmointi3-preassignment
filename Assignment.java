import java.nio.charset.Charset;
import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.*;          // try-catch InputMismatchException vaatii taman

public class Assignment {
 
    public static final Scanner lukija = new Scanner( System.in );
    
    public static void main( String [] args ) {
       
        int valinta = 0;

        System.out.println(" ");
        System.out.println(" PRE-ASSIGNMENT");

        String tiedostonimi = "textfile.txt";
        File tekstiJuhan = new File( tiedostonimi);  // 
        
        System.out.println();
        System.out.print(" Anna username > ");
        String username = lukija.nextLine();

        do  {
            tulostaValikko();
            valinta = lueInt();

            switch ( valinta ) {
                case 1:     // Kirjoita uusi viesti tiedostoon
                    //Date object
                    Date paivays = new Date();    
                    //getTime() returns current time in milliseconds
                    long aika = paivays.getTime();
                    //Passed the milliseconds to constructor of Timestamp calss
                    Timestamp ts = new Timestamp(aika);
                    //System.out.println("Current Time Stamp: "+ts);
                    
                    System.out.print("\n Anna viesti > ");
                    String viesti = lukija.nextLine();

                    //kokoa kirjoitettava viesti
                    viesti = "(" + ts + ") <" + username + ">" + viesti;
            
                    //kirjoita viesti tiedostoon
                    FileWriter fwr = null;
                    try {
                        fwr = new FileWriter(tiedostonimi);
                        fwr.write(viesti);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //close resources
                        try {
                            fwr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:     // anna viesti
                    //luetaan viesti tiedostosta
                    try {
                        FileReader fr = new FileReader(tiedostonimi);
                        BufferedReader br = new BufferedReader(fr);
                        String last;
                        String line;
                        System.out.print("\nPrevious user input:");
                        while ( (line = br.readLine()) != null) {
                            last = line;
                            System.out.println(last);
                        }
                    } catch (IOException ioe) {
                        System.out.println("Virhe lukemisessa");
                        ioe.printStackTrace();
                    }
                    break;
                case 0:
                    break;
                default: 
                    System.out.println("Vaara valinta. Yrita uudelleen.");
                    break;
            }
        } while (valinta != 0);
    }        
          
    // TEHTAVAN VALIKKO - METODI
	public static void tulostaValikko() {
        System.out.println("\n Valitse mita haluat tehda");
        System.out.println(" 1) Kirjoita uusi viesti tiedostoon");
        System.out.println(" 2) Hae edellinen viesti tiedostosta");
        System.out.println(" 0) Lopeta");
        System.out.print("\n Valintasi > ");
    }

    // INTEGERIN LUKU -METODI
    public static int lueInt() {
        Scanner lukija = new Scanner(System.in);
        boolean ok = false;
        int luku = 0;
      
        do {
  
            try {
                luku = lukija.nextInt();
                lukija.nextLine();                     // Lukijan tyhjennys
                ok = true;
            }catch( InputMismatchException ime ){
                lukija.nextLine();                     // Lukijan tyhjennys
                System.out.print("Virhe, yrita uudelleen > ");
            }
        }while( !ok );
        
        return luku;
    }
}