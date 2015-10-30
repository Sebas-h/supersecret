import java.util.ArrayList;

/**
 * Created by Sebas on 30-10-2015.
 */
public class PlanetWarsWrapper extends PlanetWars {

    // Ik wilde de PlanetWars uitbereiden zodat we niet alles daarin hoeven te gooien. Not sure how tho.

    public PlanetWarsWrapper(String gameStateString) {
        super(gameStateString);
    }

    public void Test(){
        System.out.print(MyPlanets());
    }
}
