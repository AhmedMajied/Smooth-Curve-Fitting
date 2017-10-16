
public class Knapsack {
	public static TestCase [] TestCases;
	public static Chromosome randomPopulation[];
	public static int popSize = 5; // need to be verified
	public static int maxNumberOfGenerations = 3;// need to be verified
	
	public static void main(String [] args){
		int testCasesNumber = FileUtility.readTestCasesFile();
		
		for(int i=0;i<testCasesNumber;i++){
			int itemsNumber = TestCases[i].Items.length; 
			randomPopulation = new Chromosome[popSize];
			
			initPopulation(itemsNumber);
			
			//for(int c=0;c<maxNumberOfGenerations;c++){
				for(int itemIndex=0;itemIndex<itemsNumber;itemIndex++){
					calcFitness(itemsNumber,TestCases[i]);
				}
				
				
				/* here where Selection function should be called */
				
				/* here where Xover function should be called */
				
				/* here where mutation function should be called */
				
				/* here where replacement function should be called */
				
			//}
			
			/* for testing */
			/*for(int tmp=0;tmp<popSize;tmp++){
				System.out.print(randomPopulation[tmp].fitness+" ");
			}
			System.out.print(TestCases[i].knapsackSize+" "+i+"\n");*/	
			
		}
		
		
	}
	
	private static void initPopulation(int itemsNumber){
		double random;
		
		for(int i=0;i<popSize;i++){
			randomPopulation[i] = new Chromosome(itemsNumber);
			
			for(int c=0;c<itemsNumber;c++){
				
				random = Math.random();
				
				if(random > 0.5){
					randomPopulation[i].bits[c] = 1;
				}
				else{
					randomPopulation[i].bits[c] = 0;
				}
			}
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

}
