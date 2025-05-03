package mylab.customer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mylab.customer.dao.mapper.CustomerMapper;
import mylab.customer.vo.CustomerVO;

//@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
//	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public CustomerVO select(int id) {
		CustomerVO customerVO  = customerMapper.selectCustomerById(id);
		return customerVO;
	}

	@Override
	public List<CustomerVO> selectAll() {
		List<CustomerVO> customerList = customerMapper.selectAllCustomer();
		return customerList;
	}
	
	@Override
	public void insert(CustomerVO customerVO) {
		customerMapper.insertCustomer(customerVO);
	}
	
	@Override
	public void update(CustomerVO customerVO) {
		customerMapper.updateCustomer(customerVO);
	}
	
	@Override
	public void delete(int id) {
		customerMapper.deleteCustomer(id);
	}

}
