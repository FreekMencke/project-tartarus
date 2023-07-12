package com.freekmencke.tartarus.map.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineTest {

    @Test
    @DisplayName("Should return true when the lines overlap")
    void testIsOnLine() {
        Line line = new Line(new Vector2(-2,0), new Vector2(2,0));

        assertTrue(line.isOnLine(new Line(new Vector2(-1, 0), new Vector2(1, 0))));
        assertTrue(line.isOnLine(new Line(new Vector2(-2, 0), new Vector2(0, 0))));
        assertTrue(line.isOnLine(new Line(new Vector2(0, 0), new Vector2(2, 0))));
    }

    @Test
    @DisplayName("Should substract line at the beginning of a line")
    void testSubstractLineAtStart() {
        Line line = new Line(new Vector2(0, 0), new Vector2(0, 4));
        Array<Line> substractLines = new Array<>();
        substractLines.add(new Line(new Vector2(0, 0), new Vector2(0, 2)));

        Array<Line> resultLines = line.subtractLines(substractLines);

        assertEquals(1, resultLines.size);
        assertTrue(Arrays.stream(resultLines.get(0).getVertices()).anyMatch(v -> v.equals(new Vector2(0, 2))));
        assertTrue(Arrays.stream(resultLines.get(0).getVertices()).anyMatch(v -> v.equals(new Vector2(0, 4))));
    }

    @Test
    @DisplayName("Should substract line at the end of a line")
    void testSubstractLineAtEnd() {
        Line line = new Line(new Vector2(0, 0), new Vector2(0, 4));
        Array<Line> substractLines = new Array<>();
        substractLines.add(new Line(new Vector2(0, 2), new Vector2(0, 4)));

        Array<Line> resultLines = line.subtractLines(substractLines);

        assertEquals(1, resultLines.size);
        assertTrue(Arrays.stream(resultLines.get(0).getVertices()).anyMatch(v -> v.equals(new Vector2(0, 0))));
        assertTrue(Arrays.stream(resultLines.get(0).getVertices()).anyMatch(v -> v.equals(new Vector2(0, 2))));
    }
}
