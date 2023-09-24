import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #1269 대칭 차집합
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < sizeA ; i ++){
            setA.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < sizeB ; i ++){
            setB.add(Integer.parseInt(st.nextToken()));
        }

        int cntAm = 0;
        int cntBm = 0;
        for(int cur : setA){
            if(setB.contains(cur)){
                cntAm ++;
            }
        }
        for(int cur : setB){
            if(setA.contains(cur)){
                cntBm ++;
            }
        }
        System.out.println(sizeA - cntBm + sizeB - cntAm);
    }

}
