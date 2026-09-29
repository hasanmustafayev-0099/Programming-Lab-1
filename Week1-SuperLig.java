
package mackolik;

import java.util.Scanner;

public class Mackolik {

    
    public static void main(String[] args) { Scanner scanner = new Scanner(System.in);

        String[] teamNames = {"Team A", "Team B", "Team C", "Team D"};
        
        int[] matchesPlayed = new int[4];
        int[] wins = new int[4];
        int[] draws = new int[4];
        int[] losses = new int[4];
        int[] points = new int[4];
        int[] goalsFor = new int[4];
        int[] goalsAgainst = new int[4];
        int[] goalDifference = new int[4];

        int[][] fixtures = {
            {0, 1}, // Match 1: Team A vs Team B
            {0, 2}, // Match 2: Team A vs Team C
            {0, 3}, // Match 3: Team A vs Team D
            {1, 2}, // Match 4: Team B vs Team C
            {1, 3}, // Match 5: Team B vs Team D
            {2, 3}  // Match 6: Team C vs Team D
        };

        String[] matchResults = new String[6];

        System.out.println("--- TOURNAMENT FIXTURE ---");
        for (int i = 0; i < fixtures.length; i++) {
            System.out.println("Match " + (i + 1) + ": " + teamNames[fixtures[i][0]] + " vs " + teamNames[fixtures[i][1]]);
        }
        System.out.println("--------------------------\n");

        System.out.println("--- ENTER MATCH SCORES ---");
        for (int i = 0; i < 6; i++) {
            int homeIndex = fixtures[i][0];
            int awayIndex = fixtures[i][1];

            System.out.printf("Match %d: %s vs %s\n", (i + 1), teamNames[homeIndex], teamNames[awayIndex]);
            
            System.out.print(teamNames[homeIndex] + " goals: ");
            int homeGoals = scanner.nextInt();
            
            System.out.print(teamNames[awayIndex] + " goals: ");
            int awayGoals = scanner.nextInt();
            System.out.println();

            matchesPlayed[homeIndex]++;
            matchesPlayed[awayIndex]++;

            goalsFor[homeIndex] += homeGoals;
            goalsAgainst[homeIndex] += awayGoals;
            
            goalsFor[awayIndex] += awayGoals;
            goalsAgainst[awayIndex] += homeGoals;

            if (homeGoals > awayGoals) {
                wins[homeIndex]++;
                points[homeIndex] += 3;
                losses[awayIndex]++;
            } else if (homeGoals == awayGoals) {
                draws[homeIndex]++;
                points[homeIndex] += 1;
                draws[awayIndex]++;
                points[awayIndex] += 1;
            } else {
                losses[homeIndex]++;
                wins[awayIndex]++;
                points[awayIndex] += 3;
            }

            matchResults[i] = String.format("Match %d: %s %d - %d %s", (i + 1), teamNames[homeIndex], homeGoals, awayGoals, teamNames[awayIndex]);
        }

        for (int i = 0; i < 4; i++) {
            goalDifference[i] = goalsFor[i] - goalsAgainst[i];
        }

        System.out.println("--- ALL MATCH SCORES ---");
        for (String result : matchResults) {
            System.out.println(result);
        }
        System.out.println();

        System.out.println("--- STANDINGS TABLE ---");
        System.out.printf("%-10s | %-2s | %-2s | %-2s | %-2s | %-3s | %-3s | %-3s | %-4s\n", 
                "Team", "MP", "W", "D", "L", "GF", "GA", "GD", "Pts");
        System.out.println("---------------------------------------------------------");
        
        for (int i = 0; i < 4; i++) {
            System.out.printf("%-10s | %-2d | %-2d | %-2d | %-2d | %-3d | %-3d | %-3d | %-4d\n",
                    teamNames[i], matchesPlayed[i], wins[i], draws[i], losses[i], 
                    goalsFor[i], goalsAgainst[i], goalDifference[i], points[i]);
        }
        System.out.println();

        int championIndex = 0;
        
        for (int i = 1; i < 4; i++) {
            if (points[i] > points[championIndex]) {
                championIndex = i;
            } else if (points[i] == points[championIndex]) {
                if (goalDifference[i] > goalDifference[championIndex]) {
                    championIndex = i;
                }
            }
        }

        System.out.println("**************");
        System.out.println("Tournament Champion: " + teamNames[championIndex]);
        System.out.println("**************");
        
        scanner.close();
    }
        
    }
 

