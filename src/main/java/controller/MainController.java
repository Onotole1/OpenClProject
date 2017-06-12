package controller;

import com.aparapi.Kernel;
import com.aparapi.Range;
import com.sun.istack.internal.NotNull;
import model.graph.Arc;
import model.graph.Vertex;
import utils.Constants;
import utils.Timer;
import view.MainForm;

import java.util.ArrayList;

/**
 * Date: 11.06.2017
 * Time: 13:43
 *
 * @author Anatoliy
 */
public class MainController {
    private static MainController instance;
    private MainFormController mainFormController;


    static MainController getInstance() {
        if (null == instance) {
            instance = new MainController();
        }

        return instance;
    }

    private MainController() {
    }

    public static void main(final String[] args) {
        final MainForm mainForm = MainForm.createAndShowMainForm();
        final MainFormController mainFormController = new MainFormController(mainForm);
        mainForm.addObserver(mainFormController);

        final MainController instance = getInstance();
        instance.mainFormController = mainFormController;
    }

    void calculateParallel(@NotNull final ArrayList<Arc> arcsClones
            , final Vertex from, final Vertex to) {

        SyncPathBuilder.resetSyncPathBuilder();

        Timer.getInstance().startTimer();

        final int sizeArcs = arcsClones.size();
        if (sizeArcs > 0) {

            final Kernel kernel = new Kernel() {
                @Override
                public void run() {
                    final int i = getGlobalId();
                    System.out.println(i);
                    if (i < sizeArcs) {
                        final Arc arc = arcsClones.get(i);
                        final SyncPathBuilder pathBuilder = SyncPathBuilder.getInstanceSync(from, to, sizeArcs);
                        pathBuilder.addArc(arc);
                    }
                }
            };

            final Range range = Range.create(Range.THREADS_PER_CORE);

            kernel.execute(range);

        } else {
            calculateParallelCallback(0);
        }
    }

    void calculateParallelCallback(final int pathsSize) {
        final long elapsedTime = Timer.getInstance().calculateElapsedTime();
        System.out.println("Paths size = " + pathsSize + ", elapsed time " + elapsedTime);
        mainFormController.showResult(Constants.PARALLEL_METHOD_OF_CALCULATION, pathsSize, elapsedTime);
    }

    void calculateConsistent(final ArrayList<Arc> arcsClones, final Vertex from, final Vertex to) {
        SyncPathBuilder.resetSyncPathBuilder();

        Timer.getInstance().startTimer();

        final int sizeArcs = arcsClones.size();
        if (sizeArcs > 0) {

            final SyncPathBuilder pathBuilder = SyncPathBuilder.getInstance(from, to, arcsClones.size());

            for (int i = 0, arcsClonesSize = arcsClones.size(); i < arcsClonesSize; i++) {
                final Arc arc = arcsClones.get(i);
                pathBuilder.addArc(arc);
            }
        } else {
            calculateConsistentCallback(0);
        }
    }

    void calculateConsistentCallback(final int pathsSize) {
        final long elapsedTime = Timer.getInstance().calculateElapsedTime();
        System.out.println("Paths size = " + pathsSize + ", elapsed time " + elapsedTime);
        mainFormController.showResult(Constants.PARALLEL_METHOD_OF_CALCULATION, pathsSize, elapsedTime);
    }
}
