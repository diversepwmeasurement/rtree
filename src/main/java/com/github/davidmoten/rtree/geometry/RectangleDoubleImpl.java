package com.github.davidmoten.rtree.geometry;

import com.github.davidmoten.guavamini.Objects;
import com.github.davidmoten.guavamini.Optional;
import com.github.davidmoten.guavamini.Preconditions;
import com.github.davidmoten.rtree.internal.util.ObjectsHelper;

final class RectangleDoubleImpl implements RectangleDouble {
    private final double x1, y1, x2, y2;

    private RectangleDoubleImpl(double x1, double y1, double x2, double y2) {
        Preconditions.checkArgument(x2 >= x1);
        Preconditions.checkArgument(y2 >= y1);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    static RectangleDoubleImpl create(double x1, double y1, double x2, double y2) {
        return new RectangleDoubleImpl((double) x1, (double) y1, (double) x2, (double) y2);
    }

    static RectangleDoubleImpl create(float x1, float y1, float x2, float y2) {
        return new RectangleDoubleImpl(x1, y1, x2, y2);
    }

    @Override
    public float x1() {
        return (float) x1;
    }

    @Override
    public float y1() {
        return (float) y1;
    }

    @Override
    public float x2() {
        return (float) x2;
    }

    @Override
    public float y2() {
        return (float) y2;
    }

    @Override
    public float area() {
        return (float) areaD();
    }

    @Override
    public Rectangle add(Rectangle r) {
        if (r instanceof RectangleDoubleImpl) {
            RectangleDoubleImpl rd = (RectangleDoubleImpl) r;
            return new RectangleDoubleImpl(min(x1, rd.x1d()), min(y1, rd.y1d()), max(x2, rd.x2d()),
                    max(y2, rd.y2d()));
        } else {
            return new RectangleDoubleImpl(min(x1, r.x1()), min(y1, r.y1()), max(x2, r.x2()),
                    max(y2, r.y2()));
        }
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    @Override
    public boolean intersects(Rectangle r) {
        if (r instanceof RectangleDoubleImpl) {
            RectangleDoubleImpl rd = (RectangleDoubleImpl) r;
            return intersects(rd);
        } else {
            return intersects(x1, y1, x2, y2, r.x1(), r.y1(), r.x2(), r.y2());
        }
    }

    private boolean intersects(RectangleDoubleImpl rd) {
        return intersects(x1, y1, x2, y2, rd.x1, rd.y1, rd.x2, rd.y2);
    }

    @Override
    public double distance(Rectangle r) {
        if (r instanceof RectangleDoubleImpl) {
            RectangleDoubleImpl rd = (RectangleDoubleImpl) r;
            return distance(x1, y1, x2, y2, rd.x1, rd.y1, rd.x2, rd.y2);
        } else {
            return distance(x1, y1, x2, y2, r.x1(), r.y1(), r.x2(), r.y2());
        }
    }

    public static double distance(float x1, float y1, float x2, float y2, float a1, float b1,
            float a2, float b2) {
        if (intersects(x1, y1, x2, y2, a1, b1, a2, b2)) {
            return 0;
        }
        boolean xyMostLeft = x1 < a1;
        double mostLeftX1 = xyMostLeft ? x1 : a1;
        float mostRightX1 = xyMostLeft ? a1 : x1;
        float mostLeftX2 = xyMostLeft ? x2 : a2;
        double xDifference = max(0, mostLeftX1 == mostRightX1 ? 0 : mostRightX1 - mostLeftX2);

        boolean xyMostDown = y1 < b1;
        float mostDownY1 = xyMostDown ? y1 : b1;
        float mostUpY1 = xyMostDown ? b1 : y1;
        float mostDownY2 = xyMostDown ? y2 : b2;

        double yDifference = max(0, mostDownY1 == mostUpY1 ? 0 : mostUpY1 - mostDownY2);

        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

    public static double distance(double x1, double y1, double x2, double y2, double a1, double b1,
            double a2, double b2) {
        if (intersects(x1, y1, x2, y2, a1, b1, a2, b2)) {
            return 0;
        }
        boolean xyMostLeft = x1 < a1;
        double mostLeftX1 = xyMostLeft ? x1 : a1;
        double mostRightX1 = xyMostLeft ? a1 : x1;
        double mostLeftX2 = xyMostLeft ? x2 : a2;
        double xDifference = max(0, mostLeftX1 == mostRightX1 ? 0 : mostRightX1 - mostLeftX2);

        boolean xyMostDown = y1 < b1;
        double mostDownY1 = xyMostDown ? y1 : b1;
        double mostUpY1 = xyMostDown ? b1 : y1;
        double mostDownY2 = xyMostDown ? y2 : b2;

        double yDifference = max(0, mostDownY1 == mostUpY1 ? 0 : mostUpY1 - mostDownY2);

        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

    private static boolean intersects(float x1, float y1, float x2, float y2, float a1, float b1,
            float a2, float b2) {
        return x1 <= a2 && a1 <= x2 && y1 <= b2 && b1 <= y2;
    }

    private static boolean intersects(double x1, double y1, double x2, double y2, double a1,
            double b1, double a2, double b2) {
        return x1 <= a2 && a1 <= x2 && y1 <= b2 && b1 <= y2;
    }

    @Override
    public Rectangle mbr() {
        return this;
    }

    @Override
    public String toString() {
        return "Rectangle [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x1, y1, x2, y2);
    }

    @Override
    public boolean equals(Object obj) {
        Optional<RectangleDoubleImpl> other = ObjectsHelper.asClass(obj, RectangleDoubleImpl.class);
        if (other.isPresent()) {
            return Objects.equal(x1, other.get().x1) && Objects.equal(x2, other.get().x2)
                    && Objects.equal(y1, other.get().y1) && Objects.equal(y2, other.get().y2);
        } else
            return false;
    }

    @Override
    public float intersectionArea(Rectangle r) {
        return (float) intersectionAreaD(r);
    }

    @Override
    public float perimeter() {
        return (float) perimeterD();
    }

    @Override
    public Geometry geometry() {
        return this;
    }

    private static float max(float a, float b) {
        if (a < b)
            return b;
        else
            return a;
    }

    private static double max(double a, double b) {
        if (a < b)
            return b;
        else
            return a;
    }

    private static double min(double a, double b) {
        if (a < b)
            return a;
        else
            return b;
    }

    @Override
    public double intersectionAreaD(Rectangle r) {
        if (!intersects(r))
            return 0;
        else {
            if (r instanceof RectangleDoubleImpl) {
                RectangleDoubleImpl rd = (RectangleDoubleImpl) r;
                return create(max(x1, rd.x1), max(y1, rd.y1), min(x2, rd.x2), min(y2, rd.y2))
                        .areaD();
            } else {
                return create(max(x1, r.x1()), max(y1, r.y1()), min(x2, r.x2()), min(y2, r.y2()))
                        .areaD();
            }
        }

    }

    @Override
    public double perimeterD() {
        return 2 * (x2 - x1) + 2 * (y2 - y1);
    }

    @Override
    public double x1d() {
        return x1;
    }

    @Override
    public double y1d() {
        return y1;
    }

    @Override
    public double x2d() {
        return x2;
    }

    @Override
    public double y2d() {
        return y2;
    }

    @Override
    public double areaD() {
        return (x2 - x1) * (y2 - y1);
    }

}