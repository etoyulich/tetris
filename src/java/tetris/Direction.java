package tetris;

public class Direction {
    // ----------------------- Свойства --------------------------

    // определяем направление в часах (0 до 12)
    private final int _hours;

    private Direction(int hours) {

        // Приводим заданные часы к допустимому диапазону
        hours = hours % 12;
        if (hours < 0) { hours += 12; }

        _hours = hours;
    }

    // ----------------------- Порождение --------------------------

    public static Direction up() {
        return new Direction(0);
    }

    public static Direction down() {
        return new Direction(6);
    }

    public static Direction right() {
        return new Direction(3);
    }

    public static Direction left() {
        return new Direction(9);
    }

    // ------------------ Сравнение направлений ---------------------

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Direction)) {
            // если объект не совместим c Direction, возвращаем false
            return false;
        }

        Direction direct = (Direction) other;
        return _hours == direct._hours;
    }

    public Direction clockwise() {
        return new Direction(this._hours + 3);
    }

    public Direction anticlockwise() {
        return new Direction(this._hours - 3);
    }

    public Direction opposite() {
        return new Direction(this._hours + 6);
    }

    public boolean isOpposite(Direction other) {
        return this.opposite().equals(other);
    }

    // --------------------------------------------------
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this._hours;
        return hash;
    }

    @Override
    public String toString() {
        String msg = "";

        if(_hours == 0) {
            msg = "Up";
        } else if(_hours == 3) {
            msg = "Right";
        } else if (_hours == 6) {
            msg = "Down";
        } else if(_hours == 9) {
            msg = "Left";
        }

        return msg;
    }
}
