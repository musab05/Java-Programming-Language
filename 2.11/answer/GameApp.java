abstract class Hero {
    String name;
    int level;

    // 3. Abstract class constructor
    public Hero(String name, int level) {
        this.name = name;
        this.level = level;
    }

    abstract void useAbility();
}

class Mage extends Hero {
    // 5. Child constructor passing data up
    public Mage(String name, int level) {
        super(name, level); 
    }

    @Override
    void useAbility() {
        System.out.println(name + " (Level " + level + ") casts Arcane Blast!");
    }
}

public class GameApp {
    public static void main(String[] args) {
        Mage myMage = new Mage("Musab", 50);
        myMage.useAbility();
    }
}