import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #34691 대전과학고등학교를 사랑하십니까?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String input = br.readLine();
            if(input.equals("end")) break;
            String result = switch (input) {
                case "animal" -> "Panthera tigris";
                case "tree" -> "Pinus densiflora";
                case "flower" -> "Forsythia koreana";
                default -> throw new IllegalArgumentException();
            };
            sb.append(result).append("\n");

        }
        System.out.println(sb);
    }
}
