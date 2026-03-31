import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// BOJ #3043 장난감 탱크
class Tank implements Comparable<Tank> {
    public static final int NULL = -1;

    int index;
    int x, y;
    int targetX, targetY;


    @Override
    public int compareTo(Tank o) {
        return this.targetX - o.targetX;
    }

    public Tank(int index, int y, int x) {
        this.index = index;
        this.y = y;
        this.x = x;
        this.targetX = NULL;
        this.targetY = NULL;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public int getIndex() {
        return index;
    }
}

public class Main {

    private static final String OUTPUT_UP = "U";
    private static final String OUTPUT_RIGHT = "R";
    private static final String OUTPUT_DOWN = "D";
    private static final String OUTPUT_LEFT = "L";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Tank> tanks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int x = Integer.parseInt(st.nextToken()) - 1;

            Tank tank = new Tank(i + 1, y, x);
            tanks.add(tank);
        }

        System.out.println(new Main().solve(N, tanks));
    }

    private String solve(int N, List<Tank> tanks) {

        StringBuilder answer = new StringBuilder();

        setTargetPoint(N, tanks);

        int moveCounts = 0;
        moveCounts += moveTanksX(tanks, answer);
        moveCounts += moveTanksY(tanks, answer);

        return moveCounts + "\n" + answer;
    }

    private void setTargetPoint(int N, List<Tank> tanks) {
        List<Tank> tanksByX = tanks.stream()
                .sorted(Comparator.comparing(Tank::getX))
                .collect(Collectors.toList());

        List<Tank> tanksByY = tanks.stream()
                .sorted(Comparator.comparingInt(Tank::getY))
                .collect(Collectors.toList());

        for (int i = 0; i < N; i++) {
            tanksByX.get(i).setTargetX(i);
            tanksByY.get(i).setTargetY(i);
        }
    }

    private int moveTanksX(List<Tank> tanks, StringBuilder answer) {
        int totalMove = 0;

        totalMove += moveTanksLeft(tanks, answer);
        totalMove += moveTanksRight(tanks, answer);

        return totalMove;
    }

    private int moveTanksY(List<Tank> tanks, StringBuilder answer) {
        int totalMove = 0;

        for (Tank tank : tanks) {
            if (tank.getY() > tank.getTargetY()) {
                int moveCount = tank.getY() - tank.getTargetY();
                totalMove += moveCount;

                for (int i = 0 ; i < moveCount ; i ++){
                    answer.append(tank.getIndex()).append(" ").append(OUTPUT_UP).append("\n");
                }
            }

            if (tank.getY() < tank.getTargetY()) {
                int moveCount = tank.getTargetY() - tank.getY();
                totalMove += moveCount;

                for (int i = 0 ; i < moveCount ; i ++){
                    answer.append(tank.getIndex()).append(" ").append(OUTPUT_DOWN).append("\n");
                }
            }
        }

        return totalMove;
    }

    private int moveTanksLeft(List<Tank> tanks, StringBuilder answer) {
        int totalMove = 0;

        List<Tank> leftMoveTanks = tanks.stream()
                .filter(t -> t.getX() > t.getTargetX())
                .sorted(Comparator.comparingInt(Tank::getTargetX))
                .collect(Collectors.toList());

        for (Tank tank : leftMoveTanks) {
            int moveCount = tank.getX() - tank.getTargetX();
            totalMove += moveCount;

            for (int i = 0; i < moveCount; i++) {
                answer.append(tank.getIndex()).append(" ").append(OUTPUT_LEFT).append("\n");
            }
        }

        return totalMove;
    }

    private int moveTanksRight(List<Tank> tanks, StringBuilder answer) {
        int totalMove = 0;

        List<Tank> rightMoveTanks = tanks.stream()
                .filter(t -> t.getX() < t.getTargetX())
                .sorted(Comparator.comparingInt(Tank::getTargetX).reversed())
                .collect(Collectors.toList());

        for (Tank tank : rightMoveTanks) {
            int moveCount = tank.getTargetX() - tank.getX();
            totalMove += moveCount;

            for (int i = 0; i < moveCount; i++) {
                answer.append(tank.getIndex()).append(" ").append(OUTPUT_RIGHT).append("\n");
            }
        }
        return totalMove;
    }
}
