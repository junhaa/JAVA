import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #2481 해밍 경로 
class Edge implements Comparable<Edge> {
    int num, idx;

    public Edge(int num, int idx) {
        this.num = num;
        this.idx = idx;
    }

    @Override
    public int compareTo(Edge O) {
        return this.num - O.num;
    }
}

public class Main {
    static int N, K;
    static boolean canMove;
    static int[] seq;
    static ArrayList<Edge> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static int binarySearch(int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid).num == target) {
                return mid;
            } else if (list.get(mid).num < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    static void BFS(Edge first) {
        Queue<Edge> Q = new LinkedList<>();
        Q.offer(first);
        seq[first.idx] = -2;
        while (!Q.isEmpty()) {
            Edge tmp = Q.poll();
            int bit = 1;
            for (int i = 0; i < K; i++) {
                int next = tmp.num ^ bit;
                int BSans = binarySearch(next);
                if(BSans != -1){
                    int nextIdx = list.get(BSans).idx;
                    if(seq[nextIdx] == -1) {
                        seq[nextIdx] = tmp.idx;
                        Q.offer(new Edge(next, nextIdx));
                    }
                }
                bit <<= 1;
            }
        }
    }

    static void recursive(Stack<Integer> stack, int curIdx){
        stack.push(curIdx);
        if(curIdx == 0){
            return;
        }
        if(seq[curIdx] == -1) {
            canMove = false;
            return;
        }
        recursive(stack, seq[curIdx]);
    }


    static void findRoute(int des){
        Stack<Integer> stack = new Stack<>();
        canMove = true;
        recursive(stack, des);
        if(!canMove) {
            sb.append(-1);
            return;
        }
        while(!stack.isEmpty()){
            sb.append((stack.pop() + 1) + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        seq = new int[N];
        Arrays.fill(seq, -1);
        boolean flag = false;
        Edge first = null;
        for (int i = 0; i < N; i++) {
            list.add(new Edge(Integer.parseInt(br.readLine(), 2), i));
            if (i == 0) first = list.get(0);
        }
        Collections.sort(list);

        int M = Integer.parseInt(br.readLine());

        BFS(first);
        for (int i = 0; i < M; i++) {
            int tmp = Integer.parseInt(br.readLine()) - 1;
            findRoute(tmp);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
