public abstract class Play {

  public String name;
  public String type;

  public Play(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public abstract int calculAmount(int audience);

  public int calculVolumeCredits(int audience) {
    return Math.max(audience - 30, 0);
  }
}
