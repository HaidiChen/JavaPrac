package javaprac.gof.behavioral.iterator;

import java.util.*;


public class Science implements CusIterable {

    private final List<String> subjects;

    public Science(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public CusIterator getCusIterator() {
        return new ScienceCusIterator(subjects);
    }

    private class ScienceCusIterator implements CusIterator {

        private final List<String> subjects;
        private int index;

        public ScienceCusIterator(List<String> subjects) {
            this.subjects = subjects;
            this.index = 0;
        }

        @Override
        public void first() {
            index = 0;
        }

        @Override
        public void next() {
            index += 1;
        }

        @Override
        public boolean isDone() {
            return index >= subjects.size();
        }

        @Override
        public String currentItem() {
            return subjects.get(index);
        }
    }
}
