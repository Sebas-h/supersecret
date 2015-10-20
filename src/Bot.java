import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 10/19/2015.
 */

public class Bot{
    int playerID;
    PlanetWars planetWars;


    public Bot(PlanetWars planetWars, int ID){
        this.planetWars = planetWars;
        playerID = ID;
    }



    public List<Attack> get_all_attacks(){

        // TODO allow the bot to send ships to its own planet
        List<Attack> attacks = new ArrayList<>();

        for(Planet my_planet : getPlanets()){
            for(Planet not_my_planet : getNotMyPlanets()) {
                if (isValidAttack(my_planet, not_my_planet)) {
                    if (planetWars.NeutralPlanets().contains(not_my_planet)){
                        attacks.add(
                                new Attack(my_planet, not_my_planet,
                                        not_my_planet.NumShips()+1,
                                        planetWars.Distance(my_planet.PlanetID(),
                                                not_my_planet.PlanetID())));

                    }
                    else{

                        attacks.add(
                                new Attack(my_planet, not_my_planet,
                                        not_my_planet.NumShips() + not_my_planet.GrowthRate() * planetWars.Distance(my_planet.PlanetID(), not_my_planet.PlanetID()) + 1,
                                        planetWars.Distance(my_planet.PlanetID(),
                                                not_my_planet.PlanetID())));
                    }

                }
            }
        }
        return attacks;
    }

    private boolean isValidAttack(Planet source, Planet destination){
        List<Planet> neutralPlanets = planetWars.NeutralPlanets();

       if (neutralPlanets.contains(destination) && destination.NumShips() < source.NumShips() && !fleet_already_sent(destination)) {
            return true;
        }
        else if (!neutralPlanets.contains(destination) &&
                destination.NumShips() + destination.GrowthRate() * planetWars.Distance(source.PlanetID(), destination.PlanetID()) < source.NumShips()
               &&
               !fleet_already_sent(destination)) {
            return true;

        }
        else {
            return false;
        }



    }

    public Attack get_best_attack(){

        float value = 0;
        List<Attack> attacks = get_all_attacks();
        Attack best_attack = new Attack(null, null, 0, 0);

        for(Attack attack : attacks){
            Simulation sim = new Simulation(planetWars);
            float simres = sim.simulate_one(attack).value_myself(playerID);
            if(simres> value){
                best_attack = attack;
                value = simres;
            }

        }
        return best_attack;


    }


    private boolean fleet_already_sent(Planet destination){
        List<Fleet> fleets = planetWars.Fleets();
        for(Fleet fleet : fleets){
            if(fleet.DestinationPlanet() == destination.PlanetID() && fleet.Owner() == playerID){
                return true;
            }
        }
        return false;
    }


    public int getShips() {
        List<Fleet> fleets = getFleets();
        List<Planet> planets = getPlanets();
        int ships = 0;
        for (Fleet fleet : fleets) {
            ships += fleet.NumShips();
        }

        for (Planet planet : planets) {
            ships += planet.NumShips();
        }
        return ships;

    }

    public List<Fleet> getFleets() {
        List<Fleet> fleets = planetWars.Fleets();
        List<Fleet> output = new ArrayList<>();

        for (Fleet fleet : fleets) {
            if (fleet.Owner() == playerID) {
                output.add(fleet);
            }
        }
        return output;
    }

    public List<Planet> getNotMyPlanets() {
        List<Planet> output = new ArrayList<>();
        for(Planet planet : planetWars.Planets()){
            if(planet.Owner() != playerID){
                output.add(planet);
            }
        }
        return output;
    }

    public List<Planet> getPlanets() {
        List<Planet> output = new ArrayList<>();
        for(Planet planet : planetWars.Planets()){
            if(planet.Owner() == playerID){
                output.add(planet);
            }
        }
        return output;
    }

}

