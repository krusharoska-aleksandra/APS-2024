package Mirror;
import java.util.Scanner;

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
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

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if(after==last){
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if(before == first){
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if(node==first){
            deleteFirst();
            return node.element;
        }
        if(node==last){
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void izvadiDupliIPrebroj(){

    }
}
class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

//        vlez: 1 2 3 4 5
//        izlez: 1 5 2 4 3;
//        vlez: 1 4 8 6 3 5 7;
//        izlez: 1 7 4 5 8 3 6;
//        se spojuvaat prv so posleden, vtor so predposleden itn.

public class Main
{
    public static void mirror(DLL<Integer>lista) {
//        DLLNode<Integer> first = lista.getFirst();
//        DLLNode<Integer> last = lista.getLast();
//
//        while(first != last) {
//            first = first.succ;
//            if(first == null || last == null) break;
//            lista.insertBefore(last.element, first);
//            lista.delete(last);
//            last = last.pred;
//
//        }
//        DLLNode<Integer> first = lista.getFirst();
//        DLLNode<Integer> last = lista.getLast();
//
//        first = first.succ;
//
//        while (first != last && first != null) {
//            lista.insertBefore(last.element, first);
//            lista.delete(last); // null
//            last = last.pred;// pred null
//            first = first.succ;
//        }

        DLLNode<Integer> first = lista.getFirst();
        DLLNode<Integer> last = lista.getLast();

        while (first != last && first != null) {
            lista.insertAfter(last.element, first);
            lista.delete(last);
            last = last.pred;
            first = first.succ.succ;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        DLL<Integer> lista = new DLL();
        for(int i = 0;i < N;i++){
            int broj = input.nextInt();
            lista.insertLast(broj);
        }
        mirror(lista);
        System.out.println(lista);
    }
}