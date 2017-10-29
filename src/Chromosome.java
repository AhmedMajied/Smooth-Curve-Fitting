import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
	public double genes[];
	public double fitness;
	public Double fitnessUpperBound;

	public Chromosome(int degree) {
		genes = new double[degree+1];// why degree +1?
	}
	
	public Chromosome(Chromosome c) {
		fitness=c.fitness;
		fitnessUpperBound=c.fitnessUpperBound;
		genes=new double[c.genes.length];
		for(int i=0;i<genes.length;++i) {
			genes[i]=c.genes[i];
		}
	}
	
	public void generateRandomGenes(double low,double high) {
		for(int i=0;i<genes.length;++i) {
			Random random = new Random();
			genes[i]=(high-low)*random.nextDouble()+low;// why we use (high-low)?
		}
	}
	
	public void calcSingleChromosomeFitness(Point[] points) {
	
		fitness = 0.0;
		for(int i=0;i<points.length;++i) {
			int x = 1;
			float y = 0;
			
			for(int j=0;j<genes.length;++j) {
				y += x*genes[j];
				x *= points[i].x;
			}
			fitness += (points[i].y-y)*(points[i].y-y); 
		}
		fitness /= points.length;
		fitness = 1.0/fitness;
	}
	
	public void generateRandomGenes(int low,int high) {
		for(int i=0;i<genes.length;++i) {
			Random random = new Random();
			genes[i]=(high-low)*random.nextDouble()+low;
		}
	}
	
	public void PerformSinglecrossOver(int point,Chromosome c) {

		for(int i=0;i<point;++i)
			genes[i]=c.genes[i];	
	}
	
	@Override
	public int compareTo(Chromosome chromosome) {
		return Double.compare(this.fitnessUpperBound, chromosome.fitnessUpperBound);
	}
}
