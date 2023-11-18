import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #1331 나이트 투어
public class Main {
    static boolean[][] visited = new boolean[6][6];
    static int getDis(String cur, String next){
        int cury = cur.charAt(0) - 'A';
        int curx = cur.charAt(1) - '1';
        int nexty = next.charAt(0) - 'A';
        int nextx = next.charAt(1) - '1';
        visited[nexty][nextx] = true;
        return (int)Math.pow(Math.abs(cury - nexty), 2) + (int)Math.pow(Math.abs(curx - nextx), 2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start;
        String cur, next;
        cur = br.readLine();
        start = cur;
        visited[cur.charAt(0) - 'A'][cur.charAt(1) - '1'] = true;
        for(int i = 1 ; i < 36 ; i ++){
            next = br.readLine();
            if(getDis(cur, next) != 5) {
                System.out.println("Invalid");
                return;
            }
            cur = next;
        }

        for(int i = 0 ; i < 6 ; i ++){
            for(int j = 0 ; j < 6 ; j ++){
                if(!visited[i][j]){
                    System.out.println("Invalid");
                    return;
                }
            }
        }

        if(getDis(cur, start) != 5){
            System.out.println("Invalid");
            return;
        }
        System.out.println("Valid");
    }
}
