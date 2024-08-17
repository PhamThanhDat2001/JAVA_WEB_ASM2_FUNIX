package asm2.service;

import asm2.dao.CompanyDAO;
import asm2.dao.RecruitmentDAO;
import asm2.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CompanyServiceImpl implements  CompanyService{
    @Autowired
    CompanyDAO companyDAO;

    @Override
    @Transactional
    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    @Override
    @Transactional
    public Company findById(int companyId) {
        return companyDAO.findById(companyId);
    }

    @Override
    @Transactional
    public Company saveCompany(Company company) {
         companyDAO.saveCompany(company);
        return company;
    }

    @Override
    public int countCompanies() {
        return companyDAO.countCompanies();
    }
}
