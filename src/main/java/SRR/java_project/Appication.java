package SRR.java_project;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructType;

import java.util.*;

public class Appication {
     SparkSession spark;
    private static final String AGE_MIDPOINT = "null Yrs of Exp";

    public static void main(String args[]) throws Exception {

        DAO d1 = new DAO();
        JobsDataService s = new JobsDataService();
        // CSV File Path
        String filePath = "data/Wuzzuf_Jobs.csv";
//        Dataset<Row> d = d1.LoadData(filePath);
//         Requirement 1
//         Create a spark session and read the data set from the csv file and convert it into a dataframe

        Dataset<Row> d = d1.LoadData(filePath);



        // Display some of the data
        System.out.println("=== Display Some of the data ===");
        d.show(20);

        // Requirement 2
        // Display the data's Display the data Structure & Summary
        System.out.println("======== Display the data's Structure & Summary ========");
        System.out.println("=== Data's structure ===");
        s.getDataStructure(d).printTreeString();
        System.out.println("=== Data's summary ===");
        s.getDataSummary(d).show();

        // Requirement 3
        // Clean the data
        Dataset<Row> cleanData = s.getCleanData(d);
        System.out.println("=== Display Some of the cleaned data ===");
        cleanData.show(20);

        // problem
        // Dataset<Response> cleanDataSet = cleanData.as(Encoders.bean(Response.class));

        // Requirement 4
        // Count the Jobs for each company & display them in (Most demanding companies for jobs)
        System.out.println("=== Display most demanding companies for jobs ===");
        Dataset<Row> d4 = s.getCountJobEachCompany(cleanData);
        d4.show(20);
        System.out.println("The most demanding company for jobs is ");
        d4.show(1);

        // Requirement 5
        // Show Step 4 in pie chart
        DrawCharts.drawPieChart(d4, 800, 1000,"Companies Job");

        // Requirement 6
        // What are the most popular job titles
        System.out.println("=== Display most popular job titles ===");
        Dataset<Row> d6 = s.getMostPopularJob(cleanData);
        d6.show();

        // Requirement 7
        // Show Step 6 in bar chart
        DrawCharts.drawBarChart(d6, "Most Popular Jobs","Job Title","Popularity",800, 1500);

        // Requirement 8
        // Display the most popular locations
        System.out.println("=== Display most popular locations ===");
        Dataset<Row> d8 = s.getMostPopularArea(cleanData);
        d8.show(10);

        // Requirement 9
        // Show Step 8 in bar chart
        DrawCharts.drawBarChart(d8, "Most Popular Areas","Area","Popularity",800, 1500);

        // Requirement 10
        System.out.println("=== Display most important skills ===");
        s.getCountSkills(d);
        LinkedHashMap<String, Integer> map=s.getCountSkills(d);
        for (Map.Entry<String, Integer> entry : map.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());



    }
}