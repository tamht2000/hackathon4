package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    static Scanner scanner = new Scanner(System.in);
    static Book[] arrBook = new Book[100];



    static int currentIndex = 0;
    public static void main(String[] args) {

        while (true) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần\t");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");

            System.out.println("Chọn chức năng từ 1-7");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:

                    inputBooks(scanner);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    sortBooksByInterest();
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    searchBooks(scanner);
                    break;
                case 6:
                    updateBook(scanner);
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Chức năng không hợp lệ, vui lòng chọn lại");
                    break;
            }
        }
    }


    private static void inputBooks(Scanner scanner) {
        // Nhập số lượng sách cần thêm
        System.out.print("Nhập số lượng sách cần thêm: ");
        int n = Integer.parseInt(scanner.nextLine());

        // Kiểm tra số lượng sách có hợp lệ không
        if (n < 1 || n > 100) {
            System.out.println("Số lượng sách không hợp lệ. Số lượng tối đa là 100.");
            return;
        }

        for (int i = 0; i < n; i++) {
            arrBook[currentIndex] = new Book();
            arrBook[currentIndex].inputData(scanner);
            currentIndex++;
        }
    }

    private static void displayAllBooks() {
        if (currentIndex == 0) {
            System.out.println("Thư viện sách đang trống.");
        } else {
            System.out.println("Thông tin tất cả sách trong thư viện:");
            for (int i =0; i < currentIndex; i++) {
                arrBook[i].displayData();
            }
        }
    }

    private static void sortBooksByInterest() {
        if (currentIndex == 0) {
            System.out.println("Thư viện sách đang trống.");
        } else {
            for (int i = 0; i < currentIndex - 1; i++) {
                for (int j = i + 1; j < currentIndex; j++) {
                    if (arrBook[i].getInterest() > arrBook[j].getInterest()) {
                        Book temp = arrBook[j];
                        arrBook[j] = arrBook[i];
                        arrBook[i] = temp;
                    }
                }
            }
            System.out.println("Sap xep cac san pham theo loi nhuan tăng dan ");
            for (int i = 0; i < currentIndex; i++) {
                arrBook[i].calculateInterest();
                System.out.println(arrBook[i].getBookName() + " " +  arrBook[i].getInterest() + "VND/sp");
            }

        }
    }

    private static void deleteBook(Scanner scanner) {
        if (currentIndex == 0) {
            System.out.println("Thư viện sách đang trống.");
            return;
        }

        while (true) {
            System.out.print("Nhập mã sách cần xóa (hoặc nhập -1 để thoát): ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            if (bookId == -1) {
                System.out.println("Đã thoát khỏi chức năng xóa sách.");
                return;
            }

            int foundIndex = -1;
            for (int i = 0; i < currentIndex; i++) {
                if (arrBook[i].getBookId() == bookId) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex != -1) {
                // Xóa sách và cập nhật lại ID của sách còn lại
                for (int i = foundIndex; i < currentIndex - 1; i++) {
                    arrBook[i] = arrBook[i + 1];
                    arrBook[i].setBookId(arrBook[i].getBookId() - 1); // Giảm ID đi 1
                }
                arrBook[currentIndex - 1] = null;
                currentIndex--;

                System.out.println("Sách đã được xóa.");
            } else {
                System.out.println("Không tìm thấy sách có mã " + bookId + ". Vui lòng nhập lại.");
            }
        }
    }

    private static void searchBooks(Scanner scanner) {
        if (currentIndex == 0) {
            System.out.println("Thư viện sách đang trống.");
            return;
        }

        boolean found = false;
        do {
            System.out.print("Nhập chuỗi tìm kiếm: ");
            String searchString = scanner.nextLine().toLowerCase();

            System.out.println("Kết quả tìm kiếm:");
            found = false;

            for (int i = 0; i < currentIndex; i++) {
                Book book = arrBook[i];
                if (book.getBookName().toLowerCase().contains(searchString)
                        || book.getDescriptions().toLowerCase().contains(searchString)) {
                    book.displayData();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Không tìm thấy kết quả. Vui lòng nhập lại.");
            }

        } while (!found);
    }

    private static void updateBook(Scanner scanner) {
        if (currentIndex == 0) {
            System.out.println("Thư viện sách đang trống.");
            return;
        }

        System.out.print("Nhập mã sách cần cập nhật: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        for (Book book : arrBook) {
            if (book.getBookId() == bookId) {
                // Cập nhật thông tin sách
                book.inputData(scanner);
                book.calculateInterest();
                System.out.println("Thông tin sách đã được cập nhật.");
                return;
            }
        }

        System.out.println("Không tìm thấy sách có mã " + bookId);
    }
}
