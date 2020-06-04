// GraphTea Project: http://github.com/graphtheorysoftware/GraphTea
// Copyright (C) 2012 Graph Theory Software Foundation: http://GraphTheorySoftware.com
// Copyright (C) 2008 Mathematical Science Department of Sharif University of Technology
// Distributed under the terms of the GNU General Public License (GPL): http://www.gnu.org/licenses/

package graphtea.extensions.reports.basicreports;

import graphtea.extensions.algorithms.shortestpath.algs.FloydWarshall;
import graphtea.extensions.algs4.AdjMatrixEdgeWeightedDigraph;
import graphtea.extensions.algs4.DirectedEdge;
import graphtea.graph.graph.Edge;
import graphtea.graph.graph.GraphModel;
import graphtea.platform.lang.CommandAttitude;
import graphtea.plugins.reports.extension.GraphReportExtension;

/**
 * @author Mohammad Ali Rostami
 */

@CommandAttitude(name = "graph_diameter", abbreviation = "_gd")
public class Diameter implements GraphReportExtension<Integer> {

    public Integer calculate(GraphModel g) {
        AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(g.numOfVertices());
        for(Edge e : g.edges()) {
            G.addEdge(new DirectedEdge(e.source.getId(), e.target.getId(), 1d));
            G.addEdge(new DirectedEdge(e.target.getId(), e.source.getId(), 1d));
        }
        FloydWarshall spt = new FloydWarshall();
        Integer[][] dist = spt.getAllPairsShortestPathWithoutWeight(g);
        double max = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int u = 0; u < G.V(); u++) {
                if(dist[v][u] < g.numOfVertices()) {
                    double distance = dist[u][v];
                    if(distance > max) {
                        max = distance;
                    }
                }
            }
        }
        return (int)max;
    }

    public String getName() {
        return "Graph Diameter";
    }

    public String getDescription() {
        return "Graph Diameter";
    }


	@Override
	public String getCategory() {
		return "General";
	}
}
