// GraphTea Project: http://github.com/graphtheorysoftware/GraphTea
// Copyright (C) 2012 Graph Theory Software Foundation: http://GraphTheorySoftware.com
// Copyright (C) 2008 Mathematical Science Department of Sharif University of Technology
// Distributed under the terms of the GNU General Public License (GPL): http://www.gnu.org/licenses/
package graphtea.extensions.reports;
import graphtea.platform.lang.CommandAttitude;
import graphtea.plugins.main.GraphData;
import graphtea.plugins.reports.extension.GraphReportExtension;
import graphtea.graph.graph.Edge;
import graphtea.platform.core.BlackBoard;
import graphtea.graph.graph.GraphModel;
import graphtea.graph.graph.SubGraph;
import graphtea.graph.graph.Vertex;
import graphtea.library.BaseVertex;
import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;
/**
 * @author Mostafa Shaeri (m.shaeri@stu.umz.ac.ir)
 */

@CommandAttitude(name = "maxium_Cut", abbreviation = "max_Cut")
public class MaxCut implements GraphReportExtension {
    public String getName() {
        return "Maximum Cut";
    }

    public String getDescription() {
        return "Axproximate Maximum Cut In An Undirected Graph";
    }

    public Object calculate(GraphData gd) {
        return getMaxCut(gd.getGraph());
    }



    private int getMaxCut(GraphModel graph){

         permution(graph);
         Vertex v[]=graph.getVertexArray();
         for(int i=0;i<graph.getVerticesCount();i++)
         {
         if(getVertexInnerWeight(graph,v[i])>getVertexOuterWeight(graph,v[i]))
            {
                v[i].setColor(v[i].getColor() == 2 ? 3 : 2);
                continue;
            }
        }
         return getCutWeight(graph);

    }


        private int getCutWeight(GraphModel graph){
            Vertex v[]=graph.getVertexArray();
            int SumCutWeight=0;
            for(int i=0;i<graph.getVerticesCount();i++)
                {
                   SumCutWeight+=getVertexOuterWeight(graph,v[i]);
                }

            return SumCutWeight/2;
        }

        private void permution(GraphModel graph){
         int VertexCount=graph.getVerticesCount();
         Vertex v[]=graph.getVertexArray();
         Random rnd=new Random();
         for(int i=0;i<VertexCount;i++)
         {
            if(rnd.nextBoolean())
            {
                ((Vertex)v[i]).setColor(2);
            }
            else
            {
                ((Vertex)v[i]).setColor(3);
            }
         }
        }

        private int getVertexInnerWeight(GraphModel graph,Vertex v){
            int SumWeight=0;
            for(Vertex n: graph.neighbors(v))
              {
                    if(n.getColor()==v.getColor())
                         SumWeight+=(graph.getEdge(v,n).getWeight());
              }

              return (SumWeight/2);
        }

        private int getVertexOuterWeight(GraphModel graph,Vertex v){
            int SumWeight=0;
            for(Vertex n: graph.neighbors(v))
              {
                    if(n.getColor()!=v.getColor())
                         SumWeight+=(graph.getEdge(v,n).getWeight());
              }

              return (SumWeight/2);
        }




	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Property";
	}
}
