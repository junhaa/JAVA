import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
//BOJ #1004 어린 왕자
class Point{
    int y, x, r;
    public Point(int y, int x, int r) {
        this.y = y;
        this.x = x;
        this.r = r;
    }
}
public class Main {

    static boolean isInBound(Point p1, Point p2){
        int dis = (int)Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p1.x - p2.x), 2));
        if(dis < p2.r) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -1);
            Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -1);
            HashSet<Integer> set = new HashSet<>();
            int n = Integer.parseInt(br.readLine());
            Point[] planet = new Point[n];
            for(int i = 0 ; i < n ; i ++){
                st = new StringTokenizer(br.readLine());
                Point cur = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                if(isInBound(start, cur)){
                    set.add(i);
                }
                if(isInBound(end, cur)){
                    if(set.contains(i)){
                        set.remove(i);
                    }
                    else{
                        set.add(i);
                    }
                }
            }
            sb.append(set.size() + "\n");
        }
        System.out.println(sb);
    }
}
