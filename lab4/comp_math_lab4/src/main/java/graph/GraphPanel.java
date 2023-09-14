package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.function.Function;

public class GraphPanel extends ApplicationFrame{
    public static final double DEFAULT_STEP = 0.05;

    public GraphPanel(String title) {
        super(title);
    }

    public void graph(double a, double b,  Function<Double, Double>... functions) {
        XYDataset dataset = generateDataset(a, b, DEFAULT_STEP, functions);
        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Graph",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        ChartPanel panel = new ChartPanel(chart);
        //控制如何绘制缩放矩形的标志。
        panel.setFillZoomRectangle(false);
        //设置控制是否为域轴启用缩放的标志。
        panel.setDomainZoomable(true);
        //控制垂直轴上基于鼠标的缩放的标志。
        panel.setRangeZoomable(true);
        pack();
        setSize(800, 600);
        // 将窗口的位置设置为屏幕中心。
        setLocationRelativeTo(null);
        //将图表或其他内容添加到窗口中的关键步骤。
        setContentPane(panel);
        //将窗口设置为可见。
        setVisible(true);
    }

    private XYDataset generateDataset(double from, double to, double step, Function<Double, Double>... functions) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Function<Double, Double> f: functions) {
            XYSeries series = new XYSeries(f.hashCode());
            for (double x = from; x < to + step; x += step) {
                series.add(x, f.apply(x));
            }
            dataset.addSeries(series);
        }

        return dataset;
    }
}
