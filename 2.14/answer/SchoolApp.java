class Student {
    String name;
    double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    // 2. Override toString for debugging and logging
    @Override
    public String toString() {
        return "Student Name: " + name + ", GPA: " + gpa;
    }
}

public class SchoolApp {
    public static void main(String[] args) {
        Student s1 = new Student("Musab", 3.9);
        
        // 3. System.out.println implicitly calls s1.toString()
        System.out.println(s1); 
    }
}