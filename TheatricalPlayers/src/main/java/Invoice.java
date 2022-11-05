import java.util.*;
import java.text.NumberFormat;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public double calculTotalAmount(Map<String, Play> plays){
    double totalAmount = 0;
    for (Performance perf : performances) {
      Play play = plays.get(perf.playID);
      totalAmount += play.calculAmount(perf.audience);
  }
  return totalAmount;
}

  public int calculTotalVolumeCredits(Map<String, Play> plays){
    int totalCredits = 0;
    for (Performance perf : performances) {
      Play play = plays.get(perf.playID);
      totalCredits += play.calculVolumeCredits(perf.audience);
  }
  return totalCredits;
}

  public String toText(Map<String, Play> plays) {
    double totalAmount = this.calculTotalAmount(plays);
    int volumeCredits = this.calculTotalVolumeCredits(plays);
    String result = String.format("Statement for %s\n", customer);

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = play.calculAmount(perf.audience);

      // print line for this order
      result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience);
    }
    result += String.format("Amount owed is %s\n", frmt.format(totalAmount));
    result += String.format("You earned %s credits\n", volumeCredits);
    return result;
  }

  
  public String toHTML (Map<String, Play> plays) {
    double totalAmount = this.calculTotalAmount(plays);
    int volumeCredits = this.calculTotalVolumeCredits(plays);
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    String result = "<!DOCTYPE html> \n";
    result +="\t<html> \n";
    result +="\t\t<body> \n";
    result +="\t\t\t<h1>Invoice</h1> \n";
    result +="\t\t\t<p><b>Client :</b> " + customer + "</h2> \n";
    result += "\t\t\t<table style=\"width: 400px\" border=\"1px solid black\"> \n";
    result += "\t\t\t\t<tr> \n";
    result += "\t\t\t\t\t <th>Piece</th> \n";
		result += "\t\t\t\t\t <th>Seats sold</th> \n";
    result += "\t\t\t\t\t <th>Price</th> \n";	
		result += "\t\t\t\t</tr> \n";

    for (Performance perf : performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = play.calculAmount(perf.audience);

      // print line for this order
      result += "\t\t\t\t<tr> \n";
      result += "\t\t\t\t\t <td>" + play.name + "</td> \n";
      result += "\t\t\t\t\t <td>" + perf.audience + "</td> \n";
      result += "\t\t\t\t\t <td>" + frmt.format(thisAmount) + "</td> \n";	
      result += "\t\t\t\t</tr> \n";
    }

    result += "\t\t\t\t<tr> \n";
    result += "\t\t\t\t\t <td colspan=\"2\" style=\"text-align: right;\"><b>Total owed:<b></td> \n";
    result += "\t\t\t\t\t <td>" + frmt.format(totalAmount) + "</td> \n";	
    result += "\t\t\t\t</tr> \n";

    result += "\t\t\t\t<tr> \n";
    result += "\t\t\t\t\t <td colspan=\"2\" style=\"text-align: right;\"><b>Fidelity points earned:<b></td> \n";
    result += "\t\t\t\t\t <td>" + volumeCredits + "</td> \n";	
    result += "\t\t\t\t</tr> \n";

    result +="\t\t\t</table> \n";
    result +="\t\t\t<p>Payment is required under 30 days. We can break your knees if you don't do so.</p> \n";
    result +="\t\t</body> \n";
    result +="\t</html> \n";

    return result;
  }

}
