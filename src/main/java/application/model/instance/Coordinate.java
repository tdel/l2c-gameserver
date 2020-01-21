package application.model.instance;

public class Coordinate {

    private int x;
    private int y;
    private int z;
    private int heading;

    public Coordinate(int _x, int _y, int _z) {
        this.setCoordinate(_x, _y, _z);
    }

    public Coordinate(int _x, int _y, int _z, int _heading) {
        this.setCoordinate(_x, _y, _z, _heading);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public int getHeading() {
        return this.heading;
    }

    public void setCoordinate(int _x, int _y, int _z) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }

    public void setCoordinate(int _x, int _y, int _z, int _heading) {
        this.setCoordinate(_x, _y, _z);
        this.heading = _heading;
    }

    public double calculateDistance3D(Coordinate _target) {
        return Math.sqrt(
                Math.pow(this.getX() - _target.getX(), 2) +
                Math.pow(this.getY() - _target.getY(), 2) +
                Math.pow(this.getZ() - _target.getZ(), 2)
        );
    }

    public boolean isSameCoordinate(int _x, int _y, int _z) {
        if (this.getX() != _x) {
            return false;
        }

        if (this.getY() != _y) {
            return false;
        }

        return this.getZ() == _z;
    }
}
