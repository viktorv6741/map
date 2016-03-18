package v3;

public class TestMyHashMap {
    public static void main(String[] args) {

        MyHashMap<Integer, String> myHashMap = new MyHashMap<Integer, String>();

        myHashMap.put(1, "first");
        myHashMap.put(2, "second");
        myHashMap.put(3, "third");

        System.out.println(myHashMap);

        myHashMap.remove(2);

        System.out.println(myHashMap);

        myHashMap.printByIndex(1);
    }
}
