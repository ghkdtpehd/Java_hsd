class Person2{
	private String name;
	private int age;
	Person2(String name,int age){
		this.name = name;
		this.age = age;
	}
	String getName() {
		return name;
	}
	int getAge() {
		return age;
	}
	void display() {
		System.out.println("name: "+name);
		System.out.println("age: "+age);
	}
}
class Student2 {
	Person2 p;
	int kor;
	Student2(String name, int age, int kor){
		p = new Person2(name,age);
		this.kor = kor;
	}
	
	void display() {
		p.display();
		System.out.println("kor: "+kor);
	}
}
public class Ex06_04_���� {
	public static void main(String[] arg) {
		Student2 s = new Student2("���",30,78);
		s.display();
		System.out.println(s.p.getName());
	}
}