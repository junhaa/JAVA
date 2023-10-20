import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17825 주사위 윷놀이 
class Point{
    int r, c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class Main {
    static int[] dice = new int[10];
    static int[][] score = new int[4][23];
    static int[][] map = new int[4][23];
    static Point[] cur = new Point[4];

    static Point getNextPoint(Point cur, int L){
        Point newP = new Point(-1, -1);
        int move = dice[L];
        if(cur.c == 5 || cur.r == 1){
            if(cur.c + move > 8){
                newP.r = 3;
                newP.c = cur.c + move + 10;
                if(newP.c == 22){
                    newP.r = 0;
                    newP.c = 20;
                }
            }
            else{
                newP.r = 1;
                newP.c = cur.c + move;
            }
        }
        else if(cur.c == 10 || cur.r == 2){
            if(cur.c + move > 12){
                newP.r = 3;
                newP.c = cur.c + move + 6;
                if(newP.c == 22){
                    newP.r = 0;
                    newP.c = 20;
                }
            }
            else{
                newP.r = 2;
                newP.c = cur.c + move;
            }
        }
        else if(cur.c == 15 || cur.r == 3){
            newP.r = 3;
            newP.c = cur.c + move;
            if(newP.c == 22){
                newP.r = 0;
                newP.c = 20;
            }
        }
        else{
            newP.r = 0;
            newP.c = cur.c + move;
        }
        if(newP.r == 3 && newP.c > 22){
            newP.r = 0;
            newP.c = 21;
        }
        else if(newP.r == 0 && newP.c > 20){
            newP.r = 0;
            newP.c = 21;
        }
        if(newP.r == 0 && newP.c == 21);
        else if(map[newP.r][newP.c] != 0) return null;
        return newP;
    }

    static int backTracking(int L, int sum){
        if(L == 10) return 0;
        // 이동할 말 선택
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < 4 ; i ++){
            Point curP = cur[i];
            if(curP.r == 0 && curP.c == 21) continue;
            Point tmpP = curP;
            Point nextP = getNextPoint(curP, L);
            if(nextP == null) continue;
            cur[i] = nextP;
            map[tmpP.r][tmpP.c] --;
            map[nextP.r][nextP.c] ++;
            max = Math.max(max, backTracking(L + 1, sum + score[nextP.r][nextP.c]) + score[nextP.r][nextP.c]);
            map[tmpP.r][tmpP.c] ++;
            map[nextP.r][nextP.c] --;
            cur[i] = tmpP;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < 10 ; i ++){
            dice[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i <= 20 ; i ++){
            score[0][i] = i * 2;
        }

        for(int i = 6 ; i <= 8 ; i ++){
            score[1][i] = 10 + (i - 5) * 3;
        }

        for(int i = 11 ; i <= 12 ; i ++){
            score[2][i] = 2 * i;
        }

        for(int i = 16 ; i <= 18 ; i ++){
            score[3][i] = 28 - (i - 16);
        }

        score[3][19] = 25;
        score[3][20] = 30;
        score[3][21] = 35;
        score[3][22] = 40;

        for(int i = 0 ; i < 4 ; i ++){
            cur[i] = new Point(0, 0);
        }
        map[0][0] = 4;
        System.out.println(backTracking(0, 0));
    }
}
