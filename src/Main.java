import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) throws FileNotFoundException {
		
		File file=new File("testcases.txt");
		Scanner input =new Scanner(file);
		int testCaseNum=input.nextInt();
		
		for(int i=1;i<=testCaseNum;i++)
		{
			int numOfPoints=input.nextInt();
			int degree=input.nextInt();
			Point points[]=new Point[numOfPoints];
			
			for(int j=0;j<numOfPoints;j++)
			{
				Point point=new Point();
				point.x=input.nextDouble();
				point.y=input.nextDouble();
				points[j]=point;
			}
			
			System.out.println("case "+i);
			
			double cofficient[]=new double[degree+1];
			cofficient=GeneticAlgorithm.run(degree, points);
			
			for(int k=0;k<cofficient.length;k++)
			{
				System.out.print(cofficient[k]+"\t ");
			}
			System.out.println();
		}
		input.close();
	}
}
