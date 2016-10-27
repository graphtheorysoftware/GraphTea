package graphtea.extensions.reports.boundcheck.forall.filters;

import graphtea.extensions.reports.boundcheck.forall.GraphFilter;
import graphtea.graph.graph.GraphModel;

/**
 * Created by rostam on 30.09.15.
 */
public class BicyclicGraph implements GraphFilter {
    @Override
    public boolean filter(GraphModel g) {
        return g.numOfVertices()  == g.getEdgesCount() + 1;
    }

    @Override
    public String getName() {
        return "bicyclic";
    }
}
