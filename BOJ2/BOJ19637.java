import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// BOJ #19637 IF문 좀 대신 써줘
class TitleBound implements Comparable<TitleBound> {
    String title;
    int bound;

    TitleBound(String title, int bound) {
        this.title = title;
        this.bound = bound;
    }

    @Override
    public int compareTo(TitleBound o) {
        return this.bound - o.bound;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, TitleBound> titleBoundsMap = new HashMap<>();

        for(int i = 0 ; i < n ; i ++){
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int bound = Integer.parseInt(st.nextToken());
            if(titleBoundsMap.containsKey(bound)) continue;
            titleBoundsMap.put(bound, new TitleBound(title, bound));
        }

        List<TitleBound> titleBounds = titleBoundsMap.values().stream().collect(Collectors.toList());
        Collections.sort(titleBounds);

        List<Integer> strengths = new ArrayList<>();
        for(int i = 0 ; i < m ; i ++){
            strengths.add(Integer.parseInt(br.readLine()));
        }

        System.out.println(T.solve(titleBounds, strengths));
    }

    private String solve(List<TitleBound> titleBounds, List<Integer> strengths){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < strengths.size() ; i ++){
            sb.append(titleBounds.get(calcTitleBoundIndex(titleBounds, strengths.get(i))).title).append("\n");
        }

        return sb.toString();
    }

    private int calcTitleBoundIndex(List<TitleBound> titleBounds, int strength){
        int lt = 0;
        int rt = titleBounds.size() - 1;
        int answer = Integer.MAX_VALUE;

        while(lt <= rt){
            int mid = (lt + rt) / 2;
            if(titleBounds.get(mid).bound >= strength){
                rt = mid - 1;
                answer = Math.min(answer, mid);
                continue;
            }
            lt = mid + 1;
        }

        return answer;
    }
}
