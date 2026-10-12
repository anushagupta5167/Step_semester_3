import java.util.Arrays;

public class AssignmentProblems7 {

    static class GameCharacter {
        private final int maxHealth;
        private int health;

        GameCharacter(int maxHealth) {
            this.maxHealth = maxHealth;
            this.health = maxHealth;
        }

        void takeDamage(int amount) {
            health = Math.max(0, health - amount);
        }

        void heal(int amount) {
            health = Math.min(maxHealth, health + amount);
        }

        int getHealth() {
            return health;
        }
    }

    static class Playlist {
        private final String[] songs;
        private int count;

        Playlist(int maxSize) {
            songs = new String[maxSize];
        }

        void addSong(String title) {
            if (count < songs.length) {
                songs[count++] = title;
            }
        }

        String[] getSongs() {
            return Arrays.copyOf(songs, count);
        }

        int getSongCount() {
            return count;
        }
    }

    static class PasswordChecker {
        private final String password;

        PasswordChecker(String password) {
            this.password = password;
        }

        String getStrength() {
            if (password.length() < 6) {
                return "Weak";
            }
            if (password.length() < 10) {
                return "Medium";
            }
            return "Strong";
        }
    }

    static class TrafficLight {
        private final String id;
        private final String[] colors = {"RED", "GREEN", "YELLOW"};
        private int current = 0;

        TrafficLight(String id) {
            this.id = id;
        }

        String next() {
            current = (current + 1) % colors.length;
            return colors[current];
        }

        String getColor() {
            return colors[current];
        }
    }

    static class Cart {
        private final String cartId;
        private final int[] prices;
        private int count;

        Cart(String cartId, int maxItems) {
            this.cartId = cartId;
            prices = new int[maxItems];
        }

        void addItem(int price) {
            if (count < prices.length) {
                prices[count++] = price;
            }
        }

        int getTotal() {
            int total = 0;
            for (int i = 0; i < count; i++) {
                total += prices[i];
            }
            return total;
        }

        int getItemCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println("1. The Health Bar");
        GameCharacter character = new GameCharacter(100);
        character.takeDamage(30);
        System.out.println("health = " + character.getHealth());
        character.heal(50);
        System.out.println("health = " + character.getHealth());
        character.takeDamage(150);
        System.out.println("health = " + character.getHealth());

        System.out.println("\n2. The Playlist");
        Playlist playlist = new Playlist(10);
        playlist.addSong("Song A");
        playlist.addSong("Song B");
        String[] copy = playlist.getSongs();
        copy[0] = "Hacked";
        System.out.println(Arrays.toString(playlist.getSongs()));
        System.out.println("Song count: " + playlist.getSongCount());

        System.out.println("\n3. The Password Checker");
        System.out.println(new PasswordChecker("abcd").getStrength());
        System.out.println(new PasswordChecker("abcdefgh").getStrength());
        System.out.println(new PasswordChecker("abcdefghijkl").getStrength());

        System.out.println("\n4. The Traffic Light");
        TrafficLight light = new TrafficLight("TL-9");
        System.out.println(light.getColor());
        System.out.println(light.next());
        System.out.println(light.next());
        System.out.println(light.next());

        System.out.println("\n5. The Shopping Cart");
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("Total: " + cart.getTotal());
        System.out.println("Items: " + cart.getItemCount());
    }
}
