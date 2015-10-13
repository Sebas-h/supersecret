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
        // (1) If we currently have a fleet in flight, just do nothing.
        if (pw.MyFleets().size() >= 4) {
            return;
        }
        // (2) Find my strongest planet.
        Planet source = null;
        double sourceScore = Double.MIN_VALUE;
        for (Planet p : pw.MyPlanets()) {
            double score = (double) p.NumShips();
            if (score > sourceScore) {
                sourceScore = score;
                source = p;
            }
        }
        // (3) Find the weakest enemy or neutral planet.
        Planet dest = null;
        double destScore = Double.MIN_VALUE;
        for (Planet p : pw.NotMyPlanets()) {
            double score = 1.0 / (1 + p.NumShips());
            if (score > destScore) {
                destScore = score;
                dest = p;
            }
        }
        // (4) Send half the ships from my strongest planet to the weakest
        // planet that I do not own.
        if (source != null && dest != null) {
            int numShips = source.NumShips() / 2;
            pw.IssueOrder(source, dest, numShips);
        }
    }

    /* PSEUDO CODE
    Breadth first zoeken naar nodes
    elke node krijgt een waarde toegekent op basis van de eigen growth rate ten opzichte van die van de tegenstander (misschien het aantal schepen ook meenemen)
    zoeken tot een bepaalde diepte bereikt is
    de node met de beste waarde wordt gekozen.

    functie:
    params: startnode
    nodeList = [startNode]


    while nodeList is not empty{

    }

     */


    public class Node{
        public PlanetWars gamestate;


        public Node(PlanetWars planetWars){
            gamestate = planetWars;
        }


        public ArrayList<Node> collapse() {
            ArrayList<Node> output = new ArrayList<Node>();
            PlanetWars newstate = new PlanetWars(this.gamestate.gamestateString);

            // For all neutral or hostile planets
            for (Planet planet : this.gamestate.NotMyPlanets()) {

                newstate = new PlanetWars(gamestate.gamestateString);
            }
            output.add(new Node(newstate));
            return output;
        }

    }

    public void minimax(Node startNode){
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(startNode);
        while (nodes.size() > 0)
        {
            // Collapse node
            // dismiss negative nodes
            // simulate opponents reaction
            // add viable states to the nodes array
        }



    }



    public static void main(String[] args){
        String line = "";
        String message = "";
        int c;
        try {
            while ((c = System.in.read()) >= 0) {
                switch (c) {
                    case '\n':
                        if (line.equals("go")) {
                            PlanetWars pw = new PlanetWars(message);
                            DoTurn(pw);
                            pw.FinishTurn();
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
            // Owned.
        }
    }
}

