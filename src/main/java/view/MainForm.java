package view;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import controller.MainFormController;
import utils.Constants;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Date: 11.06.2017
 * Time: 14:27
 *
 * @author Anatoliy
 */
@SuppressWarnings("unchecked")
public class MainForm {
    private final static int NUMBER_OF_OBSERVERS = 1;
    private final static String[] modelComboBox = {Constants.PARALLEL_METHOD_OF_CALCULATION, Constants.CONSISTENT_METHOD_OF_CALCULATION};

    private JSpinner vertexNumberSpinner;
    private JButton calculationButton;
    private JPanel panelMain;
    private JComboBox methodOfCalculationComboBox;
    private JList arcsList;
    private JButton addArcButton;
    private JButton removeArcButton;
    private JComboBox addArcComboBox;
    private JComboBox removeArcComboBox;
    private JComboBox pathSearchComboBox;
    private JLabel resultLabel;

    private final ArrayList<MainFormController> controllers = new ArrayList<MainFormController>(NUMBER_OF_OBSERVERS);

    public static MainForm createAndShowMainForm() {
        final MainForm mainForm = new MainForm();
        final JFrame jFrame = new JFrame();
        jFrame.setContentPane(mainForm.panelMain);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        return mainForm;
    }

    private MainForm() {
        vertexNumberSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                final Integer spinnerValue = (Integer) vertexNumberSpinner.getValue();

                notifyOnVertexNumberSpinnerChanged(spinnerValue);
            }
        });

        calculationButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String methodOfCalculation = (String) methodOfCalculationComboBox.getItemAt(methodOfCalculationComboBox.getSelectedIndex());
                final String pathSearch = (String) pathSearchComboBox.getItemAt(pathSearchComboBox.getSelectedIndex());
                notifyOnCalculationButtonPressed(methodOfCalculation, pathSearch);
            }
        });

        addArcButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String selectedItem = (String) addArcComboBox.getItemAt(addArcComboBox.getSelectedIndex());
                notifyOnAddArcButtonPressed(selectedItem);
            }
        });

        removeArcButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String selectedItem = (String) removeArcComboBox.getItemAt(removeArcComboBox.getSelectedIndex());
                notifyOnRemoveArcButtonPressed(selectedItem);
            }
        });

        methodOfCalculationComboBox.setModel(new DefaultComboBoxModel(modelComboBox));
    }

    public void setValueSpinnerNumber(final int valueSpinnerNumber) {
        vertexNumberSpinner.setValue(valueSpinnerNumber);
    }

    public void setAddArcComboBoxModel(@NotNull final String[] stringModel) {
        addArcComboBox.setModel(new DefaultComboBoxModel(stringModel));
    }

    public void setRemoveArcComboBoxModel(@NotNull final String[] stringModel) {
        removeArcComboBox.setModel(new DefaultComboBoxModel(stringModel));
    }

    public void addObserver(@NotNull final MainFormController controller) {
        if (!controllers.contains(controller)) {
            controllers.add(controller);
        }
    }

    public void removeObserver(@NotNull final MainFormController controller) {
        if (controllers.contains(controller)) {
            controllers.remove(controller);
        }
    }

    public void setArcsList(@NotNull final String[] arcsListString) {
        arcsList.setListData(arcsListString);
    }

    public void setPathSearchComboBoxModel(@NotNull final String[] pathSearchComboBoxModelStrings) {
        pathSearchComboBox.setModel(new DefaultComboBoxModel(pathSearchComboBoxModelStrings));
    }

    public void showResult(final String format) {
        resultLabel.setText(format);
    }

    private void notifyOnCalculationButtonPressed(@NotNull final String methodOfCalculation, final String pathSearch) {
        for (final MainFormController controller : controllers) {
            controller.updateOnCalculationButtonPressed(methodOfCalculation, pathSearch);
        }
    }

    private void notifyOnVertexNumberSpinnerChanged(final int spinnerValue) {
        for (final MainFormController controller : controllers) {
            controller.updateOnVertexNumberSpinnerChanged(spinnerValue);
        }
    }

    private void notifyOnAddArcButtonPressed(@Nullable final String selectedItem) {
        for (final MainFormController controller : controllers) {
            controller.updateOnAddArcButtonPressed(selectedItem);
        }
    }

    private void notifyOnRemoveArcButtonPressed(@Nullable final String selectedItem) {
        for (final MainFormController controller : controllers) {
            controller.updateOnRemoveArcButtonPressed(selectedItem);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 4, new Insets(24, 24, 24, 24), -1, -1));
        panelMain.setBorder(BorderFactory.createTitledBorder("Курсовая работа"));
        calculationButton = new JButton();
        calculationButton.setText("Вычислить");
        panelMain.add(calculationButton, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 48), null, null, 0, false));
        methodOfCalculationComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        methodOfCalculationComboBox.setModel(defaultComboBoxModel1);
        panelMain.add(methodOfCalculationComboBox, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(168, 211), null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder("Дуги"));
        addArcButton = new JButton();
        addArcButton.setText("Добавить");
        panel1.add(addArcButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 48), null, null, 0, false));
        removeArcButton = new JButton();
        removeArcButton.setText("Удалить");
        panel1.add(removeArcButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 48), null, null, 0, false));
        addArcComboBox = new JComboBox();
        panel1.add(addArcComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), null, null, 0, false));
        removeArcComboBox = new JComboBox();
        panel1.add(removeArcComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Список дуг");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        arcsList = new JList();
        arcsList.setEnabled(true);
        arcsList.setSelectionBackground(new Color(-4473925));
        scrollPane1.setViewportView(arcsList);
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Способ вычисления");
        panelMain.add(label2, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(294, 15), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(0);
        label3.setHorizontalTextPosition(0);
        label3.setText("Найти путь");
        panelMain.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(294, 15), null, 0, false));
        pathSearchComboBox = new JComboBox();
        panelMain.add(pathSearchComboBox, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder("Вершины"));
        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(0);
        label4.setHorizontalTextPosition(0);
        label4.setText("Количество вершин");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(294, 15), null, 0, false));
        vertexNumberSpinner = new JSpinner();
        panel2.add(vertexNumberSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(24, 24), null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder("Результаты"));
        resultLabel = new JLabel();
        resultLabel.setText("");
        panel3.add(resultLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 24), null, null, 0, false));
        label2.setLabelFor(methodOfCalculationComboBox);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }
}
