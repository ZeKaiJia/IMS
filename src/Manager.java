public class Manager {
    public static boolean flag = false;
    public static void main(String[] args) {
        Menu.show();
        while (true) {
            switch (Menu.choose()) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    flag = true;
                    break;
            }
            if ( flag ) {
                break;
            }
        }
    }
}
