package controller;

import com.sun.istack.internal.NotNull;
import model.graph.Arc;
import model.graph.Vertex;
import utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Date: 11.06.2017
 * Time: 14:16
 *
 * @author Anatoliy
 * Данный класс отвечает за работу с направленным графом (построение.удаление вершин и дуг)
 */
class GraphController {
    private final static String ARC_STRING = "Из вершины %d в вершину %d";
    private final static String ARC_STRING_FIRST_ELEMENT = "Из вершины ";
    private final static String ARC_STRING_LAST_ELEMENT = " в вершину ";
    private final static String ARC_STRING_EMPTY_STRING = "";
    private final static String ARC_STRING_WHITE_SPACE = " ";
    private final static int ARC_FROM_ELEMENT_POSITION = 0;
    private final static int ARC_TO_ELEMENT_POSITION = 1;

    private final static int VERTEX_PAIR_SIZE = 2;

    private final ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private final TreeMap<String, Arc> arcs = new TreeMap<String, Arc>();

    private void addVertex(final int vertexNumber) {
        final Vertex vertex = new Vertex(vertexNumber);

        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }

    private void removeVertex(final int vertexNumber) {
        final Vertex vertex = new Vertex(vertexNumber);

        if (vertices.contains(vertex)) {
            vertices.remove(vertex);

            removeArcsByVertex(vertex);
        }
    }

    void addArc(@NotNull final String selectedItem) {
        final String replaceFirstElement = selectedItem.replace(ARC_STRING_FIRST_ELEMENT, ARC_STRING_EMPTY_STRING);
        final String replaceLastElement = replaceFirstElement.replace(ARC_STRING_LAST_ELEMENT, ARC_STRING_WHITE_SPACE);

        final String[] split = replaceLastElement.split(ARC_STRING_WHITE_SPACE);
        final String fromString = split[ARC_FROM_ELEMENT_POSITION];
        final String toString = split[ARC_TO_ELEMENT_POSITION];

        final int vertexNumberFrom = Integer.parseInt(fromString);
        final int vertexNumberTo = Integer.parseInt(toString);

        final Vertex vertexFrom = vertices.get(vertexNumberFrom - 1);
        final Vertex vertexTo = vertices.get(vertexNumberTo - 1);

        final Arc arc = new Arc(vertexFrom, vertexTo);

        arcs.put(String.format(ARC_STRING, vertexNumberFrom, vertexNumberTo), arc);
    }

