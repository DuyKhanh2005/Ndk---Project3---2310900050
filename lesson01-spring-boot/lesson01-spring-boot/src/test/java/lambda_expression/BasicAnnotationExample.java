package annotation_demo;

// Ví dụ các annotation có sẵn trong Java
class Parent {
    void show() {
        System.out.println("Xin chào từ lớp cha");
    }
}

class Child extends Parent {

    @Override
    void show() {
        System.out.println("Xin chào từ lớp con");
    }

    @Deprecated
    void oldMethod() {
        System.out.println("Phương thức này đã cũ, không nên dùng nữa!");
    }
}

public class BasicAnnotationExample {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Child c = new Child();

        // @Override
        c.show();

        // @Deprecated
        c.oldMethod(); // không báo warning nhờ @SuppressWarnings
    }
}
