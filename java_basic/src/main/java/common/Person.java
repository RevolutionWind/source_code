package common;

/**
 * @author walker
 * @date 2020-07-10
 */
public class Person {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String telNum;

    /**
     * 爱人
     */
    private Person lover;

    public Person(){}

    public Person(String name, Integer age, Integer sex, String telNum) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.telNum = telNum;
    }

    public Person(String name, Integer age, Integer sex, String telNum, Person lover) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.telNum = telNum;
        this.lover = lover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public Person getLover() {
        return lover;
    }

    public void setLover(Person lover) {
        this.lover = lover;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", telNum='" + telNum + '\'' +
                ", lover=" + lover +
                '}';
    }
}
