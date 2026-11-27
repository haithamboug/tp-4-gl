
package library.model;

public class TeacherMember extends Member {

    private final String department;

    public TeacherMember(String memberNumber, String name,
                         String email, String department) {
        super(memberNumber, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public int maxLoans() {
        return 10;
    }
}
