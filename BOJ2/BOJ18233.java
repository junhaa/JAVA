import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #18233 러버덕을 사랑하는 모임
class Range {
    int start;
    int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Range addRange(Range other) {
        return new Range(this.start + other.start, this.end + other.end);
    }

    public boolean isInBound(int num) {
        return start <= num && num <= end;
    }

    public int getRange(){
        return this.end - this.start;
    }

    public int getStart(){
        return this.start;
    }

    public int getEnd(){
        return this.end;
    }
}

public class Main {
    private static final String IMPOSSIBLE_RESULT = "-1";
    private static int N, P, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ranges.add(new Range(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(new Main().solve(ranges));
    }

    private String solve(List<Range> ranges) {
        boolean[] isSelected = new boolean[N];

        if (!check(0, 0, new Range(0, 0), ranges, isSelected)) {
            return IMPOSSIBLE_RESULT;
        }
        return toResult(isSelected, ranges);
    }

    private boolean check(int startIdx, int selectCount, Range curRange, List<Range> ranges, boolean[] isSelected) {
        if (selectCount == P) {
            if (!curRange.isInBound(E)) {
                return false;
            }
            return true;
        }

        for (int i = startIdx; i < N; i++) {
            isSelected[i] = true;

            boolean isPossible = check(i + 1, selectCount + 1, curRange.addRange(ranges.get(i)), ranges, isSelected);

            if (isPossible) {
                return true;
            }

            isSelected[i] = false;
        }

        return false;
    }

    private String toResult(boolean[] isSelected, List<Range> ranges) {
        Range rangeSum = new Range(0, 0);

        for(int i = 0 ; i < N; i ++){
            if(!isSelected[i]) continue;
            rangeSum = rangeSum.addRange(ranges.get(i));
        }

        int remain = E - rangeSum.start;

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N ; i ++) {
            if(!isSelected[i]) {
                sb.append("0").append(" ");
                continue;
            }
            Range cur = ranges.get(i);
            int possibleRange = remain >= cur.getRange() ? cur.getRange() : remain;
            sb.append(cur.getStart() + possibleRange).append(" ");
            remain -= possibleRange;
        }

        return sb.toString();
    }
}
