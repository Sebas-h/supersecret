/**
 * Created by Jan on 10/19/2015.
 */

import java.util.List;

public class Simulation {


    private PlanetWars planetWars;
    private List<Attack> attacks;


    public Simulation(PlanetWars planetWars, List<Attack> attacks){

        this.planetWars = planetWars.clone();
        this.attacks = attacks;
    }


    public PlanetWars simulate_one_turn(){
        // Departure
        for (Attack attack : attacks)
        {
            planetWars.depart(attack);
        }
        //advancement
        planetWars.advance();
        //arrival
        planetWars.arrival();
        return planetWars;
    }




}
