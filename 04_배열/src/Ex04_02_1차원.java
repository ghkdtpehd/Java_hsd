public class Ex04_02_1���� {
	public static void main(String[] args) {
		double[] a = {1.1,2.2,3.3};
		double[] b = new double[] {1.1,2.2,3.3};
		double[] c = new double[3];
		
		c[1]=1.1;
		c[2]=2.2;
		c[3]=3.3;
		
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
		
		for(double i: b) {
			System.out.println(i);
		}
	}

}
