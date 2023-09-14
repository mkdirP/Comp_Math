package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

public class FunctionPlotter extends ApplicationFrame {

    public FunctionPlotter(String title, double[][] rematrix) {
        super(title);

        DefaultXYDataset dataset = createDataset(rematrix);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultXYDataset createDataset(double[][] rematrix) {
        DefaultXYDataset dataset = new DefaultXYDataset();

        dataset.addSeries("Data", rematrix);

        return dataset;
    }

    private JFreeChart createChart(DefaultXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        // 设置函数图像颜色为绿色
        renderer.setSeriesPaint(0, Color.RED);
        // 设置数据点形状
        renderer.setSeriesShapesVisible(0, true);
        // 设置数据点颜色为红色
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesPaint(1, Color.BLUE);

        return chart;
    }

}
