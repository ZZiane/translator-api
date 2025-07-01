package org.zzach.sample;

import org.springframework.stereotype.Service;
import org.zzach.translator.annotations.Translate;

@Service
public class ProductService {

	
	@Translate(target="ar")
	public ProductDTO showProduct(ProductDTO product) {
		return product;
	}
}
