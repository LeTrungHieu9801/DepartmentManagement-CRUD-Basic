package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IAccountRepository accountRepository;

//	public List<Department> getAllDepartments() {
//		return departmentRepository.findAll();
//	}
	
	public Page<Department> getAllDepartments(Pageable pageable)
	{
		return departmentRepository.findAll(pageable);
	}

	public Department getDepartmentByID(short id) {
		return departmentRepository.findById(id).get();
	}

	public Department getDepartmentByName(String name) {
		return departmentRepository.findByName(name);
	}
	
	public void createDepartment(DepartmentFormForCreating form) {
		// convert form --> entity

		// get author
		Account author = accountRepository.findById(form.getAuthorId()).get();

		Department department = new Department(form.getName());
		department.setAuthor(author);

		departmentRepository.save(department);
	}

	public void updateDepartment(short id, DepartmentFormForUpdating form)
	{
		Department department = getDepartmentByID(id);
		department.setName(form.getName());
		departmentRepository.save(department);
	}
	
	public void deleteDepartment(short id)
	{
		departmentRepository.deleteById(id);
	}
	
	public void deleteDepartments(List<Short> ids) {
		departmentRepository.deleteByIds(ids);
	}
	public boolean isDepartmentExistsByName(String name)
	{
		return departmentRepository.existsByName(name);
	}
}
