package asm2.dao;

import asm2.entity.Company;
import asm2.entity.User;

import java.util.List;
import java.util.Map;

public interface CompanyDAO {
    public void updateCompany(Company company);
    public Company findById(int companyId);

    public Company saveCompany(Company company);

    int countCompanies();
}
