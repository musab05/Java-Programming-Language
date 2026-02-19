// 1. Package is all lowercase, reverse domain format
package com.academiq.security;

// 2. Interface is PascalCase and typically an adjective (capability)
public interface Authenticatable {

    // 3. Constant is SCREAMING_SNAKE_CASE
    // Note: Variables in interfaces are implicitly 'public static final'
    int STANDARD_TIMEOUT_MS = 5000;

    // 4. Method signature is lowerCamelCase (verb action)
    boolean verifyUserToken(String token);
}