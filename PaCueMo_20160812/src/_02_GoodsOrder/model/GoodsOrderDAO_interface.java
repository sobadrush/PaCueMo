package _02_GoodsOrder.model;

import java.util.List;

public interface GoodsOrderDAO_interface
{
	public void insert(GoodsOrderVO vo);

	public void update(GoodsOrderVO vo);

	public void delete(Integer orderId);

	public GoodsOrderVO findByPrimaryKey(Integer orderId);

	public List<GoodsOrderVO> getAll();

}
