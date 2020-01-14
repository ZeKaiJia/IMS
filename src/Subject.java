public class Subject {
    public int ID;
    public String name;
    public int GP;

    public Subject(int ID, String name, int GP) {
        this.ID = ID;
        this.name = name;
        this.GP = GP;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getGP() {
        return GP;
    }
}
