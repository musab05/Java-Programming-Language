// 1. Create a common interface (or abstract class)
interface Animal {
    void speak();
}

// 2. Have each specific class implement the interface
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Woof");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Meow");
    }
}

class Cow implements Animal {
    @Override
    public void speak() {
        System.out.println("Moo");
    }
}

// 3. The Refactored Farm Class
class Farm {
    // Notice we accept the interface type 'Animal', not 'Object'
    public void makeNoise(Animal animal) {
        // Polymorphism handles the rest! No instanceof needed.
        animal.speak();
    }
}

public class CodeSmell {
    public static void main(String[] args) {
        Farm myFarm = new Farm();
        
        Animal myDog = new Dog();
        Animal myCat = new Cat();
        Animal myCow = new Cow();
        
        myFarm.makeNoise(myDog); // Output: Woof
        myFarm.makeNoise(myCat); // Output: Meow
        myFarm.makeNoise(myCow); // Output: Moo
    }
}