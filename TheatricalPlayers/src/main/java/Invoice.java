import java.util.*;
import java.text.NumberFormat;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public String toText(Map<String, Play> plays) {
    double totalAmount = 0;
    int volumeCredits = 0;
    String result = String.format("Statement for %s\n", customer);

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = play.calculAmount(perf.audience);

      // add volume credits
      volumeCredits += play.calculVolumeCredits(perf.audience);

      // print line for this order
      result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience);
      totalAmount += thisAmount;
    }
    result += String.format("Amount owed is %s\n", frmt.format(totalAmount));
    result += String.format("You earned %s credits\n", volumeCredits);
    return result;
  }

  
}