    void removeArc(@NotNull final String selectedItem) {
        final String replaceFirstElement = selectedItem.replace(ARC_STRING_FIRST_ELEMENT, ARC_STRING_EMPTY_STRING);
        final String replaceLastElement = replaceFirstElement.replace(ARC_STRING_LAST_ELEMENT, ARC_STRING_WHITE_SPACE);

        final String[] split = replaceLastElement.split(ARC_STRING_WHITE_SPACE);
        final String fromString = split[ARC_FROM_ELEMENT_POSITION];
        final String toString = split[ARC_TO_ELEMENT_POSITION];

        final int vertexNumberFrom = Integer.parseInt(fromString);
        final int vertexNumberTo = Integer.parseInt(toString);

        final Vertex vertexFrom = vertices.get(vertexNumberFrom - 1);
        final Vertex vertexTo = vertices.get(vertexNumberTo - 1);

        final Arc arc = new Arc(vertexFrom, vertexTo);

        final Iterator<Map.Entry<String, Arc>> iterator = arcs.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Arc> next = iterator.next();
            if (next.getValue().equals(arc)) {
                iterator.remove();
            }
        }
    }

    Vertex[] getVerticesFromString(@NotNull final String string) {
        final String replaceFirstElement = string.replace(ARC_STRING_FIRST_ELEMENT, ARC_STRING_EMPTY_STRING);
        final String replaceLastElement = replaceFirstElement.replace(ARC_STRING_LAST_ELEMENT, ARC_STRING_WHITE_SPACE);

        final String[] split = replaceLastElement.split(ARC_STRING_WHITE_SPACE);
        final String fromString = split[ARC_FROM_ELEMENT_POSITION];
        final String toString = split[ARC_TO_ELEMENT_POSITION];

        final int vertexNumberFrom = Integer.parseInt(fromString);
        final int vertexNumberTo = Integer.parseInt(toString);

        final Vertex[] result = new Vertex[VERTEX_PAIR_SIZE];
        result[Constants.VERTEX_FIRST_ELEMENT] = new Vertex(vertexNumberFrom);
        result[Constants.VERTEX_SECOND_ELEMENT] = new Vertex(vertexNumberTo);

        return result;
    }

    String[] getArcsStringArray() {
        final ArrayList<String> resultArrayList = new ArrayList<String>(arcs.size());

        for (final Map.Entry<String, Arc> next : arcs.entrySet()) {
            final String key = next.getKey();
            resultArrayList.add(key);
        }

        final String[] resultArray = new String[resultArrayList.size()];

        for (int i = 0, resultArrayListSize = resultArrayList.size(); i < resultArrayListSize; i++) {
            final String string = resultArrayList.get(i);
            resultArray[i] = string;
        }

        return resultArray;
    }

    String[] getPossibleArcsToAdd() {
        final ArrayList<String> resultArrayList = new ArrayList<String>();

        for (int i = 0, length = vertices.size(); i < length; i++) {
            final Vertex vertex = vertices.get(i);

            for (final Vertex otherVertex:vertices) {
                final int vertexNumberFrom = vertex.getVertexNumber();
                final int vertexNumberTo = otherVertex.getVertexNumber();

                final String key = String.format(ARC_STRING, vertexNumberFrom, vertexNumberTo);

                if (vertexNumberFrom != vertexNumberTo && !arcs.containsKey(key)) {
                    resultArrayList.add(key);
                }
            }
        }

        final String[] resultArray = new String[resultArrayList.size()];

        for (int i = 0, resultArrayListSize = resultArrayList.size(); i < resultArrayListSize; i++) {
            final String string = resultArrayList.get(i);
            resultArray[i] = string;
        }

        return resultArray;
    }

    /**
     * Получить все пути, которые могут быть при заданном количестве вершин в виде строк
     * (см. ARC_STRING)
     * @return массив строк
     */
    String[] getPaths() {
        final ArrayList<String> resultArrayList = new ArrayList<String>();

        for (int i = 0, length = vertices.size(); i < length; i++) {
            final Vertex vertex = vertices.get(i);

            for (final Vertex otherVertex:vertices) {
                final int vertexNumberFrom = vertex.getVertexNumber();
                final int vertexNumberTo = otherVertex.getVertexNumber();

                final String key = String.format(ARC_STRING, vertexNumberFrom, vertexNumberTo);

                if (vertexNumberFrom != vertexNumberTo) {
                    resultArrayList.add(key);
                }
            }
        }

        final String[] resultArray = new String[resultArrayList.size()];

        for (int i = 0, resultArrayListSize = resultArrayList.size(); i < resultArrayListSize; i++) {
            final String string = resultArrayList.get(i);
            resultArray[i] = string;
        }

        return resultArray;
    }

    String[] getPossibleArcsToRemove() {
        final ArrayList<String> resultArrayList = new ArrayList<String>();

        for (int i = 0, length = vertices.size(); i < length; i++) {
            final Vertex vertex = vertices.get(i);

            for (final Vertex otherVertex:vertices) {
                final int vertexNumberFrom = vertex.getVertexNumber();
                final int vertexNumberTo = otherVertex.getVertexNumber();

                final String key = String.format(ARC_STRING, vertexNumberFrom, vertexNumberTo);

                if (vertexNumberFrom != vertexNumberTo && arcs.containsKey(key)) {
                    resultArrayList.add(key);
                }
            }
        }

        final String[] resultArray = new String[resultArrayList.size()];

        for (int i = 0, resultArrayListSize = resultArrayList.size(); i < resultArrayListSize; i++) {
            final String string = resultArrayList.get(i);
            resultArray[i] = string;
        }

        return resultArray;
    }

    void updateVertexNumber(final int vertexNumber) {
        final int size = vertices.size();

        if (vertexNumber > size) {
            addVertex(vertexNumber);
        } else if (vertexNumber < size) {
            removeVertex(size);
        }
    }

    private void removeArcsByVertex(@NotNull final Vertex vertex) {
        final Iterator<Map.Entry<String, Arc>> iterator = arcs.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Arc> next = iterator.next();
            if (next.getValue().isArcContainsVertex(vertex)) {
                iterator.remove();
            }
        }
    }

    ArrayList<Arc> getArcsClones() {
        final ArrayList<Arc> result = new ArrayList<Arc>(arcs.size());

        for (final Map.Entry<String, Arc> next : arcs.entrySet()) {
            final Arc value = next.getValue();
            try {
                result.add(value.clone());
            } catch (final CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
