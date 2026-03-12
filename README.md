# Hệ Thống Thương Mại Điện Tử (Backend)

Đây là backend cho hệ thống thương mại điện tử, được xây dựng bằng Java Spring Boot. Hệ thống cung cấp các API RESTful để quản lý người dùng, sản phẩm, đơn hàng, giỏ hàng, và các chức năng liên quan khác.

## Công nghệ sử dụng

*   **Java**: 17
*   **Framework**: Spring Boot 3.5.7
*   **Cơ sở dữ liệu**: SQL Server
*   **Bảo mật**: Spring Security, JWT (JSON Web Token)
*   **Lưu trữ hình ảnh**: Cloudinary
*   **Build Tool**: Maven

## Cài đặt và Chạy ứng dụng

1.  **Clone repository:**
    ```bash
    git clone `https://github.com/Lamprro/hethongthuongmaidientu_backend`
    cd hethongthuongmaidientu_backend
    ```

2.  **Cấu hình cơ sở dữ liệu:**
    *   Tạo cơ sở dữ liệu SQL Server tên là `HE_THONG_THUONG_MAI_DIEN_TU`.
    *   Mở file `src/main/resources/application.properties` và cập nhật thông tin kết nối database (username, password) nếu cần thiết:
        ```properties
        spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=HE_THONG_THUONG_MAI_DIEN_TU;encrypt=false
        spring.datasource.username=sa
        spring.datasource.password=<your_password>
        ```

3.  **Cấu hình Cloudinary (Tùy chọn):**
    *   Nếu bạn muốn sử dụng tính năng upload ảnh, hãy cập nhật thông tin Cloudinary trong `application.properties`:
        ```properties
        cloudinary.cloud_name=<your_cloud_name>
        cloudinary.api_key=<your_api_key>
        cloudinary.api_secret=<your_api_secret>
        ```

4.  **Chạy ứng dụng:**
    ```bash
    mvn spring-boot:run
    ```
    Ứng dụng sẽ chạy tại `http://localhost:8080`.

## Cấu trúc dự án

Dự án được tổ chức theo mô hình Layered Architecture:

*   `src/main/java/Demo`
    *   `Controller`: Chứa các REST Controller xử lý HTTP requests (e.g., `AccountController`, `ProductsController`, `CartsController`).
    *   `Service`: Chứa logic nghiệp vụ (Business Logic).
    *   `DAO`: Data Access Object (Repository) để tương tác với cơ sở dữ liệu.
    *   `Enity`: Các Java Bean ánh xạ với các bảng trong cơ sở dữ liệu.
    *   `Security`: Cấu hình Spring Security và xử lý JWT.
    *   `Config`: Các lớp cấu hình khác (ví dụ: Cloudinary).
    *   `DTO`: Data Transfer Objects.

## Các tính năng chính (API)

Hệ thống cung cấp các API cho các chức năng sau:

### Quản lý Tài khoản & Xác thực (`/accounts`)
*   Đăng ký tài khoản người dùng.
*   Đăng nhập (trả về JWT Token).
*   Lấy thông tin tài khoản.
*   Đổi mật khẩu.

### Quản lý Sản phẩm (`/products`)
*   Tạo mới, cập nhật sản phẩm.
*   Tìm kiếm sản phẩm theo tên và danh mục (có phân trang).
*   Xem chi tiết sản phẩm.
*   Quản lý hình ảnh sản phẩm, đánh giá sản phẩm.

### Quản lý Đơn hàng (`/orders`)
*   Tạo đơn hàng.
*   Xem lịch sử đơn hàng.
*   Chi tiết đơn hàng.

### Quản lý Giỏ hàng (`/carts`)
*   Thêm sản phẩm vào giỏ.
*   Cập nhật số lượng, xóa sản phẩm khỏi giỏ.

### Khác
*   **Cửa hàng (`/stores`)**: Quản lý thông tin cửa hàng.
*   **Danh mục (`/categories`)**: Quản lý danh mục sản phẩm.
*   **Khuyến mãi (`/promotions`)**: Quản lý các chương trình khuyến mãi.
*   **Báo cáo (`/reports`)**: Các API hỗ trợ báo cáo thống kê.

