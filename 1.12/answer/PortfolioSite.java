public class PortfolioSite {

    // 1 & 2. Create the non-static method with a void return type
    public void printWelcome(String visitorName) {
        // 3. Print the greeting (no return statement needed for void)
        System.out.println("Welcome to my portfolio, " + visitorName + "!");
    }

    public static void main(String[] args) {
        // 4. Create an instance (object) of the class using the 'new' keyword
        PortfolioSite mySession = new PortfolioSite();
        
        // 5. Use the object to call the non-static method and pass an argument
        mySession.printWelcome("Musab");
        // Output: Welcome to my portfolio, Musab!
    }
}
/* 
If you tried to call printWelcome("Musab"); directly inside the main method without using mySession. first, the Java compiler would throw an error saying: "non-static method printWelcome(String) cannot be referenced from a static context." This is one of the most common errors developers run into when learning classes and methods
*/