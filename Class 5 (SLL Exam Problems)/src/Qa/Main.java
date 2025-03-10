package Qa;

import java.util.Scanner;

//Податоци за вработените во компанијата IOSolutions се чуваат во две еднострано поврзани листи. Во првата листа се чуваат податоците
// за вработените кои се дел од тимот Dev,
//а додека пак во втората се чуваат податоците за вработените кои се дел од тимот Qa.
//И кај двете листи, во секој од јазлите се чуваат број за идентификација (id) и возраста на вработениот за кој се однесува тој јазел.
//
//Со зголемување на обемот на работа, потребно е да се зголеми бројот на членови во Dev тимот.
//За таа цел, главниот менаџер решил да го отстрани најмладиот член од Qa тимот и да го додаде кон Dev тимот.
//Тоа значи дека потребно е од листата каде се чуваат членовите на Qa тимот да се отстрани (избрише) најмладиот член (доколку има повеќе, последниот таков член),
//и истиот да се додаде на средина на листата каде што се чуваат членовите на Dev тимот.
//
//Влез:
//
//Во првиот ред е даден бројот на вработени кои се дел од Dev тимот. Во вториот ред е даден бројот на вработени кои се дел од Qa тимот.
//Во следните редови се дадени паровите податоци за секој вработен, одделени со празно место, во формат id возраст.
//
//Излез:
//
//Во првиот ред id на сите вработени кои се дел од Dev тимот. Во вториот ред id на сите вработени кои се дел од Qa тимот.
//
//Забелешка:
//
//Даден е целосниот код на структурата која што треба да се користи.
//Потребно е да направат дополнувања во main функцијата и да се имплементирана void alterTeams(SLL devTeam, SLL qaTeam) функцијата.
//Притоа, бришењето треба да биде имплементирано како бришење на цел јазел, а додавањето како додавање на цел јазел.
//Промените (бришење/додавање елемент) не треба да се однесуваат на информациите во самите јазли туку во промени на врските помеѓу јазлите.
//
//


class SLLNode {
    int id;
    int age;
    SLLNode succ;

    public SLLNode(int id, int age, SLLNode succ) {
        this.id = id;
        this.age = age;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;
    }

    public void deleteFirst() {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if (first == node) {
                this.deleteFirst();
                return;
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }

    }

    public void insertFirst(int id, int age) {
        SLLNode ins = new SLLNode(id, age, first);
        first = ins;
    }

    public void insertAfter(int id, int age, SLLNode node) {
        if (node != null) {
            SLLNode ins = new SLLNode(id, age, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(int id, int age, SLLNode before) {

        if (first != null) {
            SLLNode tmp = first;
            if (first == before) {
                this.insertFirst(id, age);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode ins = new SLLNode(id, age, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }


    public void insertLast(int id, int age) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, age, null);
            tmp.succ = ins;
        } else {
            insertFirst(id, age);
        }
    }

    public SLLNode getFirst() {
        return first;
    }

    @Override
    public String toString() {
        String s = new String();
        SLLNode dvizi = first;
        while (dvizi != null) {
            s = s + dvizi.id + " ";
            dvizi = dvizi.succ;
        }
        return s;
    }
}


//3
//3
//16100 20
//17200 30
//18112 45
//14102 19
//19203 35
//18900 19
//16100 17200 18900 18112
//14102 19203

//2
//3
//16100 20
//17200 30
//19898 20
//14203 35
//18100 19
//16100 18100 17200
//19898 14203

//Со зголемување на обемот на работа, потребно е да се зголеми бројот на членови во Dev тимот.
//За таа цел, главниот менаџер решил да го отстрани најмладиот член од Qa тимот и да го додаде кон Dev тимот.
//Тоа значи дека потребно е од листата каде се чуваат членовите на Qa тимот да се отстрани (избрише) најмладиот член (доколку има повеќе, последниот таков член),
//и истиот да се додаде на средина на листата каде што се чуваат членовите на Dev тимот.


public class Main {

    // todo: complete function
    public static void alterTeams(SLL devTeam, SLL qaTeam) {
        // TODO: За дома!!!
        SLLNode qaYoungest = qaTeam.getFirst();
        SLLNode qaIterator = qaTeam.getFirst().succ;

        while (qaIterator != null) {
            if (qaYoungest.age >= qaIterator.age) {
                qaYoungest = qaIterator;
            }
            qaIterator = qaIterator.succ;
        }

        qaTeam.delete(qaYoungest);

        SLLNode findTheMiddle = devTeam.getFirst();
        for (int i = 0; i < devTeam.length() / 2; i++) {
            findTheMiddle = findTheMiddle.succ;
        }

        if (devTeam.length() % 2 == 0) {
            devTeam.insertBefore(qaYoungest.id, qaYoungest.age, findTheMiddle);
        } else {
            devTeam.insertAfter(qaYoungest.id, qaYoungest.age, findTheMiddle);
        }

    }

    public static void main(String[] args) {
        SLL dev = new SLL();
        SLL qa = new SLL();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();

        for (int i = 0; i < n; i++) {
            int id = input.nextInt();
            int age = input.nextInt();
            dev.insertLast(id, age);
        }
        for (int j = 0; j < m; j++) {
            int id = input.nextInt();
            int age = input.nextInt();
            qa.insertLast(id, age);
        }
        alterTeams(dev, qa);
        System.out.println(dev);
        System.out.println(qa);


    }
}