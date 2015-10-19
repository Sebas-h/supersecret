/**
 * Created by Jan on 16-10-2015.
 */
public class State {
    // Class to bundle an Attack object and a resulting planetWars object

    public State(Attack attack, PlanetWars startState){
        this.attack = attack;
        if (attack!=null){
            this.planetWars = MyBot.attack_to_planetWars(startState, attack);

        }
        else{
            this.attack = null;
        }
    }

    public Attack attack;

    public PlanetWars planetWars;
}
