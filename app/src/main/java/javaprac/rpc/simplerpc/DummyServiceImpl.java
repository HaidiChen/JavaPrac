package javaprac.rpc.simplerpc;

import java.util.*;


public class DummyServiceImpl implements DummyService {

    @Override
    public int luckyNumber(String name) {
        return new Random().nextInt(name.hashCode());
    }
}
