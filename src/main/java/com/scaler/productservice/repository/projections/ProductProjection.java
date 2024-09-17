package com.scaler.productservice.repository.projections;

import com.scaler.productservice.models.Category;

public interface ProductProjection {
    Long getId();
    String getTitle();
    String getDescription();
    String getImageUrl();
    Category getCategory();
}