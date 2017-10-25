import java.nio.channels.FileLockInterruptionException;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
	public Float genes[];
	public Float fitness;
	public Float fitnessUpperBound;
	public Chromosome(int degree) {
		genes = new Float[degree];
	}
	public Chromosome(Chromosome c) {
		fitness=c.fitness;
		fitnessUpperBound=c.fitnessUpperBound;
		for(int i=0;i<genes.length;++i) {
			genes[i]=c.genes[i];
		}
	}
	public void generateRandomGenes(int low,int high) {
		for(int i=0;i<genes.length;++i) {
			Random random = new Random();
			genes[i]=(high-low)*random.nextFloat()+low;
		}
	}
	public void calculateFitness(Point[] points) {
		for(int i=0;i<points.length;++i) {
			int x=1;
			float y=0;
			for(int j=0;j<genes.length;++j) {
				y+=x*genes[j];
				x*=points[i].x;
			}
			fitness+=(points[i].y-y)*(points[i].y-y);
		}
		fitness/=points.length;
	}
	public void crossOver(int point,Chromosome c) {
		for(int i=0;i<point;++i)
			genes[i]=c.genes[i];	
	}
	@Override
	public int compareTo(Chromosome chromosome) {
		return Float.compare(this.fitnessUpperBound, chromosome.fitnessUpperBound);
	}
}
