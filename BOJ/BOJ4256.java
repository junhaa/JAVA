import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #4256 트리
class Node{
    int num;
    Node left = null, right = null;
    public Node(int num){
        this.num = num;
    }
}
public class Main {

    static int preO[], inO[], prer[];
    static int pt, n;
    static StringBuilder sb, answer = new StringBuilder();
    static Node root = null;

    static int makeTree(Node tmp){
        if(pt >= n) return 1;
        int next = preO[pt];
        int sum = 0;
        if(inO[next] < inO[tmp.num]){
            tmp.left = new Node(preO[pt ++]);
            sum += makeTree(tmp.left);
        }

        if(pt >= n) return sum + 1;
        next = preO[pt];
        if(prer[next] - prer[tmp.num] == sum + 1){
            tmp.right = new Node(preO[pt ++]);
            sum += makeTree(tmp.right);
        }

        return sum + 1;
    }


    static void postorder(int cur, int start, int end){
        for(int i = start ; i < end ; i ++){
            if(inO[i] == preO[cur]){
                postorder(cur + 1, start, i);
                postorder(cur - start + i + 1, i+1, end);
                sb.append(preO[cur] + " ");
            }
        }
    }

    /*
    static void postorder(Node cur){
        if(cur.left != null){
            postorder(cur.left);
        }
        if(cur.right != null){
            postorder(cur.right);
        }
        sb.append((cur.num + 1) + " ");
    }
    */

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(T -- > 0) {
            n = Integer.parseInt(br.readLine());
            preO = new int[n];
            prer = new int[n];
            pt = 1;
            inO = new int[n];
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            root = new Node(Integer.parseInt(st.nextToken()));
            preO[0] = root.num;

            for(int i = 1 ; i < n ; i ++){
                preO[i] = Integer.parseInt(st.nextToken());
            }
            /*
            for(int i = 0 ; i < n ; i ++){
                prer[preO[i]] = i;
            }
             */
            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < n ; i ++){
                inO[i] = Integer.parseInt(st.nextToken());
            }
            // makeTree(root);
            /*
            if(pt < n) {
                Node left = new Node((preO[pt]));
                root.left = left;
                root.left = makeTree(new Node(preO[pt]));
            }
            if(pt < n) root.right = makeTree(new Node(preO[pt]));
             */
            postorder(0, 0, n);
            answer.append(sb + "\n");
        }
        System.out.println(answer);
    }
}

