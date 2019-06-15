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
	private JTextField txtRules;
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
		setBounds(100, 100, 814, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		playerGuess = new JTextField();
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
		computerGuess.setEditable(false);
		computerGuess.setColumns(10);
		computerGuess.setBounds(471, 72, 272, 22);
		contentPane.add(computerGuess);
		
		JLabel label = new JLabel("My Guess");
		label.setFont(new Font("Tahoma", Font.BOLD, 27));
		label.setBounds(512, 13, 160, 42);
		contentPane.add(label);
		
		computerBullsValue = new JTextField();
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
		JButton rollButton = new JButton("Roll");
		rollButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(computerBullsValue.getText()) > 4 || Integer.parseInt(computerBullsValue.getText()) < 0 ||
						Integer.parseInt(computerCowsValue.getText()) > 4 || Integer.parseInt(computerCowsValue.getText()) < 0 ||
						computerHint.bulls + computerHint.cows > 4) {
						JOptionPane.showMessageDialog(null, "Your hint numbers are invalid");
						computerBullsValue.setText("");
						computerCowsValue.setText("");
					}
				else if(Integer.parseInt(playerGuess.getText()) > 9999 || Integer.parseInt(playerGuess.getText()) < 1000 ||
						hasDupes(Integer.parseInt(playerGuess.getText()))) {
					JOptionPane.showMessageDialog(null, "Your guessed number is invalid");
					playerGuess.setText("");
				}
				else {
				
				int plAnswer = Integer.parseInt(playerGuess.getText());
				Hint a = PlusMinus.getPlusMinus(target, plAnswer);
				playerBullsValue.setText( String.valueOf(a.bulls));
				playerCowsValue.setText( String.valueOf(a.cows));
				if(a.bulls == 4) JOptionPane.showMessageDialog(null, "You have win !!! my number is "+answers.get(0));
				//**********************//
				char[] guessElement = computerGuess.getText().toCharArray();
				computerHint.bulls= Integer.parseInt(computerBullsValue.getText());
				computerHint.cows= Integer.parseInt(computerCowsValue.getText());
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
				  if(answers.size() == 1)
					  JOptionPane.showMessageDialog(null, "I have win !!! your number is "+answers.get(0));
				
				  computerGuess.setText(answers.get(0));
			}}
		});
		rollButton.setBounds(337, 204, 160, 58);
		contentPane.add(rollButton);
		//********************************************************//
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBullsValue.setText( "");
				playerCowsValue.setText( "");
				computerBullsValue.setText( "write pluses");
				computerCowsValue.setText( "write minus");
				playerGuess.setText("start to write your guess here");
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
		
		txtRules = new JTextField();
		txtRules.setText("Rules:");
		txtRules.setBounds(46, 203, 266, 125);
		contentPane.add(txtRules);
		txtRules.setColumns(10);
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
