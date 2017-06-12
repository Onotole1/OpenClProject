package model.graph;

import com.sun.istack.internal.NotNull;

/**
 * Date: 11.06.2017
 * Time: 22:00
 *
 * @author Anatoliy
 */
public class Arc implements Cloneable {
    private final Vertex from;
    private final Vertex to;

    public Arc(@NotNull final Vertex from, @NotNull final Vertex to) {
        this.from = from;
        this.to = to;
    }

    public boolean isArcContainsVertex(@NotNull final Vertex vertex) {
        return from.equals(vertex) || to.equals(vertex);
    }

    @Override
    public boolean equals(@NotNull final Object o) {
        if (this == o) return true;
        if (!(o instanceof Arc)) return false;

        final Arc arc = (Arc) o;

        return (from != null ? from.equals(arc.from) : arc.from == null)
                && (to != null ? to.equals(arc.to) : arc.to == null);
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    public Arc clone() throws CloneNotSupportedException {
        return (Arc) super.clone();
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }
}
