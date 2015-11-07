import java.io.*;
import java.util.*;


public class MyBot {
    // The DoTurn function is where your code goes. The PlanetWars object
    // contains the state of the game, including information about all planets
    // and fleets that currently exist. Inside this function, you issue orders
    // using the pw.IssueOrder() function. For example, to send 10 ships from
    // planet 3 to planet 8, you would say pw.IssueOrder(3, 8, 10).
    //
    // There is already a basic strategy in place here. You can use it as a
    // starting point, or you can throw it out entirely and replace it with
    // your own. Check out the tutorials and articles on the contest website at
    // http://www.ai-contest.com/resources.
    public static void DoTurn(PlanetWars pw) {
        TimeTest tt = new TimeTest();
        Uniform_cost uc = new Uniform_cost(pw, tt);
        tt.start();
        Node result = uc.search();
        tt.stop();
        tt.writeResults();
        if(result.first_Turn != null){
            for(Attack attack : result.first_Turn.attacks){
                System.err.println(attack.source.PlanetID() +" "+  attack.destination.PlanetID() + " " + attack.amount);
                pw.IssueOrder(attack.source, attack.destination, attack.amount);
            }
        }

    }


    public static void main(String[] args) {

        File file = new File("err.txt");
        try {
            FileOutputStream p = new FileOutputStream(file);
            PrintStream ps = new PrintStream(p);
            System.setErr(ps);
            System.err.println("initializing");
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        int turn = 1;


        String line = "";
        String message = "";
        int c;

        try {
            while ((c = System.in.read()) >= 0) {
                switch (c) {
                    case '\n':
                        if (line.equals("go")) {
                            PlanetWars pw = new PlanetWars(message);

                            System.err.println("\nturn " + turn + " :");
                            //System.err.println(pw.gamestateString);
                            try {
                                DoTurn(pw);
                            } catch (Exception e) {
                                System.err.println(pw.gamestateString);
                                System.err.println("caught exception");
                                e.printStackTrace(System.err);
                            }
                            pw.FinishTurn();
                            turn++;
                            message = "";
                        } else {
                            message += line + "\n";
                        }
                        line = "";
                        break;
                    default:
                        line += (char) c;
                        break;
                }
            }
        } catch (Exception e) {

        }
    }
}

