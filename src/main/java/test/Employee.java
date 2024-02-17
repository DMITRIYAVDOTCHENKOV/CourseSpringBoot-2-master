package test;

public class Employee {
    private String post;
    private String email;
    private String telefone;
    private double salary;
    private int age;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Employee(String post, String email, String telefone, double salary, int age) {
        this.post = post;
        this.email = email;
        this.telefone = telefone;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "post='" + post + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
