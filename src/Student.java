import java.util.Map;

public class Student {
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
}
