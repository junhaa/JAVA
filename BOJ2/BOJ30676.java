import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #30676 이 별은 무슨 색일까
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N >= 620){
			System.out.println("Red");
		}
		else if(N >= 590)
			System.out.println("Orange");
		else if(N >= 570)
			System.out.println("Yellow");
		else if(N >= 495)
			System.out.println("Green");

		else if(N >= 450)
			System.out.println("Blue");

		else if(N >= 425)
			System.out.println("Indigo");

		else System.out.println("Violet");


	}
}

