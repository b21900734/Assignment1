import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.io.File;
import java.util.Collections;

public class Main {
    static String companiesFilepath;
    static String studentsFilepath;
    static String commandsFilepath;
    static String outputFilepath;
    static ArrayList<Student> allStudentsArray = new ArrayList<>();
    static ArrayList<Company> allCompaniesArray = new ArrayList<>();


    public static void applyStudent(String studentID, String companyID){
        for (Student s:allStudentsArray){
            if (s.getId().equals(studentID)){
                for (Company c:allCompaniesArray){
                    if (c.getId().equals(companyID)) {
                        if (c.getApplicants().size() <= c.getApplication_quota()
                                && s.getYear() >= c.getMin_accepted_year() && s.getGpa() >= c.getMin_accepted_gpa()
                                && s.getApplications().size() < 5){
                            s.getApplications().add(c);
                            c.getApplicants().add(s);
                            System.out.println("Application from " + s.getName() + " " + s.getSurname() + " (" + s.getId() + ")" +
                                    " to " + c.getName() + " (" +
                                    c.getId() +") was registered successfully." );
                        } else {
                            System.out.println("Application from " + s.getName() + " " + s.getSurname() + " to " + c.getName() + " (" +
                                    c.getId() +") was failed.");
                        }
                    }


                }
            }
        }
    }

    public static void admitStudent(String studentID, String companyID){
        for (Student s:allStudentsArray){
            if (s.getId().equals(studentID)){
                for (Company c:allCompaniesArray){
                    if (c.getId().equals(companyID)) {
                        if (c.getApplicants().contains(s) && c.getAdmitted_students().size() <= c.getAdmission_quota()
                                && s.getAdmitted().equals("None")) {
                            c.getAdmitted_students().add(s);
                            s.setAdmitted(c.getName() + " (" + c.getId() + ")");
                            System.out.println(c.getName() + " (" + c.getId() + ") admitted " +
                                    s.getName() + " " + s.getSurname() + " (" + s.getId() + ").");
                        } else {
                            System.out.println(c.getName() + " (" + c.getId() + ") COULD NOT admit " +
                                    s.getName() + " " + s.getSurname() + " (" + s.getId() + ").");
                        }
                    }
                }
            }
        }
    }

    public static String getCompanyInfo(String id){
        for (Company c:allCompaniesArray){
            if (c.getId().equals(id)){
                return c.toString();
            }
        }
        return "nothing";
    }

    public static String getStudentInfo(String id){
        for (Student s:allStudentsArray){
            if (s.getId().equals(id)){
                return s.toString();
            }
        }
        return "nothing";
    }

    public static void listCompanyApplications(String id){
        for (Company c:allCompaniesArray){
            if (c.getId().equals(id)) {
                Collections.sort(c.getApplicants(), new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        if (s2.getGpa() > s1.getGpa()){
                            return 1;
                        } else if(s2.getGpa() < s1.getGpa()){
                            return -1;
                        } else {
                            if(s2.getYear() > s1.getYear()){
                                return 1;
                            } else if (s2.getYear() < s1.getYear()){
                                return -1;
                            } else  {
                                return 0;
                            }
                        }
                    }
                });

                System.out.println("### START OF COMPANY APPLICATIONS ###\n" +
                        "Admitted Students:");
                for (Student s : c.getAdmitted_students()) {
                    System.out.println(s.toString());
                }
                System.out.println("\nApplied Students:");
                //Collections.sort(c.getApplicants(), );
                for (Student s : c.getApplicants()) {
                    System.out.println(s.toString());
                }
                System.out.println("### END OF COMPANY APPLICATIONS ###");
            }
        }
    }

    public static void listStudentsApplications(String id){
        for (Student s:allStudentsArray){
            if (s.getId().equals(id)){
                System.out.println("### START OF STUDENT APPLICATIONS ###\n" +
                        "Admitted Company:\n" +
                        s.getAdmitted() + "\n\n" +
                        "Applied Companies:");
                if (s.getApplications().isEmpty()){
                    System.out.println("None");
                } else {
                    for (Company c : s.getApplications()) {
                        System.out.println(c.getName() + " (" + c.getId() + ")");
                    }
                }
                System.out.println("### END OF STUDENT APPLICATIONS ###");
            }
        }
    }

    public static void main(String[] args) {
        companiesFilepath = args[0];
        studentsFilepath = args[1];
        commandsFilepath = args[2];
        outputFilepath = args[3];
        File companies = new File(companiesFilepath);
        File students = new File(studentsFilepath);
        File commands = new File(commandsFilepath);

        try {
            PrintStream printTo = new PrintStream(new File(outputFilepath));
            PrintStream console = System.out;
            System.setOut(printTo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner studentsScanner = new Scanner(students)) {
            studentsScanner.nextLine();
            while (studentsScanner.hasNext()) {
                String line = studentsScanner.nextLine();
                String[] info =  line.split("\t");
                allStudentsArray.add(new Student(info[0], info[1], info[2], Integer.parseInt(info[3]), Double.parseDouble(info[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner companiesScanner = new Scanner(companies)){
            companiesScanner.nextLine();
            while (companiesScanner.hasNext()){
                String line = companiesScanner.nextLine();
                String[] info = line.split("\t");
                allCompaniesArray.add(new Company(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]),
                        Integer.parseInt(info[4]), Double.parseDouble(info[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner commandsScanner = new Scanner(commands)) {
            while (commandsScanner.hasNext()){
                String line = commandsScanner.nextLine();
                String[] info = line.split(" ");
                switch (info[0]) {
                    case "APPLY":
                        applyStudent(info[4], info[2]);

                        break;
                    case "ADMIT":
                        admitStudent(info[1], info[3]);

                        break;
                    case "INFO":
                        if (info[1].equals("COMPANY")) {
                            System.out.println("\n### START OF COMPANY INFO ###\n" + getCompanyInfo(info[2]) +
                                    "\n### END OF COMPANY INFO ###\n");

                        } else if (info[1].equals("STUDENT")) {
                            System.out.println("\n### START OF STUDENT INFO ###\n" +
                                    getStudentInfo(info[2]) +
                                    "\n### END OF STUDENT INFO ###\n");
                        }
                        break;
                    case "LIST":
                        if (info[2].equals("TO")) {
                            listCompanyApplications(info[3]);
                        } else if (info[2].equals("BY")) {
                            listStudentsApplications(info[3]);
                        }
                        break;
                }
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
