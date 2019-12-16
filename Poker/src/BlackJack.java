import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class BlackJack {
	private static ArrayList<Card> cards = new ArrayList<Card>(); 
	 private static ArrayList<Card> cardsComputer = new ArrayList<Card>();
	 private static ArrayList<Card> cardsPlayer = new ArrayList<Card>(); 
	 
	 
	 public static void main(String[] args){
	  System.out.println("Black jack");
	  System.out.println("*****************");
	  System.out.println("A=1,2-10=2-10,J、Q、K=10");
	  reset();
	  System.exit(0);
	 }
	 
	 public static int sendCardInit(int id){
	  int counter = 1;
	  int point = 0;
	  if(id == 1){
	   point = sendCard(id,counter)+sendCard(id,counter+1);
	   System.out.println("players total point: "+point);
	  }else{
	   int x = sendCard(id,counter);
	   counter = counter +1;
	   int y = sendCard(id,counter);
	   point = x+y;
	   System.out.println("computer's chance of point: "+(point-x+1)+"~"+(point-x+10));
	   //System.out.println(point);
	  }
	  return point;
	 }
	 
	 
	 public static int sendCard(int id,int counter){
	  //printCards();
	  int sum = 0;
	  Card curr = cards.get(0);
	  if(id == 0){
	   if(counter == 1){
	    System.out.println("the number of card"+counter+"card: *");
	    sum = sum +curr.getValue();
	    //System.out.println(sum);
	    //System.out.println(curr.getIconMask()+curr.getMask());
	   }else{
	    sum = sum +curr.getValue();
	    System.out.print("the number of"+counter+"card: ");
	    System.out.println(curr.getIconMask()+curr.getMask());
	   }
	   cardsComputer.add(cards.get(0));
	  }else{
	   sum = sum +curr.getValue();
	   System.out.print("the number of"+counter+"card: ");
	   System.out.println(curr.getIconMask()+curr.getMask());
	   cardsPlayer.add(cards.get(0));
	  }
	  cards.remove(0);
	  return sum;
	 }
	 
	 
	 public static int extraCard(int id, int counter,int point){
	  int sum = point;
	  System.out.print("the number of"+(counter+2)+"card: ");
	  System.out.println(cards.get(0).getIconMask()+cards.get(0).getMask());
	  sum = sum +cards.get(0).getValue();
	  if(id == 0){
	   cardsComputer.add(cards.get(0));
	  }else{
	   cardsPlayer.add(cards.get(0));
	  }
	  cards.remove(0);
	  return sum;
	 }
	 
	 public static void printCards(){
	  for(int i=0; i<cards.size();i++){
	   System.out.print(cards.get(i).getIconMask()+cards.get(i).getMask()+" ;");
	  }
	 }
	 
	 public static void init(){
	  initMethod(1);
	  initMethod(2);
	  initMethod(3);
	  initMethod(4);
	 }
	 
	 public static void initMethod(int icon){
	  int index = 0;
	  String iconMask= "";
	 

	  if(icon == 1){
	   index = 0;
	   iconMask = "";
	  }else if(icon == 2){
	   index = 13;
	   iconMask = "";
	  }else if(icon == 3){
	   index = 26;
	   iconMask = "";
	  }else if(icon == 4){
	   index = 39;
	   iconMask = "";
	  }
	  
	  for(int i = index;i < index+13;i++){
	   if(i == index+0){
	    Card  c = new Card("A",i-index+1,icon,iconMask,true);
	    cards.add(c);
	   }else if(i >index && i <10+index){
	    Card c =new Card(String.valueOf(i+1-index),i-index+1,icon,iconMask,true);
	    cards.add(c);
	   }else if(i >=10+index && i <13+index){
	    if(i == 10+index){
	     Card c =new Card("J",10,icon,iconMask,true);
	     cards.add(c);
	    }else if(i == 11+index){
	     Card c =new Card("Q",10,icon,iconMask,true);
	     cards.add(c);
	    }else if(i == 12+index){
	     Card c =new Card("K",10,icon,iconMask,true);
	     cards.add(c);
	    }
	   }
	  }
	 }
	 
	 public static void printComputer(){
	  System.out.println("computer's card: ");
	  for(int j =0; j< cardsComputer.size();j++){
	   System.out.print("the number of"+(j+1)+"card: ");
	   System.out.println(cardsComputer.get(j).getIconMask()+cardsComputer.get(j).getMask());
	  }
	 }
	 
	 public static void printPlayer(){
	  System.out.println("player's card: ");
	  for(int j =0; j< cardsPlayer.size();j++){
	   System.out.print("the number of"+(j+1)+"card: ");
	   System.out.println(cardsPlayer.get(j).getIconMask()+cardsPlayer.get(j).getMask());
	  }
	 }
	 
	 
	 public static void reset(){
	  init();
	  Collections.shuffle(cards);
	  System.out.println("computer's card: ");
	  int computer = sendCardInit(0);
	  System.out.println("----------------");
	  System.out.println("player's card: ");
	  int player = sendCardInit(1);
	  System.out.println("----------------");
	  System.out.println("please enter what to do:");
	  System.out.println("Enter K for opening; A for plus; R for reset; Q for exit");
	  
	  Scanner s = new Scanner(System.in);
	  while(true){
	   String command = s.nextLine();
	   if(command.equals("K")||command.equals("k")){
	    if(player>21){
	     System.out.println("*****************");
	     printComputer();
	     System.out.println("Final points of the computer: " + computer);
	     System.out.println("----------------");
	     printPlayer();
	     System.out.println("Player's final points: " + player);
	     System.out.println("It's a pity you lost and the computer won!");
	    }
	    
	    if(computer<16){
	     System.out.println("Computer plus");
	     for(int j =0; j< cardsComputer.size();j++){
	      System.out.print("the number of"+(j+1)+"card: ");
	      System.out.println(cardsComputer.get(j).getIconMask()+cardsComputer.get(j).getMask());
	     }
	    }
	    int extraCounter = 0;
	    while(computer<16){
	     computer = extraCard(0,extraCounter+1,computer);
	     extraCounter = extraCounter+1;
	    }
	    
	    if(computer > 21){
	     System.out.println("*****************");
	     printComputer();
	     System.out.println("Final points of the computer: " + computer);
	     System.out.println("----------------");
	     printPlayer();
	     System.out.println("Player's final points: " + player);
	     System.out.println("Really great, you win, the player wins!");
	    }else if(computer > player){
	     System.out.println("*****************");
	     printComputer();
	     System.out.println("Final points of the computer: " + computer);
	     System.out.println("----------------");
	     printPlayer();
	     System.out.println("Player's final points: " + player);
	     System.out.println("It's a pity you lost and the computer won!");
	    }else if(computer < player){
	     System.out.println("*****************");
	     printComputer();
	     System.out.println("Final points of the computer: " + computer);
	     System.out.println("----------------");
	     printPlayer();
	     System.out.println("Player's final points: " + player);
	     System.out.println("Really great, you win, the player wins!");
	    }else{
	     System.out.println("*****************");
	     printComputer();
	     System.out.println("Final points of the computer: " + computer);
	     System.out.println("----------------");
	     printPlayer();
	     System.out.println("Player's final points: " + player);
	     System.out.println("Tie, no one wins!");
	    }
	   }else if(command.equals("A")||command.equals("a")){
	    System.out.println("player add card");
	    for(int j =0; j< cardsPlayer.size();j++){
	     System.out.print("the number of"+(j+1)+"card: ");
	     System.out.println(cardsPlayer.get(j).getIconMask()+cardsPlayer.get(j).getMask());
	    }
	    player =extraCard(1,cardsPlayer.size()-2+1,player);
	    System.out.println("the total number of palyer: "+player);
	    if(player>21){
	     System.out.println("*****************");
	     printComputer();
	     System.out.println("Final points of the computer: " + computer);
	     System.out.println("----------------");
	     printPlayer();
	     System.out.println("Player's final points: " + player);
	     System.out.println("It's a pity you lost and the computer won!");
	    }
	   }else if(command.equals("R")||command.equals("r")){
	    System.out.println("Black jack");
	    System.out.println("*****************");
	    System.out.println("A means 1 point, 2-10 means 2-10 points, J, Q, K means 10 points");
	    cards.clear();
	    cardsComputer.clear();
	    cardsPlayer.clear();
	    init();
	    Collections.shuffle(cards);
	    System.out.println("computer's card: ");
	    computer = sendCardInit(0);
	    System.out.println("----------------");
	    System.out.println("player's card: ");
	    player = sendCardInit(1);
	    System.out.println("----------------");
	    print();
	   }else if(command.equals("Q")||command.equals("q")){
	    System.err.println("Exit the game successfully");
	    break;
	   }else{
	    System.err.println("Invalid input! Enter K for opening; A for plus; R for reset; Q for exit");
	   }
	  }
	  s.close();
	 }
	 
	 public static void print(){
	  System.out.println("Please enter instructions:");
	  System.out.println("Enter K for opening; A for plus; R for reset; Q for exit");
	 }
	}

