import java.util.ArrayList;

import java.text.DecimalFormat;

public class Student {
    private String id;
    private String name;
    private String surname;
    private int year;
    private double gpa;
    private String admitted = "None";
    private ArrayList<Company> applications = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Student(String id, String name, String surname, int year, double gpa) {
        setId(id);
        setName(name);
        setSurname(surname);
        setYear(year);
        setGpa(gpa);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public ArrayList<Company> getApplications() {
        return applications;
    }

    public String getAdmitted() {
        return admitted;
    }

    public void setAdmitted(String admitted) {
        this.admitted = admitted;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " (" + getId() + ") - Year: " + getYear() + ", GPA: " + df.format(getGpa());
    }
}
