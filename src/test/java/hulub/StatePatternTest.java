package hulub;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StatePatternTest {

    @Test
    public void test1() {
        LockedCollection c = new LockedCollection(new ArrayList());
        assertThat(c.add(1), is(equalTo(true)));
        Iterator iterator = c.iterator();
        assertThat(c.add(2), is(equalTo(false)));
        iterator.next();
        assertThat(c.add(1), is(equalTo(true)));
    }

    interface Unsafe {
        boolean add(Object o);
        boolean remove(Object o);
    }

    public final class LockedCollection {
        private final Collection collection;
        private int activeIterators = 0;
        private Unsafe active = new IsActive();
        private Unsafe locked = new IsLocked();
        private Unsafe state = active;

        public LockedCollection(Collection collection) {
            this.collection = collection;
        }

        public Iterator iterator() {
            final Iterator wrapped = collection.iterator();
            ++activeIterators;
            state = locked;
            return new Iterator() {
                private boolean valid = true;

                public boolean hasNext() {
                    return wrapped.hasNext();
                }

                public Object next() {
                    Object next = wrapped.next();
                    if (!hasNext()) {
                        if (--activeIterators == 0)
                            state = active;
                        valid = false;
                    }
                    return next;
                }
            };
        }

        public int size() {
            return collection.size();
        }

        public boolean isEmpty() {
            return collection.isEmpty();
        }

        // Collection methods that don't
// change behavior are defined here.
        public boolean add(Object o) {
            return state.add(o);
        }

        public boolean remove(Object o) {
            return state.remove(o);
        }


        private final class IsActive implements Unsafe {

            public boolean add(Object o) {
                return collection.add(o);
            }

            public boolean remove(Object o) {
                return collection.remove(o);
            }
        }

        private final class IsLocked implements Unsafe {
            public boolean add(Object o) {
                return false;
            }

            public boolean remove(Object o) {
                return false;
            }
        }
    }
}
