import com.sun.javafx.collections.NonIterableChange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 10/26/2015.
 */
public class Node {
    PlanetWars planetWars;

    List<List<Attack>> route;

    int depth;

    float value;

    public Node(PlanetWars pw, int depth, List<List<Attack>> attacks){
        planetWars = pw;
        this.depth = depth;
        route = attacks;
        value = planetWars.value_myself(1);
    }

    /*public float value(){
        // TODO maybe remove dependency on player ID
        return planetWars.value_myself(1);
    }*/

    public List<List<Attack>> getAttacks(){
        return new ArrayList<>();
    }

    public List<Node> getChildren(){
        List<Node> children = new ArrayList<>();
        List<List<Attack>> attackLists = getAttacks();
        // For every Attack possible.
        for(List<Attack> attack : attackLists){
            // Simulate one turn
            Simulation sim = new Simulation(planetWars, attack);
            PlanetWars simulationResult = sim.simulate_one_turn();
            // Add the resulting planetWars object to a new node
            // and add the Node as a child
            List<List<Attack>> tempRoute = route;
            tempRoute.add(attack);
            children.add(
                    new Node(
                            simulationResult,
                            depth + 1,
                            tempRoute));
        }
        return children;
    }
}
