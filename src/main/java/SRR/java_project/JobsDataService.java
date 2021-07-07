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

    public LinkedHashMap<String, Integer> getCountSkills(Dataset<Row> dataset) {
        LinkedHashMap<String, Integer> Skillsmap = new LinkedHashMap<>();

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
//        int ind=0;
        for (int j= 0; j < skillselect.size(); j++) {
            for (int k = 0; k < allskills.size(); k++) {
                if (skillselect.get(j).equals(allskills.get(k))) {
                    count += 1;
                }
            }
            Skillsmap.put(skillselect.get(j), count);
            count= 0;
//            ind++;
//            if (ind == skillselect.size())
//                ind = 0;
        }
        Skillsmap=sortMap( Skillsmap);
////        Dataset<Row> dataset2=daSkillsmap;
        return Skillsmap;
    }
    public static LinkedHashMap<String, Integer> sortMap(Map<String, Integer> map)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(map.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        LinkedHashMap<String, Integer> Sorted = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> ma : list)
        {
            Sorted.put(ma.getKey(), ma.getValue());
        }
        return Sorted;
    }
}
