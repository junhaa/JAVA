import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2942 퍼거슨과 사과
class div implements Comparable<div>{
    int a, b, c;

    public div(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public int compareTo(div O){
        return this.a - O.a;
    }
}
public class Main {
    static int GCD(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int R = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int gcd = GCD(R, G);
        ArrayList<div> list = new ArrayList<>();
        for(int i = 1 ; i <= Math.sqrt(gcd) ; i ++){
            if(gcd % i == 0){
                list.add(new div(i, R / i, G / i));
                if(i != Math.sqrt(gcd)) list.add(new div(gcd / i, R / (gcd / i), G / (gcd / i)));
            }
        }
        Collections.sort(list);
        for(div d : list){
            sb.append(d.a + " " + d.b + " " + d.c + "\n");
        }
        System.out.println(sb);
    }
}
