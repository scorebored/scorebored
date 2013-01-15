/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package pongscorebored;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ScoreBored {

    public static boolean player_one_serving;
    public static int player_one_score;
    public static int player_two_score;
    public static String playerInput;
    public static String[] playerArray;
    public static String who_is_serving;
    public static String player_one;
    public static String player_two;
    public static String scoreInput;

    public static void main(String[] args) {
       /*try{
            Runtime r = Runtime.getRuntime();
            r.exec("say Test Test Test");
        }
       catch(Exception e) {
           System.out.println("TESTing ERROR");
       }

        */
        player_one_serving = false;
        player_one_score = 0;
        player_two_score = 0;
        playerInput = "";
        who_is_serving = "";
        player_one = "";
        player_two = "";
        scoreInput = "";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            Runtime r = Runtime.getRuntime();
            Process p;
            System.out.print("Enter the Players(separate by comma): ");
            playerInput = input.readLine();
            System.out.print("Enter who serves first: ");
            who_is_serving = input.readLine();

            //
            System.out.println("playerInput:" + playerInput + " " + who_is_serving);
            //
            playerArray = playerInput.split(",");
            player_one = playerArray[0];
            player_two = playerArray[1];
            //
            System.out.println("players:" + player_one + " " + player_two);
            //
            p = r.exec("say Todays matchup, " + player_one + " versus " + player_two);
            p.waitFor();
            p = r.exec("say " + who_is_serving + " serves first");
            p.waitFor();
            if(who_is_serving.equals(player_one)) {
                player_one_serving = true;
            }

            while(true){
                scoreInput = "";
                System.out.print("Enter 1 or 2, for 1st or 2nd player: ");
                scoreInput = input.readLine();

                //announce point
                if(scoreInput.equals("1")) {
                    p = r.exec("say Point " + player_one);
                    p.waitFor();
                    player_one_score += 1;
                }
                else if(scoreInput.equals("-1")) {
                    player_one_score -= 1;
                    sayScore(player_one_serving, player_one_score, player_two_score);
                    continue;
                }
                else if(scoreInput.equals("2")) {
                    p = r.exec("say Point " + player_two);
                    p.waitFor();
                    player_two_score += 1;
                }
                else if(scoreInput.equals("-2")) {
                    player_two_score -= 1;
                    sayScore(player_one_serving, player_one_score, player_two_score);
                    continue;
                }
                else if(scoreInput.equals("3")) {
                    sayScore(player_one_serving, player_one_score, player_two_score);
                    continue;
                }
                else if(scoreInput.equals("4")) {
                    p = r.exec("say " + who_is_serving + " is serving");
                    p.waitFor();
                    continue;
                }
                else if(scoreInput.equals("5")) {
                    randomJacobExcuse();
                    continue;
                }
                else {
                     System.out.println("not a correct value");
                }
                //check for match point

                if(player_one_score >= 21 && player_one_score - player_two_score >= 2) {
                    p = r.exec("say Congratulations " + player_one +", You have Defeated " + player_two + "," +
                            " Would You like to play another game? ");
                    p.waitFor();
                    //clear score and start loop and again and add new game
                    scoreInput = input.readLine();

                    if(scoreInput.equals("y") || scoreInput.equals("yes")) {
                        player_one_score = 0;
                        player_two_score = 0;
                        who_is_serving = player_two;
                        continue;
                    }
                    else {
                        break;
                    }
                }
                if(player_two_score >= 21 && player_two_score - player_one_score >= 2) {
                    p = r.exec("say Congratulations " + player_two + ", You have Defeated " + player_one);
                    p.waitFor();
                    //clear score and start loop and agian and add new game
                    break;
                }
                //check to change servers
                if((player_one_score + player_two_score)%5 == 0) {
                    p = r.exec("say Change Servers!");
                    p.waitFor();
                    player_one_serving = !player_one_serving;
                    if(player_one_serving) {
                        p = r.exec("say " + player_one + " is serving");
                        p.waitFor();
                        who_is_serving = player_one;
                    }
                    else {
                        p = r.exec("say " + player_two + " is serving");
                        p.waitFor();
                        who_is_serving = player_two;
                    }
                }
                //announce the score base on who is serving
                sayScore(player_one_serving, player_one_score, player_two_score);

                //check for match point
                if(player_one_score >= 20 && player_one_score - player_two_score >= 1) {
                    p = r.exec("say Game Point " + player_one);
                    p.waitFor();
                }
                if(player_two_score >= 20 && player_two_score - player_one_score >= 1) {
                    p = r.exec("say Game Point " + player_two);
                    p.waitFor();
                }
            }
        }catch(Exception e) {
            System.out.println("Error in main()");
        }
    }

    public static void sayScore(boolean player_one_serving, int player_one_score, int player_two_score) {
        try {
            Runtime r = Runtime.getRuntime();
            Process p;
            if(player_one_score == player_two_score) {
                if(player_one_score == 10) {
                    p = r.exec("say The Score is Ken, all");
                    p.waitFor();
                }
                else {
                    p = r.exec("say The Score is " + String.valueOf(player_one_score) + ", all");
                    p.waitFor();
                }
            }
            else if(player_one_serving) {
                if(player_one_score == 10 || player_two_score == 10) {
                    if(player_one_score == 10) {
                        p = r.exec("say The Score is Ken to " + String.valueOf(player_two_score));
                        p.waitFor();
                    }
                    else {
                        p = r.exec("say The Score is " + String.valueOf(player_one_score) + " to Ken");
                        p.waitFor();
                    }
                }
                else {
                    p = r.exec("say The Score is " + player_one_score + " to " + player_two_score);
                    p.waitFor();
                }
            }
            else {
                if(player_one_score == 10 || player_two_score == 10) {
                    if(player_one_score == 10) {
                        p = r.exec("say The Score is " + String.valueOf(player_two_score) + " to Ken");
                        p.waitFor();
                    }
                    else {
                        p = r.exec("say The Score is Ken to " + String.valueOf(player_one_score));
                        p.waitFor();
                    }
                }
                else {
                    p = r.exec("say The Score is " + String.valueOf(player_two_score) +
                            " to " + String.valueOf(player_one_score));
                    p.waitFor();
                }
            }
         }catch(Exception e) {
            System.out.println("Error in sayScore()");
        }
    }
    public static void randomJacobExcuse() {
        try {
            Runtime r = Runtime.getRuntime();
            Process p;
            String[] jacobList = new String[] {"Your shirt is white, cant see the ball when its in front of your shirt.",
                          "My back hurts.",
                          "I let him win",
                          "The lighting was bad",
                          "I was practicing my swing, and was not playing serious.",
                          "It is Tuesday.",
                          "I was blowing my thing.",
                          "These balls are too big.",
                          "I thought he was Jeff.",
                          "My wrist hurts."};
            int randomNumber = (int)(Math.random() * (jacobList.length));
            p = r.exec("say " + jacobList[randomNumber]);
            p.waitFor();
         }catch(Exception e) {
            System.out.println("Error in randomJacobExcuse()");
        }
    }

    public static String[] getScore() {
        String[] scoreList = new String[] {
                      String.valueOf(player_one_score),
                      String.valueOf(player_two_score)};
        return scoreList;
    }

    public static void setScore(String playerOneScore, String playerTwoScore) {

    }

    public static String[] getPlayers() {
         String[] playerList = new String[] {
                      player_one,
                      player_two};
        return playerList;
    }
    public static void setPlayers(String playerOne, String playerTwo) {

    }

    public static String getServer() {
        return who_is_serving;
    }

    public static void setServer(String whoIsServing) {

    }
}

