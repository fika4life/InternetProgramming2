package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fiona on 2/2/15.
 */
public class HttpUrlConnectionClient {


    public static void main(String[] args){
        URL url = null;
        try {
            System.out.println("Indicate IP address");
            Scanner in = new Scanner(System.in);
            String IP = in.next();



            int rounds = 100;
            int[] guesses = new int[rounds];

            int guess=50;
            String clientId=null;

            for(int i = 0 ; i<rounds; i++) {
                int guessNumber = 0;

                while (true) {
                    guessNumber++;
                    url = new URL("http", IP, 8080, "/?guess=" + guess);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestProperty("User-Agent", "Mozilla");

                    if (clientId != null) {
                        con.setRequestProperty("Cookie", "clientId=" + clientId);
                    }

                    con.connect();

                    String response = convertStreamToString(con.getInputStream());


                    String clientIdRaw = con.getHeaderField("Set-Cookie");
                    if (clientIdRaw != null) {
                        clientId = clientIdRaw.replace("clientId=", "");
                    }

                    if (response.contains("CORRECT!")) {
                        guesses[i] = guessNumber;
                        break;
                    } else {
                        Pattern pattern = Pattern.compile("<p>The number is between: ([0-9]+) and ([0-9]+)</p>");
                        Matcher matcher = pattern.matcher(response);
                        matcher.find();
                        int lowerInterval = Integer.parseInt(matcher.group(1));
                        int higherInterval = Integer.parseInt(matcher.group(2));
                        //System.out.println(lowerInterval + " " + higherInterval);
                        guess = (int) Math.floor((lowerInterval + higherInterval) / 2);

                    }


                }
            }
            int totalGuesses = 0;
            for (int theGuess:guesses){
                totalGuesses+=theGuess;

            }
            double averageGuesses = (double) totalGuesses/(double)rounds;
            System.out.println(averageGuesses);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


}

