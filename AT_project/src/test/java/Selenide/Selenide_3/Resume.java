package Selenide.Selenide_3;

public class Resume {

    private final String gender;
    private final String city;
    private final int age;
    private final boolean isNumberConfirmed;
    private final boolean isReadyToRelocate;

    public Resume(String gender,  int age, String city, boolean isNumberConfirmed, boolean isReadyToRelocate) {
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.isNumberConfirmed = isNumberConfirmed;
        this.isReadyToRelocate = isReadyToRelocate;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public boolean isNumberConfirmed() {
        return isNumberConfirmed;
    }

    public boolean isReadyToRelocate() {
        return isReadyToRelocate;
    }
}
