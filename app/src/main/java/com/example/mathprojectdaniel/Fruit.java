package com.example.mathprojectdaniel;

public class Fruit {
    private String name;
    private int drawable;

    // Constructor
    public Fruit(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for drawable
    public int getDrawable() {
        return drawable;
    }

    // Setter for drawable
    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
