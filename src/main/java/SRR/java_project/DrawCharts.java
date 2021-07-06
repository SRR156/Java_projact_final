package SRR.java_project;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.*;
import org.knowm.xchart.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class DrawCharts {

    //Methods

    // This method draws a pie chart
    public static void drawPieChart(Dataset<Row> data, int width, int height, String title) {
        PieChart chart = new PieChartBuilder().width(width).height(height).title(title).build();
        data.collectAsList().stream().forEach(row -> chart.addSeries(row.get(0).toString(), Integer.parseInt(row.get(1).toString())));

        new SwingWrapper(chart).displayChart();
    }

    // This method draws a bar chart
    public static void drawBarChart(Dataset<Row> data, String title, String xTitle, String yTitle, int width, int height) {
        List<String> names = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        data.collectAsList().subList(0, 10).forEach(row -> names.add(row.get(0).toString()));
        data.collectAsList().subList(0, 10).forEach(row -> count.add(Integer.parseInt(row.get(1).toString())));
        CategoryChart chart = new CategoryChartBuilder().height(width).width(height).title(title).xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.addSeries(title, names, count);
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }
}