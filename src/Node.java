import com.sun.javafx.collections.NonIterableChange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 10/26/2015.
 */
public class Node {
    PlanetWars planetWars;

    Move first_Move;

    int depth;

    float value;

    public Node(PlanetWars pw, int depth, Move firstMove){
        planetWars = pw;
        this.depth = depth;
        first_Move = firstMove;
        value = planetWars.value_myself(1);
    }

    /*public float value(){
        // TODO maybe remove dependency on player ID
        return planetWars.value_myself(1);
    }*/

    public List<List<Attack>> getAttacks(){
        getNtoN();



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
            if (first_Move == null){
                children.add(new Node(simulationResult,
                        depth + 1,
                        new Move(attack)));
            }
            children.add(
                    new Node(
                            simulationResult,
                            depth + 1,
                            new Move(first_Move.attacks)));
        }
        return children;
    }

    public List<List<Attack>> getNtoN(){

        // TODO: skip some possibilities when N gets to big
        int N;
        if(planetWars.MyPlanets().size() > planetWars.NotMyPlanets().size()){
            N = planetWars.NotMyPlanets().size();
        }
        else{
            N = planetWars.MyPlanets().size();
        }

        for(int i = 1; i < N; i++){
            CombinationGenerator cg = new CombinationGenerator(N, i);
            PermutationGenerator pg = new PermutationGenerator(N, i);



        }





    }





}
