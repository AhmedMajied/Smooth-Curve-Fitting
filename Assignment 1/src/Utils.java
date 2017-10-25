
public class Utils {
	
	public static void initPopulation(int itemsNumber){
		double random;
		
		for(int i=0;i<Knapsack.popSize;i++){
			Knapsack.randomPopulation[i] = new Chromosome(itemsNumber);
			
			for(int c=0;c<itemsNumber;c++){
				
				random = Math.random();
				
				if(random > 0.5){
					Knapsack.randomPopulation[i].bits[c] = 1;
				}
				else{
					Knapsack.randomPopulation[i].bits[c] = 0;
				}
			}
		}
	}
	
	public static void sortRandomPopulation(){
		Chromosome tmp;
		
		for(int first=0;first<Knapsack.popSize;first++){
			for(int second=0;second<Knapsack.popSize-1;second++){
				if(Knapsack.randomPopulation[second].fitness > Knapsack.randomPopulation[second+1].fitness){
					tmp = Knapsack.randomPopulation[second];
					Knapsack.randomPopulation[second] = Knapsack.randomPopulation[second+1];
					Knapsack.randomPopulation[second+1] = tmp;
				}
			}
		}
	}
}
