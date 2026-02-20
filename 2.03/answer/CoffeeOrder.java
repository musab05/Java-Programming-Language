public class CoffeeOrder {

    // 2. Method returning the current object
    public CoffeeOrder addMilk() {
        System.out.println("Adding Milk...");
        return this;
    }

    // 3. Method returning the current object
    public CoffeeOrder addSugar() {
        System.out.println("Adding Sugar...");
        return this;
    }

    // 4. Final action method (void)
    public void brew() {
        System.out.println("Brewing coffee!");
    }

    public static void main(String[] args) {
        CoffeeOrder myOrder = new CoffeeOrder();
        
        // 5. Method Chaining
        // We can string these together because the first two methods return 'this'
        myOrder.addMilk().addSugar().brew();
    }
}