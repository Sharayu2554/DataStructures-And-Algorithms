/**
 * Class to Implement AntBoard
 *
 * @author Sharayu Sharad Mantri
 * Date : 6/2/19
 * Project : Moderate
 **/

package Moderate;

import java.util.HashSet;

public class AntBoard {

    class Position {
        int x;
        int y;

        public Position(int _x, int _y) {
            x = _x;
            y = _y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Position) {
                Position p = (Position) o;
                return p.x == x && p.y == y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (x * 31) ^ y;
        }

        @Override
        public Position clone() {
            return new Position(this.x, this.y);
        }
    }

    enum Orientation {
        top, left, bottom, right;
    }

    class Ant {

        Orientation o;
        Position p;

        public Ant() {
            o = Orientation.right;
            p = new Position(0, 0);
        }

        public Ant(Orientation o, Position p) {
            this.o = o;
            this.p = p;
        }

        public void clockwise90() {

            switch (o) {
                case right: o = Orientation.bottom;break;
                case left : o =  Orientation.top;break;
                case bottom: o = Orientation.left;break;
                default : o = Orientation.right;break;
            }
        }

        public void antiClockwise90() {
            switch(o) {
                case right: o = Orientation.top;break;
                case top : o = Orientation.left;break;
                case left: o = Orientation.bottom;break;
                default: o = Orientation.right;break;
            }
        }

        public void moveForward() {
            switch(o) {
                case right :
                    p.y = p.y +1;
                    break;
                case top :
                    p.x = p.x-1;
                    break;
                case left :
                    p.y = p.y -1;
                    break;
                case bottom:
                    p.x = p.x +1;
                    break;
            }
        }
    }

    HashSet<Position> whites;

    public AntBoard() {
        whites = new HashSet<>();
    }

    public void printKMoves(int K) {

        Ant ant = new Ant();
        Position topLeft = new Position(0, 0);
        Position bottomRight = new Position(0, 0);

        processK(ant, K, topLeft, bottomRight);

        StringBuilder sb = new StringBuilder();
        for (int i = topLeft.x; i <= bottomRight.x; i++) {
            for (int j = topLeft.y; j <= bottomRight.y; j++) {
                if (whites.contains(new Position(i, j))) {
                    sb.append("X");
                }
                else if (ant.p.x == i && ant.p.y == j) {
                    sb.append(ant.o);
                }
                else {
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void processK(Ant ant, int k, Position topLeft, Position bottomRight) {

        if (k == 0) {
            return;
        }

        if (!whites.contains(ant.p)) {
            //flip the color (add to whites), turn anitclockwise, move forward
            whites.add(ant.p.clone());
            ant.antiClockwise90();
            ant.moveForward();
        }
        else {
            whites.remove(ant.p);
            ant.clockwise90();
            ant.moveForward();
        }

        if (ant.p.x < topLeft.x) {
            topLeft.x = ant.p.x;
        }
        if (ant.p.y < topLeft.y) {
            topLeft.y = ant.p.y;
        }

        if (ant.p.x > bottomRight.x) {
            bottomRight.x = ant.p.x;
        }
        if (ant.p.y > bottomRight.y) {
            bottomRight.y = ant.p.y;
        }

        processK(ant, k-1, topLeft, bottomRight);
    }

    public static void main(String args[]) {
        AntBoard antB = new AntBoard();
        antB.printKMoves(6);
    }

}
