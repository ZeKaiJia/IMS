import java.util.HashMap;
import java.util.Map;
public class Student implements Comparable{
    private int ID;
    private String name;
    private Map<Subject, Integer> subject;

    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
        subject = new HashMap<>();
        Subject Chinese = new Subject(1,"语文",5);
        subject.put(Chinese,null);
        Subject Maths = new Subject(2,"数学",4);
        subject.put(Maths,null);
        Subject English = new Subject(3,"英语",3);
        subject.put(English,null);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Subject, Integer> getSubject() {
        return subject;
    }

    @Override
    public int compareTo(Object o) {
        Student p = (Student) o;
        return this.ID - p.ID;
    }
}
