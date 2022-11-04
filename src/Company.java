import java.util.ArrayList;
import java.text.DecimalFormat;

public class Company {
    private String id;
    private String name;
    private int admission_quota;
    private int application_quota;
    private int min_accepted_year;
    private double min_accepted_gpa;
    private ArrayList<Student> applicants = new ArrayList<>();
    private ArrayList<Student> admittedStudents = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Company(String id, String name, int admission_quota, int application_quota, int min_accepted_year, double min_accepted_gpa) {
        this.id = id;
        this.name = name;
        this.admission_quota = admission_quota;
        this.application_quota = application_quota;
        this.min_accepted_year = min_accepted_year;
        this.min_accepted_gpa = min_accepted_gpa;
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

    public int getAdmission_quota() {
        return admission_quota;
    }

    public void setAdmission_quota(int admission_quota) {
        this.admission_quota = admission_quota;
    }

    public int getApplication_quota() {
        return application_quota;
    }

    public void setApplication_quota(int application_quota) {
        this.application_quota = application_quota;
    }

    public int getMin_accepted_year() {
        return min_accepted_year;
    }

    public void setMin_accepted_year(int min_accepted_year) {
        this.min_accepted_year = min_accepted_year;
    }

    public double getMin_accepted_gpa() {
        return min_accepted_gpa;
    }

    public void setMin_accepted_gpa(double min_accepted_gpa) {
        this.min_accepted_gpa = min_accepted_gpa;
    }

    public ArrayList<Student> getApplicants() {
        return applicants;
    }

    public ArrayList<Student> getAdmitted_students() {
        return admittedStudents;
    }

    @Override
    public String toString() {
        return  getName() + " (" + getId() + "):\n" +
                "Min. Accepted GPA: " + df.format(getMin_accepted_gpa()) + "\n" +
                "Min. Accepted Year: " + getMin_accepted_year() + "\n" +
                "Applications: " + getApplicants().size() + "/" + getApplication_quota() + "\n" +
                "Admissions: " + getAdmitted_students().size() + "/" + getAdmission_quota() ;
    }


}
