import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #1933 스카이라인 
class Building implements Comparable<Building>{
    int height, idx;
    public Building(int height, int idx){
        this.height = height;
        this.idx = idx;
    }
    @Override
    public int compareTo(Building O){
        if(this.idx != O.idx) return this.idx - O.idx;
        else return O.height - this.height;
    }
}
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Building> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            list.add(new Building(height, idx));
            idx = Integer.parseInt(st.nextToken());
            list.add(new Building(height * -1, idx));
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        for(Building tmp : list){
            if(tmp.height > 0){
                if(map.isEmpty() || map.firstKey() < tmp.height) {
                    sb.append(tmp.idx + " " + tmp.height + " ");
                }
                map.put(tmp.height, map.getOrDefault(tmp.height, 0) + 1);
            }
            else{
                int maxHeight = map.firstKey();
                if(map.get(tmp.height * -1) == 1){
                    map.remove(tmp.height * -1);
                }
                else{
                    map.put(tmp.height * -1, map.get(tmp.height * -1) - 1);
                }
                if(map.isEmpty()){
                    sb.append(tmp.idx + " 0 ");
                }
                else if(map.firstKey() != maxHeight){
                    sb.append(tmp.idx + " " + map.firstKey() + " ");
                }
            }
        }
        System.out.println(sb);
    }
}
