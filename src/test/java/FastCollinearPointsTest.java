import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class FastCollinearPointsTest {

	FastCollinearPoints bcp10_2;
	FastCollinearPoints bcp20_5;
	FastCollinearPoints bcp56_20;
	FastCollinearPoints bcp40_4;
	@Before
	public void setUp() throws Exception {
		bcp10_2 = generateBCP("input10.txt");
		bcp20_5 = generateBCP("input20.txt");
		bcp56_20 = generateBCP("input56.txt");
		bcp40_4 = generateBCP("input40.txt");
	}

	private FastCollinearPoints generateBCP(String filename) {
		In in = new In("collinear-test-files/" + filename);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		} 
		return new FastCollinearPoints(points);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testNull() {
		new FastCollinearPoints(null);
	}

	@Test
	public void testNumberOfSegments() {
		for(LineSegment line : bcp56_20.segments()) {
		}
		assertTrue("number of segments should be 2, was " + bcp10_2.numberOfSegments(), bcp10_2.numberOfSegments() == 2);
		assertTrue("number of segments should be 5, was " + bcp20_5.numberOfSegments(), bcp20_5.numberOfSegments() == 5);
		assertTrue("number of segments should be 4, was " + bcp40_4.numberOfSegments(), bcp40_4.numberOfSegments() == 4);
		assertTrue("number of segments should be 20, was " + bcp56_20.numberOfSegments(), bcp56_20.numberOfSegments() == 20);
		
	}

	@Test
	public void testSegments() {
		for(LineSegment line : bcp10_2.segments()) {
			assertFalse("bcp10_2 should not include line segment (1000, 18000) -> (2000, 22000)", line.toString() == "(1000, 18000) -> (2000, 22000)");
			assertFalse("bcp10_2 should not include line segment (1000, 18000) -> (3000, 26000)", line.toString() == "(1000, 18000) -> (3000, 26000)");
			
		}
	}

}


