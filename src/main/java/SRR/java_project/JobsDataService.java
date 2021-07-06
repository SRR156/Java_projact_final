package SRR.java_project;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import scala.collection.Iterator;

import java.util.*;

import static org.apache.spark.sql.functions.col;

public class JobsDataService {

    public StructType getDataStructure(Dataset<Row> dataset)  {
        return dataset.schema();
    }

    public Dataset<Row> getDataSummary(Dataset<Row> dataset)  {
        return dataset.limit(10);
    }

    public Dataset<Row> getCleanData(Dataset<Row> dataset) {
        Dataset<Row> datacleand= dataset;
        datacleand= datacleand.filter(row -> !row.get(5).equals("null Yrs of Exp"));
        datacleand=datacleand.dropDuplicates().na().drop();
        return datacleand;
    }

    public Dataset<Row> getCountJobEachCompany(Dataset<Row> dataset) {
        Dataset<Row> coutjobs=dataset.groupBy("Company").count().orderBy(col("count").desc());
        return coutjobs;
    }

    public Dataset<Row> getMostPopularJob(Dataset<Row> dataset) {
        Dataset<Row> PopularJob=dataset.groupBy("Title").count().orderBy(col("count").desc());
        return PopularJob;
    }

    public Dataset<Row> getMostPopularArea(Dataset<Row> dataset) {
        Dataset<Row> PopularArea=dataset.groupBy("Location").count().orderBy(col("count").desc());
        return PopularArea;
    }

    public Hashtable<String, Integer> getCountSkills(Dataset<Row> dataset) {
        Hashtable<String, Integer> Skillsmap = new Hashtable<>();

        List<Row> dataSkills = dataset.select("Skills").collectAsList();

        List<String> allskills = new ArrayList<>();
        for (int i = 0; i < dataSkills.size(); i++) {
            String[] skillsarray = dataSkills.get(i).getString(0).split(",");
            allskills.addAll(Arrays.asList(skillsarray));
        }

        List<String> skillselect = new ArrayList<>();
        for (int i = 0; i < allskills.size(); i++) {
            if (!(skillselect.contains(allskills.get(i)))) {
                skillselect.add(allskills.get(i));
            }
        }

        int count = 0;
        int ind=0;
        for (int j= 0; j < allskills.size(); j++) {
            for (int k = 0; k < allskills.size(); k++) {
                if (skillselect.get(ind).equals(allskills.get(k))) {
                    count += 1;
                }
            }
            Skillsmap.put(skillselect.get(ind), count);
            count= 0;
            ind++;
            if (ind == skillselect.size())
                ind = 0;
        }
////        Dataset<Row> dataset2=daSkillsmap;
        return Skillsmap;
    }
}
