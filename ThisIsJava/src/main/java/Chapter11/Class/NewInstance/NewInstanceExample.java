package Chapter11.Class.NewInstance;

public class NewInstanceExample {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("Chapter11.Class.NewInstance.SendAction");
//            Class clazz = Class.forName("Chapter11.Class.NewInstance.ReceiveAction");

            Action action = (Action) clazz.newInstance();
            action.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
