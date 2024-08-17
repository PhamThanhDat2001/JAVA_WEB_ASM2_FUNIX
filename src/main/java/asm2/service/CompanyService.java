package asm2.service;

import asm2.entity.Company;
import asm2.entity.User;

public interface CompanyService {

    public void updateCompany(Company company);
    public Company findById(int companyId);

    public Company saveCompany(Company company);

    int countCompanies();
}
