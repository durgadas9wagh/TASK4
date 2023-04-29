package test2;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExam {
    private static final int MAX_QUESTIONS = 10;  // maximum number of questions in exam
    private static final int QUESTION_TIME_LIMIT = 1;  // time limit for each question in seconds
    private static final int EXAM_TIME_LIMIT = MAX_QUESTIONS * QUESTION_TIME_LIMIT;  // total time limit for exam in seconds
    private static final String[] QUESTIONS = {  // sample questions
        "What is the capital of India?",
        "What year did World War II end?",
        "Who painted the Mona Lisa?",
        "What is the largest planet in our solar system?",
        "What is the smallest country in the world by land area?",
        "Who wrote the  'National Anthem of India'?",
        "What is the chemical symbol for gold?",
        "What is the highest mountain in Africa?",
        "What is the world's largest mammal?",
        "What is the name of the currency used in Japan?"
    };
    private static final String[][] ANSWERS = {  // sample answers
        {"Hyderabad", "Delhi", "Mumbai", "Pune"},
        {"1945", "1918", "1939", "1941"},
        {"Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
        {"Jupiter", "Saturn", "Mars", "Venus"},
        {"Vatican City", "Monaco", "Nauru", "Liechtenstein"},
        {"S. Bose", "Jane Austen", "M. Gandhi", "Rabindranath Tagore"},
        {"Au", "Ag", "Cu", "Fe"},
        {"Mount Kilimanjaro", "Mount Everest", "Mount McKinley", "Mount Kilimanjaro"},
        {"Blue whale", "Elephant", "Giraffe", "Hippopotamus"},
        {"Yen", "Won", "Dollar", "Euro"}
    };
    private static final int[] CORRECT_ANSWERS = {1,1,1,1,1,1,1,1,1,1};  // index of the correct answer for each question

    private String username;
    private String password;

    private int[] selectedAnswers= {4,4,4,4,4,4,4,4,4,4};
    private Timer timer;
    private int remainingTime=30;

    public OnlineExam(String username, String password) {
        this.username = username;
        this.password = password;
        this.selectedAnswers = new int[MAX_QUESTIONS];
    }

    public void updateProfile(String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void startExam() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the online exam, " + username + "!");
        System.out.println("You have " + EXAM_TIME_LIMIT + " seconds to complete " + MAX_QUESTIONS + " multiple-choice questions.");
        System.out.println("Each question has a time limit of " + QUESTION_TIME_LIMIT + " seconds.");

        timer = new Timer();
        System.out.println("Remaining time in seconds: ");
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() 
            {
                remainingTime--;
                if (remainingTime == 0) {
                    endExam();
                } else {
                	 System.out.print("Remaining time in seconds: "+remainingTime+"\r");
                }
            }
        }, 1, 1000);

        for (int i = 0; i < MAX_QUESTIONS; i++) 
        {
            System.out.println("Question " + (i + 1) + ": " + QUESTIONS[i]);
            for (int j = 0; j < ANSWERS[i].length; j++) 
            {
                System.out.println((j + 1) + ") " + ANSWERS[i][j]);
            }
            int selectedAnswer = -1;
            do {
                System.out.print("Enter your answer (1-" + ANSWERS[i].length + "): ");
                for(int k=0;k<=remainingTime;k++)
                {
                	
                   // System.out.print("Remaining time in seconds: " + remainingTime+"\r");

                }
                
                selectedAnswer = scanner.nextInt();
            } 
            while (selectedAnswer < 1 || selectedAnswer > ANSWERS[i].length);
            selectedAnswers[i] = selectedAnswer ;
            remainingTime += QUESTION_TIME_LIMIT;
        }

        endExam();
    }

    private void endExam() {
        timer.cancel();
        int score = 0;
        for (int i = 0; i < MAX_QUESTIONS; i++) {
            if (selectedAnswers[i] == CORRECT_ANSWERS[i]) {
                score++;
            }
        }
        System.out.println("Time's up!");
        System.out.println("Your score is " + score + " out of " + MAX_QUESTIONS);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        OnlineExam exam = new OnlineExam(username, password);

        int choice;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Update profile");
            System.out.println("2. Update password");
            System.out.println("3. Start exam");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your new username: ");
                    String newUsername = scanner.next();
                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.next();
                    exam.updateProfile(newUsername, newPassword);
                    System.out.println("Profile updated successfully!");
                    break;
                case 2:
                    System.out.print("Enter your new password: ");
                    newPassword = scanner.next();
                    exam.updatePassword(newPassword);
                    System.out.println("Password updated successfully!");
                    break;
                case 3:
                    exam.startExam();
                    break;
                case 4:
                    System.out.println("Exit successfully !");
                    System.out.println("thank you !");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (choice != 4);
    }
}
