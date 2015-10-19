
public class Attack{
    public Attack(Planet sourcePlanet, Planet destinationPlanet, int numberOfShips, int turns){
        source = sourcePlanet;
        destination = destinationPlanet;
        amount = numberOfShips;

        this.turns = turns;
    }

    public Planet source;
    public Planet destination;
    public int amount;
    public int turns;
}