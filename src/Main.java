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
			Chromosome chromosome=new Chromosome(degree);
			chromosome=GeneticAlgorithm.run(degree, points);
			for(int k=0;k<chromosome.genes.length;k++)
			{
				System.out.print(chromosome.genes[k]+"\t ");
			}
			System.out.println();
			System.out.println("Error : "+(1/chromosome.fitness));
		}
		input.close();
	}
}
