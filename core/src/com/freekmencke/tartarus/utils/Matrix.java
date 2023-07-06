package com.freekmencke.tartarus.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Matrix<T> {

    public static class Element<T> {
        public Vector2 position;
        public T value;

        Element(Vector2 position, T value) {
            this.position = position;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element<?> element = (Element<?>) o;
            return position.equals(element.position);
        }
    }

    private final int size;
    private final Array<Element<T>> matrix = new Array<>();

    public Matrix(int size) {
        this.size = size;
    }

    public void setElement(int x, int y, T value) throws IllegalArgumentException {
        if ((x < 0 || x >= size) || (y < 0 || y >= size)) throw new IllegalArgumentException("Param has to be at least zero, and smaller then size { " + size + " }");

        Element<T> element = new Element<>(new Vector2(x, y), value);

        matrix.removeValue(element, false);
        matrix.add(element);
    }
    public Element<T> getElement(int x, int y) {
        Iterator<Element<T>> iterator = matrix.select(e -> e.position.equals(new Vector2(x, y))).iterator();

        return iterator.hasNext() ? iterator.next() : null;
    }

    public Array<Element<T>> getAllElements() {
        return new Array<>(matrix);
    }

}
