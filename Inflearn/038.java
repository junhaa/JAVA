package codingTest_038;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public int solution(int N, int[][] board, int num, int[] moves) {

		int answer = 0;
		ArrayList<Integer> arrList = new ArrayList<>();

		int depth = 0;

		for (int i = 0; i < num; i++) {
			while (true) {

				if (depth == N)
					break;

				else if (board[depth][moves[i]] == 0) {
					depth++;
				}

				else {
					if (i == 0) {
						arrList.add(board[depth][moves[i]]);
						board[depth][moves[i]] = 0;
						break;

					}
					if (board[depth][moves[i]] == arrList.get(arrList.size() - 1)) {
						arrList.remove(arrList.size() - 1);
						answer++;
						board[depth][moves[i]] = 0;
						break;

					}
					arrList.add(board[depth][moves[i]]);
					board[depth][moves[i]] = 0;

					break;
				}
			}

			depth = 0;
		}

		return answer * 2;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[][] board = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				board[i][k] = sc.nextInt();
			}
		}
		int num = sc.nextInt();
		int[] moves = new int[num];

		for (int t = 0; t < num; t++) {
			moves[t] = sc.nextInt() - 1;
		}

		System.out.println(T.solution(N, board, num, moves));

	}

}
