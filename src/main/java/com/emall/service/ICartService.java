package com.emall.service;

import com.emall.commom.ServerResponse;
import com.emall.vo.CartVo;

public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> delete(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> checkedOrUnchecked(Integer userId, Integer checkedStatus, Integer productId);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
