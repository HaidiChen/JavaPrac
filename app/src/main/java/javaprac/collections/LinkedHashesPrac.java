package javaprac.collections;

import java.util.*;
import java.time.*;

import javaprac.Prac;


public class LinkedHashesPrac implements Prac {

    @Override
    public void runPrac() {
        /*
         * LinkedHashSet & LinkedHashMap remember the insertion order.
         */
        Set<String> lhs = new LinkedHashSet<>();
        lhs.add("hello");
        lhs.add("world");
        lhs.add(null);
        lhs.add("howdy");
        System.out.println("LinkedHashSet output:");
        lhs.forEach(System.out::println);

        Map<Integer, String> lhm = new LinkedHashMap<>();
        lhm.put(1, "one");
        lhm.put(22, "two");
        lhm.put(33, "three");
        lhm.put(4, "four");
        System.out.println("LinkedHashMap output:");
        lhm.forEach((k, v) -> System.out.format("<%d, %s>\n", k, v));

        /*
         * HashSet & HashMap don't remember the insertion order.
         */
        Set<String> hs = new HashSet<>();
        hs.add("hello");
        hs.add("world");
        hs.add(null);
        hs.add("howdy");
        System.out.println("HashSet output:");
        hs.forEach(System.out::println);

        Map<Integer, String> hm = new HashMap<>();
        hm.put(1, "one");
        hm.put(22, "two");
        hm.put(33, "three");
        hm.put(4, "four");
        System.out.println("HashMap output:");
        hm.forEach((k, v) -> System.out.format("<%d, %s>\n", k, v));

        /*
         * LRUCache implemented with LinkedHashMap.
         */
        LRUCache<Integer, String> lru = new LRUCache<>(3);

        lru.cache(1, "one");
        lru.cache(2, "two");
        lru.cache(3, "three");

        lru.cache(4, "four");
        lru.cache(3, "four");
        System.out.println("final cache: " + lru);
    }

    @Override
    public String getDescription() {
        return "Practice LinkedHashSet/Map usage.";
    }
}

class LRUCache<K, V> {

    private final Map<K, V> caches = new LinkedHashMap<K, V>();
    private final int maxCapacity;

    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void cache(K key, V value) {
        if (caches.containsKey(key)) {
            caches.remove(key);
        } else if (caches.size() >= maxCapacity) {
            Iterator<K> iter = caches.keySet().iterator();
            iter.next();
            iter.remove();
        }

        caches.put(key, value);
        System.out.println("current cache: " + caches);
    }

    @Override
    public String toString() {
        return caches.toString();
    }
}
