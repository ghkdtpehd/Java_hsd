public class Ex05_09_���ȣ�� {
	public static void main(String[] args) {
		int x=1;
		for(int i=4;i>0;i--) {
			x*=i;
		}
		System.out.println(x);
		
		System.out.println(factorial(4));
	}

	static int factorial(int x) {
		if(x==1) return 1;
		else return x*factorial(x-1);
	}
}