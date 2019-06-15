package oyun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BullsAndCows {
	public static ArrayList<String> answers = new ArrayList<String>();
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args){
		int number;
		System.out.println("Bulls and Cows");
		System.out.println("==============");
		System.out.println();
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
		 while (answers.size() > 1)
         {
             String guess = answers.get(0);
             char[] guessElement = guess.toCharArray();
             System.out.println("My guess is"+guess+". How many bulls, cows? ");
             int bulls, cows;
             bulls = input.nextInt();
             cows = input.nextInt();
            	 //System.out.println("Sorry, I didn't understand that. Please try again.");
             //else
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
                     if ((tb != bulls) || (tc != cows))
                         answers.remove(ans);
                 }
         }
         if (answers.size() == 1)
        	 System.out.println("Hooray! The answer is "+answers.get(0)+"!" );
         else
        	 System.out.println("No possible answer fits the scores you gave.");
	}
}
