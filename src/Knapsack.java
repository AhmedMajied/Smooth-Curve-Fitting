
public class Knapsack {
	public static TestCase [] TestCases;
	public static Chromosom randomPopulation[];
	public static int popSize = 5; // need to be verified
	public static int maxNumberOfGenerations = 3;// need to be verified
	
	public static void main(String [] args){
		int testCasesNumber = FileUtility.readTestCasesFile();
		
		for(int i=0;i<testCasesNumber;i++){
			int itemsNumber = TestCases[i].Items.length; 
			randomPopulation = new Chromosom[popSize];
			
			initPopulation(itemsNumber);
			
			for(int c=0;c<maxNumberOfGenerations;c++){
				for(int itemIndex=0;itemIndex<itemsNumber;itemIndex++){
					calcFitness(itemsNumber,TestCases[i].Items);
				}
				
			}
		}
		
		
	}
	
	private static void initPopulation(int itemsNumber){
		double random;
		
		for(int i=0;i<popSize;i++){
			randomPopulation[i] = new Chromosom(itemsNumber);
			
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
	private static void calcFitness(int bitsNumber,int Items[]){
		int chromosomBenefit;
		int chromosomWeight;
		
		for(int chromosomIndex=0;chromosomIndex<popSize;chromosomIndex++){
			chromosomBenefit = 0;
			chromosomWeight = 0;
			for(int bitIndex=0;bitIndex<bitsNumber;bitIndex++){
				if(randomPopulation[chromosomIndex].bits[bitIndex] == 1){
					Items[bitIndex].benefit;
				}
			}
		}
	}

}
