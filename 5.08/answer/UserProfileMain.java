import java.util.Optional;

public class UserProfileMain {

    static class UserProfile {
        private String nickname;
        private String bio;
        private String avatarUrl;

        UserProfile(String nickname, String bio, String avatarUrl) {
            this.nickname = nickname;
            this.bio = bio;
            this.avatarUrl = avatarUrl;
        }

        // No if/else — pure Optional transformations
        public String getDisplayName() {
            return Optional.ofNullable(nickname)
                .or(() -> Optional.ofNullable(bio)
                    .map(b -> b.length() > 10 ? b.substring(0, 10) + "..." : b))
                .orElse("Anonymous");
        }

        public String getAvatarOrDefault() {
            return Optional.ofNullable(avatarUrl)
                .filter(url -> url.startsWith("http"))
                .orElse("https://default-avatar.com/placeholder.png");
        }
    }

    public static void main(String[] args) {
        UserProfile full = new UserProfile("CoolDev", "I love Java!", "https://img.com/me.jpg");
        UserProfile partial = new UserProfile(null, "A passionate developer who loves coding", null);
        UserProfile empty = new UserProfile(null, null, "ftp://invalid-url.com/pic.jpg");

        System.out.println("=== Full Profile ===");
        System.out.println("Display: " + full.getDisplayName());     // CoolDev
        System.out.println("Avatar: " + full.getAvatarOrDefault());  // https://img.com/me.jpg

        System.out.println("\n=== Partial Profile ===");
        System.out.println("Display: " + partial.getDisplayName());    // A passiona...
        System.out.println("Avatar: " + partial.getAvatarOrDefault()); // default placeholder

        System.out.println("\n=== Empty Profile ===");
        System.out.println("Display: " + empty.getDisplayName());     // Anonymous
        System.out.println("Avatar: " + empty.getAvatarOrDefault());  // default (ftp is not http)
    }
}
