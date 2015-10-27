import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jan on 10/26/2015.
 */

}

public class Uniform_cost {
    Node ROOT;
    int minDepth;

    int maxDepth;

    List<Node> queue = new ArrayList<>();

    public Uniform_cost(PlanetWars planetWars) {
        ROOT = new Node(planetWars, 0, new ArrayList<List<Attack>>());
        List<Node> root_children = ROOT.getChildren();
        Attack longest_attack = longestAttack(root_children);
        Attack shortest_attack = shortestAtack(root_children);

        maxDepth = longest_attack.turns;
        minDepth = shortest_attack.turns;
        queue.addAll(root_children);
    }

    public Node search() {
        while (queue.size() > 0) {
            Node currentNode = queue.get(0);
            queue.remove(0);
            if (currentNode.depth <= maxDepth) {
                if (currentNode.depth >= minDepth && goaltest(currentNode)) {
                    return currentNode;
                }
                for (Node child : currentNode.getChildren()) {
                    queue.add(child);
                }
                sortQueue();
            }

        }
        return new Node(null, 0, null);
    }

        // TODO @Sebas: sorteer code moet hier.

    private void sortQueue() {
    }

    // TODO @Sebas: goaltest code moet hier.
    private boolean goaltest(Node node) {
        return false;
    }

    private Attack longestAttack(List<Node> nodeList) {
        int longest = 0;
        Attack longest_attack = new Attack(null, null, 0, 0);
        for (Node node : nodeList) {
            Attack attack = node.route.get(0).get(0);
            if (attack.turns > longest) {
                longest_attack = attack;
            }
        }
        return longest_attack;
    }

    private Attack shortestAtack(List<Node> nodeList) {
        // TODO replace 999 with something that makes sense.
        // This is dumb
        int smallest = 999;

        Attack smallest_attack = new Attack(null, null, 0, 0);
        for (Node node : nodeList) {
            Attack attack = node.route.get(0).get(0);
            if (attack.turns < smallest) {
                smallest_attack = attack;
            }
        }
        return smallest_attack;
    }
}
