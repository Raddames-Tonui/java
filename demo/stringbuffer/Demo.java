class Human {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private int age;
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        if (age < 0 ){
            System.out.println("Age cannot be neative");
        }
        else {
            this.age =age;
        }
    }




    public void show(){
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Demo {
    public static void main(String[] args) {
        Human h1 = new Human();
        h1.show();
        h1.setName("John");
        h1.setAge(25);
        h1.show();

        Human h2 = new Human();
        
        
    }
}
