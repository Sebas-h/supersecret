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

    public Attack run(){
        for(Attack my_attack : player1.get_all_attacks()){
            for(Attack enemy_attack : player2.get_all_attacks()){
                simulate()
            }
        }
    }

}
