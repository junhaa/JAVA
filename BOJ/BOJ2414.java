import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #2414 게시판 구멍 막기
public class Main {
    static int N, M, cnt1 = 0, cnt2 = 0;
    static int[] arr1, arr2;
    static char[][] map;
    static int[][] map1, map2;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;

    static boolean DFS(int cur) {
        visited[cur] = true;
        for (int next : adj[cur]) {
            if (arr2[next] == -1 || !visited[arr2[next]] && DFS(arr2[next])) {
                arr1[cur] = next;
                arr2[next] = cur;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        map1 = new int[N][M];
        map2 = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for(int i = 0 ; i < N ; i ++){
            boolean last = false;
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] == '*'){
                    if(!last){
                        cnt1 ++;
                    }
                    map1[i][j] = cnt1;
                    last = true;
                }
                else last = false;
            }
        }


        for(int i = 0 ; i < M ; i ++){
            boolean last = false;
            for(int j = 0 ; j < N ; j ++){
                if(map[j][i] == '*'){
                    if(!last){
                        cnt2 ++;
                    }
                    map2[j][i] = cnt2;
                    last = true;
                }
                else last = false;
            }
        }

        adj = new ArrayList[cnt1 + 1];
        for(int i = 0 ; i < cnt1 + 1 ; i ++) adj[i] = new ArrayList<>();

        arr1 = new int[cnt1 + 1];
        arr2 = new int[cnt2 + 1];

        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map1[i][j] != 0 && map2[i][j] != 0){
                    adj[map1[i][j]].add(map2[i][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < cnt1 + 1 ; i++) {
            if (arr1[i] == -1) {
                visited = new boolean[cnt1 + 1];
                if (DFS(i)) answer++;
            }
        }
        System.out.println(answer);
    }
}
