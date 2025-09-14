import java.util.*;

class Item {
    int id;
    String name;
    double price;
    String category;
    int quantity;

    Item(int id, String name, double price, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + " - " + name + " | Gia: " + price + " | Loai: " + category + " | Ton: " + quantity;
    }
}

public class shopManager {
    static List<Item> list = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        list.add(new Item(1, "Laptop", 1000, "Electronics", 10));
        list.add(new Item(2, "Đien thoai", 800, "Electronics", 20));
        list.add(new Item(3, "Ao thun", 20, "Clothes", 50));
        list.add(new Item(4, "Quan jean", 35, "Clothes", 30));
        list.add(new Item(5, "Banh keo", 2, "Food", 100));

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Sua thong tin san pham");
            System.out.println("2. Hien thi san pham theo gia");
            System.out.println("3. Hien thi san pham theo danh muc");
            System.out.println("4. Tong gia tri ton kho theo danh muc");
            System.out.println("5. Giam gia san pham");
            System.out.println("6. Dat hang");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int c = input.nextInt();

            switch (c) {
                case 1 -> editItem();
                case 2 -> showByPrice();
                case 3 -> showByCategory();
                case 4 -> showTotalByCategory();
                case 5 -> discount();
                case 6 -> orderItem();
                case 0 -> {
                    System.out.println("End!");
                    return;
                }
                default -> System.out.println("Chon sai!");
            }
        }
    }

    static Item getItem(int id) {
        for (Item i : list)
            if (i.id == id)
                return i;
        return null;
    }

    static void editItem() {
        System.out.print("Nhap ID san pham: ");
        int id = input.nextInt();
        Item i = getItem(id);
        if (i != null) {
            System.out.print("Gia moi: ");
            i.price = input.nextDouble();
            System.out.print("So luong moi: ");
            i.quantity = input.nextInt();
            System.out.println("Cap nhat xong!");
        } else
            System.out.println("Khong tim thay!");
    }

    static void showByPrice() {
        List<Item> sorted = new ArrayList<>(list);
        sorted.sort(Comparator.comparingDouble(it -> it.price));
        for (Item i : sorted)
            System.out.println(i);
    }

    static void showByCategory() {
        System.out.print("Nhap danh muc: ");
        String cat = input.next();
        for (Item i : list)
            if (i.category.equalsIgnoreCase(cat))
                System.out.println(i);
    }

    static void showTotalByCategory() {
        Map<String, Double> map = new HashMap<>();
        for (Item i : list) {
            double total = i.price * i.quantity;
            map.put(i.category, map.getOrDefault(i.category, 0.0) + total);
        }
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    static void discount() {
        System.out.print("Nhap ID: ");
        int id = input.nextInt();
        Item i = getItem(id);
        if (i != null) {
            System.out.print("Nhap % giam: ");
            double percent = input.nextDouble();
            i.price = i.price * (1 - percent / 100);
            System.out.println("Gia moi: " + i.price);
        } else
            System.out.println("Khong tim thay!");
    }

    static void orderItem() {
        System.out.print("Nhap ID: ");
        int id = input.nextInt();
        Item i = getItem(id);
        if (i != null) {
            System.out.print("Nhap so luong: ");
            int q = input.nextInt();
            if (q <= i.quantity) {
                double total = q * i.price;
                i.quantity -= q;
                System.out.println("Dat hang thanh cong. Tong tien: " + total);
            } else
                System.out.println("Khong du ton kho!");
        } else
            System.out.println("Khong tim thay!");
    }
}
