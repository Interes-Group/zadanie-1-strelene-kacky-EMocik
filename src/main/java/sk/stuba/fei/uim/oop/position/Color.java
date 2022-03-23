package sk.stuba.fei.uim.oop.position;

public class Color {

    public Color(){}

    final private String[] colors = {"Red", "Green", "Blue", "Cyan", "Grey", "Black"};


    public String getColor(int index){
        return colors[index];
    }


}
