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

    private List<Attack> get_all_attacks(){
        List<Attack> attacks = new ArrayList<>();

        for(Planet my_planet : getPlanets()){
            for(Planet not_my_planet : getNotMyPlanets()) {
                if (isValidAttack(my_planet, not_my_planet)) {
                    attacks.add(
                            new Attack(my_planet, not_my_planet,
                                    not_my_planet.NumShips()+1,
                                    planetWars.Distance(my_planet.PlanetID(),
                                            not_my_planet.PlanetID())));

                }
            }
        }
        return attacks;
    }

    private boolean isValidAttack(Planet source, Planet destination){
        List<Planet> neutralPlanets = planetWars.NeutralPlanets();

        if (neutralPlanets.contains(destination) && destination.NumShips() < source.NumShips()) {
            return true;
        }
        else if (!neutralPlanets.contains(destination) &&
                destination.NumShips() + destination.GrowthRate() * planetWars.Distance(source.PlanetID(), destination.PlanetID()) < source.NumShips()) {
            return true;

        }
        else {
            return false;
        }



    }


    private int getShips() {
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

    private List<Fleet> getFleets() {
        List<Fleet> fleets = planetWars.Fleets();
        List<Fleet> output = new ArrayList<>();

        for (Fleet fleet : fleets) {
            if (fleet.Owner() == playerID) {
                output.add(fleet);
            }
        }
        return output;
    }

    private List<Planet> getNotMyPlanets() {
        List<Planet> output = new ArrayList<>();
        for(Planet planet : planetWars.Planets()){
            if(planet.Owner() != playerID){
                output.add(planet);
            }
        }
        return output;
    }

    private List<Planet> getPlanets() {
        List<Planet> output = new ArrayList<>();
        for(Planet planet : planetWars.Planets()){
            if(planet.Owner() == playerID){
                output.add(planet);
            }
        }
        return output;
    }

}

