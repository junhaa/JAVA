import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #15814 야바위 대장
public class Main {
    static class Command {
        int a, b;

        Command(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int t = Integer.parseInt(br.readLine());
        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            commands.add(new Command(a, b));
        }

        System.out.println(T.solve(s, t, commands));
    }

    private String solve(String s, int t, List<Command> commands){
        char[] sCharArray = s.toCharArray();
        for(int i = 0 ; i < t ; i ++){
            change(sCharArray, commands.get(i));
        }

        return String.valueOf(sCharArray);
    }

    private void change(char[] sCharArray, Command command){
        char tmp = sCharArray[command.a];
        sCharArray[command.a] = sCharArray[command.b];
        sCharArray[command.b] = tmp;
    }
}
