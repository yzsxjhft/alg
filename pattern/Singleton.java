package pattern;

public class Singleton {

}


enum Singleton1 {
   INSTANCE;

    Singleton1() {

    }
}


class Singleton2 {

    public static class Factory{
        public static Singleton2 instance = new Singleton2();
    }

    public Singleton2 getInstance() {
        return Factory.instance;
    }
}