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
        // Zoek beste aanval tegenstander
        State enemy_attack = best_attack(pw, false);

        // Zoek de beste aanval met de aanname dat de beste aanval van de tegenstander gelukt is.
        State our_attack = best_attack(enemy_attack.planetWars, true);



    }

    private static void attack_to_turn(Attack attack, PlanetWars planetWars){
        planetWars.IssueOrder(attack.source, attack.destination, attack.amount);
    }

    private static State best_attack(PlanetWars planetWars, boolean me){
        List<Attack> possible_attacks = new ArrayList<Attack>();
        for(Planet own_planet : getPlanets(planetWars, me)){
            for(Planet planet : planetWars.Planets()){
                if(planet.Owner() != own_planet.Owner()
                        && viable_attack(own_planet, planet, planetWars)){
                    possible_attacks.add(new Attack(own_planet, planet, own_planet.NumShips()-1, planetWars.Distance(own_planet.PlanetID(), planet.PlanetID())));
                }
            }
        }

        List<State> states = new ArrayList<State>();

        for(Attack attack : possible_attacks){
            states.add(new State(attack, planetWars));
        }

        float max_value = 0;
        State best_state = null;
        for(State state : states){
            float val = value_planetwars(state.planetWars, me);
            if (val > max_value){
                max_value = val;
                best_state = state;
            }
        }
        return best_state;



    }

    public static PlanetWars attack_to_planetWars(PlanetWars startState, Attack attack){
        PlanetWars endState = new PlanetWars(startState.gamestateString);

        Planet sourcePlanet = endState.GetPlanet(attack.source.PlanetID());
        Planet destinationPlanet = endState.GetPlanet(attack.destination.PlanetID());

        List<Planet> planets = endState.Planets();
        for(Planet planet : planets){
            if( !endState.NeutralPlanets().contains(planet)) {
                planet.NumShips(planet.NumShips() + planet.GrowthRate() * attack.turns);
            }
        }

        if (startState.NeutralPlanets().contains(attack.destination)){
            sourcePlanet.RemoveShips(attack.amount);
            destinationPlanet.NumShips(Math.abs(destinationPlanet.NumShips() - attack.amount));
            destinationPlanet.Owner(attack.source.Owner());
        }
        else{
            sourcePlanet.RemoveShips(attack.amount);
            destinationPlanet.NumShips(Math.abs(destinationPlanet.NumShips() - attack.amount));
            destinationPlanet.Owner(attack.source.Owner());
        }

        return endState;

    }

    private static float value_planetwars(PlanetWars planetWars, boolean me){
        // TODO add heuristics for amount of turns for a attack
        // This will discourage the bot from picking attacks that will span a lot of turns.
        int my_ships = getShips(planetWars, me);
        int enemy_ships = getShips(planetWars, !me);

        int my_planets = getPlanets(planetWars, me).size();
        int enemy_planets = getPlanets(planetWars, !me).size();

        int my_growth = getGrowthRate(planetWars, me);
        int enemy_growth = getGrowthRate(planetWars, me);

        // TODO add multipliers per heuristic.
        // By adding the multiplier, some heuristics may have a bigger impact on decision making
        float value = my_ships/enemy_ships + my_planets/enemy_planets + my_growth/enemy_growth;
        return value;
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

    private static int getGrowthRate(PlanetWars planetWars, boolean me){
        List<Planet> my_planets = getPlanets(planetWars, me);
        int growth_rate = 0;

        for(Planet planet : my_planets){
            growth_rate += planet.GrowthRate();
        }
        return growth_rate;

    }

    private static int getShips(PlanetWars planetWars, boolean me){
        List<Fleet> fleets = getFleets(planetWars, me);
        List<Planet> planets = getPlanets(planetWars, me);
        int ships = 0;
        for(Fleet fleet : fleets){
            ships += fleet.NumShips();
        }

        for(Planet planet : planets){
            ships += planet.NumShips();
        }
        return ships;

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
                            try {
                                DoTurn(pw);
                            }
                            catch(Exception e){
                                System.err.print(e);
                            }
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

