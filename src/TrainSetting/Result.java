package TrainSetting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Result extends JFrame {
	private JPanel p1,p2;
	private String[] result = {"열차","출발역","도착역","좌석현황","가격"};
	public Result(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
//		setLocation(800, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.setBorder(BorderFactory.createEmptyBorder(10, 0, -4, 0));
		JLabel[] labels = new JLabel[result.length];
		for (int i = 0; i < result.length; i++) {
			labels[i] = new JLabel(result[i]);
			labels[i].setPreferredSize(new Dimension(85, 25));
			labels[i].setBackground(Color.GRAY);
			labels[i].setForeground(Color.WHITE);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setOpaque(true);
	        p1.add(labels[i]);
		}
		p2 = new JPanel();
		p2.setBorder(new LineBorder(Color.GRAY));
		JScrollPane js = new JScrollPane(p2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0));
		
		add(p1, BorderLayout.NORTH);
		add(js,BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Result("조회 결과", 580, 500);
	}

}