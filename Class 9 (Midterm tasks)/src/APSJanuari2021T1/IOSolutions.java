package APSJanuari2021T1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


class Vraboten {
    int id;
    int age;

    public Vraboten(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

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


public class IOSolutions {

    public static void alterTeams(SLL<Vraboten> devTeam, SLL<Vraboten> qaTeam) {
//        SLLNode<Vraboten> tmp = qaTeam.getFirst();
//        SLLNode<Vraboten> minNode = tmp;
//        int min = tmp.element.age;
//        while (tmp != null) {
//            if (tmp.element.age < min) {
//                min = tmp.element.age;
//                minNode = tmp;
//            }
//            tmp = tmp.succ;
//        }
//
//        int n = devTeam.length();
//        int counter = 0;
//        tmp = devTeam.getFirst();
//
//        while (tmp != null) {
//            ++counter;
//            if (counter == (n / 2) + 1) {
//                break;
//            }
//
//            tmp = tmp.succ;
//        }
//
//        devTeam.insertBefore(minNode.element, tmp);
//        qaTeam.delete(minNode);

        SLLNode<Vraboten> minNode = qaTeam.getFirst();
        SLLNode<Vraboten> iterator = minNode.succ;

        for (; iterator != null; iterator = iterator.succ) {
            if (minNode.element.age >= iterator.element.age) {
                minNode = iterator;
            }
        }
        qaTeam.delete(minNode);

        iterator = devTeam.getFirst();
        int n = devTeam.length();
        for (int i = 0; i < n / 2; i++) {
            iterator = iterator.succ;
        }
        if (n % 2 != 0) {
            iterator = iterator.succ;
        }
        devTeam.insertBefore(minNode.element, iterator);


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int devNum = Integer.parseInt(scanner.nextLine());
        int qaNum = Integer.parseInt(scanner.nextLine());
        SLL<Vraboten> devTeam = new SLL<>();
        SLL<Vraboten> qaTeam = new SLL<>();

        for (int i = 0; i < devNum; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            // parts[0] = 16100 -- parts[1] = 20
            Vraboten vraboten = new Vraboten(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            devTeam.insertLast(vraboten);
        }

        for (int i = 0; i < qaNum; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            Vraboten vraboten = new Vraboten(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            qaTeam.insertLast(vraboten);

        }
        alterTeams(devTeam, qaTeam);
        System.out.println(devTeam);
    }
}