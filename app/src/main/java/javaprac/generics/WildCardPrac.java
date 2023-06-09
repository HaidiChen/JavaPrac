package javaprac.generics;

import java.util.*;

import javaprac.Prac;


public class WildCardPrac implements Prac {

    @Override
    public void runPrac() {
        List<? super C> objs = new ArrayList<>(List.of(new A()));
        /*
         * Can only write type C or its sub types to 'objs'
         */
        objs.add(new D());
        objs.add(new C());
        /*
         * Can't write super type of C to 'objs'. Following line won't compile
         */
        //objs.add(new A());

        System.out.println(objs);
        objs.forEach(o -> System.out.println(o.getClass()));

        List<? extends C> anotherObjs = List.of(new C(), new D());
        /*
         * Write operation isn't allowed for type <? extends E>. Following lines won't compile
         */
        //anotherObjs.add(new C());
        //anotherObjs.add(new D());
        System.out.println(anotherObjs);
        anotherObjs.forEach(o -> System.out.println(o.getClass()));
    }

    @Override
    public String getDescription() {
        return "Practice wildcard usage.";
    }
}

/*
 *        Class A
 *         ^   ^
 *        /     \
 *    Class B,  Class C
 *                ^
 *                |
 *              Class D
 */
class A {

    @Override
    public String toString() {
        return "this is A";
    }
}

class B extends A {

    @Override
    public String toString() {
        return "this is B";
    }
}

class C extends A {

    @Override
    public String toString() {
        return "this is C";
    }
}

class D extends C {

    @Override
    public String toString() {
        return "this is D";
    }
}
