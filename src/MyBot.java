import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

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
        // Zoek beste aanval tegenstander
        PlanetWars enemy_attack = best_attack(pw, false);

        PlanetWars our_attack = best_attack(enemy_attack, true);



    }

    private static PlanetWars best_attack(PlanetWars planetWars, boolean me){
        List<Attack> possible_attacks = new ArrayList<Attack>();
        for(Planet own_planet : getPlanets(planetWars, me)){
            for(Planet planet : planetWars.Planets()){
                if(planet.Owner() != own_planet.Owner()
                        && viable_attack(own_planet, planet, planetWars)){
                    possible_attacks.add(new Attack(own_planet, planet, own_planet.NumShips()-1, planetWars.Distance(own_planet.PlanetID(), planet.PlanetID())));
                }
            }
        }



    }

    private static PlanetWars attack_to_planetwars(PlanetWars startState, Attack attack){
        PlanetWars endState = new PlanetWars(startState.gamestateString);

        Planet sourcePlanet = endState.GetPlanet(attack.source.PlanetID());
        Planet destinationPlanet = endState.GetPlanet(attack.destination.PlanetID());

        if (startState.NeutralPlanets().contains(attack.destination)){
            sourcePlanet.RemoveShips(attack.amount);
            destinationPlanet.NumShips(Math.abs(destinationPlanet.NumShips() - attack.amount));
            destinationPlanet.Owner(attack.source.Owner());
        }
        else{
            sourcePlanet.RemoveShips(attack.amount);
            destinationPlanet.NumShips(Math.abs(destinationPlanet.NumShips() + destinationPlanet.GrowthRate() * attack.turns - attack.amount));
            destinationPlanet.Owner(attack.source.Owner());
        }

        return endState;





    }
    private static boolean viable_attack(Planet sourcePlanet, Planet destinationPlanet, PlanetWars planetWars){
        List<Planet> neutralPlanets = planetWars.NeutralPlanets();
        if(neutralPlanets.contains(destinationPlanet) && destinationPlanet.NumShips() <sourcePlanet.NumShips()){
            return true;
        }
        else if(! neutralPlanets.contains(destinationPlanet)&&
                destinationPlanet.NumShips() + destinationPlanet.GrowthRate() * planetWars.Distance(sourcePlanet.PlanetID(),destinationPlanet.PlanetID()) < sourcePlanet.NumShips()) {
            return true;

        }
        else{
            return false;
        }
    }

    private static List<Fleet> getFleets(PlanetWars planetWars, boolean me){
        List<Fleet> fleets = planetWars.Fleets();
        List<Fleet> output = new ArrayList<>();

        int MyNumber = planetWars.MyPlanets().get(0).Owner();
        int enemynumber = planetWars.EnemyPlanets().get(0).Owner();

        int playernumber;
        if (me){
            playernumber = MyNumber;
        }
        else{
            playernumber = enemynumber;
        }

        for (Fleet fleet : fleets){
            if(fleet.Owner()==playernumber){
                output.add(fleet);
            }
        }
        return output;
    }

    private static List<Planet> getPlanets(PlanetWars planetWars, boolean me){

        if (me){
            return planetWars.MyPlanets();
        }
        else{
            return planetWars.EnemyPlanets();
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

