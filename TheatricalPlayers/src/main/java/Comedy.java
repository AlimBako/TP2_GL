public class Comedy extends Play{
    
    public Comedy(String name) {
        super(name, "comedy");
    }

    public int calculAmount(int audience) {
        int Amount = 300;
          if (audience > 20) {
            Amount += 100 + 5 * (audience - 20);
          }
          Amount += 3 * audience;
        return Amount;
    }

    public int calculVolumeCredits(int audience) {
        int credits = super.calculVolumeCredits(audience);
        credits += Math.floor(audience / 5);
        return credits;
    }
}