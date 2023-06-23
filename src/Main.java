import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
	static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private JPanel contentPane;
	private final JLabel lblBoard = new JLabel("New label");
	JLabel[] jLabels = new JLabel[101];
	boolean[] visited;
	private JLabel lblDImage;
	private JLabel lblP1;
	private JButton btnDice;
	private JButton btnShowMin;
	private JLabel lblPlayerName;
	private JLabel p1score;
	private JLabel lblP2;
	private JLabel p2score;
	private JButton btnRestart;
	private int flag1 = 0, winingPosition = 0;
	private boolean minShow = false;
	private int player = 1;
	private int[] flag = new int[6];
	private int[] playerPosition = new int[4];
	private int point;
	private JLabel lblP3;
	private JLabel p3score;
	private JLabel lblP4;
	private JLabel p4score;
	private JLabel lblPlayer;
	private JLabel p1btn;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private int dicePerviousPosition = 0, dice = 0;
	private int[] minArr;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Dice() {
		Random rn = new Random();

		point = rn.nextInt(6) + 1;

		String location = "/Image/dice " + point + ".jpg";
		lblDImage.setIcon(new ImageIcon(Main.class.getResource(location)));

		if (point == 1)
			flag[player - 1] = 1;
		if (flag[player - 1] == 1)
			moveDisc();

	}

	public void moveDisc() {
		if (point == 6)
			dice++;
		else
			dice = 0;

		if (dice > 0)
			dicePerviousPosition = playerPosition[player - 1];

		if (playerPosition[player - 1] + point > 100) {
			playerPosition[player - 1] = playerPosition[player - 1];
		}

		else if (dice == 3) {
			playerPosition[player - 1] = dicePerviousPosition;
		}

		else {

			RemoveImage(playerPosition[player - 1]);
			playerPosition[player - 1] += point;

			SetImage(playerPosition[player - 1], player);

			SkipText(playerPosition[player - 1]);

			if (playerPosition[player - 1] != Skipper(playerPosition[player - 1])) {
				RemoveImage(playerPosition[player - 1]);
				playerPosition[player - 1] = Skipper(playerPosition[player - 1]);
				SetImage(playerPosition[player - 1], player);
			}

			if (playerPosition[player - 1] == 100)
				winingPosition++;

			if (playerPosition[player - 1] == 100 && flag1 == 0) {
				JOptionPane.showMessageDialog(null, "Player " + player + " won!!");
				flag1 = 1;
			}

			if (player == 1) {

				if (playerPosition[player - 1] == 100 && winingPosition == 4)
					p1score.setText("Looser HaHA ");
				else if (playerPosition[player - 1] == 100)
					p1score.setText("Winner " + winingPosition);
				else
					p1score.setText(String.valueOf(playerPosition[player - 1]));
			}

			else if (player == 2) {

				if (playerPosition[player - 1] == 100 && winingPosition == 4)
					p2score.setText("Looser HaHA! ");
				else if (playerPosition[player - 1] == 100)
					p2score.setText("Winner " + winingPosition);
				else
					p2score.setText(String.valueOf(playerPosition[player - 1]));

			} else if (player == 3) {

				if (playerPosition[player - 1] == 100 && winingPosition == 4)
					p3score.setText("Looser HaHA ");
				else if (playerPosition[player - 1] == 100)
					p3score.setText("Winner " + winingPosition);
				else
					p3score.setText(String.valueOf(playerPosition[player - 1]));

			} else if (player == 4) {

				if (playerPosition[player - 1] == 100 && winingPosition == 4)
					p4score.setText("Looser HaHA ");
				else if (playerPosition[player - 1] == 100)
					p4score.setText("Winner " + winingPosition);
				else
					p4score.setText(String.valueOf(playerPosition[player - 1]));
			}
		}

	}

	public void PositionCheck(int x) {

		for (int i = 0; i < 4; i++) {
			if (playerPosition[i] == x && playerPosition[i] != 0 && player != i + 1) {
				SetImage(x, i + 1);
			}
		}

	}

	public Main() {
		setTitle("Snake And Ladder Game using BFS");
		FrameInMiddle();
		initialize();
	}

	public void SkipText(int score) {
		if (score == 23 || score == 32 || score == 61 || score == 65 || score == 75) {
			System.out.println("Ladder at " + score);
			JOptionPane.showMessageDialog(null, "Climbed Ladder at " + String.valueOf(score));
		} else if (score == 21 || score == 27 || score == 47 || score == 56 || score == 74 || score == 90 || score == 99) {
			System.out.println("Snake at " + score);
			JOptionPane.showMessageDialog(null, "Eaten by Snake at " + String.valueOf(score));
		}
	}

	public int Skipper(int score) {
		switch (score) {
			case 21:
				return 2;
			case 27:
				return 15;
			case 23:
				return 42;
			case 32:
				return 51;
			case 47:
				return 29;
			case 56:
				return 37;
			case 61:
				return 79;
			case 65:
				return 84;
			case 74:
				return 46;
			case 90:
				return 52;
			case 75:
				return 96;
			case 99:
				return 41;
			default:
				return score;
		}
	}

	class qentry {
		int v;
		int dist;
		String[] path;
	}

	int[] bfsMinDice(int move[], int n, int currentPos) {
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
			arr[i] = Integer.parseInt(lastEntry.path[i]) + 1;
		}

		return arr;
	}

	int[] getMin(int currentPos) {

		int N = 100;
		int moves[] = new int[N];
		for (int i = 0; i < N; i++)
			moves[i] = -1;

		moves[20] = 1;
		moves[26] = 14;
		moves[46] = 28;
		moves[55] = 36;
		moves[22] = 41;
		moves[73] = 45;
		moves[31] = 50;
		moves[89] = 51;
		moves[60] = 78;
		moves[64] = 83;
		moves[74] = 95;
		moves[98] = 40;

		return bfsMinDice(moves, N, currentPos);
	}

	public void RemoveImage(int x) {
		if (x > 0 && x <= 100) {
			jLabels[x].setVisible(false);
			PositionCheck(x);

		}
	}

	void toggleMinShow() {
		if (!minShow) {
			for (int i : minArr) {
				SetImage(i, player);
			}
			minShow = true;
		} else {
			for (int i : minArr) {
				RemoveImage(i);
			}
			minShow = false;
		}
	}

	public void SetImage(int x, int p) {
		if (x > 0 && x <= 100) {
			String location = "/Image/Player " + p + ".png";

			jLabels[x].setVisible(true);
			jLabels[x].setIcon(new ImageIcon(Main.class.getResource(location)));
		}
	}

	@SuppressWarnings("serial")
	public void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		int[] x = new int[] { 92, 151, 211, 271, 331, 393, 453, 511, 572, 630, 630, 572, 511, 453, 393, 331, 271, 211, 151,
				92, 92, 151, 211, 271, 331, 393, 453, 511, 572, 630, 630, 572, 511, 453, 393, 331, 271, 211, 151, 92, 92, 151,
				211, 271, 331, 393, 453, 511, 572, 630, 630, 572, 511, 453, 393, 331, 271, 211, 151, 92, 92, 151, 211, 271, 331,
				393, 453, 511, 572, 630, 630, 572, 511, 453, 393, 341, 271, 211, 151, 92, 92, 151, 211, 271, 331, 393, 453, 511,
				572, 630, 630, 572, 511, 453, 393, 331, 271, 211, 151, 92 };
		int[] y = new int[] { 576, 576, 576, 576, 576, 576, 576, 576, 576, 576, 517, 517, 517, 517, 517, 517, 517, 517, 517,
				517, 457, 457, 457, 457, 457, 457, 457, 457, 457, 457, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 335,
				335, 335, 335, 335, 335, 335, 335, 335, 335, 277, 277, 277, 277, 277, 277, 277, 277, 277, 277, 217, 217, 217,
				217, 217, 217, 217, 217, 217, 217, 157, 157, 157, 157, 157, 157, 157, 157, 157, 157, 97, 97, 97, 97, 97, 97, 97,
				97, 97, 97, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38 };

		for (int i = 1; i <= 100; i++) {
			JLabel jLbl = new JLabel("");

			jLbl.setBounds(x[i - 1], y[i - 1], 60, 60);
			contentPane.add(jLbl);
			jLabels[i] = jLbl;
		}

		lblPlayerName = new JLabel("Playing Player -");
		lblPlayerName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPlayerName.setForeground(new Color(51, 204, 204));
		lblPlayerName.setBounds(724, 67, 132, 26);
		contentPane.add(lblPlayerName);

		btnDice = new JButton("Roll Dice");
		btnDice.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		btnDice.setForeground(new Color(255, 255, 255));
		btnDice.setBackground(new Color(0, 0, 51));
		btnDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDice.setBackground(new Color(204, 0, 102));
				if (minShow) {
					toggleMinShow();
				}
				if (dice == 0) {
					if (winingPosition == 4) {
						int confirm = JOptionPane.showConfirmDialog(null, "Replay?", "", JOptionPane.YES_NO_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							dispose();
							new Main().setVisible(true);

						} else {
							setVisible(false);
							dispose();

						}
					}

					if (player == 4)
						lblPlayer.setText(String.valueOf(1));
					else
						lblPlayer.setText(String.valueOf(player + 1));

				}
				Dice();
				if (dice == 0) {
					player++;
					if (player == 5)
						player = 1;
				}

			}
		});
		btnDice.setBackground(new Color(0, 0, 51));
		btnDice.setBounds(724, 122, 150, 44);
		contentPane.add(btnDice);

		btnShowMin = new JButton("Minimum Path");
		btnShowMin.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnShowMin.setForeground(new Color(255, 255, 255));
		btnShowMin.setBackground(new Color(0, 0, 51));
		btnShowMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minArr = getMin(playerPosition[player - 1]);
				toggleMinShow();
			}
		});

		btnShowMin.setBackground(new Color(0, 0, 51));
		btnShowMin.setBounds(904, 122, 200, 44);
		contentPane.add(btnShowMin);

		contentPane.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "EXIT");
		contentPane.getRootPane().getActionMap().put("EXIT", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				btnDice.doClick();
			}
		});

		lblP1 = new JLabel("Player1-");
		lblP1.setForeground(new Color(0, 204, 255));
		lblP1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblP1.setBounds(718, 323, 69, 26);
		contentPane.add(lblP1);

		lblDImage = new JLabel(".");
		lblDImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDImage.setBounds(724, 176, 132, 129);
		contentPane.add(lblDImage);

		p1score = new JLabel("Score");
		p1score.setHorizontalAlignment(SwingConstants.LEFT);
		p1score.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		p1score.setForeground(new Color(255, 255, 255));
		p1score.setBounds(790, 329, 84, 14);
		contentPane.add(p1score);

		lblP2 = new JLabel("Player2-");
		lblP2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblP2.setForeground(new Color(255, 204, 0));
		lblP2.setBounds(718, 389, 69, 14);
		contentPane.add(lblP2);

		p2score = new JLabel("Score");
		p2score.setHorizontalAlignment(SwingConstants.LEFT);
		p2score.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		p2score.setForeground(new Color(255, 255, 255));
		p2score.setBounds(790, 389, 84, 14);
		contentPane.add(p2score);
		lblBoard.setBounds(92, 38, 600, 600);
		contentPane.add(lblBoard);

		lblBoard.setIcon(new ImageIcon(Main.class.getResource("/Image/Board Small 600 600.png")));

		lblP3 = new JLabel("Player3-");
		lblP3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblP3.setForeground(new Color(255, 51, 102));
		lblP3.setBounds(718, 437, 69, 26);
		contentPane.add(lblP3);

		p3score = new JLabel("Score");
		p3score.setHorizontalAlignment(SwingConstants.LEFT);
		p3score.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		p3score.setForeground(new Color(255, 255, 255));
		p3score.setBounds(790, 443, 84, 14);
		contentPane.add(p3score);

		lblP4 = new JLabel("Player4-");
		lblP4.setForeground(new Color(204, 51, 204));
		lblP4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblP4.setHorizontalAlignment(SwingConstants.CENTER);
		lblP4.setBounds(718, 499, 69, 14);
		contentPane.add(lblP4);

		p4score = new JLabel("Score");
		p4score.setHorizontalAlignment(SwingConstants.LEFT);
		p4score.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		p4score.setForeground(new Color(255, 255, 255));
		p4score.setBounds(790, 499, 84, 14);
		contentPane.add(p4score);

		lblPlayer = new JLabel("1");
		lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer.setForeground(new Color(255, 0, 0));
		lblPlayer.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblPlayer.setBounds(858, 67, 62, 26);
		contentPane.add(lblPlayer);

		p1btn = new JLabel("");
		p1btn.setIcon(new ImageIcon(Main.class.getResource("/Image/player 1.png")));
		p1btn.setBounds(884, 312, 50, 50);
		contentPane.add(p1btn);

		label = new JLabel("");
		label.setIcon(new ImageIcon(Main.class.getResource("/Image/player 2.png")));
		label.setBounds(884, 367, 50, 50);
		contentPane.add(label);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Main.class.getResource("/Image/player 3.png")));
		label_1.setBounds(884, 429, 50, 50);
		contentPane.add(label_1);

		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Main.class.getResource("/Image/player 4.png")));
		label_2.setBounds(884, 490, 50, 50);
		contentPane.add(label_2);

		btnRestart = new JButton("Restart??");
		btnRestart.setBackground(new Color(255, 255, 255));
		btnRestart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Do you want to Restart?", "Restart!!!",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					new Main().setVisible(true);
				}
			}
		});
		btnRestart.setBounds(734, 576, 150, 60);
		contentPane.add(btnRestart);

		for (int i = 0; i < 4; i++) {
			playerPosition[i] = 0;
		}
	}

	public void FrameInMiddle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

	}

}
