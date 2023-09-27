package ra.bussiness;

import java.util.Scanner;

public class Book {
    private static int nextId = 1;
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean status;

    public Book() {
        this.bookId = nextId++;
        this.status = true;
    }

    public Book(int bookId, String bookName, String author, String descriptions, double importPrice, double exportPrice, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.status = status;
        calculateInterest();

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        if (descriptions.length() >= 10) {
            this.descriptions = descriptions;
        } else {
            System.out.println("Mô tả phải chứa ít nhất 10 ký tự.");
        }
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        if (importPrice > 0) {
            this.importPrice = importPrice;
        } else {
            System.out.println("Giá nhập phải lớn hơn 0.");
        }
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        if (exportPrice > 1.2 * importPrice) {
            this.exportPrice = exportPrice;
        } else {
            System.out.println("Giá xuất phải lớn hơn 1.2 lần giá nhập.");
        }
    }

    public float getInterest() {
        return interest;
    }

    public void setInteres(float interest) {
        this.interest = interest;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void calculateInterest() {
        this.interest = (float) (exportPrice - importPrice);
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên sách: ");
        String name = scanner.nextLine();
        while (name.isEmpty()) {
            System.out.println("Tên sách không được để trống. Vui lòng nhập lại.");
            System.out.print("Nhập tên sách: ");
            name = scanner.nextLine();
        }
        this.setBookName(name);

        System.out.print("Nhập tác giả: ");
        String author = scanner.nextLine();
        while (author.isEmpty()) {
            System.out.println("Tên tác giả không được để trống. Vui lòng nhập lại.");
            System.out.print("Nhập tác giả: ");
            author = scanner.nextLine();
        }
        this.setAuthor(author);

        System.out.print("Nhập mô tả sách (ít nhất 10 ký tự): ");
        String description = scanner.nextLine();
        while (description.length() < 10) {
            System.out.println("Mô tả sách phải chứa ít nhất 10 ký tự. Vui lòng nhập lại.");
            System.out.print("Nhập mô tả sách: ");
            description = scanner.nextLine();
        }
        this.setDescriptions(description);

        System.out.print("Nhập giá nhập: ");
        double importPrice = scanner.nextDouble();
        while (importPrice <= 0) {
            System.out.println("Giá nhập phải lớn hơn 0. Vui lòng nhập lại.");
            System.out.print("Nhập giá nhập: ");
            importPrice = scanner.nextDouble();
        }
        this.setImportPrice(importPrice);

        System.out.print("Nhập giá xuất: ");
        double exportPrice = scanner.nextDouble();
        while (exportPrice <= 1.2 * importPrice) {
            System.out.println("Giá xuất phải lớn hơn 1.2 lần giá nhập. Vui lòng nhập lại.");
            System.out.print("Nhập giá xuất: ");
            exportPrice = scanner.nextDouble();
        }
        this.setExportPrice(exportPrice);

    }

    public void displayData(){
        System.out.printf("ID: %s - Tên: %s - Tác giả: %s - Mô tả: %s - Giá nhập: %2f - Giá xuất: %2f - Trạng thái: %s\n", this.bookId, this.bookName, this.author,this.descriptions,this.importPrice,this.exportPrice,this.status ? "Đang bán" : "Không bán");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}
