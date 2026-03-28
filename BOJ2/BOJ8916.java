import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #8916 이진 검색 트리
class Node {
    public static final Node NULL = new Node(-1);

    int value;
    int childCount;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = NULL;
        this.right = NULL;
        this.childCount = 0;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void increaseChildCount() {
        this.childCount++;
    }
}

public class Main {

    private static final int MOD = 9_999_991;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        StringBuilder answer = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] input = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            Node root = main.createBinarySearchTree(n, input);

            answer.append(main.calcChildNodeCombination(root)).append("\n");
        }

        System.out.println(answer);
    }

    private Node createBinarySearchTree(int n, int[] input) {
        Node root = new Node(input[0]);

        for (int i = 1; i < n; i++) {
            int value = input[i];
            setNode(root, value);
        }

        return root;
    }

    private long calcChildNodeCombination(Node cur) {
        if(cur == Node.NULL) return 1;
        int childCount = cur.childCount;

        int leftNodeCount = cur.getLeft() == Node.NULL ? 0 : cur.getLeft().childCount + 1;

        long leftNodeCombination = calcChildNodeCombination(cur.getLeft());
        long rightNodeCombination = calcChildNodeCombination(cur.getRight());
        long countCombination = getCombinationWithMod(childCount, leftNodeCount);

        long combination = leftNodeCombination * rightNodeCombination % MOD;
        combination = combination * countCombination % MOD;

        return combination;
    }

    private void setNode(Node cur, int value) {
        int curValue = cur.getValue();

        cur.increaseChildCount();

        if (curValue < value) {
            if (cur.getRight() == Node.NULL) {
                Node newNode = new Node(value);
                cur.setRight(newNode);
                return;
            }
            setNode(cur.getRight(), value);
            return;
        }
        if (curValue > value) {
            if (cur.getLeft() == Node.NULL) {
                Node newNode = new Node(value);
                cur.setLeft(newNode);
                return;
            }
            setNode(cur.getLeft(), value);
            return;
        }

        throw new IllegalArgumentException("invalid duplicate input");
    }

    public long getCombinationWithMod(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }
        if (r == 0 || r == n) {
            return 1;
        }

        r = Math.min(r, n - r);

        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
            result %= MOD;
        }
        return result;
    }
}

