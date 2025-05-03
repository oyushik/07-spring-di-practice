package mylab.customer.dao.mapper;

import java.util.List;

import mylab.customer.vo.CustomerVO;

public interface CustomerMapper {
	CustomerVO selectCustomerById(int id);
	List<CustomerVO> selectAllCustomer();
	void insertCustomer(CustomerVO customerVO);
	void updateCustomer(CustomerVO customerVO);
	void deleteCustomer(int id);
}
