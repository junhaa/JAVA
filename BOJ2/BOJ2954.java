import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// BOJ #2954 창영이의 일기장
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for(int i = 0 ; i < input.length() ; i ++) {
            char cur = input.charAt(i);
            sb.append(cur);
            if(vowels.contains(cur)) {
                i += 2;
            }
        }

        System.out.println(sb);
    }
}
