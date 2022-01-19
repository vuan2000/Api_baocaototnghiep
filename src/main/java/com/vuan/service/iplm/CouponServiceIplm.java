package com.vuan.service.iplm;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vuan.dto.CouponDTO;
import com.vuan.model.Coupon;
import com.vuan.repository.CouponRepository;
import com.vuan.service.CouponService;

@Service
public class CouponServiceIplm implements CouponService{
	private CouponRepository couponRepository;
	private ModelMapper modelMapper;

    public CouponServiceIplm(CouponRepository couponRepository, ModelMapper modelMapper) {
		super();
		this.couponRepository = couponRepository;
		this.modelMapper = modelMapper;
	}

	@Override
    public <S extends CouponDTO> S add(S entity) {
        Coupon Coupon = couponRepository.save(convertToEntity(entity));
        entity.setId(Coupon.getId());
        return entity;
    }

    @Override
    public <S extends CouponDTO> S update(S entity) {
    	Coupon Coupon = couponRepository.save(convertToEntity(entity));
        entity.setId(Coupon.getId());
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public CouponDTO findById(Long id) {
        Optional<Coupon> classOptional = Optional.ofNullable(couponRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Coupon not found")));
        return convertToDTO(classOptional.get());
    }

    @Override
    public List<CouponDTO> findAll() {
        return couponRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CouponDTO> findByName(String name, Pageable pageable) {
        Page<Coupon> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = couponRepository.findAll(pageable);
        }else {
            pageClass = couponRepository.findByNameContaining(name, pageable);
        }
        return pageClass.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Long countFindByName(String name, Pageable pageable) {
        Page<Coupon> pageClass;
        if(name == null || name.isEmpty()) {
            pageClass = couponRepository.findAll(pageable);
        }else {
            pageClass = couponRepository.findByNameContaining(name, pageable);
        }
        return (long) pageClass.getContent().size();
    }

    @Override
    public Long countFindAll() {
        return (long) couponRepository.findAll().size();
    }

    private CouponDTO convertToDTO(Coupon coupon) {
    	CouponDTO CouponDTO = modelMapper.map(coupon, CouponDTO.class);
        return CouponDTO;
    }
    
    private Coupon convertToEntity(CouponDTO couponDTO) {
    	Coupon coupon = modelMapper.map(couponDTO, Coupon.class);
        return coupon;
    }
}
