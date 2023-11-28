import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #3145 지리지도
public class Main {
    static int N, M;
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1}, dx = { 0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<Integer>[] edge;
    static boolean[] visited;

    static int[] aarr, barr;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(barr[next] == -1 || !visited[barr[next]] && DFS(barr[next])){
                aarr[cur] = next;
                barr[next] = cur;
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
        String input;
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("");
        int[][] map = new int[N][M];
        int wordCnt = 0;
        int landCnt = 0;
        for(int i = 0 ; i < N ; i ++){
            input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                if(Character.isUpperCase(input.charAt(j))){
                    wordCnt ++;
                    String curStr = "";
                    while(j < M && Character.isUpperCase(input.charAt(j))){
                        curStr += input.charAt(j);
                        map[i][j] = wordCnt;
                        j ++;
                    }
                    nameList.add(curStr);
                }
                if(j >= M) break;
                if(input.charAt(j) == 'x'){
                    map[i][j] = 10000 + landCnt ++;
                }
            }
        }

        edge = new ArrayList[landCnt];
        for(int i = 0 ; i < landCnt ; i ++){
            edge[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] >= 10000){
                    for(int k = 0 ; k < 8 ; k ++){
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if(!OOB(ny, nx) && map[ny][nx] != 0 && map[ny][nx] < 10000 && !edge[map[i][j] - 10000].contains(map[ny][nx])){
                            edge[map[i][j] - 10000].add(map[ny][nx]);
                        }
                    }
                }
            }
        }



        aarr = new int[landCnt];
        barr = new int[wordCnt + 1]; // 1 - based
        Arrays.fill(aarr, -1);
        Arrays.fill(barr, - 1);



        for(int i = 0 ; i < landCnt ; i ++){
            if(aarr[i] == -1){
                visited = new boolean[landCnt];
                DFS(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] >= 10000){
                    int curLandIdx = map[i][j] - 10000;
                    sb.append((i + 1) + " " + (j + 1) + " " + nameList.get(aarr[curLandIdx]) + "\n");
                }
            }
        }

        System.out.println(sb);
    }
}
