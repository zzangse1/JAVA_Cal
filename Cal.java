package 계산기;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cal extends JFrame {
//	JButton[] buttons;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cal app = new Cal("test");
	}

	public Cal(String s) {
		super(s);
		init();
	}

	ArrayList<String> numbers = new ArrayList<>();
	String[] btn = { "AC", "/", "x", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0" };
	// 456 89 10 12 13 14 15
	JButton[] btns = new JButton[btn.length];
	JTextField textbox = new JTextField();
	String num2;

	private void init() {
		Container c = getContentPane();
		setLayout(null);
		JPanel result = new JPanel();
		JPanel button = new JPanel();
		Font f1, f2;
		f1 = new Font("Arial", Font.BOLD, 50);
		f2 = new Font("Arial", Font.BOLD, 20);
		textbox.setEditable(false);
		textbox.setHorizontalAlignment(JTextField.RIGHT); // 오른쪽 정렬
		result.setBounds(8, 10, 270, 100);
		button.setBounds(8, 120, 270, 235);
		c.add(textbox);
		c.add(result);
		c.add(Box.createRigidArea(new Dimension(0, 5)));
		c.add(button);
		result.add(textbox);
		result.setLayout(new GridLayout(1, 1, 0, 0));
		c.add(Box.createRigidArea(new Dimension(0, 5)));

		button.setLayout(new GridLayout(4, 4, 7, 7));
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton(btn[i]);
			button.add(btns[i]);
			btns[i].setBackground(Color.GRAY);
			btns[i].setForeground(Color.WHITE);
			btns[i].setFont(f2);
			btns[i].setBorderPainted(false);
		}

		for (int i = 0; i < btns.length; i++) {
			int clickBtn = i;
			btns[i].addActionListener(event -> {
				clickBtn(clickBtn);
			});
		}

		textbox.setFont(f1);

		setTitle("계산기");
		setSize(300, 400);
		setLocation(300, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	String str;
	String num = "";
	String resultStr;
	String check = "";
	boolean switchFlag = false;
	boolean eqFlag = false;
	int count = 0;

	private void clickBtn(int click) {
		resultFlag = true;
		str = btn[click]; // str에 btn[?]값을 저장
		if (click >= 4 && click <= 6 || click >= 8 && click <= 10 || click >= 12 && click <= 15) {
			num += str;
			resultStr = num;
			check += btn[click];
			textbox.setText(check);
			switchFlag = true;// 처음에 연산자가 못 들어가게 제어
			System.out.println("numbers: " + numbers);
		}
		if (btn[click] == "AC") {
			num = "";
			check = "";
			clear();
		}
		if (eqFlag == true) {
			if (btn[click].equals("=")) {
				if (switchFlag == false) {
					numbers.add(btn[click]);
					textbox.setText("Error!!!");
					System.out.println(numbers);
					result();
					eqFlag = false;
				} else {
					numbers.add(resultStr);
					numbers.add(btn[click]);
					textbox.setText("Error!!!");
					System.out.println(numbers);
					result();
					eqFlag = false;
				}

			}

		}

		if (switchFlag == true) {
			switch (click) {
			case 1: // ====> /
				num = "";
				check += btn[click];
				textbox.setText(check);
				numbers.add(resultStr);
				numbers.add(btn[1]);
				System.out.println(numbers);
				switchFlag = false;
				eqFlag = true;
				break;
			case 2: // ====> x
				num = "";
				check += btn[click];
				textbox.setText(check);
				numbers.add(resultStr);
				numbers.add(btn[2]);
				System.out.println(numbers);
				switchFlag = false;
				eqFlag = true;
				break;
			case 7: // ====> +
				num = "";
				check += btn[click];
				textbox.setText(check);
				numbers.add(resultStr);
				numbers.add(btn[7]);
				System.out.println(numbers);
				switchFlag = false;
				eqFlag = true;
				break;
			case 11: // ====> x
				num = "";
				check += btn[click];
				textbox.setText(check);
				numbers.add(resultStr);
				numbers.add(btn[11]);
				System.out.println(numbers);
				switchFlag = false;
				eqFlag = true;
				break;
			}
		}

	}

	private void clear() {
		num = "";
		check = "";
		numbers.clear();
		System.out.println(numbers);
		textbox.setText(num);
	}

	boolean resultFlag = true;
	String str2;

	private void result() {
		double a, b, result = 0;
		String dap;
		resultStr = "";
		// 마지막이 연산자인지 확인
		if (numbers.get(numbers.size() - 2) == "+" || numbers.get(numbers.size() - 2) == "-"
				|| numbers.get(numbers.size() - 2) == "x" || numbers.get(numbers.size() - 2) == "/") {
			//연산자 삭제
			numbers.remove(numbers.size() - 2);
			System.out.println("연산작 삭제 확인: " + numbers);
		}
		numbers.remove("=");
		numbers.remove("");
		String zero;
		zero = numbers.get(1);
		if (!zero.equals("+") && !zero.equals("-") && !zero.equals("x") && !zero.equals("/")) {
			System.out.println("numbers before: " + numbers);
			String tmp, mainVal;
			tmp = numbers.get(1);
			mainVal = tmp.substring(1);
			mainVal = numbers.get(0) + mainVal;
			numbers.add(0, mainVal);
			numbers.remove(2);
			numbers.remove(1);
			System.out.println("numbers after: " + numbers);
		} 
		boolean md_or_pm=true;
		// 3+1*2+4/2-1=6
		while (numbers.size() > 1) {// 무한루프 해
			for (int idx = 1; idx < numbers.size();) {
				String s = numbers.get(idx);
				a = Double.parseDouble(numbers.get(idx - 1));
				b = Double.parseDouble(numbers.get(idx + 1));
				boolean firstCal = false;
				if(md_or_pm) {
					if (s.equals("x")) {
						firstCal = true;
						result = a * b;
					} else if (s.equals("/")) {
						firstCal = true;
						result = a / b;
					}
				}else {
					if (s.equals("+")) {
						firstCal = true;
						result = a + b;
					} else if (s.equals("-")) {
						firstCal = true;
						result = a - b;
					}
				}
				if (firstCal) {
					dap = Double.toString(result);
					numbers.remove(idx - 1);
					numbers.remove(idx);
					numbers.remove(idx - 1);
					numbers.add(idx - 1, dap);
					textbox.setText(dap);
					System.out.println("연산 후: " + numbers);
				}

				if (!s.equals("x") && !s.equals("/")) {
					idx += 2;
				}
			}
			
			md_or_pm = false;
		}
		check = numbers.get(0);
	}
}
