public class Tragedy extends Play {

    public Tragedy(String name) {
        super(name, "tragedy");
    }

    public int calculAmount(int audience) {
        int Amount = 400;
          if (audience > 30) {
            Amount += 10 * (audience - 30);
          }
        return Amount;
    }
}
