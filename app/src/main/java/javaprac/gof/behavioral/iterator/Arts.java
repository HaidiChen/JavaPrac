package javaprac.gof.behavioral.iterator;


public class Arts implements CusIterable {

    private final String[] subjects;

    public Arts(String[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public CusIterator getCusIterator() {
        return new ArtsCusIterator(subjects);
    }

    private class ArtsCusIterator implements CusIterator {

        private final String[] subjects;
        private int index;

        public ArtsCusIterator(String[] subjects) {
            this.subjects = subjects;
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
            return index >= subjects.length;
        }

        @Override
        public String currentItem() {
            return subjects[index];
        }
    }
}
