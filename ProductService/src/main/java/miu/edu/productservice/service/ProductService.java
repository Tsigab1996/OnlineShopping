package miu.edu.productservice.service;

import miu.edu.productservice.domain.Product;
import miu.edu.productservice.domain.ProductDTO;
import miu.edu.productservice.repository.ProductRespository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRespository productRespository;
    private final ModelMapper modelMapper;
    public ProductService(ProductRespository productRespository, ModelMapper modelMapper) {
        this.productRespository = productRespository;
        this.modelMapper = modelMapper;
    }
    public ProductDTO getProduct(int id) {
        Product product = productRespository.findById(id).orElse(null);
        return modelMapper.map(product, ProductDTO.class);
    }
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productRespository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
    public List<ProductDTO> getAllProducts() {
        return productRespository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }
    public ProductDTO deleteProduct(int id) {

        return productRespository.findById(id)
                .map(product -> {
                    productRespository.deleteById(id);
                    return modelMapper.map(product, ProductDTO.class);
                })
                .orElse(null);
    }
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return productRespository.findById(productDTO.getId())
                .map(product -> {
                    product.setName(productDTO.getName());
                    product = productRespository.save(product);
                    return modelMapper.map(product, ProductDTO.class);
                })
                .orElse(null);
    }
}
