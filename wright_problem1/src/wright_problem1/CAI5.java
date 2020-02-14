package wright_problem1;

import java.security.SecureRandom;
import java.util.Scanner;
public class CAI5 {
	private int [] check = new int [10];
	private double score;
	private int rand1;
	private int rand2;
	private int difficulty;
	private int type;
	
	// contains program logic
	
	public void quiz() {
		
		//default messages
		System.out.println("Please enter your difficulty level: ");
		readDifficulty();
		System.out.println();
		System.out.println("1: Addition");
		System.out.println("2: Multiplication");
		System.out.println("3: Subtraction");
		System.out.println("4: Division (Do not round)");
		System.out.println("Please enter a problem type: ");
		readProblemType();
		System.out.println();
		System.out.println("Student's Quiz loading...");
		
		//asks 10 questions
		for(int i = 0; i < 10; i++) {
		generateQuestionArgument();
		
		//call askQuestion
		askQuestion();
		
		//read response
		double sAnswer = readResponse();
		
		//check if answer is correct then calls matching display class
		check[i] = isAnswerCorrect(sAnswer);
		}
		calculateStudentScore();
		displayCompletionMethod();
		continueOrQuit();
		
	}
	
	//read difficulty 
	
	private void readDifficulty() {
		Scanner in = new Scanner(System.in);
		difficulty = in.nextInt();
	}
	
	//read operation
	
	private void readProblemType() {
		Scanner in = new Scanner(System.in);
		type = in.nextInt();
	}
	
	private double calculate() {
		
		if(type == 1) {
			return (rand1 + rand2);
		} else if(type == 2) {
			return (rand1 * rand2);
		} else if(type == 3) {
			return(rand1 - rand2);
		} else if(type == 4) {
			double temp = (double)rand1 / (double)rand2;
			System.out.println(temp);
			return temp;
		} else {
			return 0;
		}
	}
	
	private String checkType() {
		if(type == 1) {
			return "plus";
		} else if(type == 2) {
			return "times";
		} else if(type == 3) {
			return "minus";
		} else if(type == 4) {
			return "divided by";
		} else {
			return " ";
		}
	}
	//uses difficulty level to generate random numbers
	
	private void generateQuestionArgument() {
		//creates instance of random class
		SecureRandom rand = new SecureRandom();
		
		if(difficulty == 1) {
			//generates 2 random integers from 0 to 9
			rand1 = rand.nextInt(10);
			rand2 = rand.nextInt(10);
		} else if (difficulty == 2) {
			//generates 2 random integers from 0 to 99
			rand1 = rand.nextInt(100);
			rand2 = rand.nextInt(100);
		} else if (difficulty == 3) {
			//generates 2 random integers from 0 to 999
			rand1 = rand.nextInt(1000);
			rand2 = rand.nextInt(1000);
		} else if (difficulty == 4) {
			//generates 2 random integers from 0 to 9999
			rand1 = rand.nextInt(10000);
			rand2 = rand.nextInt(10000);
		}
		
	}
	
	//displays question to the screen
	
	private void askQuestion() {
		System.out.println("How much is " + rand1 + " " + checkType() + " "+ rand2 + "?");	
	}
	
	//reads students answer
	
	private double readResponse() {
		//take and store user input
		Scanner in = new Scanner(System.in);
		double answer = in.nextDouble();
		
		//return student's answer
		return answer;
		
	}
	
	//calls matching display function based on correct/incorrect input
	
	private int isAnswerCorrect(double a) {
		
		//checks if student answer is actual answer
		if(calculate() == a) {
			displayCorrectResponse();
			return 1;
		}
		else {
			displayIncorrectResponse();
			return 0;
		}
	}
	
	//random answer selection for correct answers
	
	private String randomCorrectAnswers() {
		String correct;
		SecureRandom rand = new SecureRandom();
		int rand1 = rand.nextInt(4);
		switch(rand1) {
		case 0 :
		correct = "Very good!";
		break;
		case 1 :
		correct = "Excellent!";
		break;
		case 2 :
		correct = "Nice work!";
		break;
		case 3 :
		correct = "Keep up the good work!";
		break;
		default :
		correct = "Invalid";
		}
		return correct;
	}
	
	//random answer selection for incorrect answers
	
	private String randomIncorrectAnswers() {
		String incorrect;
		SecureRandom rand = new SecureRandom();
		int rand1 = rand.nextInt(4);
		switch(rand1) {
		case 0 :
		incorrect = "No. Please try again.";
		break;
		case 1 :
		incorrect = "Wrong. Try once more.";
		break;
		case 2:
		incorrect = "Don't give up!";
		break;
		case 3:
		incorrect = "No. Keep trying.";
		break;
		default :
			incorrect = "Invalid";
		}
		return incorrect;
	}  
	
	//prints when answer is  correct
	
	private void displayCorrectResponse() {
		String rand = randomCorrectAnswers();
		System.out.println(rand);
		}
		
	//prints when answer is incorrect
		
	private void displayIncorrectResponse() {
		String rand = randomIncorrectAnswers();
		System.out.println(rand);
		}
	
	//calculate students score
	
	private double calculateStudentScore() {
		for(int i = 0; i < check.length - 1; i++) {
			if(check[i] == 1) {
				score += check[i];
			}
		}
		score /= (double)10;
		score *= 100;
		return score;
	}
	
	//check if the score is passing
	
	private int isPassing() {
		if(score >= 75) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//display completion method and score
	
	private void displayCompletionMethod() {
		System.out.println("You scored a: " + score + "%");
		if(isPassing() == 1) {
		System.out.println("Congratulations, you are ready to go to the next level!");
		}
		else {
		System.out.println("Please ask your teacher for extra help.");	
		}
	}
	
	//loop or terminate based on input
	
	private void continueOrQuit() {
		System.out.println();
		String option;
		Scanner in = new Scanner (System.in);
		System.out.println("Would you like another quiz (y/n)? ");
		option = in.nextLine();
		if(option.equalsIgnoreCase("y")) {
			quiz();
		}
		else if(option.equalsIgnoreCase("n")){
			System.exit(0);
		} else {
			System.out.println("Invalid.");
		}
	}
	//runs quiz function
	
	public static void main(String [] args) {
		CAI5 quiz5 = new CAI5();
		quiz5.quiz();
	}
}
