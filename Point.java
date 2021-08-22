/**
 * Maman 12 - This Class represent a point
 *
 * @autor Yarin Hazan
 * 
 */
public class Point {

    private double _x;
    private double _y;

    /**
     * Constructs a new Point
     * 
     * @param x The new X coordinate
     * @param y The new Y coordinate
     */
    public Point(double x, double y) {
        _x = x;  
        _y = y;

    }

    /**
     * Constructor for objects of class Point. Copy constructor, construct a point using another point.
     *
     * @param other The point from which to construct the new object
     */
    public Point(Point other) {
        _x = other._x;
        _y = other._y;
    }

    /**
     * This method returns the x coordinate of the point.
     *
     * @return The x coordinate of the point
     */
    public double getX() {
        return _x; //if alpha is 90 x must be 0 . otherwise calculate x using cosine
    }

    /**
     * This method returns the y coordinate of the point.
     *
     * @return The y coordinate of the point
     */
    public double getY() {
        return _y;
    }

    /**
     * This method sets the x coordinate of the point. If the new x coordinate is negative the old x coordinate will remain unchanged.
     *
     * @param num The new x coordinate
     */
    public void setX(double num) {
        _x=num;
    }

    /**
     * This method sets the y coordinate of the point. If the new y coordinate is negative the old y coordinate will remain unchanged.
     *
     * @param num The new y coordinate
     */
    public void setY(double num) {

        _y=num;
    }

    /**
     * Returns a string representation of Point.
     *
     * @return A String representation of the Point
     */
    public String toString() {
        // double x = Math.round(getX() * 10000) / (double) 10000; // round numbers
        // double y = Math.round(getY() * 10000) / (double) 10000;
        return "(" + _x + "," + _y + ")";
    }

    /**
     * Check if the given point is equal to this point.
     *
     * @param other The point to check equality with
     * @return True if the given point is equal to this point
     */
    public boolean equals(Point other) {
        return (_x == other._x && _y == other._y);
    }

    /**
     * Check if this point is above a received point.
     *
     * @param other The point to check if this point is above
     * @return True if this point is above the other point
     */
    public boolean isAbove(Point other) {
        return (this.getY() > other.getY());
    }

    /**
     * Check if this point is below a received point.
     *
     * @param other The point to check if this point is below
     * @return True if this point is below the other point
     */
    public boolean isUnder(Point other) {
        return other.isAbove(this);
    }

    /**
     * Check if this point is left of a received point.
     *
     * @param other The point to check if this point is left of
     * @return True if this point is left of the other point
     */
    public boolean isLeft(Point other) {
        return this.getX() < other.getX();
    }

    /**
     * Check if this point is right of a received point.
     *
     * @param other The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other) {
        return other.isLeft(this);
    }

    /**
     * Check the distance between this point and a given point.
     *
     * @param p The point to check the distance from
     * @return The distance
     */
    public double distance(Point p) {
        double dxSquared = Math.pow(getX() - p.getX(), 2);
        double dySquared = Math.pow(getY() - p.getY(), 2);
        return Math.sqrt(dxSquared + dySquared); //the distance formula
    }

    /**
     * Return number of quadrant or 0 if the point is on an axis
     * 
     * @return The quadrant of the point
     */
    public int quadrant () {
        int i=0;
        int quadrant;
        double x=_x;
        double y=_y;
        i = (x==0 | y==0)? 0:(x>0 && y>0) ? 1:(x<0 && y>0)? 2:(x<0 && y<0)? 3:4;
        return i;
    }
}

