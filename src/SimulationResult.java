/**
 * Created by Jan on 10/20/2015.
 */
public class SimulationResult {

    public Attack attack;


    public Float score;

    public SimulationResult(Attack attack, Float score){
        this.attack = attack;
        this.score = score;
    }


    public int compare(Float f1, Float f2){
        return Float.compare(f1, f2);

    }
}
