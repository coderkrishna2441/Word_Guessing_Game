import java.util.Scanner;

public class Main {

    static String randomWord;
    static boolean isGuessed;
    static int remaining_guesses=10;
    static char a;
    static boolean won=false;
    static String hidden_word="";
    static String[] movies ={"morbius","moonknight","eternals","wandavision","venom","deadpool","logan","daredevil" ,
                            "avengers","elektra","flash","aquaman","superman","antman","spiderman"};
    static String[] animals ={"lion","tiger","monkey","giraffe","deer","cow","goat","cheetah","buffalo","camel",
                                "bear","panda","elephant","rabbit","hippopotamus"};
    static String[] food ={"burger","sandwich","pizza","waffle","pancake","pastry","jalebi","samosa","manchurian","noodles",
                            "biryani","dhokla","vadapav","paratha","panipuri"};
    static String[] places ={"adipur","rajkot", "surat","ahmedabad","gandhinagar","kutch","mumbai","delhi","kolkata","goa",
                            "junagadh","pune","banglore","hyderabad","indore"};

    static void printRules(){
        System.out.println("The rules of the game are:-");
        System.out.println("1. Their will be a word randomly selected from our database you need to guess the word.");
        System.out.println("2. The vowels of the word would be automatically seen by you.");
        System.out.println("3. You will have 10 tries to guess the word.");
        System.out.println("4. You will get a hint after 5 wrong guesses if you want,but if you take a hint " +
                "your remaining guess will reduce by three.");
    }
    // Start method generates a random word from the array
    static void start(){
        int i = (int) (Math.random()*15);
        int x;
        do {
            System.out.println("What do you want to guess?");
            System.out.println("Enter 1 for guessing movie");
            System.out.println("Enter 2 for guessing animal");
            System.out.println("Enter 3 for guessing food dish");
            System.out.println("Enter 4 for guessing city");
            Scanner sc=new Scanner(System.in);
            x= sc.nextInt();
        }while(x>5&&x<0);

        if(x==1){
            System.out.println("Nice,you have selected a random movie");
           randomWord=movies[(int)(Math.random()*15)];
        }
        if(x==2){
            System.out.println("Nice,you have selected a random animal");
            randomWord=animals[(int)(Math.random()*15)];
        }
        if(x==3){
            System.out.println("Nice,you have selected a random food item");
            randomWord=food[(int)(Math.random()*15)];
        }
        if(x==4){
            System.out.println("Nice,you have selected a random city");
            randomWord=places[(int)(Math.random()*15)];
        }

        System.out.println("The word has "+randomWord.length()+" letters.");
    }
    //Method to Display vowels and guessed letters
    static void display(){
        for(int i=0;i<randomWord.length();i++){
            if(randomWord.charAt(i)=='a'){
                hidden_word += "a";
            }
            else if(randomWord.charAt(i)=='e') {
                hidden_word += "e";
            }
            else if(randomWord.charAt(i)=='i') {
                hidden_word += "i";
            }
            else if(randomWord.charAt(i)=='o') {
                hidden_word += "o";
            }
            else if(randomWord.charAt(i)=='u') {
                hidden_word += "u";
            }
            else{
                hidden_word+="_";
            }
        }
        System.out.println("You have "+remaining_guesses+" guesses left.");
    }
}


class WordGuessing extends Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printRules();
        start();
        display();
        while(remaining_guesses>0 || (!won)){
            System.out.println("Current word "+ hidden_word);
            System.out.println("Guess any letter");
            String guess = sc.next().toLowerCase();
            if (randomWord.contains(guess)) {
                System.out.println("Correct!");
                //update hidden word with guessed letter
                StringBuffer sb = new StringBuffer(hidden_word);
                for (int i = 0; i < randomWord.length(); i++) {
                    if (randomWord.charAt(i) == guess.charAt(0)) {
                        sb.setCharAt(i, guess.charAt(0));
                    }
                }
                hidden_word = sb.toString();
            } else {
                remaining_guesses--;
                System.out.println("Wrong! You have " + remaining_guesses + " guesses remaining.");
            }
            if (hidden_word.equals(randomWord)) {
                won = true;
                System.out.println("Congratulations, you won! The word was " + randomWord);
                break;
            }
            if (remaining_guesses==0) {
                break;

            }
            if(remaining_guesses==5){
                System.out.println("Do you want a hint");
                System.out.println("Enter y for yes or any other key for no");
                sc.nextLine();
                String ch=sc.nextLine();
                char c=ch.charAt(0);
                if(c=='y'||c=='Y'){
                    System.out.println("You have chosen to take a hint");
                    for(int i=0;i<hidden_word.length();i++){
                        if(hidden_word.charAt(i)=='_'){
                            System.out.println("Your remaining guesses are reduced by 3!");
                            System.out.println("Now you have " + remaining_guesses + " guesses remaining.");
                            StringBuffer sb=new StringBuffer(hidden_word);
                            sb.setCharAt(i,randomWord.charAt(i));
                            hidden_word=sb.toString();
                            remaining_guesses-=3;
                            break;
                        }
                    }
                }
                else{
                    System.out.println("You have chosen not to take a hint");
                }
            }
        }
        if (!won) {
            System.out.println("Sorry, you lost. The word was " + randomWord);
        }
    }
}
