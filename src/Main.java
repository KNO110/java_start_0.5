import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ///// Завдання 1: Люди і друзі
        Builder builder = new Builder("Джозеф", "Джостар", 2);
        Sailor sailor = new Sailor("Джонатан", "Джостар", 3);
        Pilot pilot = new Pilot("Діо", "Брандо", 1);

        List<Human> friends = new ArrayList<>();
        friends.add(builder);
        friends.add(sailor);
        friends.add(pilot);

        int totalChildren = 0;
        for (Human friend : friends) {
            totalChildren += friend.getChildren();
        }

        System.out.println("Сумарна кількість дітей у друзів: " + totalChildren);

        ///// Завдання 2: Студенти, групи, курси
        Course math = new Course("Математика", 2, 60);
        Course physics = new Course("Фізика", 2, 70);
        Course literature = new Course("Література", 1, 90);

        Student student1 = new Student("Джотаро", "Куджо");
        student1.addCourse(math);
        student1.addCourse(physics);

        Student student2 = new Student("Кіре", "Йошикаге");
        student2.addCourse(physics);
        student2.addCourse(literature);

        Group group = new Group("Група A");
        group.addStudent(student1);
        group.addStudent(student2);

        System.out.println("\nНеуспішні студенти:");
        group.printFailingStudents();

        System.out.println("\nСтуденти без боргів:");
        group.printDebtFreeStudents();

        System.out.println("\nПредмети з найбільше неуспішних результатів:");
        group.printCoursesWithMostFailures();

        System.out.println("\nРейтинг студентів:");
        group.printStudentRatings();

        System.out.println("\nКурси студента Джотаро у семестрі 2:");
        student1.printCoursesBySemester(2);

        ///// Завдання 3: Зоопарк
        Tiger tiger = new Tiger("Тигр", 30);
        Rabbit rabbit = new Rabbit("Кролик", 5);
        Wolf wolf = new Wolf("Вовк", 25);
        Kangaroo kangaroo = new Kangaroo("Кенгуру", 15);

        Zoo zoo = new Zoo();
        zoo.addAnimal(tiger);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(wolf);
        zoo.addAnimal(kangaroo);

        System.out.println("\nКількість хижаків у зоопарку: " + zoo.getPredatorCount());
        System.out.println("Загальний обсяг корму: " + zoo.getTotalFoodRequirement() + " кг");
    }
}

// Завдання 1: Люди і друзі
class Human {
    protected String firstName;
    protected String lastName;
    protected int children;

    public Human(String firstName, String lastName, int children) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = children;
    }

    public int getChildren() {
        return children;
    }
}

class Builder extends Human {
    public Builder(String firstName, String lastName, int children) {
        super(firstName, lastName, children);
    }
}

class Sailor extends Human {
    public Sailor(String firstName, String lastName, int children) {
        super(firstName, lastName, children);
    }
}

class Pilot extends Human {
    public Pilot(String firstName, String lastName, int children) {
        super(firstName, lastName, children);
    }
}

// Завдання 2: Студенти, групи, курси
class Course {
    private String name;
    private int semester;
    private int grade; // -1 для незданих курсів

    public Course(String name, int semester, int grade) {
        this.name = name;
        this.semester = semester;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public int getGrade() {
        return grade;
    }

    public boolean isPassed() {
        return grade >= 60;
    }
}

class Student {
    private String firstName;
    private String lastName;
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean hasDebt() {
        for (Course course : courses) {
            if (!course.isPassed()) {
                return true;
            }
        }
        return false;
    }

    public void printCoursesBySemester(int semester) {
        for (Course course : courses) {
            if (course.getSemester() == semester) {
                System.out.println(course.getName());
            }
        }
    }

    public double getAverageGrade() {
        int total = 0;
        int count = 0;
        for (Course course : courses) {
            if (course.isPassed()) {
                total += course.getGrade();
                count++;
            }
        }
        return count == 0 ? 0 : (double) total / count;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

class Group {
    private String name;
    private List<Student> students = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printFailingStudents() {
        for (Student student : students) {
            if (student.hasDebt()) {
                System.out.println(student);
            }
        }
    }

    public void printDebtFreeStudents() {
        for (Student student : students) {
            if (!student.hasDebt()) {
                System.out.println(student);
            }
        }
    }

    public void printCoursesWithMostFailures() {
        List<Course> allCourses = new ArrayList<>();
        for (Student student : students) {
            allCourses.addAll(student.getCourses());
        }

        allCourses.stream()
                .filter(course -> !course.isPassed())
                .map(Course::getName)
                .distinct()
                .forEach(System.out::println);
    }

    public void printStudentRatings() {
        for (Student student : students) {
            System.out.println(student + ": " + student.getAverageGrade());
        }
    }
}

// Завдання 3: Зоопарк
abstract class Animal {
    protected String name;
    protected int foodRequirement; // в кг

    public Animal(String name, int foodRequirement) {
        this.name = name;
        this.foodRequirement = foodRequirement;
    }

    public abstract boolean isPredator();

    public int getFoodRequirement() {
        return foodRequirement;
    }
}

class Tiger extends Animal {
    public Tiger(String name, int foodRequirement) {
        super(name, foodRequirement);
    }

    @Override
    public boolean isPredator() {
        return true;
    }
}

class Rabbit extends Animal {
    public Rabbit(String name, int foodRequirement) {
        super(name, foodRequirement);
    }

    @Override
    public boolean isPredator() {
        return false;
    }
}

class Wolf extends Animal {
    public Wolf(String name, int foodRequirement) {
        super(name, foodRequirement);
    }

    @Override
    public boolean isPredator() {
        return true;
    }
}

class Kangaroo extends Animal {
    public Kangaroo(String name, int foodRequirement) {
        super(name, foodRequirement);
    }

    @Override
    public boolean isPredator() {
        return false;
    }
}

class Zoo {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public int getPredatorCount() {
        return (int) animals.stream().filter(Animal::isPredator).count();
    }

    public int getTotalFoodRequirement() {
        return animals.stream().mapToInt(Animal::getFoodRequirement).sum();
    }
}
