// GraphTea Project: http://github.com/graphtheorysoftware/GraphTea
// Copyright (C) 2012 Graph Theory Software Foundation: http://GraphTheorySoftware.com
// Copyright (C) 2008 Mathematical Science Department of Sharif University of Technology
// Distributed under the terms of the GNU General Public License (GPL): http://www.gnu.org/licenses/
package graphtea.extensions.reports.topological;

import graphtea.extensions.algorithms.shortestpath.algs.FloydWarshall;
import graphtea.graph.graph.GraphModel;
import graphtea.platform.lang.CommandAttitude;
import graphtea.plugins.reports.extension.GraphReportExtension;

/**
 * @author Ali Rostami
 */


@CommandAttitude(name = "harary_index", abbreviation = "_Additiveharary")
public class AdditiveHarary implements GraphReportExtension<Double> {
    public String getName() {
        return "AdditiveHarary Index";
    }

    public String getDescription() {
        return "AdditiveHarary Index";
    }

    public Double calculate(GraphModel g) {
        double sum =0;
        FloydWarshall fw = new FloydWarshall();
        int[][] spt = fw.getAllPairsShortestPathWithoutWeight(g);

        double max = 0;
        for (int v = 0; v < g.numOfVertices(); v++) {
            for (int u = v+1; u < g.numOfVertices(); u++) {
                if(spt[v][u] < g.numOfVertices()+1) {
                    double dist = spt[u][v];
                    if(dist > max) {
                    	int degreeOfU = g.getDegree(g.getVertex(u));
                    	int degreeOfV = g.getDegree(g.getVertex(v));
                        sum += (degreeOfU + degreeOfV)*(1 / dist);
                    }
                }
            }
        }
        return sum;
    }

	@Override
	public String getCategory() {
		return "Topological Indices-Distance";
	}
}
