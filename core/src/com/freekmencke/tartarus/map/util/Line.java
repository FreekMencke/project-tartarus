package com.freekmencke.tartarus.map.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {

    public final Vector2 a;
    public final Vector2 b;

    public Line(Vector2 a, Vector2 b) {
        this.a = a;
        this.b = b;
    }

    public Line add(Vector2 add) {
        a.add(add);
        b.add(add);
        return this;
    }

    public Vector2[] getVertices() {
        return new Vector2[] { new Vector2(a), new Vector2(b) };
    }

    public Array<Line> subtractLines(Array<Line> linesToSubtract) {
        Array<Line> lines = new Array<>();

        Iterable<Line> overlappingLines = linesToSubtract.select(this::isOnLine);

        if (!overlappingLines.iterator().hasNext()) {
            lines.add(this);
            return lines;
        }

        Array<Vector2> filteredVertices = new Array<>();

        ArrayList<Vector2> vertices = new ArrayList<>(List.of(getVertices()));
        overlappingLines.forEach(ol -> vertices.addAll(List.of(ol.getVertices())));

        vertices.sort((a, b) -> (int) Math.signum(b.dst2(new Vector2(this.a.x, this.a.y)) - a.dst2(new Vector2(this.a.x, this.a.y))));

        vertices.forEach(vert -> {
            long count = Arrays.stream(vertices.toArray()).filter(v -> v.equals(vert)).count();
            if (count == 1) filteredVertices.add(vert);
        });

        for (int i = 0; i < filteredVertices.size; i+=2) {
            lines.add(new Line(filteredVertices.get(i), filteredVertices.get(i + 1)));
        }

        return lines;
    }

    boolean isOnLine(Line line) {
        boolean aOnLine = a.dst(line.a) + b.dst((line.a)) == a.dst(b);
        boolean bOnLine = a.dst(line.b) + b.dst((line.b)) == a.dst(b);

        return aOnLine && bOnLine;
    }

    @Override
    public String toString() {
        return String.format("Line [%s,%s]", a.toString(), b.toString());
    }
}
