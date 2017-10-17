
public class Chromosome {
	public int bits[];
	public int fitness;
	
	public Chromosome(int itemsNumber){
		bits = new int[itemsNumber];
	}
	
	public void crossOver(int point,Chromosome c) {
		for(int i=0;i<point;++i)
			bits[i]=c.bits[i];	
	}
	
}
