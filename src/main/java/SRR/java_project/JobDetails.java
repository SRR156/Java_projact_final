package SRR.java_project;

import java.io.Serializable;

//some probem happend with us because of it we didn't yous pojo class (JobeDetails)
//I hope we can contact with you to learn from you how to solve  this probem



public  class JobDetails implements Serializable {

    private String Title;
    private String Company;
    private String Location;
    private String Type;
    private String Level;
    private String YearsExp;
    private String Country;
    private String Skills;


    public JobDetails (String title, String company, String location, String type,
                       String level, String yearsExp, String country, String skills) {
        this.Title=title;
        this.Company=company;
        this.Location=location;
        this.Type=type;
        this.Level=level;
        this.YearsExp=yearsExp;
        this.Country=country;
        this.Skills=skills;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getYearsExp() {
        return YearsExp;
    }

    public void setYearsExp(String yearsExp) {
        YearsExp = yearsExp;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "Title='" + Title + '\'' +
                ", Company='" + Company + '\'' +
                ", Location='" + Location + '\'' +
                ", Type='" + Type + '\'' +
                ", Level='" + Level + '\'' +
                ", YearsExp='" + YearsExp + '\'' +
                ", Country='" + Country + '\'' +
                ", Skills='" + Skills + '\'' +
                '}';
    }
}
