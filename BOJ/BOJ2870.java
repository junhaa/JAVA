import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2870 ¼öÇÐ¼÷Á¦
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<BigInteger> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String buffer = "";
		while(N -- > 0) {
			String input = br.readLine();
			for(char x : input.toCharArray()) {
				if(Character.isAlphabetic(x)) {
					if(buffer != "") { 
						list.add(new BigInteger(buffer));
						buffer = "";
					}
				}
				else {
					buffer += x;
				}
			}
			if(buffer != "") {
				list.add(new BigInteger(buffer));
				buffer = "";
			}
		}
		Collections.sort(list);
		for(BigInteger x : list) {
			sb.append(x).append('\n');
		}
		System.out.println(sb);
	}
}	
