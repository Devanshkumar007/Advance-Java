package com.cap.Repository;

import com.cap.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepo {
    private List<Product> productList = new ArrayList<>();
    private Long idCount = 1L;

    public List<Product> findAll(){
        return productList;
    }

    public void save(Product p){
        if(p.getId() == null){ p.setId(idCount++);
        productList.add(p);}
        else update(p);
    }

    public void deleteById(Long id){
        productList.removeIf(p-> p.getId()==id);
    }

    public void update(Product p) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == p.getId()) {
                productList.set(i, p);
                return;
            }
        }
    }

    public Product findById(Long id){
        return productList.stream().filter(a-> a.getId()==id).findFirst().orElse(null);
    }



}
