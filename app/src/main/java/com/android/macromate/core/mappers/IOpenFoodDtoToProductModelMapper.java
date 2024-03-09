package com.android.macromate.core.mappers;

import com.android.macromate.data.model.MockProduct;
import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;
import com.android.macromate.integration.mappers.OpenFoodFactsProductMapper;

public interface IOpenFoodDtoToProductModelMapper {

    MockProduct map(OpenFoodFactsProductDTO dto);

}
