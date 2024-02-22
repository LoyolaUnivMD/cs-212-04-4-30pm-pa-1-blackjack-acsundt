//pa1
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;  // Import the Scanner class
import java.util.Random;

public class Hand{

    public static int numberCards;
    public int counter = 0;
    public static String[] handContent = new String[10];
    public int [] cardValues;
    public Hand(int numberCards, String[] handContent){
        this.numberCards = numberCards;
        this.handContent = handContent;
    }
    public int total(){
        int totalValues = 0;
        int i = 0;
        while (!Objects.equals(handContent[i],null)){
            try {
                totalValues = totalValues + Integer.parseInt(handContent[i]);
                i++;
            }
            catch (NumberFormatException e){
                if (totalValues < 11) {
                    totalValues += 10;
                } else {
                    totalValues += 1;
                }
                i++;
            }
        }
        return totalValues;
    }
    public void displayContent(String user){
        int i = 0;
        System.out.println("");
        System.out.print(user+": ");
        do {
//            System.out.println(Arrays.toString(handContent[i].toCharArray()));
            String displayData = handContent[i].replace(",", "").replace("[", " ").replace("]", " ");

            System.out.print(displayData);
            i++;
        }
        while (!Objects.equals(handContent[i],null));
    }

    public String cardType(String [] newCard){
        Random random = new Random();
        int randomNum = random.nextInt(4);
        if (randomNum == 0){
            newCard[1] = "H";
            return newCard[1];
        } else if (randomNum == 1) {
            newCard[1] = "D";
            return newCard[1];
        } else if (randomNum==2) {
            newCard[1]="S";
            return newCard[1];
        } else if (randomNum==3) {
            newCard[1]="C";
            return newCard[1];
        }
        return null;
    }
    public void addContent(){
        Random random = new Random();
        String [] newCard = new String [2];
        int randomNum = random.nextInt(8);
        if (randomNum <= 3){
            newCard[0] = String.valueOf(random.nextInt(2,11));
            newCard[1] = cardType(newCard);

        } else if (randomNum <=6) {
            newCard[1] = cardType(newCard);
            if (randomNum == 6){
                newCard[0] = "J";
            } else if (randomNum == 5) {
                newCard[0] = "K";
            } else if (randomNum==4) {
                newCard[0] = "Q";
            }
        } else if (randomNum==7) {
            newCard[0] = "A";
            newCard[1] = cardType(newCard);
        }
        numberCards++;
        handContent[counter] = Arrays.toString(newCard);
        counter++;
//        System.out.println(handContent[counter]+"000");
    }






    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Let's Play BlackJack! ");

        Hand player = new Hand(numberCards, handContent);
        Hand dealer = new Hand(numberCards, handContent);
        player.addContent();
        player.addContent();
        dealer.addContent();
        dealer.displayContent("Dealer");
        player.displayContent("Player");
        System.out.println(" ");
        System.out.println("Hit (1) or Stay (2)");
        String choice = input.next();
        while(player.total() < 21 && !Objects.equals(choice,"2")){
            player.addContent();
            player.displayContent("Player");
            dealer.displayContent("Dealer");
            System.out.println("");
            System.out.println("Hit (1) or Stay (2)");
            choice = input.next();
        }
        if (player.total() > 21){
            player.displayContent("Player");
            System.out.println(player.total());
            System.out.println("Dealer wins!");
        } else if (Objects.equals(choice,"2")) {
            while (dealer.total()<17){
                dealer.addContent();
                player.displayContent("Player");
                dealer.displayContent("Dealer");
            }
        }
        else{
            while (dealer.total()<17){
                dealer.addContent();
                player.displayContent("Player");
                dealer.displayContent("Dealer");
            }
            if (dealer.total()>21){
                System.out.println("Player wins!");
            } else if (dealer.total()>player.total()) {
                System.out.println("Dealer wins!");
            }
        }
    }
}
