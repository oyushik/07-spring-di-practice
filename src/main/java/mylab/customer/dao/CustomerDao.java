package mylab.customer.dao;

import java.util.List;

import mylab.customer.vo.CustomerVO;

public interface CustomerDao {
	public CustomerVO select(int id);
	
	public List<CustomerVO> selectAll();
	
	public void insert(CustomerVO customerVO);
	
	public void update(CustomerVO customerVO);
	
	public void delete(int id);
}
