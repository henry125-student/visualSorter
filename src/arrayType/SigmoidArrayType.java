package arrayType;

public class SigmoidArrayType extends ArrayType {

	public SigmoidArrayType() {}
	
	public String getName() {
		return "Sigmoid";
	}

	public int[] get(int length) {
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i] = 
				(int)(length / (1.0 + Math.pow(Math.E, -10.0 * (i - length/2) / length))) + 1;
		}
		
		return array;
	}

}
