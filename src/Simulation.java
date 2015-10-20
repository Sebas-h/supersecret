/**
 * Created by Jan on 10/19/2015.
 */
public class Simulation {

    private Bot player1;

    private Bot player2;

    private PlanetWars planetWars;


    public Simulation(Bot player1, Bot player2, PlanetWars planetWars){
        this.player1 = player1;
        this.player2 = player2;
        this.planetWars = planetWars;
    }

    public void run(){




    }



    private SimulationResult simulate(Attack my_attack, Attack enemy_attack){
        // Make new planetwars object.
        PlanetWars pw = new PlanetWars(planetWars.gamestateString);

        int turns;
        // Get longest attack
        if (my_attack.turns > enemy_attack.turns){
            turns = my_attack.turns;
        }
        else{
            turns = enemy_attack.turns;
        }
        // make fleets
        pw.depart(my_attack, enemy_attack);
        for(int i = 0; i<turns; i++){
            // simulate turns
            simulate_one_turn(pw);
        }
        // return result
        return new SimulationResult(my_attack, enemy_attack, pw.value_myself());

    }

    private void simulate_one_turn(PlanetWars pw){
        //advancement
        pw.advance();
        //arrival
        pw.arrival();
    }




}
