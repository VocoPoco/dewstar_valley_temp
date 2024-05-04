package org.example.models;

import java.util.Collection;
import java.util.Vector;

public class PlaneVector extends Vector<Double> {
    public static final PlaneVector up = new PlaneVector(0d, 1d);
    public static final PlaneVector down = new PlaneVector(0d, -1d);
    public static final PlaneVector right = new PlaneVector(1d, 0d);
    public static final PlaneVector left = new PlaneVector(-1d, 0d);
    public static final PlaneVector zero = new PlaneVector(0d, 0d);

    public PlaneVector(Double x, Double y) {
        super(2);
        this.add(x);
        this.add(y);
    }

    @Override
    public void add(int index, Double element) {
        throw new UnsupportedOperationException("Cannot add elements to a PlaneVector.");
    }

    @Override
    public boolean add(Double e) {
        throw new UnsupportedOperationException("Cannot add elements to a PlaneVector.");
    }

    @Override
    public boolean addAll(Collection<? extends Double> c) {
        throw new UnsupportedOperationException("Cannot add elements to a PlaneVector.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends Double> c) {
        throw new UnsupportedOperationException("Cannot add elements to a PlaneVector.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove elements from a PlaneVector.");
    }

    @Override
    public Double remove(int index) {
        throw new UnsupportedOperationException("Cannot remove elements from a PlaneVector.");
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove elements from a PlaneVector.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot modify elements in a PlaneVector.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear elements of a PlaneVector.");
    }
    public PlaneVector() {
        this(0d, 0d);
    }

    public double getX() {
        return this.get(0);
    }

    public void setX(double x) {
        this.set(0, x);
    }

    public double getY() {
        return this.get(1);
    }

    public void setY(double y) {
        this.set(1, y);
    }
}
