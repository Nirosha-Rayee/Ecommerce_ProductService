package com.example.ecommerce_productservice.repositries;

import com.example.ecommerce_productservice.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long> {
    Categories save(Categories categories);

    Categories findById(long id);

    //way 1 to write query for 1 parameter
   @Query("SELECT c.name FROM Categories c WHERE c.id = ?1")
    String findByCategoryNameById(long id);

    //way 2 to write query for 1 parameter
    @Query("SELECT c.name FROM Categories c WHERE c.id = :id")
    String findByCategoryNameById1(@Param("id") long id);

    //if 2 or more parameters
//    @Query("SELECT c.name FROM Categories c WHERE c.id = ?2")
//    String findByCategoryNameById2(long id, String name);


}
