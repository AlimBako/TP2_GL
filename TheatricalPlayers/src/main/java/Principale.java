import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principale {

    public static void main(String[] args) {
        StatementPrinter imprimante = new StatementPrinter();
        Play piece_tragique = new Tragedy("Les miserables etudiants");
        Play fary = new Comedy("Noir is the new black");
        Play blanche = new Comedy("Blanche");

        Performance perf1 = new Performance("mis01", 2000);
        Performance perf2 = new Performance("mis01", 1200);
        Performance perf3 = new Performance("com01", 5000);
        Performance perf4 = new Performance("com02", 3100);
        
        List<Performance> liste_perf = new ArrayList<Performance>();
        liste_perf.add(perf1);
        liste_perf.add(perf2);
        liste_perf.add(perf3);
        liste_perf.add(perf4);

        Map<String, Play> plays = new HashMap<String, Play>();
        plays.put("com02", blanche);
        plays.put("com01", fary);
        plays.put("mis01", piece_tragique);
        plays.put("mis01", piece_tragique);


        Invoice clermont = new Invoice("Mohamed Yacine", liste_perf);

        System.out.println(imprimante.print(clermont, plays));
        System.out.println(clermont.toHTML(plays));


    }
}