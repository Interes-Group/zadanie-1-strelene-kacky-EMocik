package sk.stuba.fei.uim.oop.position;

public class Color {

    public Color(){}

    final private String[] colors = {"Red", "Green", "Blue",
            "Purple", "Yellow", "Black"};


    public String getColor(int index){
        return colors[index];
    }


}
