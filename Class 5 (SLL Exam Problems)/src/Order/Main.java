package Order;

import java.util.Scanner;
//    Во рамки на една фабрика се користи систем за управуване со нарачки. За секоjа нарачка се чува податок за: іd на нарачката
//    (int id), продукт. (int product), како и приоритет (Int priority).
//    Приоритетот е вредност во ранг [1,10], каде што приоритет 1 означува највисок приоритет, а продукт е Id на соодветветниот
//          продукт кој се нарачува

//        Во рамки на еден ден во фабриката стигнуваат многу нарачки кои се чуваат во две еднострано поврзани листи.
//        Во првата листа се чуваат нарачките кои се активни (Active листата), а во втората листа се
//        чуваат нарачките кои се испратени за достава (Shipping листата)

//        Ваша задача е да испроцесирате две нарачки.
//        Процесирањето на нарачка се прави на следниот начин: се отстранува наjприоритетната нарачка од Active листата и
//        се става на крај на shipping листата

//        Внимавајте: Доколку има повеке нарачки со ист приоритет, се зема последната
//         Влез:
//        Во првиот ред е даден броот на нарачки од Active листата
//        Во вториот рел е ладен бро от на нарачки од Shipping листата
//        Во секој следен ред се дадени податоци за една нарачка, одделени со празно место, во формат іd product priorty.
//        Притоа, прво се дадени нарачките од Active листата, по што следуваат податоците за нарачките од Shipping листата

//        Излез:
//        Во првиот ред іd на сите нарачки од Аctive листата
//        Во вториот ред на id сите нарачки on Shipping листата

//        Забелешка:
//        Даден е целосниот код на структурата козашто треба да се користи. Дадена е и тест класата Factory java,
//        со целосно имплементиран input и outut. Потребно е да се менува само во рамки на уолд
//        orders (SI.L <Order» active._SI.L <Order» shipping) функциата.
//        Притоа, бришеньето треба да биде имплементирано како оришенье на цел зазол,
//        а додаването како додавае на цел зазол. промените (орише е/додаване елемент) не треба да се однесуваат
//        на информациите во самите зазли туку во протони
//        на воските помегу газлит
class Order {
    private int id;
    private int product;
    private int priority;

    public Order(int id, int product, int priority) {
        this.id = id;
        this.product = product;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
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
}

//        Ваша задача е да испроцесирате две нарачки.
//        Процесирањето на нарачка се прави на следниот начин: се отстранува наjприоритетната нарачка од Active листата и
//        се става на крај на shipping листата

class Factory {

    public static void orders(SLL<Order> active, SLL<Order> shipping) {
        // TODO: За дома!!!!
        SLLNode<Order> mostPriority = active.getFirst();
        SLLNode<Order> activeInterator = active.getFirst().succ;

        while (activeInterator != null) {
            if (mostPriority.element.getPriority() >= activeInterator.element.getPriority()) {
                mostPriority = activeInterator;
            }
            activeInterator = activeInterator.succ;
        }

        active.delete(mostPriority);
        shipping.insertLast(mostPriority.element);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numActive = Integer.parseInt(scanner.nextLine());
        int numShipping = Integer.parseInt(scanner.nextLine());

        SLL<Order> active = new SLL<Order>();
        SLL<Order> shipping = new SLL<Order>();

        for (int i = 0; i < numActive; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            active.insertLast(new Order(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < numShipping; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            shipping.insertLast(new Order(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        orders(active, shipping);
        System.out.println(active.toString());
        System.out.println(shipping.toString());
    }
}