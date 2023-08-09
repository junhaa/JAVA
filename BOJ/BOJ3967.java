import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #3967 매직 스타
public class Main {
    static boolean[] visited = new boolean[12];
    static char[] cur = new char[12];
    static char[][] map = new char[5][9];

    static void isMagicStar(){
        int sum = cur[1] + cur[2] + cur[3] + cur[4];
        if(sum != cur[7] + cur[8] + cur[9] +cur[10]) return;
        if(sum != cur[0] + cur[2] + cur[5] +cur[7]) return;
        if(sum != cur[0] + cur[3] + cur[6] +cur[10]) return;
        if(sum != cur[1] + cur[5] + cur[8] +cur[11]) return;
        if(sum != cur[4] + cur[6] + cur[9] +cur[11]) return;
        int pt = 0;
        for(int i = 0 ; i < 5 ; i ++){
            for(int j = 0 ; j < 9 ; j ++){
                if(map[i][j] != '.'){
                    if(map[i][j] == 'x'){
                        map[i][j] = cur[pt];
                    }
                    pt ++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 5 ; i ++){
            for(int j = 0 ; j < 9 ; j ++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }

    static void DFS(int L){
        if(L == 12){
            isMagicStar();
            return;
        }
        if(cur[L] == 'x'){
            for(int i = 0 ; i < 12 ; i ++){
                if(visited[i]) continue;
                char tmp = (char)((int)'A' + i);
                cur[L] = tmp;
                visited[i] = true;
                DFS(L + 1);
                cur[L] = 'x';
                visited[i] = false;
            }
        }
        else{
            DFS(L + 1);
        }
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pt = 0;
        for(int i = 0 ; i < 5 ; i ++) {
            String input = br.readLine();
            for(int j = 0 ; j < 9 ; j ++){
                char tmp = input.charAt(j);
                map[i][j] = tmp;
                if(tmp != '.'){
                    if(tmp !='x'){
                        cur[pt] = tmp;
                        visited[tmp - 'A'] = true;
                    }
                    else{
                        cur[pt] = 'x';
                    }
                    pt ++;
                }
            }
        }
        DFS(0);
    }
}
