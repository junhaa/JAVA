import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
// BOJ #4659 비밀번호 발음하기
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		HashSet<Character> set = new HashSet<>();
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');

		while(true){
			String input = br.readLine();
			int seq = 0;
			char last = '.';
			if(input.equals("end")) break;
			boolean f1 = false;
			boolean f2 = true;
			for(char c : input.toCharArray()){
				if(set.contains(c)){
					f1 = true;
					if(seq <= 0) seq --;
					else seq = -1;
					if(seq == -3){
						f2 = false;
						break;
					}
				}
				else{
					if(seq <= 0) seq = 1;
					else seq ++;
					if(seq == 3){
						f2 = false;
						break;
					}
				}
				if(last == c && last != 'e' && last != 'o'){
					f2 = false;
					break;
				}
				last = c;
			}

			if(!f1 || !f2){
				sb.append("<" + input + "> is not acceptable.\n");
			}
			else{
				sb.append("<" + input + "> is acceptable.\n");
			}

		}
		System.out.println(sb);
	}
}
