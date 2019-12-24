package pet.frontend.testcase2.entity;

import java.util.UUID;


public class BaseEntity {
    private UUID id;
    private String className;
    public BaseEntity(){}
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
