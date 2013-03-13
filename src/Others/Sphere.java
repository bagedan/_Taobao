package Others;

public class Sphere {
	double radius;
	double xCenter, yCenter, zCenter;

	Sphere() {
		radius = 1.0;
	}

	Sphere(double x, double y, double z) {
		this();
		xCenter = x;
		yCenter = y;
		zCenter = z;
	}

	Sphere(double theRadius, double x, double y, double z) {
		this(x, y, z);
		radius = theRadius;
	}
}