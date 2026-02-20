class Employee {
    String name;
    int id;

    // Parameterized constructor
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

class Manager extends Employee {
    int teamSize;

    public Manager(String name, int id, int teamSize) {
        // 1. Pass data to parent constructor (MUST be first line)
        super(name, id); 
        
        // 2. Initialize child-specific field
        this.teamSize = teamSize;
    }

    public void displayInfo() {
        System.out.println("Manager: " + name + " | ID: " + id + " | Team Size: " + teamSize);
    }
}

public class HRSystem {
    public static void main(String[] args) {
        Manager mgr = new Manager("Musab", 7001, 12);
        mgr.displayInfo();
    }
}