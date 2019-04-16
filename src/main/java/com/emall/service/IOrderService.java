package com.emall.service;

import com.github.pagehelper.PageInfo;
import com.emall.commom.ServerResponse;
import com.emall.vo.OrderProductVo;
import com.emall.vo.OrderVo;

import java.util.Map;

public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse cancelOrder(Integer userId, Long orderNum);

    ServerResponse<OrderProductVo> getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    ServerResponse<PageInfo> getManageOrderList(Integer userId, int pageNum, int pageSize);

    ServerResponse<OrderVo> getManagerOrderDetail(Long orderNo);

    ServerResponse<PageInfo> searchOrderDetail(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> sendGoods(Long orderNo);
}
