package javaprac.gof.structural.adapter;

import javaprac.Prac;


public class AdapterPrac implements Prac {

    @Override
    public void runPrac() {
        Rectangle rect = new Rectangle(10, 3);
        System.out.println(Calculator.area(rect));

        Triangle tri = new Triangle(10.0, 9.0);
        Rectangle adaptedTri = new RectangleAdapter(tri);
        System.out.println(Calculator.area(adaptedTri));
    }

    @Override
    public String getDescription() {
        return "Design pattern - [structural] adapter";
    }
}
