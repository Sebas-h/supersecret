import java.util.ArrayList;
import java.util.List;

public class Node {
    PlanetWars planetWars;

    Turn first_Turn;

    int depth;

    float value;

    public Node(PlanetWars pw, int depth, Turn firstTurn){
        planetWars = pw;
        this.depth = depth;
        first_Turn = firstTurn;
        value = planetWars.value_myself(1);
    }


    public List<Node> getChildren(){

        List<Node> children = new ArrayList<>();
        List<Turn> turnList = getTurns();
        // For every Attack possible.
        for(Turn turn : turnList){
            // Simulate one turn
            Simulation sim = new Simulation(planetWars, turn.attacks);
            PlanetWars simulationResult = sim.simulate_one_turn();
            if (first_Turn == null){
                children.add(new Node(simulationResult,
                        depth + 1,
                        new Turn(turn.attacks)));
            }
            children.add(
                    new Node(
                            simulationResult,
                            depth + 1,
                            new Turn(first_Turn.attacks)));
        }
        return children;
    }

    public List<Turn> getTurns(){
        getNtoN();
        getNtoM();


        return new ArrayList<>();
    }

    public List<Turn> getNtoN(){
        List<Turn> turns;
        List<Attack> attacks;
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

        // Product, where i is size of n:
        for (int i = 0; i < N; i++) {


        }

        return new ArrayList<>();
    }

    public List<Turn> getNtoM(){
        List<Turn> turns;
        List<Attack> attacks;

        /*
        get list of MyPlanets
        get list of NotMyPlanets
        for loop through MyPlanets
            for loop through NotMyPlanets
                <samenkoppelen>
         */

        return new ArrayList<>();
    }





}
