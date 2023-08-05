import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #14570 나무 위의 구슬 
class Node{
    int num, lt = -1, rt = -1;
    public Node(int num){
        this.num = num;
    }
}
public class Main {
    static Node[] list;

    static int getNodeNum(long cur, int nodeNum){
        Node curN = list[nodeNum];
        if(curN.lt == -1 && curN.rt == -1){
            return nodeNum;
        }
        else if(curN.lt == -1){
            return getNodeNum(cur, curN.rt);
        }
        else if(curN.rt == -1){
            return getNodeNum(cur, curN.lt);
        }
        else {
            if(cur % 2 == 1){
                return getNodeNum(cur / 2 + 1, curN.lt);
            }
            else {
                return getNodeNum(cur / 2, curN.rt);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        list = new Node[N + 1];
        for(int i = 1 ; i <= N ; i ++){
            Node tmp = new Node(i);
            st = new StringTokenizer(br.readLine());
            int lt = Integer.parseInt(st.nextToken());
            int rt = Integer.parseInt(st.nextToken());
            if(lt != -1){
                tmp.lt = lt;
            }
            if(rt != -1){
                tmp.rt = rt;
            }
            list[i] = tmp;
        }
        Long K = Long.parseLong(br.readLine());
        System.out.println(getNodeNum(K, 1));
    }
}
