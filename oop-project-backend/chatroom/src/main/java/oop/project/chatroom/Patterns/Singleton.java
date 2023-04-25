package oop.project.chatroom.Patterns;

public class Singleton {
    private static Singleton instance;
    private Long id;

    public Long getId() {
        return id;
    }

    public Singleton(Long id) {
        this.id = id;
    }

    public static synchronized Singleton getInstance(Long id){
        if(instance == null){
            instance = new Singleton(id);
        }
        return instance;

    }

}
