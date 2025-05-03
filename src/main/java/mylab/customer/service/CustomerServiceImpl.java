package mylab.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mylab.customer.dao.CustomerDao;
import mylab.customer.vo.CustomerVO;

@Repository("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public CustomerVO getCustomerInfo(int id) {
		return customerDao.select(id);
	}
	
	@Override
	public List<CustomerVO> getAllCustomerList() {
		return customerDao.selectAll();
	}
	
	@Override
	public void insertCustomer(CustomerVO customerVO) {
		customerDao.insert(customerVO);
	}

	@Override
	public void updateCustomer(CustomerVO customerVO) {
		customerDao.update(customerVO);
	}

	@Override
	public void deleteCustomer(int id) {
		customerDao.delete(id);
	}
}
