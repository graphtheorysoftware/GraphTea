// GraphTea Project: http://github.com/graphtheorysoftware/GraphTea
// Copyright (C) 2012 Graph Theory Software Foundation: http://GraphTheorySoftware.com
// Copyright (C) 2008 Mathematical Science Department of Sharif University of Technology
// Distributed under the terms of the GNU General Public License (GPL): http://www.gnu.org/licenses/
package graphtea.extensions.reports.zagreb;

import graphtea.graph.graph.GraphModel;
import graphtea.platform.lang.CommandAttitude;
import graphtea.platform.parameter.Parameter;
import graphtea.platform.parameter.Parametrizable;
import graphtea.plugins.reports.extension.GraphReportExtension;

import java.util.ArrayList;

/**
 * @author Ali Rostami

 */

@CommandAttitude(name = "zagreb_index", abbreviation = "_zi")
public class ZagrebIndex implements GraphReportExtension<ArrayList<String>>, Parametrizable {
    public String getName() {
        return "All Zagreb Indices";
    }

    @Parameter(name = "Alpha", description = "")
    public Double alpha = 1.0;

    public String getDescription() {
        return "All Zagreb Indices";
    }

    public ArrayList<String> calculate(GraphModel g) {
        ArrayList<String> out = new ArrayList<>();
        ZagrebIndexFunctions zif = new ZagrebIndexFunctions(g);
        out.add("First General Zagreb Index : "+ zif.getFirstZagreb(alpha));
        out.add("Second General Zagreb Index : "+ zif.getSecondZagreb(alpha));
        out.add("First Reformulated Zagreb Index : " + zif.getFirstReZagreb(alpha));
        out.add("Second Reformulated Zagreb Index : " + zif.getSecondReZagreb(alpha));
        return out;
    }

    public String checkParameters() {
        return null;
    }

    @Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Topological Indices-Zagreb Indices";
	}
}
