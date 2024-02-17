package test;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Дворник", "test@mail.ru","+7954345663", 45908.4, 56);
        employees[1] = new Employee("Грузщик", "test@mail.ru","+7956746464", 75908.4, 25);
        employees[2] = new Employee("Повар", "test@mail.ru","+54656576868", 100000, 36);
        employees[3] = new Employee("Кондитер", "test@mail.ru","+47897856864", 56535.6, 66);
        employees[4] = new Employee("бренд-шеф", "test@mail.ru","+47897856864", 156535.6, 42);

        for (Employee employee : employees) {
            if (employee.getAge() > 40){
                System.out.println(employee);
            }
        }
    }
}
