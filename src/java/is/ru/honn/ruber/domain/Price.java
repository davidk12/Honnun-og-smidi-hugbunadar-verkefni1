package is.ru.honn.ruber.domain;

public class Price
{
  protected String id;
  protected String currencyCode;
  protected String displayName;
  protected int lowEstimate;
  protected int highEstimate;
  protected double surgeMultiplier;

  public Price()
  {
  }

  public Price(String id, String currencyCode, String displayName, int lowEstimate, int highEstimate, double surgeMultiplier)
  {
    this.id = id;
    this.currencyCode = currencyCode;
    this.displayName = displayName;
    this.lowEstimate = lowEstimate;
    this.highEstimate = highEstimate;
    this.surgeMultiplier = surgeMultiplier;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getCurrencyCode()
  {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }

  public String getDisplayName()
  {
    return displayName;
  }

  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

  public int getLowEstimate()
  {
    return lowEstimate;
  }

  public void setLowEstimate(int lowEstimate)
  {
    this.lowEstimate = lowEstimate;
  }

  public int getHighEstimate()
  {
    return highEstimate;
  }

  public void setHighEstimate(int highEstimate)
  {
    this.highEstimate = highEstimate;
  }

  public double getSurgeMultiplier()
  {
    return surgeMultiplier;
  }

  public void setSurgeMultiplier(double surgeMultiplier)
  {
    this.surgeMultiplier = surgeMultiplier;
  }
}
