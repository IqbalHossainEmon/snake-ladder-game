import java.util.LinkedList;
import java.util.Queue;

public class SnakesLadder {

	static class qentry {
		int v;
		int dist;
		String[] path;
	}

	static int[] getMinDiceThrows(int move[], int n, int currentPos) {
		int visited[] = new int[n];
		Queue<qentry> q = new LinkedList<>();
		qentry qe = new qentry();
		qe.v = currentPos;
		qe.dist = 0;
		qe.path = new String[n];
		qe.path[0] = currentPos + "";

		visited[0] = 1;
		q.add(qe);

		qentry lastEntry = null;

		while (!q.isEmpty()) {
			qe = q.remove();
			int v = qe.v;

			if (v == n - 1) {
				lastEntry = qe;
				break;
			}

			for (int j = v + 1; j <= (v + 6) && j < n; ++j) {

				if (visited[j] == 0) {

					qentry a = new qentry();
					a.dist = (qe.dist + 1);
					visited[j] = 1;

					if (move[j] != -1)
						a.v = move[j];
					else
						a.v = j;

					a.path = new String[n];
					System.arraycopy(qe.path, 0, a.path, 0, n);
					a.path[a.dist] = Integer.toString(a.v);
					q.add(a);
				}
			}
		}

		int arr[] = new int[lastEntry.dist + 1];
		for (int i = 0; i <= lastEntry.dist; i++) {
			arr[i] = Integer.parseInt(lastEntry.path[i]);
		}

		return arr;
	}

	public static void main(String[] args) {

		int N = (100);
		int moves[] = new int[N];
		for (int i = 0; i < N; i++)
			moves[i] = -1;

		moves[20] = 1;
		moves[26] = 14;
		moves[22] = 41;
		moves[31] = 50;
		moves[46] = 28;
		moves[55] = 36;
		moves[60] = 78;
		moves[64] = 83;
		moves[73] = 45;
		moves[74] = 96;
		moves[89] = 51;
		moves[98] = 40;

		int currentpos = 0;

		int arr[] = getMinDiceThrows(moves, N, currentpos);

		for (int i : arr) {
			System.out.println(i);
		}

	}
}
