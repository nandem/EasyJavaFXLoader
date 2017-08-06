package javafx.geometry;

/**
 * @author Nandem on 2017/8/6.
 */
public class Point
{
    private double x;
    private double y;

    public Point()
    {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void update(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void updateX(double x)
    {
        this.x = x;
    }

    public void updateY(double y)
    {
        this.y = y;
    }

    public double distance(double x1, double y1) {
        double a = getX() - x1;
        double b = getY() - y1;
        return Math.sqrt(a * a + b * b);
    }

    public double distance(Point point) {
        return distance(point.getX(), point.getY());
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}
