/**
 * Class to Implement BestLine
 *
 * @author Sharayu Sharad Mantri
 * Date : 6/2/19
 * Project : Moderate
 **/

package Moderate;

import java.util.ArrayList;
import java.util.HashMap;

public class BestLine {

    class Point {
        int x;
        int y;

        public Point(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    class Line {
        double slope;
        double intercept;

        public Line(Point start, Point end) {
            slope = (double)Math.round(1000 * (end.y - start.y)/(end.x - start.x))/ 1000;
            intercept = (end.y - slope * end.x);
        }

        public boolean isEqual(Line l) {

            if (Math.abs(slope - l.slope) < 0.001 && Math.abs(intercept - l.intercept) < 0.001) {
                return true;
            }
            return false;
        }
    }

    public HashMap<Double, ArrayList<Line>> getAllLines(ArrayList<Point> points ) {

        HashMap<Double, ArrayList<Line>> getLines = new HashMap<>();

        for (int i =0; i < points.size(); i++) {
            for (int j = i+1; j < points.size(); j++) {
                Line l = new Line(points.get(i), points.get(j));
                ArrayList<Line> lines = getLines.getOrDefault(l.slope, new ArrayList<>());
                lines.add(l);
                getLines.put(l.slope, lines);
            }
        }
        return getLines;
    }

    public int getCount(double slope, HashMap<Double, ArrayList<Line>>  allLines, Line l) {

        if (allLines == null || allLines.size() == 0) {
            return 0;
        }

        int count = 0;
        ArrayList<Line> lines = allLines.get(slope);
        for (Line line : lines) {
            if (line.isEqual(l)) {
                count++;
            }
        }
        return count;
    }

    public Line getBestLine(HashMap<Double, ArrayList<Line>> allLines) {

        int max = 0;
        Line best = null;
        for (Double slope : allLines.keySet()) {
            for (Line line : allLines.get(slope)) {
                int count = getCount(line.slope, allLines, line);
                count += getCount(line.slope + 0.001, allLines, line);
                count += getCount(line.slope - 0.001, allLines, line);
                if (max < count) {
                    max = count;
                    best = line;
                }
            }
        }
        return best;
    }

    public Line bestLine(ArrayList<Point> points) {

        HashMap<Double, ArrayList<Line>> getAllLines = getAllLines(points);
        Line best = getBestLine(getAllLines);
        return best;
    }

    public static void main(String args[]) {
        ArrayList<Point> points = new ArrayList<>();
        BestLine b = new BestLine();
        Line best = b.bestLine(points);

    }
}
