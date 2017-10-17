import java.util.Random;

public class Knapsack {
	public static TestCase [] TestCases;
	public static Chromosome randomPopulation[];
	public static int popSize = 5; // can be changed
	public static int maxNumberOfGenerations = 1;// can be changed
	public static Chromosome selectedChromosomes[];
	
	public static void main(String [] args){
		int testCasesNumber = FileUtility.readTestCasesFile();
		int selectionSize = 0;
		
		if(popSize % 2 == 0){
			selectionSize = popSize;
		}
		else{
			selectionSize = popSize-1;
		}
		
		for(int i=0;i<testCasesNumber;i++){
			int itemsNumber = TestCases[i].Items.length; 
			randomPopulation = new Chromosome[popSize];
			
			Utils.initPopulation(itemsNumber);
			
			selectedChromosomes = new Chromosome[selectionSize];
					
			//for(int c=0;c<maxNumberOfGenerations;c++){
				for(int itemIndex=0;itemIndex<itemsNumber;itemIndex++){
					calcFitness(itemsNumber,TestCases[i]);
				}
				
				
				selectChoromosomes(selectionSize);
				
				/* here where Xover function should be called */
				
				/* here where mutation function should be called */
				
				/* here where replacement function should be called */
				/* note: don't forget to change popSize if needed */
				
			//}
			
			/* for testing */
			/*for(int tmp=0;tmp<popSize;tmp++){
				System.out.print(randomPopulation[tmp].fitness+" ");
			}
			System.out.print(TestCases[i].knapsackSize+" "+i+"\n");*/	
			
		}
		
		
	}
	
	
	// each item is represented as one bit
	private static void calcFitness(int bitsNumber,TestCase testCase){
		int chromosomeBenefit;
		int chromosomeWeight;
		
		for(int chromosomeIndex=0;chromosomeIndex<popSize;chromosomeIndex++){
			chromosomeBenefit = 0;
			chromosomeWeight = 0;
			
			for(int bitIndex=0;bitIndex<bitsNumber;bitIndex++){
				if(randomPopulation[chromosomeIndex].bits[bitIndex] == 1){
					chromosomeBenefit += testCase.getItemBenefit(bitIndex);
					chromosomeWeight += testCase.getItemWeight(bitIndex);
					
					if(chromosomeWeight > testCase.knapsackSize){
						break;
					}
				}
			}
			
			if(chromosomeWeight > testCase.knapsackSize){
				randomPopulation[chromosomeIndex].fitness = 0;
			}
			else{
				randomPopulation[chromosomeIndex].fitness = chromosomeBenefit;
			}
		}
	}
	
	private static void selectChoromosomes(int selectionSize){
		Utils.sortRandomPopulation();
		
		int fitnessSummation = 0;
		int randomNumber;
		
		for(int chromosomeIndex=0;chromosomeIndex<popSize;chromosomeIndex++){
			fitnessSummation += randomPopulation[chromosomeIndex].fitness;
		}
		
		for(int selectedIndex=0;selectedIndex<selectionSize;selectedIndex++){
			randomNumber = (int)Math.random()*fitnessSummation;
			
			for(int chromosomeIndex=0;chromosomeIndex<popSize;chromosomeIndex++){
				if(randomNumber <= randomPopulation[chromosomeIndex].fitness){
					selectedChromosomes[selectedIndex] = randomPopulation[chromosomeIndex];
					break;
				}
			}
		}
	}
	
	private static void CrossOver(Chromosome selection[]) {
		for(int i=0;i<selection.length-2;i+=2) {
			Random r1 = new Random();
			int point = r1.nextInt(selection[i].bits.length);
			float r2=r1.nextFloat();
			if(r2<=0.5)
			{
				selection[i].CrossOver(point, selection[i+1]);
				selection[i+1].CrossOver(point, selection[i]);
			}
			
		}
	}

}
