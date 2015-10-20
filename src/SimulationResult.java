/**
 * Created by Jan on 10/20/2015.
 */
public class SimulationResult {

    public Attack our_attack;

    public Attack enemy_attack;

    public Float score;

    public SimulationResult(Attack our_attack, Attack enemy_attack, Float score){
        this.our_attack = our_attack;
        this.enemy_attack = enemy_attack;
        this.score = score;
    }
}
