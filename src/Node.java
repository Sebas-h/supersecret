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
            List<List<Planet>>


        }

        // Product, where i is size of n:
        for (int i = 0; i < N; i++) {


        }

        return new ArrayList<>();
    }

    // TODO fix nested lists ugliness
    private List<List<List<Planet>>> product (List<List<Planet>> list1, List<List<Planet>> list2){
        List<List<List<Planet>>> output = new ArrayList<>();

        for (List<Planet> collection1 : list1){
            for(List<Planet> collection2 : list2){
                List<List<Planet>> product = new ArrayList<>();
                product.add(collection1);
                product.add(collection2);
                output.add(product);

            }
        }
        return output;
    }

    private Turn productToMove(List<List<List<Planet>>> product){
        for(List<List<Planet>> )
    }

    private List<List<Planet>> getPermutations(List<Planet> planets, int size){
        List<List<Planet>> output = new ArrayList<>();

        // Make a new generator
        PermutationGenerator pg = new PermutationGenerator(planets.size(), size);

        // While there are still permutations left
        while(pg.hasMore()){
            // make a new list for the current permutation
            List<Planet> permutation = new ArrayList<>();
            // get the indices to construct a permutation
            int [] indices = pg.customGetNext();
            // make the permutation
            for(int index : indices){
                permutation.add(planets.get(index));
            }
            output.add(permutation);
        }
        return output;
    }

    private List<List<Planet>> getCombinations(List<Planet> planets, int size){
        List<List<Planet>> output = new ArrayList<>();

        CombinationGenerator cg = new CombinationGenerator(planets.size(), size);

        while(cg.hasMore()){
            List<Planet> combination = new ArrayList<>();
            int[] indices = cg.getNext();
            for(int index : indices){
                combination.add(planets.get(index));
            }
            output.add(combination);
        }
        return output;

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
