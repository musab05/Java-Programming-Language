package com.academiq.ui;

// If you try to import com.academiq.finance.TaxCalculator here, 
// the compiler will throw an error: 
// "com.academiq.finance.TaxCalculator is not public; cannot be accessed from outside package"

public class Dashboard {
    // 4. Explanation:
    // This fails because TaxCalculator has "Default" access. 
    // It is only visible to classes inside the 'com.academiq.finance' package.
    // The 'ui' package is considered "the world" to the 'finance' package.
}


/*
Note: Think of private as your personal thoughts, default as a family secret (the package), and public as a billboard (the world). Using the correct modifier ensures that your code is used exactly the way you intended it to be.
 */