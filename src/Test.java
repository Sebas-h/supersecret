import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 30-10-2015.
 */
public class Test {



    public static void main(String[] args){
        List<Integer> input = new ArrayList<Integer>(){{
            for (int i = 0; i < 10; i++) {
                this.add(i);
            }
        }
        };
        PermutationGenerator pg = new PermutationGenerator(input.size(), 3);

        while (pg.hasMore()){
            int[] a = pg.customGetNext();
            List<Integer> temp = new ArrayList<Integer>();
            for (int index :a) {
                temp.add(input.get(index));
            }
            System.out.println(temp);
        }
    };

}
