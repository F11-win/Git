package TrainSetting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TrainReservation extends JFrame implements MouseListener, ListSelectionListener, ActionListener {
   private JPanel p1, p2, p3;
   private JLabel lblMap;
   private JLabel lblStart, lblArrive, lblTrain;
   private JButton btnInq;
   private JLabel lblchkStart;
   private JLabel[] labels;
   private DefaultListModel<String> m;
   private int []count =new int[5];
   private String[] trains = { "전체", "KTX", "새마울호", "무궁화호" };
   private String[] stations = { "서울역", "대전역", "대구역", "광주역", "부산역" };
   private String[] points = { "출발역", "도착역" };
   private JLabel lblchkArrive;
   private JPanel cenpanel;
   private JList<String> list;
   private Object lblObj;

   public TrainReservation(String title, int width, int height) {
      setTitle(title);
      setSize(width, height);
      setLocationRelativeTo(this);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      cenpanel = new JPanel(new BorderLayout());

      cenpanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));// 빈공간의 border 설정
      p1 = new JPanel();

      ImageIcon icon = new ImageIcon("Images/TrainMap.jpg");
      lblMap = new JLabel(icon);
      lblMap.setLayout(null);
      lblMap.addMouseListener(this);

      labels = new JLabel[stations.length]; // 역 이름 라벨 생성
      for (int i = 0; i < stations.length; i++) {
         labels[i] = new JLabel(stations[i]);
         labels[i].addMouseListener(this);
         lblMap.add(labels[i]); // 라벨 붙이기
      }

      m = new DefaultListModel<>(); // 출발역, 도착역 리스트 생성
      for (int i = 0; i < points.length; i++) {
         m.addElement(points[i]);
      }
      list = new JList<>(m);
      list.setSize(50, 40);
      list.setVisible(false);
      list.setBorder(new LineBorder(new Color(0x9999FF)));
      list.addListSelectionListener(this);
      list.getSelectionModel().setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
      lblMap.add(list);

      // 역 이름 라벨 붙이기
      labels[0].setBounds(60, 60, 40, 15); // 서울역 라벨 크기, 위치 정하기
      labels[1].setBounds(100, 130, 40, 15); // 대전역 라벨
      labels[2].setBounds(165, 160, 40, 15); // 대구역 라벨
      labels[3].setBounds(60, 200, 40, 15); // 광주역 라벨
      labels[4].setBounds(190, 200, 40, 15); // 부산역 라벨

      p1.add(lblMap);

      p2 = new JPanel();
      p2.setLayout(new GridLayout(3, 2, 10, 10));
      lblStart = new JLabel("출발역 : ");
      lblArrive = new JLabel("도착역 : ");
      lblTrain = new JLabel("열차종류 : ");

      lblchkStart = new JLabel("서울역");
      lblchkArrive = new JLabel("부산역");
      JComboBox<String> comboTrain = new JComboBox<String>(trains);
      p2.add(lblStart);
      p2.add(lblchkStart);
      p2.add(lblArrive);
      p2.add(lblchkArrive);
      p2.add(lblTrain);
      p2.add(comboTrain);

      p3 = new JPanel();
      p3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

      btnInq = new JButton("열차조회");
      btnInq.addActionListener(this);

      p3.add(btnInq);

      add(cenpanel);
      cenpanel.add(p1, BorderLayout.NORTH);
      cenpanel.add(p2, BorderLayout.CENTER);
      cenpanel.add(p3, BorderLayout.SOUTH);

     
      
      setVisible(true);
   }

   public static void main(String[] args) {
      new TrainReservation("승차권예매", 300, 480);
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      Object obj = e.getSource();
      if (obj == lblMap)
         list.setVisible(false);
   }

   @Override
   public void mousePressed(MouseEvent e) {
      lblObj = e.getSource();
      if (lblObj == labels[0]) { // 서울역
         list.setVisible(true);
         list.setLocation(70, 80);
      } else if (lblObj == labels[1]) { // 대전역
         list.setVisible(true);
         list.setLocation(110, 140);
      } else if (lblObj == labels[2]) { // 대구역
         list.setVisible(true);
         list.setLocation(175, 170);
      } else if (lblObj == labels[3]) { // 광주역
         list.setVisible(true);
         list.setLocation(70, 210);
      } else if (lblObj == labels[4]) { // 부산역
         list.setVisible(true);
         list.setLocation(170, 210);
      }

   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      Object obj = e.getSource();
      
      for (int i = 0; i < stations.length; i++) {
         //count는 전역변수
         if(lblObj==labels[i]) {
            if (list.getSelectedValue() == "출발역") {
               for (int j = 0; j < 5; j++) {
               if(count[j]==1) {
                  labels[j].setForeground(Color.black);
                  count[j]=0;
               }
            }
               lblchkStart.setText(labels[i].getText()); //출발역이 선택되면 라벨의 텍스트와 색상 빨간색으로 바꿈
               labels[i].setForeground(Color.RED);
               count[i]=1;
            } 
            
            else if (list.getSelectedValue() == "도착역") {
               for (int j = 0; j < 5; j++) {
               if(count[j]==2) {
                  labels[j].setForeground(Color.black);
                  count[j]=0;
               }
            }
               lblchkArrive.setText(labels[i].getText());
               labels[i].setForeground(Color.BLUE);
               count[i]=2;
            }
            if(list.getSelectedValue()==lblchkStart.getText()) {
               
            }
         }
//         if(lblObj==labels[i]) {
//            JOptionPane.showMessageDialog(null, "이미 지정하신 역입니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
//         }
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if (obj == btnInq) {
         dispose();
         new Result("조회 결과", 580, 500);
      }
   }

}