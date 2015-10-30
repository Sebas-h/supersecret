/**
 * Created by Jan on 30-10-2015.
 */
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class PermutationsOfN<T> {
    public static void main( String[] args ) {
        List<String> f = Lists.newArrayList( "A", "B", "C", "D" );
        PermutationsOfN<String> g = new PermutationsOfN<String>();
        System.out.println( String.format( "n=1 subsets %s", g.processSubsets( f, 1 ) ) );
        System.out.println( String.format( "n=1 permutations %s", g.permutations( f, 1 ) ) );
        System.out.println( String.format( "n=2 subsets %s", g.processSubsets( f, 2 ) ) );
        System.out.println( String.format( "n=2 permutations %s", g.permutations( f, 2 ) ) );
        System.out.println( String.format( "n=3 subsets %s", g.processSubsets( f, 3 ) ) );
        System.out.println( String.format( "n=3 permutations %s", g.permutations( f, 3 ) ) );
        System.out.println( String.format( "n=4 subsets %s", g.processSubsets( f, 4 ) ) );
        System.out.println( String.format( "n=4 permutations %s", g.permutations( f, 4 ) ) );
        System.out.println( String.format( "n=5 subsets %s", g.processSubsets( f, 5 ) ) );
        System.out.println( String.format( "n=5 permutations %s", g.permutations( f, 5 ) ) );
    }

    public List<List<T>> processSubsets( List<T> set, int k ) {
        if ( k > set.size() ) {
            k = set.size();
        }
        List<List<T>> result = Lists.newArrayList();
        List<T> subset = Lists.newArrayListWithCapacity( k );
        for ( int i = 0; i < k; i++ ) {
            subset.add( null );
        }
        return processLargerSubsets( result, set, subset, 0, 0 );
    }

    private List<List<T>> processLargerSubsets( List<List<T>> result, List<T> set, List<T> subset, int subsetSize, int nextIndex ) {
        if ( subsetSize == subset.size() ) {
            result.add( ImmutableList.copyOf( subset ) );
        } else {
            for ( int j = nextIndex; j < set.size(); j++ ) {
                subset.set( subsetSize, set.get( j ) );
                processLargerSubsets( result, set, subset, subsetSize + 1, j + 1 );
            }
        }
        return result;
    }

    public Collection<List<T>> permutations( List<T> list, int size ) {
        Collection<List<T>> all = Lists.newArrayList();
        if ( list.size() < size ) {
            size = list.size();
        }
        if ( list.size() == size ) {
            all.addAll( Collections2.permutations( list ) );
        } else {
            for ( List<T> p : processSubsets( list, size ) ) {
                all.addAll( Collections2.permutations( p ) );
            }
        }
        return all;
    }
}