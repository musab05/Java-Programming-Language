// 1. Base return types
class Loot { }
class Weapon extends Loot { }

// 2. Parent Class
class Chest {
    public Loot open() {
        System.out.println("Opening generic chest...");
        return new Loot();
    }
}

// 3. Child Class
class WeaponChest extends Chest {
    
    // 4. Overriding with a Covariant Return Type
    // Changing 'Loot' to 'Weapon' is legal because Weapon extends Loot.
    @Override
    public Weapon open() {
        System.out.println("Opening weapon chest! Found a legendary sword.");
        return new Weapon();
    }
}

public class LootSystem {
    public static void main(String[] args) {
        // 5. Test the covariant return
        WeaponChest bossChest = new WeaponChest();
        
        // We know for a fact this returns a Weapon, not just generic Loot
        Weapon myNewSword = bossChest.open(); 
    }
}