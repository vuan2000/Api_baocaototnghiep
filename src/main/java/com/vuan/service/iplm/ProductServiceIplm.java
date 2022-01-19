package com.vuan.service.iplm;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vuan.dto.ProductDTO;
import com.vuan.model.Product;
import com.vuan.repository.ProductRepository;
import com.vuan.service.ProductService;

@Service
@Transactional
public class ProductServiceIplm implements ProductService {
	private ProductRepository ProductRepository;
	private ModelMapper modelMapper;

    public ProductServiceIplm(ProductRepository ProductRepository, ModelMapper modelMapper) {
		super();
		this.ProductRepository = ProductRepository;
		this.modelMapper = modelMapper;
	}

	@Override
    public <S extends ProductDTO> S add(S entity) {
        Product Product = ProductRepository.save(convertToEntity(entity));
        entity.setId(Product.getId());
        return entity;
    }

    @Override
    public <S extends ProductDTO> S update(S entity) {
    	Product Product = ProductRepository.save(convertToEntity(entity));
        entity.setId(Product.getId());
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        ProductRepository.deleteById(id);
    }

    @Override
    public ProductDTO findById(Long id) {
        Optional<Product> classOptional = Optional.ofNullable(ProductRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found")));
        return convertToDTO(classOptional.get());
    }

    @Override
    public List<ProductDTO> findAll() {
        return ProductRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByName(String name, Pageable pageable) {
        Page<Product> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = ProductRepository.findAll(pageable);
        }else {
            pageClass = ProductRepository.findByNameContaining(name, pageable);
        }
        return pageClass.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Long countFindByName(String name, Pageable pageable) {
        Page<Product> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = ProductRepository.findAll(pageable);
        }else {
            pageClass = ProductRepository.findByNameContaining(name, pageable);
        }
        return (long) pageClass.getContent().size();
    }

    @Override
    public Long countFindAll() {
        return (long) ProductRepository.findAll().size();
    }

    private ProductDTO convertToDTO(Product Product) {
    	ProductDTO ProductDTO = modelMapper.map(Product, ProductDTO.class);
        return ProductDTO;
    }
    
    private Product convertToEntity(ProductDTO ProductDTO) {
    	Product Product = modelMapper.map(ProductDTO, Product.class);
        return Product;
    }
}
