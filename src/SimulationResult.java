public class SimulationResult {
    PlanetWars pw;

    int turns;

    public SimulationResult(PlanetWars p, int depth){
        pw = p;
        turns = depth;
    }
}
