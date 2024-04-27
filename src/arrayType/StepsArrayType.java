package arrayType;

public class StepsArrayType extends ArrayType {

	public StepsArrayType() {}
	
	public String getName() {
		return "Steps";
	}

	public int[] get(int length) {
		int[] array = new int[length];
		
		int sectionLength = length/5;
		
		for (int i = 0; i < length; i++) {
			array[i] = i/sectionLength + 1;
		}
		
		return array;
	}

}
