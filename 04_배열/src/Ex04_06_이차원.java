public class Ex04_06_������ {
	public static void main(String[] args) {
		int[] kor = {10,20,30};
		int[] eng = {10,20,30};
		int[] math = {10,20,30};
		
		for(int i=0;i<kor.length;i++) {
			System.out.println(kor[i]+" ");			
		}
		
		for(int temp : kor) {
			System.out.println(temp);			
		}
		
		/*
		int[][] score = {
				{80,72,68},
				{33,93,44},
				{78,52},
				{30,31,32,33}
		};
		*/
		
		int[][] score= new int[4][];
		score[0]=new int[] {80,72,68};
		score[1]=new int[] {33,93,44};
		score[2]=new int[] {78,52};
		score[3]=new int[] {30,31,32,33};
		
		System.out.println("score.length:"+score.length);
		System.out.println(score[0].length);
		System.out.println(score[1].length);
		System.out.println(score[2].length);
		System.out.println(score[3].length);
		System.out.println(score[0][0]);
		
		for(int i=0;i<score.length;i++) {
			for(int j=0;j<score[i].length;j++) {
				System.out.println(score[i][j]+" ");
			}
			System.out.println();
		}
		for(int[] temp:score) {
			for(int x:temp) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
	}
}