package mylab.customer.service;

import java.util.List;

import mylab.customer.vo.CustomerVO;

public interface CustomerService {
	public CustomerVO getCustomerInfo(int id);
	
	public List<CustomerVO> getAllCustomerList();
	
	public void insertCustomer(CustomerVO customerVO);
	
	public void updateCustomer(CustomerVO customerVO);
	
	public void deleteCustomer(int id);
}
