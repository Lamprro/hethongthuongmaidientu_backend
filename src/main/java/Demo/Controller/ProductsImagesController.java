package Demo.Controller;

import Demo.Enity.ProductsImages;
import Demo.Service.ProductsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products_images")
public class ProductsImagesController {
    @Autowired
    private ProductsImagesService productsImagesService;

    @PostMapping("/upload/{productId}")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile[] files, @PathVariable int productId) {
        return productsImagesService.uploadImage(files, productId);
    }

    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<?> delete(@PathVariable int imageId) {
        return productsImagesService.deleteImage(imageId);
    }

    @GetMapping("/{productId}")
    public Page<ProductsImages> getImages(@PathVariable int productId, Pageable pageable) {
        return productsImagesService.getImages(productId, pageable);
    }

    @PutMapping("/update/{imageId}")
    public ResponseEntity<?> updateImage(
            @PathVariable int imageId,
            @RequestParam("file") MultipartFile newFile
    ) {
        return productsImagesService.updateImage(imageId, newFile);
    }


}
