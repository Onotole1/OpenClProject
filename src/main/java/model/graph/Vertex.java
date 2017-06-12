package model.graph;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

/**
 * Date: 11.06.2017
 * Time: 22:00
 *
 * @author Anatoliy
 */
public class Vertex implements Cloneable {
    private final int vertexNumber;

    public Vertex(final int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    @Override
    public boolean equals(@NotNull final Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        final Vertex vertex = (Vertex) o;

        return vertexNumber == vertex.vertexNumber;
    }

    @Override
    public int hashCode() {
        return vertexNumber;
    }
}
