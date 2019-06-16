package oyun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JTextPane;


public class HumanInterface extends JFrame {


		
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8583702989767226401L;
	public static int guessInteger; 
	private JPanel contentPane;
	private JTextField playerGuess;
	private JTextField playerBullsValue;
	private JTextField playerCowsValue;
	private JTextField computerGuess;
	private JTextField computerBullsValue;
	private JTextField computerCowsValue;
	public int target;
	public static ArrayList<String> answers = new ArrayList<String>();
	public Hint playerHint=new Hint(0,0);
	public Hint computerHint=new Hint(0,0);
	boolean gameStart=false;
	private JTable table;
	boolean exception = false; 
	int plAnswer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HumanInterface frame = new HumanInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HumanInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		playerGuess = new JTextField();
		playerGuess.setEnabled(false);
		playerGuess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				playerGuess.setText("");
			}
		});
		playerGuess.setText("write your guess");
		playerGuess.setForeground(Color.BLACK);
		playerGuess.setBounds(46, 72, 266, 22);
		contentPane.add(playerGuess);
		playerGuess.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Your Guess");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(109, 13, 214, 42);
		contentPane.add(lblNewLabel);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(385, 13, 1, 315);
		contentPane.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(408, 13, 11, 180);
		contentPane.add(verticalStrut_1);
		
		JLabel Bulls = new JLabel("+");
		Bulls.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Bulls.setHorizontalAlignment(SwingConstants.CENTER);
		Bulls.setBounds(78, 106, 56, 25);
		contentPane.add(Bulls);
		
		JLabel Cows = new JLabel("-");
		Cows.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Cows.setHorizontalAlignment(SwingConstants.CENTER);
		Cows.setBounds(225, 107, 56, 22);
		contentPane.add(Cows);
		
		playerBullsValue = new JTextField();
		playerBullsValue.setEditable(false);
		playerBullsValue.setBounds(46, 136, 116, 22);
		contentPane.add(playerBullsValue);
		playerBullsValue.setColumns(10);
		
		playerCowsValue = new JTextField();
		playerCowsValue.setEditable(false);
		playerCowsValue.setBounds(196, 136, 116, 22);
		contentPane.add(playerCowsValue);
		playerCowsValue.setColumns(10);
		
		computerGuess = new JTextField();
		computerGuess.setToolTipText("");
		computerGuess.setEditable(false);
		computerGuess.setColumns(10);
		computerGuess.setBounds(471, 72, 272, 22);
		contentPane.add(computerGuess);
		
		JLabel label = new JLabel("My Guess");
		label.setFont(new Font("Tahoma", Font.BOLD, 27));
		label.setBounds(512, 13, 160, 42);
		contentPane.add(label);
		
		computerBullsValue = new JTextField();
		computerBullsValue.setEnabled(false);
		computerBullsValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				computerBullsValue.setText("");
			}
		});
		computerBullsValue.setColumns(10);
		computerBullsValue.setBounds(471, 136, 116, 22);
		contentPane.add(computerBullsValue);
		
		computerCowsValue = new JTextField();
		computerCowsValue.setEnabled(false);
		computerCowsValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				computerCowsValue.setText("");
			}
		});
		computerCowsValue.setColumns(10);
		computerCowsValue.setBounds(627, 136, 116, 22);
		contentPane.add(computerCowsValue);
		
		JLabel label_1 = new JLabel("+");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_1.setBounds(500, 106, 56, 25);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_2.setBounds(652, 106, 56, 22);
		contentPane.add(label_2);
		//*****************************************************//
		JButton rightRollButton = new JButton("Roll");
		JButton leftRollButton = new JButton("Roll");
		rightRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					computerHint.bulls= Integer.parseInt(computerBullsValue.getText());
					computerHint.cows= Integer.parseInt(computerCowsValue.getText());
				}catch (NumberFormatException nfe){
					exception = true;
				}
				if(	computerBullsValue.getText().equals("") || computerCowsValue.getText( ).equals("")) 
					JOptionPane.showMessageDialog(null, "Fill plus and minus field");				
				else if(computerHint.bulls > 4 || computerHint.bulls < 0 ||
						computerHint.cows > 4 || computerHint.cows < 0 ||
						computerHint.bulls + computerHint.cows > 4 || exception) {
						JOptionPane.showMessageDialog(null, "Your hint numbers are invalid");
						computerBullsValue.setText("");
						computerCowsValue.setText("");
						exception = false;
					}
				else {
						char[] guessElement = computerGuess.getText().toCharArray();
						for (int ans = answers.size() - 1; ans >= 0; ans--)
						{
							int tb = 0, tc = 0;
							char[] answerElement = (answers.get(ans)).toCharArray();
							for (int ix = 0; ix < 4; ix++)
							{
								if ( answerElement[ix] == guessElement[ix])
									tb++;
								else if (answers.get(ans).contains(String.valueOf(guessElement[ix])))
									tc++;
							}
							if ((tb != computerHint.bulls) || (tc != computerHint.cows))
								answers.remove(ans);
						}
						leftRollButton.setEnabled(true);
						rightRollButton.setEnabled(false);
						
						}
						if(answers.size() == 1)
						{
							JOptionPane.showMessageDialog(null, "I have win !!! your number is "+answers.get(0));
							leftRollButton.setEnabled(false);
							rightRollButton.setEnabled(false);
						}	  
						if(answers.size()== 0)
							JOptionPane.showMessageDialog(null, "Your hints are incorrect let's play again");
						else
							computerGuess.setText(answers.get(0));
					}
			});
		rightRollButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		rightRollButton.setEnabled(false);
		rightRollButton.setBounds(512, 204, 160, 58);
		contentPane.add(rightRollButton);
		///****************************************///
	
		leftRollButton.setEnabled(false);
		leftRollButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		leftRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					plAnswer = Integer.parseInt(playerGuess.getText());
				}catch (NumberFormatException nfe){
					exception = true;
				}
				if(	playerGuess.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Fill your guess ");
				}				
				else if(plAnswer > 9999 || plAnswer < 1000 ||
						hasDupes(plAnswer) || exception) {
					JOptionPane.showMessageDialog(null, "Your guessed number is invalid");
					playerGuess.setText("");
					exception = false;
				}				
				else {
				
				
				Hint a = PlusMinus.getPlusMinus(target, plAnswer);
				playerBullsValue.setText( String.valueOf(a.bulls));
				playerCowsValue.setText( String.valueOf(a.cows));
				if(a.bulls == 4) 
					{
					JOptionPane.showMessageDialog(null, "You have win !!! my number is "+answers.get(0));
					leftRollButton.setEnabled(false);
					rightRollButton.setEnabled(false);
					}
				leftRollButton.setEnabled(false);
				rightRollButton.setEnabled(true);
				}
			}
		});
		leftRollButton.setBounds(109, 204, 160, 58);
		contentPane.add(leftRollButton);
		//********************************************************//
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerGuess.setEnabled(true);
				computerCowsValue.setEnabled(true);
				computerBullsValue.setEnabled(true);
				leftRollButton.setEnabled(true);
				rightRollButton.setEnabled(false);
				playerBullsValue.setText( "");
				playerCowsValue.setText( "");
				computerBullsValue.setText( "");
				computerCowsValue.setText( "");
				playerGuess.setText("");
				Random gen= new Random();				
				while(hasDupes(target= (gen.nextInt(9000) + 1000)));
				//*********************************//
				int number;
				for(int i = 1; i < 10; i++)
					for(int j = 0; j < 10; j++)
						for(int k = 0; k < 10; k++)
							for(int l = 0; l < 10; l++)
								if(i!= j && i != k && i != l && j != k && j != l && k != l)
								{
									number = i*1000 + j*100 + k*10 + l;
									answers.add(Integer.toString(number));
								}
				Collections.shuffle(answers);
				computerGuess.setText(answers.get(0));
			}
			
		});
		startButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		startButton.setBounds(583, 270, 160, 58);
		contentPane.add(startButton);
		
		table = new JTable();
		table.setBounds(62, 258, 1, 1);
		contentPane.add(table);
		
		JTextPane txtpnKurallarRakamlar = new JTextPane();
		txtpnKurallarRakamlar.setEditable(false);
		txtpnKurallarRakamlar.setText("Rules:\r\n1- I've got a 4-digit number that's different from each other, and you should also keep a number.\r\n2- The left side for your guess and the right side is mine.\r\n3- I'll give you a hint as I expect the same from you; If the digits are in the right place +, if the locations are different -.\r\n4- Who finds first will win the game, let's start the game!");
		txtpnKurallarRakamlar.setBounds(46, 270, 519, 116);
		contentPane.add(txtpnKurallarRakamlar);
		
		
	}
	
	
 
	public static boolean hasDupes(int num){
		boolean[] digs = new boolean[10];
		while(num > 0){
			if(digs[num%10]) return true;
			digs[num%10] = true;
			num/= 10;
		}
		return false;
	}
}
