import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Node {
    PlanetWars planetWars;

    Turn first_Turn;

    int depth;

    float value;

    Uniform_cost search;

    public Node(PlanetWars pw, int depth, Turn firstTurn, Uniform_cost searchAlgo) {
        planetWars = pw;
        this.depth = depth;
        first_Turn = firstTurn;
        if(pw == null){
            value = (float) 0.1;
        }
        else{
            value = planetWars.value_myself();
        }
        search = searchAlgo;
    }


    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        List<Turn> turnList = new ArrayList<>();
        if(planetWars.FilteredNotMyPlanets().size()>1){
            turnList.addAll(get1toM());
        }
        turnList.addAll(getNtoN());
        // For every Attack possible.
        for (Turn turn : turnList) {
            // Simulate one turn
            Simulation sim = new Simulation(planetWars, turn.attacks);
            SimulationResult simulationResult = sim.simulate_until_arrival();
            if (first_Turn == null) {
                children.add(new Node(
                        simulationResult.pw,
                        depth + simulationResult.turns,
                        new Turn(turn.attacks),
                        search));
            } else {
                children.add(
                        new Node(
                                simulationResult.pw,
                                depth + simulationResult.turns,
                                first_Turn,
                                search));
            }

        }
        return children;
    }

    public List<Turn> getNtoN() {
        List<Turn> turns = new ArrayList<>();
        List<Planet> myPlanets = planetWars.FilteredMyPlanets();
        List<Planet> notMyPlanets = planetWars.FilteredNotMyPlanets();
        // TODO: skip some possibilities when N gets to big
        // get smallest size of smallest list
        int N;
        if (myPlanets.size() > notMyPlanets.size()) {
            N = notMyPlanets.size();
        } else {
            N = myPlanets.size();
        }
        // N to N is the product(combination(myplanets, N), permutations(notmyplanets, N))
        for (int i = 1; i <= N; i++) {
            List<Turn> a = productToTurn(product(getCombinations(myPlanets, i), getPermutations(notMyPlanets, i)));
            turns.addAll(a);
        }
        return turns;
    }


    private List<Turn> get1toM(){
        // get strongest planet
        List<Turn> output = new ArrayList<>();
        List<Planet> myplanets = new ArrayList<>();
        myplanets.add(planetWars.FilteredMyPlanets().get(0));

        List<List<Planet>> tmp = new ArrayList<>();
        tmp.add(myplanets);

        List<Planet> notMyPlanets = planetWars.FilteredNotMyPlanets();

        // M kleiner maken voor als notMyPLanets < 5 and not 1
        int limit = notMyPlanets.size();
        for(int M = 2; M <= limit; M++ ){
            output.addAll(productToTurn(product(tmp, getCombinations(notMyPlanets, M))));
        }
        return output;

    }


    private List<Turn> productToTurn(List<List<List<Planet>>> product) {
        List<Turn> output = new ArrayList<>();
        for (List<List<Planet>> currentProduct : product) {
            // if the product is a result of 1 to M
            List<Attack> attacks = new ArrayList<>();
            if (currentProduct.get(0).size() == 1 && currentProduct.get(1).size() > 1) {
                // Map 1 to M
                Planet myPlanet = currentProduct.get(0).get(0);
                List<Planet> notMyPlanets = currentProduct.get(1);


                if(isValid1MAttack(myPlanet, notMyPlanets)){
                    for (Planet notMyPlanet : notMyPlanets) {
                        attacks.add(new Attack( myPlanet,
                                        notMyPlanet,
                                        notMyPlanet.NumShips() + 1,
                                        planetWars.Distance(myPlanet, notMyPlanet)
                                )
                        );
                    }
                    output.add(new Turn(attacks));
                }

            }
            // if the product is a result of N to N
            else {
                boolean allAttacksValid = true;
                List<Planet> myPlanets = currentProduct.get(0);
                List<Planet> notMyPlanets = currentProduct.get(1);
                for (int i = 0; i < myPlanets.size(); i++) {
                    Planet myPlanet = myPlanets.get(i);
                    Planet notMyPlanet = notMyPlanets.get(i);
                    if (isValidAttack(myPlanet, notMyPlanet)) {
                        int distance = planetWars.Distance(myPlanet, notMyPlanet);

                        attacks.add(new Attack( myPlanet,
                                                notMyPlanet,
                                                planetWars.predictShips(notMyPlanet, distance) + 1,
                                                distance
                                )
                        );
                    }
                    else {
                        allAttacksValid = false;
                    }
                }
                if (allAttacksValid) {
                    output.add(new Turn(attacks));
                }
            }
        }
        return output;
    }

    // TODO fix nested lists ugliness
    private List<List<List<Planet>>> product(List<List<Planet>> list1, List<List<Planet>> list2) {
        List<List<List<Planet>>> output = new ArrayList<>();

        for (List<Planet> collection1 : list1) {
            for (List<Planet> collection2 : list2) {
                List<List<Planet>> product = new ArrayList<>();
                product.add(collection1);
                product.add(collection2);
                output.add(product);
            }
        }
        return output;
    }

    private boolean isValid1MAttack(Planet myPlanet,List<Planet> notMyPlanets){
        int shipsRequired = 0;
        for (Planet notMyPlanet : notMyPlanets) {
            shipsRequired += planetWars.predictShips(notMyPlanet, planetWars.Distance(myPlanet, notMyPlanet)) + 1;
            if(shipsRequired >= myPlanet.NumShips()) {
                return false;
            }

        }
        return true;
    }

    private List<List<Planet>> getPermutations(List<Planet> planets, int size) {
        List<List<Planet>> output = new ArrayList<>();

        PermutationsOfN p = new PermutationsOfN();
        Collection<List<Planet>> stuff = p.permutations(planets, size);

        for (List<Planet> permutation : stuff) {
            output.add(permutation);
        }

        return output;
    }

    private List<List<Planet>> getCombinations(List<Planet> planets, int size) {
        List<List<Planet>> output = new ArrayList<>();

        CombinationGenerator cg = new CombinationGenerator(planets.size(), size);

        while (cg.hasMore()) {
            List<Planet> combination = new ArrayList<>();
            int[] indices = cg.getNext();
            for (int index : indices) {
                combination.add(planets.get(index));
            }
            output.add(combination);
        }
        return output;

    }

    // Check viability of an attack in a 1 to 1 situation
    private boolean isValidAttack(Planet source, Planet destination) {
        if (planetWars.predictShips(    destination,
                                        planetWars.Distance(source, destination)
                                    ) + 1
                < source.NumShips()
                &&
                !fleet_already_sent(destination))
        {
            return true;
        }
        else {
            return false;
        }
    }

    // Checks if there are already fleets under way to destination planet.
    private boolean fleet_already_sent(Planet destination) {
        List<Fleet> fleets = planetWars.Fleets();
        for (Fleet fleet : fleets) {
            if (fleet.DestinationPlanet() == destination.PlanetID() && fleet.Owner() == 1) {
                return true;
            }
        }
        return false;
    }


}
