import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// BOJ #26257 std::shared_ptr
class Query {
    public enum Command {
        assign,
        swap,
        reset
    }

    Command command;
    int x, y;

    public Query(Command command, int x, int y) {
        this.command = command;
        this.x = x;
        this.y = y;
    }
}

public class Main {

    private static int NULL_INDEX = -1;
    private static int NOT_ASSIGNED_OBJECT = 0;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] pointer = new int[M];
        for (int i = 0; i < M; i++) {
            pointer[i] = Integer.parseInt(br.readLine());
        }

        List<Query> qlist = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            Query.Command command = Query.Command.valueOf(st.nextToken());
            Query q;

            // 0-based pointer index
            if (command == Query.Command.reset) {
                q = new Query(command, Integer.parseInt(st.nextToken()) - 1, NULL_INDEX);
                qlist.add(q);
                continue;
            }
            qlist.add(new Query(
                    command,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1
            ));
        }

        System.out.println(T.solve(pointer, qlist, Q));
    }

    private String solve(int[] pointer, List<Query> qlist, int Q) {
        for (int i = 0; i < Q; i++) {
            Query q = qlist.get(i);
            switch (q.command) {
                case assign:
                    assign(pointer, q.x, q.y);
                    break;
                case swap:
                    swap(pointer, q.x, q.y);
                    break;
                case reset:
                    reset(pointer, q.x);
                    break;
                default:
                    break;
            }
        }

        Set<Integer> pointerObjects = new HashSet<>();
        for(int i = 0 ; i < pointer.length ; i ++){
            pointerObjects.add(pointer[i]);
        }
        pointerObjects.remove(NOT_ASSIGNED_OBJECT);

        StringBuilder sb = new StringBuilder();
        sb.append(pointerObjects.size()).append("\n");
        List<Integer> objects = new ArrayList<>(pointerObjects);
        Collections.sort(objects);
        for(int i = 0 ; i < objects.size() ; i ++){
            sb.append(objects.get(i)).append("\n");
        }

        return sb.toString();
    }

    private void assign(int[] pointer, int x, int y) {
        pointer[x] = pointer[y];
    }

    private void swap(int[] pointer, int x, int y) {
        int tmp = pointer[y];
        pointer[y] = pointer[x];
        pointer[x] = tmp;
    }

    private void reset(int[] pointer, int x) {
        pointer[x] = 0;
    }
}
