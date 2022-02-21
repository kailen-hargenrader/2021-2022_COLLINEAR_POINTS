import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*************************************************************************
 *  Compilation:  javac-algs4 FastCollinearPoints.java
 *  Execution:    none
 *  Dependencies: Point.java LineSegment.java
 *
 *   Given a point p, the following method determines 
 *   whether p participates in a set of 4 or more collinear points.
 *   Think of p as the origin.
 *   - For each other point q, determine the slope it makes with p.
 *   - Sort the points according to the slopes they makes with p.
 *   - Check if any 3 (or more) adjacent points in 
 *      the sorted order have equal slopes with respect to p. 
 *      If so, these points, together with p, are collinear.
 *
 *************************************************************************/
public class FastCollinearPoints {
	
	private ArrayList<CoolerLineSegment> lines;
	private ArrayList<Point> Points;
	
	public FastCollinearPoints(Point[] points) {
		if(points == null || points[0] == null)throw new IllegalArgumentException("Points cannot be null.");
		for(int i = 0; i<points.length; i++) {
			for(int j = points.length-1; j>i; j--) {
				if(points[j] == null) throw new IllegalArgumentException("Points cannot be null.");
				if(points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("Points must be Different.");
			}
		}
		Points = new ArrayList<Point>();
		Collections.addAll(Points, points);
		Collections.sort(Points);
		lines = new ArrayList<CoolerLineSegment>();
		Generate();
	}
	
	private void Generate() {
		ArrayList<DoubleWithIndex> slopes = new ArrayList<DoubleWithIndex>();
		for(int i = 0; i<Points.size()-3;i++) {
			for(int j = i+1; j < Points.size(); j++) {
				slopes.add(new DoubleWithIndex(Points.get(i).slopeTo(Points.get(j)),j));
			}
			Collections.sort(slopes);
			DoubleWithIndex start = slopes.get(0);
			int count = 1;
			for(int k = 1; k < slopes.size(); k++) {
				if(slopes.get(k).getValue() == start.getValue()) count++;
				else {
					if(count >= 3) {
						lines.add(new CoolerLineSegment(Points.get(start.getIndex()), Points.get(slopes.get(k-1).getIndex())));
					}
					count = 1;
					start = slopes.get(k);
				}
			}
			if(count >=3) lines.add(new CoolerLineSegment(Points.get(start.getIndex()), Points.get(slopes.get(slopes.size()-1).getIndex())));
			slopes.clear();
		}
		Collections.sort(lines);
		CoolerLineSegment compare = lines.get(0);
		for(int m = 1; m < lines.size(); m++) {
			if(lines.get(m).compareTo(compare) == 0 && lines.get(m).getPoint2() == compare.getPoint2()) {
				lines.remove(m);
				m--;
			}
			else {
				compare = lines.get(m);
			}
		}
	}

	public int numberOfSegments() {
		// the number of line segments
		return lines.size();
	}

	public LineSegment[] segments() {
		// the line segments
		return lines.toArray(new LineSegment[lines.size()]);
	}
}

class DoubleWithIndex implements Comparable<DoubleWithIndex>{
	private Double value;
	private int index;
	public DoubleWithIndex(Double d, int i) {
		value = d;
		index = i;
	}
	public double getValue() {
		return value;
	}
	public int getIndex() {
		return index;
	}
	@Override
	public int compareTo(DoubleWithIndex that) {
		if(this.getValue() < that.getValue()) {
			return -1;
		}
		else if(this.getValue() > that.getValue()) {
			return 1;
		}
		else return 0;
	}
	
	public String toString() {
		return value.toString();
	}
}
class CoolerLineSegment extends LineSegment implements Comparable<CoolerLineSegment>{
	private Point x;
	private Point y;
	private double slope;
	
	public CoolerLineSegment(Point X, Point Y) {
		super(X, Y);
		x = X;
		y = Y;
		slope = x.slopeTo(y);
	}
	
	public Point getPoint1() {
		return x;
	}
	
	public Point getPoint2() {
		return y;
	}
	
	public double getSlope() {
		return slope;
	}

	@Override
	public int compareTo(CoolerLineSegment that) {
		if(this.getSlope() > that.getSlope()) return 1;
		else if(this.getSlope() < that.getSlope()) return -1;
		else return 0;
	}
}