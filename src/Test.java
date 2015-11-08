import java.util.*;

public class Test {

    public static void main(String[] args){
        String test1 =
        "P 11.803996 11.215721 1 40 3\n"+
        "P 9.319567 21.808874 1 110 5\n"+
        "P 14.288424 0.622569 2 118 5\n"+
        "P 11.865493 5.273785 2 106 3\n"+
        "P 11.742498 17.157658 0 43 3";

        String test2 =
        "P 11.803996 11.215721 1 40 5\n"+
        "P 9.319567 21.808874 1 30 5\n"+
        "P 14.288424 0.622569 2 10 5\n"+
        "P 11.865493 5.273785 0 30 5\n";

        String test3 =
        "P 11.803996 11.215721 1 40 5\n"+
        "P 9.319567 21.808874 1 30 5\n"+
        "P 14.288424 0.622569 2 10 5\n"+
        "P 11.865493 5.273785 2 30 5\n";

        String test4 =
        "P 11.803996 11.215721 1 40 5\n"+
        "P 9.319567 21.808874 0 20 5\n"+
        "P 14.288424 0.622569 0 10 5\n"+
        "P 11.865493 5.273785 0 5 5";




        PlanetWars pw = new PlanetWars(test4);
        long time = System.currentTimeMillis();

        Uniform_cost uc = new Uniform_cost(pw, new TimeTest());
        Node ans = uc.search();
        for(Attack attack : ans.first_Turn.attacks){
            System.out.println(attack.source.PlanetID()+ " " + attack.destination.PlanetID() + " " + attack.amount);
        }


        long time2 = System.currentTimeMillis();
        System.out.println("time: " + (time2-time) );


    };

}
