import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

	
	private static int populationSize=1000;
	private static int maxGeneration=100;
	private static int selectionSize=populationSize;
	private static double LBound = -10.0;
	private static double UBound = 10.0;
	
	public static double[] run(int degree, Point[]points) {
		
		// Generate Random Population
		Chromosome[] population=generateRandomPopulation(populationSize, degree);
		//printPop(population);
		for(int t=0;t<maxGeneration;t++){
			//Evaluate Fitness Function
			calculateFitness(population, points);
			//Perform Selection
			Chromosome[] selectedChromosomes = performSelection(selectionSize, population);

			//Perform CrossOver
			Chromosome[] offsprings = performCrossOver(selectedChromosomes);

			//Perform Mutation
			performMutation(offsprings,t, degree);

			//Perform Replacement
			performReplacement(population, offsprings);
		}
		
		// get best chromosome
		double fitness=population[0].fitness;
		int index=0;
		
		for(int k=1;k<population.length;k++)
		{
			if(fitness<population[k].fitness)
			{
				index=k;
				fitness=population[k].fitness;
			}
		}
		return population[index].genes;
	}
	
	private static Chromosome[] generateRandomPopulation(int size,int degree) {
		
		Chromosome[] population = new Chromosome[size];
		
		for(int i=0;i<size;++i) {
			population[i] = new Chromosome(degree);
			population[i].generateRandomGenes(LBound, UBound);
		}
		
		return population;
	}
	
	private static void calculateFitness(Chromosome []population,Point[] points) {
		for(int i=0;i<population.length;++i) {
			population[i].calcSingleChromosomeFitness(points);
		}
	}
	
	private static Chromosome[] performSelection(int selectionSize,Chromosome []population) {
		Chromosome[] selectedChromosomes=new Chromosome[selectionSize];
		calculateUpperBound(population);

		for(int i=0;i<selectionSize;++i) {
			Random random=new Random();
			double r = (population[population.length-1].fitnessUpperBound)*random.nextDouble();
			selectedChromosomes[i]=population[upper_bound(population,r)];
			
		}
		return selectedChromosomes;
	}
	
	private static Chromosome[] performCrossOver(Chromosome[] selectedChromosomes) {
		Chromosome[] offsprings = Arrays.copyOf(selectedChromosomes, selectedChromosomes.length);
		for(int i=0;i<selectedChromosomes.length-1;i+=2) {
			Random r1 = new Random();
			int point = r1.nextInt(selectedChromosomes[i].genes.length);
			double r2=r1.nextDouble();
			if(r2<0.6)
			{

				Chromosome tmp = new Chromosome(offsprings[i]);
				offsprings[i].PerformSinglecrossOver(point, offsprings[i+1]);
				offsprings[i+1].PerformSinglecrossOver(point, tmp);
			}
		}
		return offsprings;
	}
	
	private static void performMutation(Chromosome[] offsprings,int t,int degree) {

		Random r1 = new Random();
		double r = 0.0,doMutation=0.0,dependency = 0.5;
		double y,deltaL,deltaU,amounfOfMutation;

		for(int i=0;i<offsprings.length;i++){
			for(int j=0;j<(degree+1);j++){// degree+1 as cofficients in genes = degree+1
				r=r1.nextDouble();
				deltaL=offsprings[i].genes[j]-LBound;
				deltaU=UBound-offsprings[i].genes[j];
				
				if(r>0.5)
					y=deltaU;
				else
					y=deltaL;
				
				amounfOfMutation=y*(1-Math.pow(r, Math.pow(1-(t/maxGeneration), dependency)));
				
				doMutation=r1.nextDouble();
				if(doMutation<=0.01){
					
					if(r > 0.5){
						offsprings[i].genes[j]+=amounfOfMutation;
					}
					else{
						offsprings[i].genes[j]-=amounfOfMutation;
					}
					
				}
			}
		}
		
	}
	
	private static void performReplacement(Chromosome[] population,Chromosome [] offsprings) {
		population=Arrays.copyOf(offsprings, offsprings.length);
	}
	
	// TODO Andrew should test this function -- ya3m sh3'alaaaa mat2rfonash b2aa
	public static int upper_bound(Chromosome[] population, double key) {
        int size = population.length;
        int start = 0;
        int end = size-1;
        int mid = (start + end)/2;
        while (true) {
            int cmp = population[mid].fitnessUpperBound.compareTo(key);
            if (cmp == 0 || cmp < 0) {
                start = mid+1;
                if (end < start)
                    return mid<size-1?mid+1:size-1;
            } else {
                end = mid-1;
                if (end < start)
                    return mid;
            }
            mid = (start + end)/2;
        }
    }
	
	private static void calculateUpperBound(Chromosome[] population) {
		population[0].fitnessUpperBound=population[0].fitness;
		for(int i=1;i<population.length;++i) {
			population[i].fitnessUpperBound=population[i].fitness+population[i-1].fitnessUpperBound;
		}
	}
//	
//	private static void printPop(Chromosome[] pop) {
//		for(int i=0;i<pop.length;++i) {
//			System.out.println(pop[i].fitness);
//			for(int j=0;j<pop[i].genes.length;++j) {
//				System.out.print(pop[i].genes[j]+"  ");
//			}
//			System.out.println("\r\n"+pop[i].fitnessUpperBound+"\r\n--------------------\r\n\r\n");
//			
//		}
//	}
	
}
