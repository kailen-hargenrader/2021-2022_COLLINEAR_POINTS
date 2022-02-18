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
	private Point[] Points;

	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if(points == null || points[0] == null)throw new IllegalArgumentException("Points cannot be null.");
		for(int i = 0; i<points.length; i++) {
			for(int j = points.length-1; j>i; j--) {
				if(points[j] == null) throw new IllegalArgumentException("Points cannot be null.");
				if(points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("Points must be Different.");
			}
		}
		Points = points.clone();
		lines = new ArrayList<LineSegment>();
		Generate();
		
	}
	private void Generate() {
		Arrays.sort(Points);
		double slope;
		for(int i = 0; i<Points.length-3; i++) {					//This is really gross
			for(int j = i+1; j<Points.length-2; j++) {
				slope = Points[i].slopeTo(Points[j]);
				for(int k = j+1; k<Points.length-1; k++) {
					if(slope == Points[j].slopeTo(Points[k])) {
						for(int m = k+1; m<Points.length; m++) {
							if(slope == Points[k].slopeTo(Points[m])) lines.add(new LineSegment(Points[i],Points[m]));
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