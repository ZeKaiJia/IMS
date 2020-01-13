public class Subject {
    public int ID;
    public String name;
    public int GP;

    public Subject(int ID, String name, int GP) {
        this.ID = ID;
        this.name = name;
        this.GP = GP;
    }

    Subject Chinese = new Subject(1,"语文",5);
    Subject Maths = new Subject(2,"数学",4);
    Subject English = new Subject(3,"英语",3);
}
