package springBoot.ems.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sId;

    private String studentId;      // username
    private String studentPassword;
    private String studentName;
    private String studentBranch;
    private int studentSem;

    // Constructors
    public Student() {}

    public Student(String studentId, String studentPassword, String studentName, String studentBranch, int studentSem) {
        this.studentId = studentId;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentBranch = studentBranch;
        this.studentSem = studentSem;
    }

    // Getters and setters
    public Integer getsId() { return sId; }
    public void setsId(Integer sId) { this.sId = sId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentPassword() { return studentPassword; }
    public void setStudentPassword(String studentPassword) { this.studentPassword = studentPassword; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentBranch() { return studentBranch; }
    public void setStudentBranch(String studentBranch) { this.studentBranch = studentBranch; }

    public int getStudentSem() { return studentSem; }
    public void setStudentSem(int studentSem) { this.studentSem = studentSem; }
}
