import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #19541 역학 조사
class Meeting {
    List<Integer> members;

    Meeting(List<Integer> members) {
        this.members = members;
    }

    public List<Integer> getMembers() {
        return members;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            List<Integer> members = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                int memberIdx = Integer.parseInt(st.nextToken()) - 1; // 0-based
                members.add(memberIdx);
            }

            meetings.add(new Meeting(members));
        }

        boolean[] finalInfected = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            finalInfected[i] = st.nextToken().equals("1");
        }

        System.out.println(new Main().solve(N, M, meetings, finalInfected));
    }

    private String solve(int n, int m, List<Meeting> meetings, boolean[] finalInfected) {
        boolean[] initInfected = getInitInfected(n, m, meetings, finalInfected);

        boolean[] expectInfected = getExpectInfected(n, meetings, initInfected);

        return getResult(n, expectInfected, finalInfected, initInfected);
    }

    private boolean[] getInitInfected(int n, int m, List<Meeting> meetings, boolean[] finalInfected) {
        boolean[] infected = new boolean[n];

        for (int i = 0; i < n; i++) {
            infected[i] = finalInfected[i];
        }

        for (int i = m - 1; i >= 0; i--) {
            Meeting cur = meetings.get(i);

            if (isInfectedInReverse(infected, cur)) {
                continue;
            }
            changeInfectState(infected, false, cur);
        }

        return infected;
    }

    private boolean isInfectedInReverse(boolean[] infected, Meeting meeting) {
        for (int idx : meeting.getMembers()) {
            if (!infected[idx]) {
                return false;
            }
        }

        return true;
    }

    private void changeInfectState(boolean[] infected, boolean isInfected, Meeting meeting) {
        for (int idx : meeting.getMembers()) {
            infected[idx] = isInfected;
        }
    }

    private boolean[] getExpectInfected(int n, List<Meeting> meetings, boolean[] initInfected) {
        boolean[] infected = new boolean[n];
        for (int i = 0; i < n; i++) {
            infected[i] = initInfected[i];
        }
        for (Meeting meeting : meetings) {
            if (!isInfected(infected, meeting)) {
                continue;
            }
            changeInfectState(infected, true, meeting);
        }

        return infected;
    }

    private boolean isInfected(boolean[] infected, Meeting meeting) {
        for (int idx : meeting.getMembers()) {
            if (infected[idx]) {
                return true;
            }
        }
        return false;
    }

    private String getResult(int n, boolean[] expectInfected, boolean[] finalInfected, boolean[] initInfected) {
        for (int i = 0; i < n; i++) {
            if (expectInfected[i] != finalInfected[i]) {
                return "NO";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("YES\n");

        for (boolean cur : initInfected) {
            sb.append(cur ? 1 : 0).append(" ");
        }

        return sb.toString();
    }
}
