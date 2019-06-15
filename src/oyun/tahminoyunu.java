package oyun;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
 
public class tahminoyunu{
	public static Hint result=new Hint(0,0);
	public static void main(String[] args){
		Random gen= new Random();
		int target= 0;
		while(hasDupes(target= (gen.nextInt(9000) + 1000)));
		String targetStr = target +"";
		boolean guessed = false;
		//Scanner input = new Scanner(System.in);
		int guesses = 0;
		do{
			System.out.print("Guess a 4-digit number with no duplicate digits: ");
			int guess;
			try{
				guess =1234;// input.nextInt();
				if(hasDupes(guess) || guess < 1000) continue;
			}catch(InputMismatchException e){
				continue;
			}
			guesses++;
			result = PlusMinus.getPlusMinus(guess,target);
			if(result.bulls == 4){
				guessed = true;
			}else{
				System.out.println(result.cows+" Cows and "+result.bulls+" Bulls.");
			}
		}while(!guessed);
		System.out.println("You won after "+guesses+" guesses!");
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


