package Demo.Service;

import Demo.DAO.ProductsDAO;
import Demo.DAO.ProductsImagesDAO;
import Demo.Enity.Notification;
import Demo.Enity.Products;
import Demo.Enity.ProductsImages;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductsImagesServiceImplement implements ProductsImagesService {

    @Autowired
    private ProductsImagesDAO productsImagesDAO;
    @Autowired
    private ProductsDAO productsDAO;
    @Autowired
    private Cloudinary cloudinary;


    @Override
    @Transactional
    public ResponseEntity<?> uploadImage(MultipartFile[] files, int productsId)  {
        try {
            Products products = productsDAO.findById(productsId)
                    .orElseThrow(() -> new RuntimeException("Product không tồn tại"));
            List<ProductsImages> images = new ArrayList<>();
            for (MultipartFile file : files) {
                Map uploadResult = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap(
                                "folder", "products/" + productsId
                        )
                );
                ProductsImages img = new ProductsImages();
                img.setImageUrl(uploadResult.get("secure_url").toString());
                img.setPublicId(uploadResult.get("public_id").toString());
                img.setProducts(products);
                productsImagesDAO.create(img);
                images.add(img);
            }
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new Notification("Lỗi khi upload ảnh"));
        }
    }
    @Override
    @Transactional
    public ResponseEntity<?> deleteImage(int imageId) {
        try {
            ProductsImages image = productsImagesDAO.findById(imageId)
                    .orElseThrow(() -> new RuntimeException("Ảnh không tồn tại"));

            cloudinary.uploader().destroy(
                    image.getPublicId(),
                    ObjectUtils.emptyMap()
            );
            productsImagesDAO.delete(imageId);
            return ResponseEntity.ok("Xóa ảnh thành công");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new Notification("Lỗi khi xóa ảnh"));
        }
    }

    @Override
    public Page<ProductsImages> getImages(int productsId, Pageable pageable) {
        try {
            return productsImagesDAO.findByProductsId(productsId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty(pageable);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateImage(int imageId, MultipartFile newFile) {

        try {

            ProductsImages oldImage = productsImagesDAO.findById(imageId)
                    .orElseThrow(() -> new RuntimeException("Ảnh không tồn tại"));

            cloudinary.uploader().destroy(
                    oldImage.getPublicId(),
                    ObjectUtils.emptyMap()
            );


            Map uploadResult = cloudinary.uploader().upload(
                    newFile.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "products/" + oldImage.getProducts().getProductsId()
                    )
            );

            String newUrl = uploadResult.get("secure_url").toString();
            String newPublicId = uploadResult.get("public_id").toString();


            oldImage.setImageUrl(newUrl);
            oldImage.setPublicId(newPublicId);

            productsImagesDAO.create(oldImage);

            return ResponseEntity.ok(oldImage);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new Notification("Lỗi khi cập nhật ảnh"));
        }
    }

}
