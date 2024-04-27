package arrayType;

public class RandomArrayType extends ArrayType {

	public RandomArrayType() {}
	
	public String getName() {
		return "Random";
	}

	public int[] get(int length) {
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i] = (int)(Math.random() * length) + 1;
		}
		
		return array;
	}

}
