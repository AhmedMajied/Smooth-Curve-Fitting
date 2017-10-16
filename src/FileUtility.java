import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtility {
	
	public static int readTestCasesFile(){
		int testCasesNumber = 0;
		int itemsNumber;
		int knapsackSize;
		int weight,benefit;
		
		try {
			Scanner in = new Scanner(new File("input.txt"));
			
			if(in.hasNext()){
				testCasesNumber = in.nextInt();
				Knapsack.TestCases = new TestCase[testCasesNumber];
			}
			
			for(int i=0;i<testCasesNumber;i++){
				itemsNumber = in.nextInt();
				knapsackSize = in.nextInt();
				Knapsack.TestCases[i] = new TestCase(knapsackSize,itemsNumber);
				
				System.out.println(itemsNumber+"\n"+knapsackSize);
				
				for(int c=0;c<itemsNumber;c++){
					weight = in.nextInt();
					benefit = in.nextInt();
					Knapsack.TestCases[i].Items[c] = new Item(weight,benefit);
					System.out.println(weight+" "+benefit);
				}
			}
			
			
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return testCasesNumber;
	}
}
