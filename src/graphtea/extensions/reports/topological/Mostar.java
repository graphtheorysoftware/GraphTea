package graphtea.extensions.reports.topological;


import graphtea.extensions.AlgorithmUtils;
import graphtea.extensions.reports.ChromaticNumber;
import graphtea.extensions.reports.basicreports.Diameter;
import graphtea.extensions.reports.basicreports.NumOfVerticesWithDegK;
import graphtea.extensions.reports.clique.MaxCliqueSize;
import graphtea.extensions.reports.others.MostarIndex;
import graphtea.graph.graph.Edge;
import graphtea.graph.graph.GraphModel;
import graphtea.graph.graph.RenderTable;
import graphtea.graph.graph.Vertex;
import graphtea.platform.lang.CommandAttitude;
import graphtea.plugins.reports.extension.GraphReportExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * @author Ali Rostami
 */

@CommandAttitude(name = "Mostar", abbreviation = "_Mostar")
public class Mostar implements GraphReportExtension<RenderTable> {
    public String getName() {
        return "Mostar";
    }

    public String getDescription() {
        return " Mostar ";
    }

    public RenderTable calculate(GraphModel g) {
        ZagrebIndexFunctions zif = new ZagrebIndexFunctions(g);
        RenderTable ret = new RenderTable();
        Vector<String> titles = new Vector<>();
        titles.add(" m ");
        titles.add(" n ");

        //     titles.add(" R ");
        //    titles.add("(n/2)- R ");
        //    titles.add(" H ");
        //    titles.add(" VR ");
        //      titles.add(" f(G) ");
        //            titles.add(" VR ");
        //          titles.add("(n/2)- VR ");
        //         titles.add(" GA ");
        //  titles.add(" chi ");
        //   titles.add("ID");
      //  titles.add("MerreField-Simmons");
     //   titles.add("Hosaya");
        //  titles.add(" SDD ");
        //   titles.add(" ISI ");
        //  titles.add(" check ");
        //   titles.add(" Rao Sir ");

        //  titles.add("(n/2)- chi ");
        //   titles.add(" W_p ");
        //    titles.add(" Diameter ");
        //     titles.add("cr");
        // titles.add("f(G)");
        //  titles.add("Hyper");
        //titles.add("T-1");
        //  titles.add("T-2");
        //   titles.add("Zenergy");
        //  titles.add("H");
        //  titles.add("check Shi");
        //   titles.add("(n/2)-R");
        //    titles.add("(n/2)-SCI");
        //        titles.add("ISI..");
        //         titles.add("Our");
        //       titles.add("New");
        //        titles.add("our Old");
        //   titles.add("HC");
        //titles.add("m-GA");
        //titles.add("R");
        // titles.add("AZI");
        //    titles.add("shi");
        //     titles.add("our");
        //     titles.add("H check.");
        // titles.add(" milo1 ");
        //  titles.add("R1 check");
        //  titles.add(" GA ");
        //    titles.add(" H ");
        //     titles.add(" chi ");
        //  titles.add(" check1 ");
        //   titles.add(" clique.check ");
        //      titles.add(" R1 ");
        //     titles.add(" chi-1 ");
        //      titles.add(" chi-0.5 ");
        //       titles.add(" chi-0.1 ");
        //   titles.add(" ISI ");
        //      titles.add("Chromatic number");
        //   titles.add(" L.H.S ");
        //       titles.add(" R.H.S even ");
        //    titles.add(" R.H.S odd ");

        //   titles.add("R");

        //titles.add("Wiener");
        //titles.add("Avg");
       // titles.add("Diameter");
      //  titles.add("Mostar");
	  titles.add("Mo-irregular");
	  	  titles.add("Mo-Albertson");
        //    titles.add("Clique Number");

        //     titles.add(" V. Degrees ");

        ret.setTitles(titles);

        double maxDeg = 0;
        double maxDeg2 = 0;
        double minDeg = Integer.MAX_VALUE;
        double minDeg2 = AlgorithmUtils.getMinNonPendentDegree(g);

        ArrayList<Integer> al = AlgorithmUtils.getDegreesList(g);
        Collections.sort(al);
        maxDeg = al.get(al.size()-1);
        if(al.size()-2>=0) maxDeg2 = al.get(al.size()-2);
        else maxDeg2 = maxDeg;
        minDeg = al.get(0);
        if(maxDeg2 == 0) maxDeg2=maxDeg;

        double a=0;
        double b=0;
        double c=0;
        double d=0;
        int p = NumOfVerticesWithDegK.numOfVerticesWithDegK(g, 1);

        for(Vertex v : g) {
            if(g.getDegree(v)==maxDeg) a++;
            if(g.getDegree(v)==minDeg) b++;
            if(g.getDegree(v)==maxDeg2) c++;
            if(g.getDegree(v)==minDeg2) d++;
        }
        if(maxDeg==minDeg) b=0;
        if(maxDeg==maxDeg2) c=0;

        double m = g.getEdgesCount();
        double n = g.getVerticesCount();

        double maxEdge = 0;
        double maxEdge2 = 0;
        double minEdge = Integer.MAX_VALUE;

        ArrayList<Integer> all = new ArrayList<>();
        for(Edge e : g.getEdges()) {
            int f = g.getDegree(e.source) +
                    g.getDegree(e.target) - 2;
            all.add(f);
        }
        Collections.sort(all);
        maxEdge = all.get(all.size()-1);
        if(all.size()-2>=0) maxEdge2 = all.get(all.size()-2);
        else maxEdge2 = maxEdge;
        minEdge = all.get(0);




        double M12=zif.getSecondZagreb(1);
        double M21=zif.getFirstZagreb(1);
        double H=zif.getHarmonicIndex();
        double M31=zif.getFirstZagreb(2);
        double M41=zif.getFirstZagreb(3);
        double M22=zif.getSecondZagreb(2);
        double Mm31=zif.getFirstZagreb(-4);
        double Mm11=zif.getFirstZagreb(-2);
        double irr=zif.getThirdZagreb();
        double R=zif.getSecondZagreb(-0.5);
        double R1=zif.getSecondZagreb(-1);
        double GA=zif.getGAindex();
        double ABC=zif.getABCindex();
        double HC=zif.getHarmonicCoindex();
        double chi11=zif.getGeneralSumConnectivityIndex(-1);
        double chi22=zif.getGeneralSumConnectivityIndex(-0.5);
        double chi33=zif.getGeneralSumConnectivityIndex(-0.1);
        double chi=zif.getGeneralSumConnectivityIndex(-0.5);
        double chi1=zif.getGeneralSumConnectivityIndex(1);
        double chi2=zif.getGeneralSumConnectivityIndex(2);
        double chi3=zif.getGeneralSumConnectivityIndex(3);
        double IED=zif.getEdgeDegree(-1);
        double SDD=zif.getSDDIndex();
        double Albertson=zif.getAlbertson();
		double irregular=zif.getirregularity();
        double ID=zif.getFirstZagreb(-2);
        double AZI=zif.getAugumentedZagrebIndex();
        double ISI=zif.getInverseSumIndegIndex();
        double chrome=ChromaticNumber.getChromaticNumber(g);
        double clique= MaxCliqueSize.maxCliqueSize(g);
        double ZEnergy=zif.getRandicEnergy(g);
        double VR=zif.getVariationRandicIndex();
        double check=zif.getCheck();
        //double S3=(M21-M3)/2;
        double RM2=zif.getRM2();

        double alpha=(1/m)*(Math.floor(m))*(1-((1/m)*(Math.floor(m/2))));
        double alpha1=(m)*(Math.floor(m/2))*(1-((1/m)*(Math.floor(m/2))));

        int diameter = (int) new Diameter().calculate(g);
        WienerIndex wi = new WienerIndex();
        double Avg=(n*(n-1)/2);
        MostarIndex mi = new MostarIndex();
        int mostar = (int)mi.calculate(g);


        Vector<Object> v = new Vector<>();
        v.add(m);
        v.add(n);

        //  v.add(R);
        //   v.add(H);
        //    v.add(VR);
        //     v.add(GA);
        //     v.add(chi);
        //     v.add(ID);
        //        v.add(SDD);
        //        v.add(ISI);

        //      v.add((n/2)-R);

        //    v.add((n/2)-H);
        //       v.add(VR);
        //      v.add((n/2)-VR);


        //

        //    v.add(SDD-(2*m));
        //rao sir
        //     v.add(((n-5)*((n*n)-(2*n)+2)/(n-1)) +(2*((n*n)-(2*n)+5)/(n-1)) +4 );
        //     v.add(((p*minDeg2)/(maxDeg+1))+((m-p)*(Math.sqrt(  (2*maxDeg*Math.pow(minDeg2,6))/((Math.pow(maxDeg,6))+(2*Math.pow(minDeg2,5))+(4*Math.pow(minDeg2,2)*Math.pow(maxDeg,3))  )   ))));
        //  v.add(RM2);
        //  v.add(chi-(n/2));

       // v.add(new NumOfIndSets().calculate(g));
      //  v.add((Integer)new NumOfIndSets().calculate(Utils.createLineGraph(g)));
        //   v.add(ISI);
        //  v.add((H*M12)/(2*m));
        //  v.add( ((H*M12)/(2*m)) + ((alpha*m*((maxEdge+2)-(minEdge+2))*((maxDeg*maxDeg)-(minDeg*minDeg))) /((maxEdge+2)*(minEdge+2))) );
        //   v.add( ((H*M12)/(2*m)) + (alpha1*(maxDeg-minDeg)*(maxDeg-minDeg)*(maxDeg+minDeg)/(2*m*maxDeg*minDeg)) );

        // v.add((H*M12)/(2*m));
        //   v.add(ABC);
        //    v.add(n*(Math.sqrt(((chrome*(n-1))-n)/(2*chrome))));
        //  v.add(AZI);
        //   v.add(chrome);
        //  v.add(chrome);
        // v.add(HC);
        // v.add(chi2);
        // v.add((2*(maxDeg+minDeg)*M21) -(4*m*maxDeg*minDeg));
        // v.add((2*(maxDeg+minDeg)*M21) -(4*m*maxDeg*minDeg) + M21 +(2*maxDeg*minDeg*H)-(2*m*(maxDeg+minDeg)));
        // v.add(M21 +(2*maxDeg*minDeg*H)-(2*m*(maxDeg+minDeg)));
        // v.add(GA);
        //  v.add(HC);
        // v.add(m-GA);
        //   v.add((n/2)-R);
        //  v.add(-(n/2)+chi);
        // v.add(n*Math.sqrt(maxDeg*minDeg)/(minDeg+maxDeg));
        //  v.add((n*minDeg)/(minDeg+maxDeg));
        //  v.add((2*n)/(maxDeg-minDeg+4));
        //   v.add(H);
        //   v.add((2/(maxEdge+2)) + (2*(chi-(1/Math.sqrt((maxEdge+2))))*(chi-(1/Math.sqrt((maxEdge+2))))/(m-1)) + ((Math.sqrt((minEdge+2))-Math.sqrt((minEdge+2)))*(Math.sqrt((minEdge+2))-Math.sqrt((minEdge+2)))/(maxEdge2*minEdge))  );
        //    v.add(ZEnergy);
        //      v.add(GA);
        // v.add(((n*(maxDeg+minDeg))-(2*m)) / (2*maxDeg*minDeg));
        //v.add(( (a/maxDeg) + (b/minDeg) + ((((n-b)*maxDeg)+((n-a)*minDeg)-(2*m) ) / ((maxDeg-1)*(minDeg+1))) )/2 );
        //  v.add(AZI);
        //   v.add((n*Math.sqrt(minDeg*maxDeg))/(maxDeg+minDeg));
        //  v.add((2*maxDeg*minDeg*n)/((maxDeg+minDeg)*(maxDeg+minDeg)));
        //      v.add(chi11);
        //       v.add(chi22);
        //        v.add(chi33);
        //
        //  v.add((2*m*R)/n);
        //   v.add((Math.pow(n-3,2)/2) + (Math.sqrt(n-1)*2/n) + (((2*(n-2)*Math.sqrt((n-1)*(n-2))))/((2*n)-3)) - ((n-2)/Math.sqrt((n-1)*(n-2))) -(1/Math.sqrt((n-1))) );
        //v.add((n*(maxDeg+minDeg)-(2*m))/(2*maxDeg*minDeg));
        //    v.add((1/(2*maxDeg2))+(((n-1)*(maxDeg2+minDeg)-((2*m)-n+1))/(2*maxDeg2*minDeg)) );
        //     v.add(chi);
        //  v.add(ISI);
        //  v.add(chi);
        // Randic check incomplete equality
        //    v.add( ((chrome-2)/2) + (((n-chrome)+Math.sqrt(chrome-1))/(Math.sqrt(n-1))) );
        //     v.add( ((clique-2)/2) + (((n-clique)+Math.sqrt(clique-1))/(Math.sqrt(n-1))) );
        //        v.add(chrome);
        //       v.add(chrome*m/n);
        //  v.add(0.88*(n-1)/2);
        //       v.add(clique);
        //  v.add(n*n/8);
        //   v.add(Math.pow(((n*n)-1), 1.5)/(8*n));

        //  v.add(ID);
        //  v.add(wi.calculate(g));
        //  v.add(Avg);
       // v.add(diameter);
	   v.add(mostar-irregular);
	   	   v.add(mostar-Albertson);
       // v.add(mostar);
        // v.add(MaxCliqueExtension.maxCliqueSize(g));


        //   v.add(al.toString());

        ret.add(v);
        return ret;
    }

    @Override
    public String getCategory() {
        return "Verification-Degree";
    }
}




