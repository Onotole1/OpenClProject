package controller;

import com.sun.istack.internal.NotNull;
import model.graph.Arc;
import model.graph.Vertex;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Date: 12.06.2017
 * Time: 13:17
 *
 * @author Anatoliy
 */
class SyncPathBuilder {
    private static SyncPathBuilder instance;
    private final Vertex from;
    private final Vertex to;
    private final int size;
    private final ArrayList<Arc> arcs;

    private boolean isSync;

    static void resetSyncPathBuilder() {
        instance = null;
    }

    static SyncPathBuilder getInstance(@NotNull final Vertex from, @NotNull final Vertex to
            , final int size) {
        if (null == instance) {
            instance = new SyncPathBuilder(from, to, size);

            instance.isSync = false;
        }

        return instance;
    }

    static synchronized SyncPathBuilder getInstanceSync(@NotNull final Vertex from, @NotNull final Vertex to
            , final int size) {
        if (null == instance) {
            instance = new SyncPathBuilder(from, to, size);

            instance.isSync = true;
        }

        return instance;
    }

    private SyncPathBuilder(@NotNull final Vertex from, @NotNull final Vertex to, final int size) {
        this.from = from;
        this.to = to;
        this.size = size;

        arcs = new ArrayList<Arc>(size);
    }

    void addArc(final Arc arc) {
        if (!arcs.contains(arc)) {
            arcs.add(arc);

            if (arcs.size() == size) {
                computePaths();
            }
        }
    }
    
    private void computePaths() {
        final Iterator<Arc> iterator = arcs.iterator();
        int pathsSize = 0;
        final ArrayList<Arc> currentPath = new ArrayList<Arc>(size);
        
        while (iterator.hasNext()) {
            final Arc next = iterator.next();

            if (next.getFrom().equals(from) && next.getTo().equals(to)) {
                iterator.remove();

                System.out.println("Current path " + pathsSize + ":");
                System.out.println("[" + next.getFrom().getVertexNumber() + "," + " " + next.getTo().getVertexNumber() + "]");
                pathsSize++;
            } else if (addAndSortInCurrentPath(currentPath, next)) {
                iterator.remove();
            }

            if (currentPath.size() > 1 && checkCurrentPath(currentPath, from, to)) {

                System.out.println("Current path " + pathsSize + ":");
                for (final Arc arc:currentPath) {
                    System.out.println("[" + arc.getFrom().getVertexNumber() + "," + " " + arc.getTo().getVertexNumber() + "]");
                }

                currentPath.clear();

                pathsSize++;
            }
        }

        final MainController mainController = MainController.getInstance();

        if (isSync) {
            mainController.calculateParallelCallback(pathsSize);
        } else {
            mainController.calculateConsistentCallback(pathsSize);
        }
    }

    private boolean addAndSortInCurrentPath(@NotNull final ArrayList<Arc> currentPath, @NotNull final Arc arc) {
        final Vertex arcFrom = arc.getFrom();
        final Vertex arcTo = arc.getTo();

        if (currentPath.size() > 0) {
            for (final Arc currentPathArc:currentPath) {
                final Vertex currentPathArcFrom = currentPathArc.getFrom();
                final Vertex currentPathArcTo = currentPathArc.getTo();

                if (arcFrom.equals(currentPathArcTo)) {
                    final int indexOfArc = currentPath.indexOf(currentPathArc);
                    currentPath.add(indexOfArc + 1, arc);
                    return true;
                } else if (arcTo.equals(currentPathArcFrom)) {
                    final int indexOfArc = currentPath.indexOf(currentPathArc);
                    currentPath.add(indexOfArc, arc);
                    return true;
                }
            }
        } else {
            currentPath.add(arc);
            return true;
        }

        return false;
    }

    private boolean checkCurrentPath(@NotNull final ArrayList<Arc> currentPath, @NotNull final Vertex from
            , @NotNull final Vertex to) {

        final Arc firstArc = currentPath.get(0);
        final Arc lastArc = currentPath.get(currentPath.size() - 1);

        final Vertex firstArcFrom = firstArc.getFrom();
        final Vertex lastArcTo = lastArc.getTo();

        return from.equals(firstArcFrom) && to.equals(lastArcTo);
    }
}
