import java.util.Map;
public class Student implements Comparable{
    private int ID;
    private String name;
    private Map<String, Integer> Score;

    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
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

    public int getScore(String key) {
        return Score.get(key);
    }

    //增
    public void setScore(String subject, int score) {
        Score.put(subject, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ( !(obj instanceof Student) ) {
            throw  new ClassCastException("类型错误");
        }
        Student p = (Student) obj;
        return this.ID == p.ID;
    }

    @Override
    public int compareTo(Object o) {
        Student p = (Student) o;
        return this.ID - p.ID;
    }
}
