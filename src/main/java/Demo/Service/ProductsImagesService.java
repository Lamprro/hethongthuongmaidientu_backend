package Demo.Service;

import Demo.Enity.ProductsImages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductsImagesService {
    public ResponseEntity<?> uploadImage(MultipartFile[] files, int productsId);
    public ResponseEntity<?> deleteImage(int imageId);
    public Page<ProductsImages> getImages(int productsId,Pageable pageable);
    public ResponseEntity<?> updateImage(int imageId,MultipartFile multipartFile);
}
