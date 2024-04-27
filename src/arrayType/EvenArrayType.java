package arrayType;

public class EvenArrayType extends ArrayType {

	public EvenArrayType() {}
	
	public String getName() {
		return "Even";
	}

	public int[] get(int length) {
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i] = i + 1;
		}
		
		return array;
	}

}
