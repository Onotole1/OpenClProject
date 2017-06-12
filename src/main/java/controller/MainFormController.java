package controller;

import com.sun.istack.internal.Nullable;
import model.graph.Vertex;
import utils.Constants;
import view.MainForm;

/**
 * Date: 12.06.2017
 * Time: 0:32
 *
 * @author Anatoliy
 */
public class MainFormController {
    private final static String RESULT = "%s метод, количество путей: %d, прошедшее время: %d ms";

    private final MainForm mainForm;
    private final GraphController graphController = new GraphController();

    public MainFormController(final MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void updateOnCalculationButtonPressed(final String methodOfCalculation, final String pathSearch) {
        final MainController mainController = MainController.getInstance();

        final Vertex[] verticesFromString = graphController.getVerticesFromString(pathSearch);
        final Vertex vertexFrom = verticesFromString[Constants.VERTEX_FIRST_ELEMENT];
        final Vertex vertexTo = verticesFromString[Constants.VERTEX_SECOND_ELEMENT];

        if (Constants.PARALLEL_METHOD_OF_CALCULATION.equals(methodOfCalculation)) {
            mainController.calculateParallel(graphController.getArcsClones(), vertexFrom, vertexTo);
        } else if (Constants.CONSISTENT_METHOD_OF_CALCULATION.equals(methodOfCalculation)) {
            mainController.calculateConsistent(graphController.getArcsClones(), vertexFrom, vertexTo);
        }
    }

    public void updateOnVertexNumberSpinnerChanged(final int spinnerValue) {
        if (spinnerValue >= 0) {
            graphController.updateVertexNumber(spinnerValue);
            mainForm.setValueSpinnerNumber(spinnerValue);
        }

        mainForm.setAddArcComboBoxModel(graphController.getPossibleArcsToAdd());
        mainForm.setRemoveArcComboBoxModel(graphController.getPossibleArcsToRemove());
        mainForm.setPathSearchComboBoxModel(graphController.getPaths());
        mainForm.setArcsList(graphController.getArcsStringArray());
    }

    public void updateOnAddArcButtonPressed(@Nullable final String selectedItem) {
        if (null != selectedItem) {
            graphController.addArc(selectedItem);
            updateForm();
        }
    }

    public void updateOnRemoveArcButtonPressed(@Nullable final String selectedItem) {
        if (null != selectedItem) {
            graphController.removeArc(selectedItem);
            updateForm();
        }
    }

    private void updateForm() {
        mainForm.setAddArcComboBoxModel(graphController.getPossibleArcsToAdd());
        mainForm.setRemoveArcComboBoxModel(graphController.getPossibleArcsToRemove());
        mainForm.setArcsList(graphController.getArcsStringArray());
    }

    public void showResult(final String methodOfCalculation, final int pathsSize, final long elapsedTime) {
        mainForm.showResult(String.format(RESULT, methodOfCalculation, pathsSize, elapsedTime));
    }

}
