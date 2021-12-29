//import java.time.LocalDate;
//import java.time.ZoneOffset;
//import java.util.ArrayList;
//import java.util.Date;
//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;
//import javax.swing.WindowConstants;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.category.IntervalCategoryDataset;
//import org.jfree.data.gantt.Task;
//import org.jfree.data.gantt.TaskSeries;
//import org.jfree.data.gantt.TaskSeriesCollection;
//public class GanttChartExample extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//
//    public GanttChartExample(String title) {
//        super(title);
//
//        IntervalCategoryDataset dataset = getCategoryDataset();
//
//        JFreeChart chart = ChartFactory.createGanttChart(
//                "CPU Scheduling Graph", // Chart title
//                "time", // X-Axis Label
//                "Process", // Y-Axis Label
//                dataset,true,true,true);
//
//        ChartPanel panel = new ChartPanel(chart);
//        setContentPane(panel);
//    }
//
//    private IntervalCategoryDataset getCategoryDataset() {
//        ArrayList<Process> processes = new ArrayList<>();
//        Process p1=new Process("p1","",1,6,1,4);
//        Process p2=new Process("p2","",1,5,1,3);
//        Process p3=new Process("p3","",2,3,3,5);
//        Process p4=new Process("p4","",2,3,2,2);
////
//        processes.add(p1);
//        processes.add(p2);
//        processes.add(p3);
//        processes.add(p4);
//        Agat a= new Agat(processes);
//        a.showAgatOutput();
//        ArrayList<Process> orderedProcess=a.getOrderProcess();
//        ArrayList<Integer> orderedTime= a.getOrderTime();
//        TaskSeries series1 = new TaskSeries("Estimated Date");
//
//
//        series1.add(new Task("Requirement",
//                Date.from(LocalDate.of(2017,7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),
//                Date.from(LocalDate.of(2017, 7,7).atStartOfDay().toInstant(ZoneOffset.UTC))
//        ));
//        series1.add(new Task(orderedProcess.get(0).name,0,
//                Date.from(LocalDate.of(2017, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
//        ));
//        for (int i = 0; i < orderedProcess.size(); i++) {
//            series1.add(new Task(orderedProcess.get(i).name,Date.from(LocalDate.of(2017, 7,10).atStartOfDay().toInstant(ZoneOffset.UTC)),
//                    Date.from(LocalDate.of(2017, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
//            ));
//        }
//
//
////        series1.add(new Task("Design",Date.from(LocalDate.of(2017, 7,10).atStartOfDay().toInstant(ZoneOffset.UTC)),
////                Date.from(LocalDate.of(2017, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
////        ));
////
////        series1.add(new Task("Coding",Date.from(LocalDate.of(2017, 7,17).atStartOfDay().toInstant(ZoneOffset.UTC)),
////                Date.from(LocalDate.of(2017, 7, 21).atStartOfDay().toInstant(ZoneOffset.UTC))
////        ));
////
////        series1.add(new Task("Testing", Date.from(LocalDate.of(2017, 7,24).atStartOfDay().toInstant(ZoneOffset.UTC)),
////                Date.from(LocalDate.of(2017, 7, 28).atStartOfDay().toInstant(ZoneOffset.UTC))
////        ));
////
////        series1.add(new Task("Deployment", Date.from(LocalDate.of(2017, 07,31).atStartOfDay().toInstant(ZoneOffset.UTC)),
////                Date.from(LocalDate.of(2017, 8, 4).atStartOfDay().toInstant(ZoneOffset.UTC))
////        ));
////
//
//
//        TaskSeriesCollection dataset = new TaskSeriesCollection();
//        dataset.add(series1);dataset.add(series2);
//        return dataset;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            GanttChartExample example = new GanttChartExample("Gantt Chart Example");
//            example.setSize(800, 400);
//            example.setLocationRelativeTo(null);
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            example.setVisible(true);
//        });
//    }
//}
/* ---------------------------
 * StackedXYBarChartDemo1.java
 * ---------------------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 */


package org.jfree.chart.demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a stacked bar chart
 * using data from a {@link CategoryDataset}.  This demo also has item labels displayed.
 *
 */
public class StackedBarChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo2(final String title) {

        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private CategoryDataset createDataset() {
        return DemoDatasetFactory.createCategoryDataset();
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return a sample chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createStackedBarChart(
                "Stacked Bar Chart Demo 2",
                "Category",                  // domain axis label
                "Value",                     // range axis label
                dataset,                     // data
                PlotOrientation.HORIZONTAL,  // the plot orientation
                true,                        // include legend
                true,                        // tooltips
                false                        // urls
        );

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setItemLabelsVisible(true);

        return chart;

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    *
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final StackedBarChartDemo2 demo = new StackedBarChartDemo2("Stacked Bar Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}