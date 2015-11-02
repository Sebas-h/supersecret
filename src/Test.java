import java.util.*;

/**
 * Created by Jan on 30-10-2015.
 */
public class Test {



    public static void main(String[] args){
        String input = "P 11.803996 11.215721 0 37 3" + "\n" +
        "P 9.319567 21.808874 1 84 5" + "\n" +
        "P 14.288424 0.622569 2 85 5" + "\n" +
        "P 11.865493 5.273785 0 81 3" + "\n" +
        "P 11.742498 17.157658 0 81 3" + "\n" +
        "P 4.254093 0.000000 0 22 1" + "\n" +
        "P 19.353899 22.431443 0 22 1" + "\n" +
        "P 14.743614 22.324001 0 83 3" + "\n" +
        "P 8.864377 0.107441 0 83 3" + "\n" +
        "P 19.854347 0.711934 0 84 1" + "\n" +
        "P 3.753644 21.719509 0 84 1" + "\n" +
        "P 8.864814 9.736624 0 12 5" + "\n" +
        "P 14.743177 12.694819 0 12 5" + "\n" +
        "P 0.000000 10.809889 0 59 2" + "\n" +
        "P 23.607991 11.621554 0 59 2" + "\n" +
        "P 20.396768 15.522861 0 59 3" + "\n" +
        "P 3.211223 6.908581 0 59 3" + "\n" +
        "P 17.028748 6.659769 2 46 2" + "\n" +
        "P 6.579243 15.771674 0 4 2" + "\n" +
        "P 0.782928 19.607505 0 55 1" + "\n" +
        "P 22.825064 2.823937 0 55 1" + "\n" +
        "P 2.601033 13.172383 0 58 3" + "\n" +
        "P 21.006958 9.259059 0 58 3" + "\n" +
        "F 1 5 1 17 17 10" + "\n" +
        "F 1 13 1 11 13 7" + "\n" +
        "F 1 13 1 12 11 6" + "\n" +
        "F 1 5 1 18 7 3" + "\n" +
        "F 1 5 1 17 17 14" + "\n" +
        "F 1 5 1 17 17 15" + "\n" +
        "F 1 5 1 17 17 16";


        PlanetWars pw = new PlanetWars(input);
        long time = System.currentTimeMillis();
        Uniform_cost uc = new Uniform_cost(pw);
        Node ans = uc.search();
        System.out.println(ans.depth);
        long time2 = System.currentTimeMillis();
        System.out.println("time: " + (time2-time) );


        PlanetWars pw = new PlanetWars(input);
        Uniform_cost uc = new Uniform_cost(pw);
        long s = System.currentTimeMillis();
        uc.search();
        long e = System.currentTimeMillis();
        System.out.println(s-e);

    };

}
