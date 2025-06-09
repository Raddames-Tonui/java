class Student {
    String name = "";
    int age = 0;
    char grade = '*';
    static String school = "";

    // Static block to initialize static variable
    // This block runs once when the class is loaded
    static {
        school = "ABC High School";
    }

    // Default constructor
    public Student() {
        // You can initialize instance variables here if needed
        this.name = "New Student";
        this.age = 18;
        this.grade = 'C';
    }

    public void show(){
        System.out.println("Name: " + name + ", Age: " + age + ", School: " + school + ", Grade: " + grade);
    }

    public static void show1(Student s){
        System.out.println("School: " + school + "Name: " +s.name + ", Age: " + s.age + ", Grade: " + s.grade);
    }
    
}

public class Schoool{
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "John";
        s1.age =16;
        s1.grade = 'B';

        Student s2 = new Student();
        s2.name = "Alice";
        s2.age = 15;
        s2.grade = 'A';

        s2.show();
        s1.show();
        Student.show1(s1);

        Student s3 = new Student();
        s3.show();
        

        // Student.show1(s3);
    }
}