package Model;

import java.util.Objects;

public class Player {
    private String name;
    private String surname;
    private int age;
    private String team;
    private double value;

    public Player(String name, String surname, int age, String team, double value) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.team = team;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return age == player.age && Double.compare(value, player.value) == 0 && Objects.equals(name, player.name) && Objects.equals(surname, player.surname) && Objects.equals(team, player.team);
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", team='" + team + '\'' +
                ", age=" + age +
                ", value=" + value +
                '}';
    }
}
