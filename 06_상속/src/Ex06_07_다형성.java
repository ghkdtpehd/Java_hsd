class Animal{
	public void bark() {
		System.out.println("bark bark");
	}
}
class Dog extends Animal{
	public void bark() {
		System.out.println("멍멍");
	}
}
public class Ex06_07_다형성 {
	public static void main(String[] args) {
		Animal a= new Animal();
		a.bark();
		
		Dog d = new Dog();
		d.bark();
		
		//자식 > 부모 업캐스팅 (생략가능 자동으로 됨)
		
		Animal a2= new Dog();
		a2.bark();
		
		//부모 > 자식 다운캐스팅
		
//		Dog d2 = (Dog)new Animal();
//		d2.bark();
	}
}