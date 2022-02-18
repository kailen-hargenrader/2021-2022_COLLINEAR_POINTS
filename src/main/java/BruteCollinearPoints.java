/*************************************************************************
 *  Compilation:  javac-algs4 BruteCollinearPoints.java
 *  Execution:    none
 *  Dependencies: Point.java LineSegment.java
 *
 *   A program that examines 4 points at a time 
 *   and checks whether they all lie on the same line segment, 
 *   returning all such line segments. 
 *   To check whether the 4 points p, q, r, and s are collinear, 
 *   check whether the three slopes between p and q, 
 *   between p and r, and between p and s are all equal.
 *
 *************************************************************************/
import java.util.Arrays;
import java.util.ArrayList;
public class BruteCollinearPoints {
	private ArrayList<LineSegment> lines; // Container for line segments before conversion to array
	private Point[] points;

	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if(points == null)throw new IllegalArgumentException("Points cannot be null.");
		for(int i = 0; i<points.length; i++) {
			if(points[i] == null) throw new IllegalArgumentException("Points cannot be null.");
			for(int j = points.length-1; j>i; j--) {
				if(points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("Points must be Different.");
			}
		}
		this.points = points;
		lines = new ArrayList<LineSegment>();
		Generate();
		
	}
	private void Generate() {
		Arrays.sort(points);
		double slope;
		for(int i = 0; i<points.length-3; i++) {					//This is really gross
			for(int j = i+1; j<points.length-2; j++) {
				slope = points[i].slopeTo(points[j]);
				for(int k = j+1; k<points.length-1; k++) {
					if(slope == points[j].slopeTo(points[k])) {
						for(int m = k+1; m<points.length; m++) {
							if(slope == points[k].slopeTo(points[m])) lines.add(new LineSegment(points[i],points[m]));
						} 
					}

				}
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