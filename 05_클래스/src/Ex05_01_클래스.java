class Person {
	int age;

	void addAge(int i) {
		age += i;
	}
}

public class Ex05_01_Ŭ���� {
	public static void main(String[] args) {
		Person p = new Person();
		p.age = 16;
		
		System.out.println(p.age);
		p.addAge(5);
		
		System.out.println(p.age);
	}
}