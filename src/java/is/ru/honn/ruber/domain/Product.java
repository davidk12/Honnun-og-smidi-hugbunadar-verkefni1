package is.ru.honn.ruber.domain;

public class Product
{
  protected String product_id;
  protected String description;
  protected String display_name;
  protected int capacity;
  protected String image;

  public String getProduct_id()
  {
    return product_id;
  }

  public void setProduct_id(String product_id)
  {
    this.product_id = product_id;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getDisplay_name()
  {
    return display_name;
  }

  public void setDisplay_name(String display_name)
  {
    this.display_name = display_name;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public void setCapacity(int capacity)
  {
    this.capacity = capacity;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }

  @Override
  public String toString()
  {
    return "Product{" +
        "product_id='" + product_id + '\'' +
        ", description='" + description + '\'' +
        ", display_name='" + display_name + '\'' +
        ", capacity=" + capacity +
        ", image='" + image + '\'' +
        '}';
  }
}
