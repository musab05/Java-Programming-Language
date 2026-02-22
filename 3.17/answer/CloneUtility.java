import java.io.*;

// 1. Both the parent object and the child object MUST be Serializable
class Address implements Serializable {
    String city;

    public Address(String city) {
        this.city = city;
    }
}

class Company implements Serializable {
    String name;
    Address headquarters;

    public Company(String name, Address headquarters) {
        this.name = name;
        this.headquarters = headquarters;
    }
}

public class CloneUtility {

    // 2. The utility method for Deep Copying
    public static Company deepCopy(Company original) {
        try {
            // 3. Serialize to a Byte Array (in RAM, not on disk)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(original);

            // 4. Deserialize directly from that same Byte Array
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            return (Company) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Deep copy failed!", e);
        }
    }

    public static void main(String[] args) {
        Company google = new Company("Google", new Address("Mountain View"));
        
        // 5. Create our perfect, disconnected clone
        Company alphabet = deepCopy(google);
        
        // Change the clone's data
        alphabet.name = "Alphabet Inc.";
        alphabet.headquarters.city = "London"; // Modifying the nested object!

        // Verification: The original remains completely untouched
        System.out.println("Original Company: " + google.name + ", HQ: " + google.headquarters.city);
        System.out.println("Cloned Company:   " + alphabet.name + ", HQ: " + alphabet.headquarters.city);
    }
}