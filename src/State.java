/**
 * Created by Jan on 16-10-2015.
 */
public class State {
    // Class to bundle an Attack object and a resulting planetWars object

    public State(Attack attack, PlanetWars startState){
        this.attack = attack;
        this.planetWars = MyBot.attack_to_planetWars(startState, attack);

    }

    public Attack attack;

    public PlanetWars planetWars;
}
