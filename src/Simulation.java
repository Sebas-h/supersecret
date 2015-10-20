/**
 * Created by Jan on 10/19/2015.
 */
public class Simulation {


    private PlanetWars planetWars;


    public Simulation(PlanetWars planetWars){

        this.planetWars = new PlanetWars(planetWars.gamestateString);
    }

    public PlanetWars simulate_one(Attack attack){
        planetWars.depart(attack);

        for(int i = 0; i < attack.turns; i ++){
            simulate_one_turn();
        }
        return planetWars;
    }

    public Float simulate(Attack my_attack, Attack enemy_attack){
        // Make new planetwars object.

        int turns;
        // Get longest attack
        if (my_attack.turns > enemy_attack.turns){
            turns = my_attack.turns;
        }
        else{
            turns = enemy_attack.turns;
        }
        // make fleets
        planetWars.depart(my_attack);
        if(enemy_attack.source!= null){
            planetWars.depart(enemy_attack);
        }

        for(int i = 0; i<turns; i++){
            // simulate turns
            simulate_one_turn();
        }
        // return result
        return planetWars.value_myself(1);

    }

    private void simulate_one_turn(){
        //advancement
        planetWars.advance();
        //arrival
        planetWars.arrival();
    }




}
