package arrayType;

public class ExponentialArrayType extends ArrayType {

	public ExponentialArrayType() {}
	
	public String getName() {
		return "Exponential";
	}

	public int[] get(int length) {
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i] = (int)(Math.pow(Math.sqrt(length), 2*i/((double)length) ));
		}
		
		return array;
	}

}
