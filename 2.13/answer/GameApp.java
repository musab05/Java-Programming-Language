// 1. Defining capabilities
interface SpellCaster { void castSpell(); }
interface Trader      { void trade(); }

// 2. Implementing both
class Mage implements SpellCaster, Trader {
    
    @Override
    public void castSpell() {
        System.out.println("Mage casts a bright blue Fireball!");
    }

    @Override
    public void trade() {
        System.out.println("Mage trades 5 gold coins for a mana potion.");
    }
}

public class GameApp {
    public static void main(String[] args) {
        Mage myMage = new Mage();
        myMage.castSpell();
        myMage.trade();
    }
}