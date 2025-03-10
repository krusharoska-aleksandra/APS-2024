package Task;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + "->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public Iterator<E> iterator() {
        // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }

    // //////////Inner class ////////////

    private class LRIterator<E> implements Iterator<E> {

        private SLLNode<E> place, curr;

        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext() {
            return (place != null);
        }

        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }

        public void remove() {
            //Not implemented
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }

    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }
}

class Task
{
    private int id;
    private int hours;
    private int priority;

    public Task(int id, int hours, int priority) {
        this.id = id;
        this.hours = hours;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public int getPriority() {
        return priority;
    }
    public int calculatePriority()
    {
        return hours*priority*2;
    }
    @Override
    public String toString()
    {
        return String.valueOf(id);
    }

}
public class TaskResolved {

// Слична задача како Client што ја решававме вo Class 6 и 7 фолдерот

//Test Case
//3
//3
//111 1 1
//222 2 4
//333 1 1
//444 2 7
//555 4 6
//666 2 9

//111 333 444
//222 555 666
//
//Test Case #2
//3
//2
//10 4 5
//20 2 8
//35 6 1
//7 12 4
//8 11 2

//20 35 8
//10 7

    public static void work(SLL<Task> toDo, SLL<Task> inProgress) {
//        SLLNode<Task> toDoIterator = toDo.getFirst().succ;
//        SLLNode<Task> najvazhen = toDo.getFirst();
//
//        while (toDoIterator != null) {
//            if (toDoIterator.element.calculatePriority() > najvazhen.element.calculatePriority()) {
//                najvazhen = toDoIterator;
//            }
//            toDoIterator = toDoIterator.succ;
//        }
//
//        SLLNode<Task> inProgressIterator = inProgress.getFirst().succ;
//        SLLNode<Task> nebiten = inProgress.getFirst();
//        while (inProgressIterator != null) {
//            if (inProgressIterator.element.calculatePriority() < nebiten.element.calculatePriority()) {
//                nebiten = inProgressIterator;
//            }
//            inProgressIterator = inProgressIterator.succ;
//        }
//
//        toDo.delete(najvazhen);
//        inProgress.insertFirst(najvazhen.element);
//        inProgress.delete(nebiten);
//        toDo.insertLast(nebiten.element);

        SLLNode<Task> mostImportant = toDo.getFirst();
        SLLNode<Task> iterator1 = mostImportant.succ;

        while (iterator1 != null) {
            if (mostImportant.element.calculatePriority() < iterator1.element.calculatePriority()) {
                mostImportant = iterator1;
            }
            iterator1 = iterator1.succ;
        }

        SLLNode<Task> leastImportant = inProgress.getFirst();
        SLLNode<Task> iterator2 = leastImportant.succ;

        while (iterator2 != null) {
            if (leastImportant.element.calculatePriority() > iterator2.element.calculatePriority()) {
                leastImportant = iterator2;
            }
            iterator2 = iterator2.succ;
        }

        toDo.delete(mostImportant);
        inProgress.insertFirst(mostImportant.element);
        inProgress.delete(leastImportant);
        toDo.insertLast(leastImportant.element);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNormal = Integer.parseInt(scanner.nextLine());
        int numGolden = Integer.parseInt(scanner.nextLine());

        SLL<Task> normal = new SLL<Task>();
        SLL<Task> golden = new SLL<Task>();

        for (int i = 0; i < numNormal; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            normal.insertLast(new Task(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < numGolden; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            golden.insertLast(new Task(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        work(normal, golden);
        System.out.println(normal.toString());
        System.out.println(golden.toString());
    }
}