package SRR.java_project;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.*;

public class DAO {

    public Dataset<Row> LoadData(String filepathCSV) throws Exception {

        //load data from CSV file and put it in dataset
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkSession session = SparkSession.builder().appName("JobsWussuf").master("local[*]").getOrCreate();

        DataFrameReader dataFrameReader = session.read();

        Dataset<Row> dataCSV = dataFrameReader.option("header", "true").csv(filepathCSV);

        //Dataset<JobDetails> Dataset = dataCSV.as(Encoders.bean(JobDetails.class));
        return dataCSV;
    }
}
