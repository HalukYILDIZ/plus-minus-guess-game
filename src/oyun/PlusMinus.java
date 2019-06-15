package oyun;

public class PlusMinus {
	public static Hint getPlusMinus(int guess,int answer) {
		Hint plusMinus=new Hint(0,0);
		String guessStr=Integer.toString(guess);
		String targetStr=Integer.toString(answer);
		int digitNumber=(guessStr).length();
		for(int i= 0;i < digitNumber;i++){
			if(guessStr.charAt(i) == targetStr.charAt(i)){
				plusMinus.bulls++;
			}else if(targetStr.contains(guessStr.charAt(i)+"")){
				plusMinus.cows++;
			}
		}
		return plusMinus;
		
	}
}
