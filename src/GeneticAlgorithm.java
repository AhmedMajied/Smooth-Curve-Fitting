import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {
	public static double run(int degree, Point[]points) {
		int populationSize=100;
		int maxGeneration=100;
		int selectionSize=populationSize;
		// Generate Random Population
		Chromosome[] population=generateRandomPopulation(populationSize, degree);
		
		//Evaluate Fitness Function
		calculateFitness(population, points);
		
		//Perform Selection
		Chromosome[] selectedChromosomes = performSelection(selectionSize, population);
		
		//Perform CrossOver
		Chromosome[] offsprings = performCrossOver(selectedChromosomes);
		
		//Perform Mutation
		performMutation(offsprings,degree, maxGeneration);
		
		//Perform Replacement
		performReplacement(population, offsprings);
		
		return 0;
	}
	private static Chromosome[] generateRandomPopulation(int size,int degree) {
		Chromosome[] population = new Chromosome[size];
		for(int i=0;i<size;++i) {
			population[i]=new Chromosome(degree);
			population[i].generateRandomGenes(-10, 10);
		}
		return population;
	}
	
	private static void calculateFitness(Chromosome []population,Point[] points) {
		for(int i=0;i<population.length;++i) {
			population[i].calculateFitness(points);
		}
	}
	
	private static Chromosome[] performSelection(int selectionSize,Chromosome []population) {
		Chromosome[] selectedChromosomes=new Chromosome[selectionSize];
		calculateUpperBound(population);
		for(int i=0;i<selectionSize;++i) {
			Random random=new Random();
			selectedChromosomes[i]=population[upper_bound(population, (population[population.length-1].fitnessUpperBound-1)*random.nextFloat())];
		}
		return selectedChromosomes;
	}
	
	private static Chromosome[] performCrossOver(Chromosome[] selectedChromosomes) {
		Chromosome[] offsprings = Arrays.copyOf(selectedChromosomes, selectedChromosomes.length);
		for(int i=0;i<selectedChromosomes.length-1;i+=2) {
			Random r1 = new Random();
			int point = r1.nextInt(selectedChromosomes[i].genes.length);
			float r2=r1.nextFloat();
			if(r2<=0.7)
			{
				Chromosome tmp = new Chromosome(offsprings[i]);
				offsprings[i].crossOver(point, offsprings[i+1]);
				offsprings[i+1].crossOver(point, tmp);
			}
			
		}
		return offsprings;
	}
	
	private static void performMutation(Chromosome[] offsprings,int degree,int T) {
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		Random r4 = new Random();
		double num = 0.0,num2=0.0,doMutation=0.0,change=0.0;
		double LBound=-10,UBound=10,y,deltaL,deltaU,amounfOfMutation;
		int t=1; //current generation
		
		for(int i=0;i<offsprings.length;i++){
			for(int j=0;j<degree;j++){
				num=r1.nextDouble();
				deltaL=offsprings[i].genes[j]-LBound;
				deltaU=UBound-offsprings[i].genes[j];
				
				if(num>0.5)
					y=deltaU;
				else
					y=deltaL;
				
				num2=r2.nextDouble();
				amounfOfMutation=y*(1-Math.pow(num2, Math.pow(1-(t/T), 0.5)));
				
				doMutation=r3.nextDouble();
				if(doMutation>0.01){
					change=r4.nextDouble();
					if(change>0.5)
						offsprings[i].genes[j]+=(float)amounfOfMutation;
					else
						offsprings[i].genes[j]-=(float)amounfOfMutation;
				}
			}
		}
		
	}
	
	private static void performReplacement(Chromosome[] population,Chromosome [] offsprings) {
		population=Arrays.copyOf(offsprings, offsprings.length);
	}
	
	public static int upper_bound(Chromosome[] population, float key) {
        int size = population.length;
        int start = 0;
        int end = size-1;
        int mid = (start + end)/2;
        while (true) {
            int cmp = population[mid].fitnessUpperBound.compareTo(key);
            if (cmp == 0 || cmp < 0) {
                start = mid+1;
                if (end < start)
                    return mid<size-1?mid+1:-1;
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
	
}
