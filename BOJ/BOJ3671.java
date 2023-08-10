import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
// BOJ #3671 산업 스파이의 편지 
public class Main {
    static boolean[] ch = new boolean[10_000_001];
    static int[] curNum;
    static boolean[] visited;
    static int len;
    static ArrayList<Integer> nList;
    static int getNumber(int L){
        int tmp = 0;
        for(int i = 0 ; i < L ; i ++){
            tmp *= 10;
            tmp += curNum[i];
        }
        return tmp;
    }
    static int DFS(int L){
        int answer = 0;
        if(!ch[getNumber(L)]) answer ++;
        if(L == len) return answer;
        int last = -1;
        for(int i = 0 ; i < nList.size() ; i ++){
            if(visited[i]) continue;
            int tmp = nList.get(i);
            if(L == 0 && tmp == 0) continue;
            if(last == tmp) continue;
            visited[i] = true;
            curNum[L] = tmp;
            last = tmp;
            answer += DFS(L + 1);
            visited[i] = false;
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        ch[0] = true;
        ch[1] = true;
        for(int i = 2 ; i <= Math.sqrt(10000000) ; i ++){
            for(int j = i * i ; j <= 10000000 ; j += i){
                ch[j] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(T -- > 0){
            String tmp = br.readLine();
            if(Integer.parseInt(tmp) == 0) {
                sb.append("0\n");
                continue;
            }
            nList = new ArrayList<>();
            len = tmp.length();
            for(int i = 0 ; i < len ; i ++){
                nList.add((int)tmp.charAt(i) - '0');
            }
            Collections.sort(nList);
            curNum = new int[len];
            visited = new boolean[len];
            sb.append(DFS(0) + "\n");
        }
        System.out.println(sb);
    }
}
