package graphtea.extensions.reports.boundcheck.forall.filters;

import graphtea.extensions.reports.boundcheck.forall.GraphFilter;
import graphtea.graph.graph.GraphModel;
import graphtea.graph.graph.Vertex;

/**
 * Created by rostam on 30.09.15.
 */
public class UnicyclicGraph implements GraphFilter {
    @Override
    public boolean filter(GraphModel g) {
        return g.numOfVertices()  == g.getEdgesCount();
    }

    @Override
    public String getName() {
        return "unicyclic";
    }
}
