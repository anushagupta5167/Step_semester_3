import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignmentProblems5 {

    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] *= 2;
        playerScores[viceCaptainIndex] *= 1.5;
    }

    static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
            if (score > max) {
                max = score;
            }
        }
        return "Min: " + min + " | Max: " + max + " | Spread: " + (max - min);
    }

    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int value : row) {
            sum += value;
        }
        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            if (i > 0) {
                result.append(" | ");
            }
            result.append("Match ").append(i).append(": ")
                    .append(rowAverage(runsPerOver[i]) >= threshold ? "Power Surge" : "Normal");
        }
        return result.toString();
    }

    static class Player implements Comparable<Player> {
        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        @Override
        public int compareTo(Player other) {
            return Double.compare(other.battingAverage, battingAverage);
        }
    }

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    static String draftAndRank(Player[] players) {
        List<Player> draftable = new ArrayList<>();
        for (Player player : players) {
            if (isDraftable(player.matchesPlayed) || isDraftable(player.matchesPlayed, player.injured)) {
                draftable.add(player);
            }
        }
        Player[] ranked = draftable.toArray(new Player[0]);
        Arrays.sort(ranked);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ranked.length; i++) {
            if (i > 0) {
                result.append(" | ");
            }
            result.append(i + 1).append(". ").append(ranked[i].name);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("1. Fantasy Team Score Multiplier");
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores));

        System.out.println("\n2. Duplicate Player Pick Checker");
        System.out.println(findDuplicatePick(new String[]{"Kohli", "Bumrah", "Kohli", "Rohit"}));
        System.out.println(findDuplicatePick(new String[]{"Kohli", "Bumrah", "Rohit"}));

        System.out.println("\n3. Top Performer Tracker");
        System.out.println(findMinMaxSpread(new int[]{45, 82, 79, 90, 33, 90, 61}));

        System.out.println("\n4. Match Day Grid Analyzer");
        System.out.println(classifyMatches(new int[][]{{4, 6, 8}, {10, 12, 14}, {2, 3, 1}}, 8));

        System.out.println("\n5. Fantasy League Auto-Draft Ranking Engine");
        System.out.println(draftAndRank(new Player[]{
                new Player("Virat", 15, 48.0, false),
                new Player("Rahul", 7, 55.0, false),
                new Player("Sameer", 3, 60.0, false),
                new Player("Dev", 12, 20.0, true)
        }));
    }
}
