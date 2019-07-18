package spoiled.mango;

public class MatrixElement {

  private int xCordinate;
  private int yCordinate;
  
  public MatrixElement(int xCordinate,
      int yCordinate) {
    super();
    this.xCordinate = xCordinate;
    this.yCordinate = yCordinate;
  }
  
  public int getxCordinate() {
    return xCordinate;
  }
  public void setxCordinate(int xCordinate) {
    this.xCordinate = xCordinate;
  }
  public int getyCordinate() {
    return yCordinate;
  }
  public void setyCordinate(int yCordinate) {
    this.yCordinate = yCordinate;
  }
  
}
