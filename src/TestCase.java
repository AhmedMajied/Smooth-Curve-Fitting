
public class TestCase {
	int knapsackSize;
	public Item Items [];
	
	public TestCase(int knapsackSize,int itemsNumber){
		this.knapsackSize = knapsackSize;
		Items = new Item[itemsNumber];
	}
}

class Item{
	public int weight;
	public int benefit;
	
	public Item(int w,int b){
		weight = w;
		benefit = b;
	}
}
