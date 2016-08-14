package _02_GoodsOrder.model;

public class GoodsOrderVO
{
	private int orderId;
	private int memberId;
	private String cardNum;
	private String fullName;
	private String expire;
	private Integer cvc;
	private Integer ntdQty;
	private Integer coinQty;
	private java.sql.Timestamp orderDateTime;
	private Boolean isPay;

	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public int getMemberId()
	{
		return memberId;
	}

	public void setMemberId(int memberId)
	{
		this.memberId = memberId;
	}

	public String getCardNum()
	{
		return cardNum;
	}

	public void setCardNum(String cardNum)
	{
		this.cardNum = cardNum;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getExpire()
	{
		return expire;
	}

	public void setExpire(String expire)
	{
		this.expire = expire;
	}

	public Integer getCvc()
	{
		return cvc;
	}

	public void setCvc(Integer cvc)
	{
		this.cvc = cvc;
	}

	public Integer getNtdQty()
	{
		return ntdQty;
	}

	public void setNtdQty(Integer ntdQty)
	{
		this.ntdQty = ntdQty;
	}

	public Integer getCoinQty()
	{
		return coinQty;
	}

	public void setCoinQty(Integer coinQty)
	{
		this.coinQty = coinQty;
	}

	public java.sql.Timestamp getOrderDateTime()
	{
		return orderDateTime;
	}

	public void setOrderDateTime(java.sql.Timestamp orderDateTime)
	{
		this.orderDateTime = orderDateTime;
	}

	public Boolean getIsPay()
	{
		return isPay;
	}

	public void setIsPay(Boolean isPay)
	{
		this.isPay = isPay;
	}

}
