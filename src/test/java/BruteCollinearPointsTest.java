import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class BruteCollinearPointsTest {

	BruteCollinearPoints bcp10_2;
	BruteCollinearPoints bcp20_5;
	BruteCollinearPoints bcp56_20;
	@Before
	public void setUp() throws Exception {
		bcp10_2 = generateBCP("input10.txt");
		bcp20_5 = generateBCP("input20.txt");
		bcp56_20 = generateBCP("input56.txt");
	}

	private BruteCollinearPoints generateBCP(String filename) {
		In in = new In("collinear-test-files/" + filename);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		} 
		return new BruteCollinearPoints(points);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testNull() {
		new BruteCollinearPoints(null);
	}

	@Test
	public void testNumberOfSegments() {

		assertTrue("number of segments should be 6, was " + bcp10_2.numberOfSegments(), bcp10_2.numberOfSegments() == 6);
		assertTrue("number of segments should be 21, was " + bcp20_5.numberOfSegments(), bcp20_5.numberOfSegments() == 21);
		assertTrue("number of segments should be 68, was " + bcp56_20.numberOfSegments(), bcp56_20.numberOfSegments() == 68);
	}

	@Test
	public void testSegments() {
		for(LineSegment line : bcp10_2.segments()) {
			assertFalse("bcp10_2 should not include line segment (1000, 18000) -> (2000, 22000)", line.toString() == "(1000, 18000) -> (2000, 22000)");
			assertFalse("bcp10_2 should not include line segment (1000, 18000) -> (3000, 26000)", line.toString() == "(1000, 18000) -> (3000, 26000)");
			
		}
	}

}


