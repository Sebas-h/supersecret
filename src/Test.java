import java.util.*;

/**
 * Created by Jan on 30-10-2015.
 */
public class Test {



    public static void main(String[] args){
        List<Integer> input = new ArrayList<Integer>(){{
            for (int i = 0; i < 4; i++) {
                this.add(i);
            }
        }
        };

        for (int i = 0; i < 2; i++) {
            
        }
        long started = System.currentTimeMillis();
        PermutationsOfN permutations = new PermutationsOfN();
        Collection result = permutations.permutations(input,4);
        long finished = System.currentTimeMillis();
        long time = finished - started;

        //result.forEach(iets -> {System.out.println(iets);});
        System.out.println("time taken: " + time);

    };

}
